package com.hand.order.domain.entity;

import java.math.BigDecimal;

public class GetItemListResult {
    /**
     *
     */
    private Long soLineId;

    /**
     *
     */
    private Long itemId;

    /**
     *
     */
    private String itemCode;

    /**
     *
     */
    private String itemUom;

    /**
     *
     */
    private String itemDescription;

    /**
     *
     */
    private String description;

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
    private Long objectVersionNumber;

    public Long getSoLineId() {
        return soLineId;
    }

    public void setSoLineId(Long soLineId) {
        this.soLineId = soLineId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }
}

