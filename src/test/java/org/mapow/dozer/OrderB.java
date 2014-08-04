package org.mapow.dozer;

public class OrderB {
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @Override
    public String toString() {
        return "orderNo: " + orderNo;
    }
}
