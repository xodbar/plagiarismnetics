package entities;

import java.io.Serializable;

public class ListItem implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer purchaseId;
    private Integer subscriptionId;
    private Integer organizationId;

    public ListItem() {}

    public ListItem(Integer id, Integer userId, Integer purchaseId, Integer subscriptionId, Integer organizationId) {
        this.id = id;
        this.userId = userId;
        this.purchaseId = purchaseId;
        this.subscriptionId = subscriptionId;
        this.organizationId = organizationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return ("~ID: " + id + "\n~User: " + userId + "\n~Purchase: " + purchaseId +
                "\n~Subscription: " + subscriptionId + "\n~Organization: " + organizationId);
    }
}
