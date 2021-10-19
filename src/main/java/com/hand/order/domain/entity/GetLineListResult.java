package com.hand.order.domain.entity;

import java.math.BigDecimal;

public class GetLineListResult {
    /**
     *
     */
    private Long soHeaderId;

    /**
     *
     */
    private String itemCode;

    /**
     *
     */
    private String itemDescription;

    /**
     *
     */
    private String orderQualityUom;

    /**
     *
     */
    private BigDecimal orderQuality;

    /**
     *
     */
    private BigDecimal unitSellingPrice;

    /**
     *
     */
    private String orderPrice;

    /**
     *
     */
    private String addition1;

    /**
     *
     */
    private String addition2;

    /**
     *
     */
    private String addition3;

    /**
     *
     */
    private String addition4;

    /**
     *
     */
    private String addition5;

    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public void setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getOrderQualityUom() {
        return orderQualityUom;
    }

    public void setOrderQualityUom(String orderQualityUom) {
        this.orderQualityUom = orderQualityUom;
    }

    public BigDecimal getOrderQuality() {
        return orderQuality;
    }

    public void setOrderQuality(BigDecimal orderQuality) {
        this.orderQuality = orderQuality;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getAddition1() {
        return addition1;
    }

    public void setAddition1(String addition1) {
        this.addition1 = addition1;
    }

    public String getAddition2() {
        return addition2;
    }

    public void setAddition2(String addition2) {
        this.addition2 = addition2;
    }

    public String getAddition3() {
        return addition3;
    }

    public void setAddition3(String addition3) {
        this.addition3 = addition3;
    }

    public String getAddition4() {
        return addition4;
    }

    public void setAddition4(String addition4) {
        this.addition4 = addition4;
    }

    public String getAddition5() {
        return addition5;
    }

    public void setAddition5(String addition5) {
        this.addition5 = addition5;
    }
}

