package entities;

import java.util.Date;

public class Purchase {
    private Integer purchaseID;
    private Date orderDate;
    private boolean successfullyPaid;
    private boolean isActive;
    private double totalCost;


    public Purchase() {
        this.purchaseID = -1;
        this.orderDate = new Date();
        this.successfullyPaid = false;
        this.totalCost = -1.0;
        this.isActive = false;
    }

    public Purchase(Integer purchaseID, Date orderDate, boolean successfullyPaid, boolean isActive, double totalCost) {
        this.purchaseID = purchaseID;
        this.orderDate = orderDate;
        this.successfullyPaid = successfullyPaid;
        this.isActive = isActive;
        this.totalCost = totalCost;
    }


    public Integer getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Integer purchaseID) {
        this.purchaseID = purchaseID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isSuccessfullyPaid() {
        return successfullyPaid;
    }

    public void setSuccessfullyPaid(boolean successfullyPaid) {
        this.successfullyPaid = successfullyPaid;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
