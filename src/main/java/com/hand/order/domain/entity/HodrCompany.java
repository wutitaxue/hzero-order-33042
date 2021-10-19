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

/**
 * 
 *
 * @author jiaxing.huang@hand-china.com 2021-07-31 14:26:56
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_company")
public class HodrCompany extends AuditDomain{

    public static final String FIELD_COMPANY_ID ="companyId";
    public static final String FIELD_COMPANY_NUMBER ="companyNumber";
    public static final String FIELD_COMPANY_NAME ="companyName";
    public static final String FIELD_ENABLED_FLAG ="enabledFlag";



                @ApiModelProperty("公司ID（主键）")
    @Id
    @GeneratedValue
            private Long companyId;
                                @ApiModelProperty(value = "公司编号", required = true)
                    @NotBlank
                                        private String companyNumber;
                                @ApiModelProperty(value = "公司名称", required = true)
                    @NotBlank
                                        private String companyName;
                                @ApiModelProperty(value = "启用标识", required = true)
                    @NotNull
                                        private Integer enabledFlag;


        /**
     * @return 公司ID（主键）
     */
    public Long getCompanyId(){
        return companyId;
    }

    public HodrCompany setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
            /**
     * @return 公司编号
     */
    public String getCompanyNumber(){
        return companyNumber;
    }

    public HodrCompany setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }
            /**
     * @return 公司名称
     */
    public String getCompanyName(){
        return companyName;
    }

    public HodrCompany setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
            /**
     * @return 启用标识
     */
    public Integer getEnabledFlag(){
        return enabledFlag;
    }

    public HodrCompany setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
        return this;
    }
                        }
