package cn.itheima.pojo;

import java.util.Date;

public class TbOrder {
    private Integer rid;

    private String userId;

    private String orderId;

    private String sta;

    private Integer addressId;

    private Double payment;

    private Date placed;

    private Date receipt;

    private Date deliver;

    private Date handover;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta == null ? null : sta.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Date getPlaced() {
        return placed;
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public Date getReceipt() {
        return receipt;
    }

    public void setReceipt(Date receipt) {
        this.receipt = receipt;
    }

    public Date getDeliver() {
        return deliver;
    }

    public void setDeliver(Date deliver) {
        this.deliver = deliver;
    }

    public Date getHandover() {
        return handover;
    }

    public void setHandover(Date handover) {
        this.handover = handover;
    }
}