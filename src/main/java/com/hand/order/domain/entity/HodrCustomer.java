package com.hand.order.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_customer")
public class HodrCustomer extends AuditDomain{

    public static final String FIELD_CUSTOMER_ID ="customerId";
    public static final String FIELD_CUSTOMER_NUMBER ="customerNumber";
    public static final String FIELD_CUSTOMER_NAME ="customerName";
    public static final String FIELD_COMPANY_ID ="companyId";
    public static final String FIELD_ENABLED_FLAG ="enabledFlag";
    public static final String FIELD_CREATED_DATE ="createdDate";



    @ApiModelProperty("客户ID（主键）")
    @Id
    @GeneratedValue
    private Long customerId;
    @ApiModelProperty(value = "客户编号", required = true)
    @NotBlank
    private String customerNumber;
    @ApiModelProperty(value = "客户名称", required = true)
    @NotBlank
    private String customerName;
    @ApiModelProperty(value = "公司ID", required = true)
    @NotNull
    private Long comapnyId;
    @ApiModelProperty(value = "启用标识", required = true)
    @NotNull
    private Integer enabledFlag;
    @ApiModelProperty(value = "创建日期", required = true)
    @NotNull
    private Date createdDate;
                


        /**
     * @return 客户ID（主键）
     */
    public Long getCustomerId(){
        return customerId;
    }

    public HodrCustomer setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
    /**
     * @return 客户编号
     */
    public String getCustomerNumber(){
        return customerNumber;
    }

    public HodrCustomer setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        return this;
    }
    /**
     * @return 客户名称
     */
    public String getCustomerName(){
        return customerName;
    }

    public HodrCustomer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }
            /**
     * @return 公司ID
     */
    public Long getComapnyId(){
        return comapnyId;
    }

    public HodrCustomer setComapnyId(Long comapnyId) {
        this.comapnyId = comapnyId;
        return this;
    }
            /**
     * @return 启用标识
     */
    public Integer getEnabledFlag(){
        return enabledFlag;
    }

    public HodrCustomer setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
        return this;
    }
                /**
     * @return 创建日期
     */
    public Date getCreatedDate(){
        return createdDate;
    }

    public HodrCustomer setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }
                }
