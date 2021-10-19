package com.hand.order.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * 
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_header")
//@MultiLanguage
public class HodrSoHeader extends AuditDomain{

    public static final String FIELD_SO_HEADER_ID ="soHeaderId";
    public static final String FIELD_ORDER_NUMBER ="orderNumber";
    public static final String FIELD_COMPANY_ID ="companyId";
    public static final String FIELD_ORDER_DATE ="orderDate";
    public static final String FIELD_ORDER_STATUS ="orderStatus";
    public static final String FIELD_CUSTOMER_ID ="customerId";

    //@MultiLanguageField
    @ApiModelProperty("订单头ID（主键）")
    @Id
    @GeneratedValue
    private Long soHeaderId;
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank
    private String orderNumber;
    @ApiModelProperty(value = "公司ID", required = true)
    @NotNull
    private Long companyId;
    @ApiModelProperty(value = "订单日期", required = true)
    @NotNull
    private Date orderDate;
    @ApiModelProperty(value = "订单状态", required = true)
    @NotBlank
    private String orderStatus;
    @ApiModelProperty(value = "客户ID", required = true)
    @NotNull
    private Long customerId;

    @ApiModelProperty(value = "token")
    @Transient
    private String token;
    @ApiModelProperty(hidden = false, value = "版本号")
    private Long objectVersionNumber;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    @Override
    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public void setHodrSoLines(List<HodrSoLine> hodrSoLines) {
        this.hodrSoLines = hodrSoLines;
    }

    @Transient
    @ApiModelProperty(value = "订单头下的订单行", required = true)
    private List<HodrSoLine> hodrSoLines;

    public List<HodrSoLine> getHodrSoLines() {
        return hodrSoLines;
    }

    /**
     * @return 订单头ID（主键）
     */
    public Long getSoHeaderId(){
        return soHeaderId;
    }

    public HodrSoHeader setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
        return this;
    }
    /**
     * @return 订单编号
     */
    public String getOrderNumber(){
        return orderNumber;
    }

    public HodrSoHeader setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }
    /**
     * @return 公司ID
     */
    public Long getCompanyId(){
        return companyId;
    }

    public HodrSoHeader setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
    /**
     * @return 订单日期
     */
    public Date getOrderDate(){
        return orderDate;
    }

    public HodrSoHeader setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }
    /**
     * @return 订单状态
     */
    public String getOrderStatus(){
        return orderStatus;
    }

    public HodrSoHeader setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
    /**
     * @return 客户ID
     */
    public Long getCustomerId(){
        return customerId;
    }

    public HodrSoHeader setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
