package com.hand.order.app.service.impl;

import com.hand.order.app.service.HodrSoHeaderService;
import com.hand.order.domain.entity.HodrSoHeader;
import com.hand.order.domain.entity.HodrSoLine;
import com.hand.order.domain.repository.HodrSoHeaderRepository;
import com.hand.order.domain.repository.HodrSoLineRepository;
import com.hand.order.infra.constant.Constants;
import io.choerodon.core.domain.Page;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.CustomUserDetails;
import io.choerodon.core.oauth.DetailsHelper;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.apache.commons.lang.StringUtils;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.boot.platform.code.constant.CodeConstants;
import org.hzero.boot.platform.plugin.hr.EmployeeHelper;
import org.hzero.boot.workflow.WorkflowClient;
import org.hzero.boot.workflow.dto.PersonalTodoDTO;
import org.hzero.core.base.BaseAppService;
import org.hzero.boot.workflow.dto.PersonalTodoDTO.PersonalTodoQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hzero.boot.report.ReportClient;
import org.hzero.boot.workflow.dto.PersonalTodoDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 应用服务默认实现
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@Service
public class HodrSoHeaderServiceImpl extends BaseAppService implements HodrSoHeaderService {

    private final HodrSoHeaderRepository hodrSoHeaderRepository;
    private final HodrSoLineRepository hodrSoLineRepository;
    private final CodeRuleBuilder codeRuleBuilder;
    @Autowired
    private MessageClient messageClient;
    @Autowired
    private ReportClient reportClient;
    @Autowired
    private WorkflowClient workflowClient;

    @Autowired
    public HodrSoHeaderServiceImpl(HodrSoHeaderRepository hodrSoHeaderRepository, HodrSoLineRepository hodrSoLineRepository, CodeRuleBuilder codeRuleBuilder) {
        this.hodrSoHeaderRepository = hodrSoHeaderRepository;
        this.hodrSoLineRepository = hodrSoLineRepository;
        this.codeRuleBuilder = codeRuleBuilder;
    }


    @Override
    public HodrSoHeader detail(Long soHeaderId) {
        return hodrSoHeaderRepository.selectByPrimaryKey(soHeaderId);
    }

    @Override
    public HodrSoHeader create(HodrSoHeader hodrSoHeader) {
        //validObject(hodrSoHeader);
        hodrSoHeaderRepository.insertSelective(hodrSoHeader);
        return hodrSoHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HodrSoHeader update(HodrSoHeader hodrSoHeader) {
        //SecurityTokenHelper.validToken(hodrSoHeader);
        hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeader);
        return hodrSoHeader;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(HodrSoHeader hodrSoHeader) {
        //SecurityTokenHelper.validToken(hodrSoHeader);
        hodrSoHeaderRepository.deleteByPrimaryKey(hodrSoHeader);
    }

    @Override
    public HodrSoHeader select(Long soHeaderId) {
        return hodrSoHeaderRepository.selectHeaderById(soHeaderId);
    }

    @Override
    public Page<HodrSoHeader> getHeaderList(Long soHeaderId, Long companyId, Long customerId, String orderNumber, String orderStatus, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, () -> hodrSoHeaderRepository.headerList(soHeaderId, companyId, customerId, orderNumber, orderStatus));
    }

    @Override
    public Page<HodrSoHeader> list(HodrSoHeader hodrSoHeader, PageRequest pageRequest) {
        return hodrSoHeaderRepository.pageAndSort(pageRequest, hodrSoHeader);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderStatusWorkflow(long organizationId, Long soHeaderId) {
        HodrSoHeader soHeader = hodrSoHeaderRepository.selectByPrimaryKey(soHeaderId);
        // 对订单进行校验

        /**
         * @param organizationId 租户ID
         * @param flowKey 流程定义编码
         * @param businessKey 业务主键
         * @param startEmpNum 流程启动人（员工编码）
         * @param variableMap 启动参数（流程描述：flowDesc；其它流程中使用到的变量参数）
         */
        String startEmpNum = EmployeeHelper.getEmployeeNum(DetailsHelper.getUserDetails().getUserId(), organizationId);
        if (StringUtils.isEmpty(startEmpNum)) {
            throw new CommonException("error.NoSuchUser");
        }
        String flowKey = Constants.ORDER_WORKFLOW_FLOWKEY;
        String businessKey = soHeader.getOrderNumber();
        HashMap<String, Object> variableMap = new HashMap<>();
        variableMap.put("soHeaderId", soHeaderId);
        workflowClient.startInstanceByFlowKey(organizationId, flowKey, businessKey, startEmpNum, variableMap);
        // 改变订单头状态
        this.workflowChangeStatus(organizationId, soHeaderId, Constants.ORDER_SUBMITED);
    }


    @Override
    public void updateStatus(HodrSoHeader hodrSoHeader) {
        //权限检查
        if (hodrSoHeader == null) {
            throw new CommonException("error.NoSuchOrder");
        }
        HodrSoHeader soHeaderCheck = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeader.getSoHeaderId());
        CustomUserDetails self = DetailsHelper.getUserDetails();

        // 用户ID
        Long userId = self.getUserId();
         if (soHeaderCheck == null) {
            throw new CommonException("error,没有这个订单");
        }
        if (hodrSoHeader.getOrderStatus().equals("SUBMITED")) {
            if (soHeaderCheck.getOrderStatus().equals("NEW") || hodrSoHeader.getOrderStatus().equals("REJECTED")) {
                throw new CommonException("error.ErrorStatus");
            }
            if (soHeaderCheck.getCreatedBy() != -1 && !userId.equals(soHeaderCheck.getCreatedBy())) {
                throw new CommonException("error.NotOwner");
            }

        } else if (hodrSoHeader.getOrderStatus().equals("APPROVED")) {
            if (soHeaderCheck.getOrderStatus().equals("SUBMITED")) {
                throw new CommonException("error.ErrorStatus");
            }
            if (soHeaderCheck.getCreatedBy() != -1 && !userId.equals(soHeaderCheck.getCreatedBy())) {
                throw new CommonException("error.NotOwner");
            }


        } else if (hodrSoHeader.getOrderStatus().equals("REJECTED")) {
            if (soHeaderCheck.getOrderStatus().equals("SUBMITED")) {
                throw new CommonException("error.ErrorStatus");
            }
            if (soHeaderCheck.getCreatedBy() != -1 &&  !userId.equals(soHeaderCheck.getCreatedBy())) {
                throw new CommonException("error.NotOwner");
            }

        }

        //soHeaderCheck.setOrderStatus(status);
        //SecurityTokenHelper.validToken(soHeader);
        //hodrSoHeaderRepository.updateByPrimaryKey(hodrSoHeader);
    }

    @Override
    public void insertOrder(HodrSoHeader hodrSoHeader) {
        hodrSoHeaderRepository.insertHeaderOrder(hodrSoHeader.getSoHeaderId(), hodrSoHeader.getOrderNumber(), hodrSoHeader.getCompanyId(), hodrSoHeader.getOrderDate(), hodrSoHeader.getOrderStatus(), hodrSoHeader.getCustomerId());
    }

    @Override
    public void removeHeaderAndLine(HodrSoHeader hodrSoHeaderCheck) {
        //权限检查
        if (hodrSoHeaderCheck == null) {
            throw new CommonException("error.NoSuchOrder");
        }
        CustomUserDetails self = DetailsHelper.getUserDetails();

        // 用户ID
        Long userId = self.getUserId();
        if (hodrSoHeaderCheck.getCreatedBy() != -1 && !userId.equals(hodrSoHeaderCheck.getCreatedBy())) {
            throw new CommonException("error.NotOwner");
        }

        if (hodrSoHeaderCheck.getOrderStatus().equals("SUBMITED")) {
            throw new CommonException("error.ErrorStatus");
        } else {
            System.out.println("Success");
        }

        hodrSoHeaderRepository.deleteByPrimaryKey(hodrSoHeaderCheck);
        hodrSoLineRepository.deleteBySoHeaderId(hodrSoHeaderCheck.getSoHeaderId());
    }


    @Override
    public void saveOrder(HodrSoHeader hodrSoHeader, Long organizationId) {
        /**
         * 头订单新增与更新
         */
        //头ID为空,新增头订单
        if (hodrSoHeader.getSoHeaderId() == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date timeDate = null;
            try {
                timeDate = simpleDateFormat.parse(hodrSoHeader.getOrderDate().toString());
                System.out.println(timeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            System.out.println(calendar.get(Calendar.YEAR));
            if (calendar.get(Calendar.YEAR) < 2019) {
                throw new CommonException("error.ErrorTime");
            }

            hodrSoHeader.setOrderStatus("NEW");

            //生成订单号，编码规则"SO+YYYYMMDD+6位流水号"
            //生成六位流水号

            int serialNumber = 0;
            for (int j = 0; j < 100; j++) {
                serialNumber = (int) ((Math.random() * 9 + 1) * 100000);
            }

            Map<String, String> map = new HashMap<>(16);
            map.put("category", Integer.toString(serialNumber));
            String code = codeRuleBuilder.generateCode(CodeConstants.Level.TENANT, 0L, "HZERO.33042.ORDER.NUMBER", CodeConstants.CodeRuleLevelCode.GLOBAL, CodeConstants.CodeRuleLevelCode.GLOBAL, map);
            hodrSoHeader.setOrderNumber(code);

            hodrSoHeader.setCreatedBy(DetailsHelper.getUserDetails().getUserId());

            hodrSoHeaderRepository.insertHeaderOrder(hodrSoHeader.getSoHeaderId(), hodrSoHeader.getOrderNumber(), hodrSoHeader.getCompanyId(), hodrSoHeader.getOrderDate(), hodrSoHeader.getOrderStatus(), hodrSoHeader.getCustomerId());

        } else {
            //头ID不为空，根据ID修改数据
            HodrSoHeader hodrSoHeaderChange = hodrSoHeaderRepository.selectByPrimaryKey(hodrSoHeader.getSoHeaderId());
            //校验
            updateStatus(hodrSoHeaderChange);

            //版本号
            if (hodrSoHeaderChange.getObjectVersionNumber().equals(hodrSoHeader.getObjectVersionNumber())) {
            } else {
                throw new CommonException("error.ErrorVersion");
            }

            //获取当前时间
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date nowDate = null;
            try {
                nowDate = formatter.parse(formatter.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hodrSoHeader.setLastUpdateDate(nowDate);

            hodrSoHeaderRepository.updateByPrimaryKeySelective(hodrSoHeader);

        }

        //获取订单行列表
        List<HodrSoLine> hodrSoLines = hodrSoHeader.getHodrSoLines();
        if (hodrSoLines != null) {
            Iterator<HodrSoLine> iterator = hodrSoLines.iterator();
            while (iterator.hasNext()) {
                HodrSoLine hodrSoLine = iterator.next();
                updateLine(hodrSoLine, hodrSoHeaderRepository.getHeaderCount(), organizationId);
            }
        }
    }

    /**
     * 行订单新增与更新
     */
    public void updateLine(HodrSoLine hodrSoLine, Long soHeaderId, Long organizationId) {

        //行ID为空,新增行订单
        if (hodrSoLine.getSoLineId() == null) {

            hodrSoLine.setSoHeaderId(soHeaderId);
            //hodrSoLine.setSoLineId(hodrSoLineRepository.getLineCount(soHeaderId) + 1);
            hodrSoLineRepository.insertSelective(hodrSoLine);

        } else {

            //行ID不为空，根据ID修改数据
            //修改版本号
            HodrSoLine hodrSoLineChange = hodrSoLineRepository.selectByPrimaryKey(hodrSoLine.getSoLineId());
            if (hodrSoLineChange == null) {
                throw new CommonException("error.NoSuchLine");
            }
            if (hodrSoLineChange.getObjectVersionNumber().equals(hodrSoLineChange.getObjectVersionNumber())) {
            } else {
                throw new CommonException("error.ErrorVersion");
            }

            //获取当前时间
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date nowDate = null;
            try {
                nowDate = formatter.parse(formatter.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hodrSoLine.setLastUpdateDate(nowDate);

            hodrSoLineRepository.updateByPrimaryKeySelective(hodrSoLine);

        }
    }

    @Override
    public void workflowChangeStatus(long organizationId, Long soHeaderId, String status) {
        HodrSoHeader hodrSoHeader = hodrSoHeaderRepository.selectByPrimaryKey(soHeaderId);
        hodrSoHeader.setOrderStatus(status);
        Integer num = hodrSoHeaderRepository.updateByPrimaryKey(hodrSoHeader);
        if(num > 0){
            long tenantId = 0L;
            //账户代码
            String serverCode = Constants.SERVER_CODE;
            //消息模板代码
            String messageTemplateCode = Constants.MESSAGE_TEMPLATECODE;
            List<Receiver> receiverList = new ArrayList<>();
            Receiver receiver = new Receiver();
            //接受方
            receiver.setEmail(Constants.RECEIVER);
            receiverList.add(receiver);
            Map<String, String> args = new HashMap<>();
            args.put("parmA", "订单编码为" + hodrSoHeader.getOrderNumber());
            args.put("parmB", status);
            messageClient.sendEmail(tenantId, serverCode, messageTemplateCode, receiverList, args, null);
        }
    }


    @Override
    public void workUpdateStatus(HodrSoHeader hodrSoHeader, String status) {
        //权限检查
        if (hodrSoHeader == null) {
            throw new CommonException("error.NoSuchOrder");
        }

        if (status.equals("SUBMITED")) {
            if (hodrSoHeader.getOrderStatus().equals("NEW") || hodrSoHeader.getOrderStatus().equals("REJECTED")) {
                hodrSoHeader.setOrderStatus("APPROVED");

            } else {
                throw new CommonException("error.ErrorStatus");
            }

        }

        if (status.equals("APPROVED")) {
            if (hodrSoHeader.getOrderStatus().equals("SUBMITED")) {
                hodrSoHeader.setOrderStatus("SUBMITED");
            } else {
                throw new CommonException("error.ErrorStatus");
            }
        }

        if (status.equals("REJECTED")) {
            if (hodrSoHeader.getOrderStatus().equals("SUBMITED")) {
                hodrSoHeader.setOrderStatus("CLOSED");
            } else {
                throw new CommonException("error.ErrorStatus");
            }
        }
        hodrSoHeaderRepository.updateByPrimaryKey(hodrSoHeader);


    }

    @Override
    public void downloadReport(Long organizationId, String exportType) {
        String reportUuid = "ORDERTABLE";
        reportClient.downloadReport(organizationId, reportUuid, "HTML");
    }

    @Override
    public void approveOrder(Long organizationId) {
        CustomUserDetails self = DetailsHelper.getUserDetails();
        PersonalTodoQueryDTO queryDTO = new PersonalTodoQueryDTO();
        queryDTO.setSelf(String.valueOf(self));
        Long taskId = workflowClient.todoPage(organizationId, 1, 5, queryDTO).getContent().get(0).getTaskId();
        System.out.println(taskId);
        List<Long> taskIds = new ArrayList<>();
        taskIds.add(taskId);
        workflowClient.approve(organizationId, taskIds, Constants.APPROVED_COMMENT, String.valueOf(self));
    }

}
