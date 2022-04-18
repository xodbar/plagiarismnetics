package entities;

import java.io.Serializable;

public class Subscription implements Serializable {
    private Integer subscriptionID;
    private String description;
    private Double price;


    public Subscription() {
        this.subscriptionID = -1;
        this.description = "DefaultSubscription";
        this.price = -1.0;
    }

    public Subscription(Integer subscriptionID, String description, Double price) {
        this.subscriptionID = subscriptionID;
        this.description = description;
        this.price = price;
    }


    public Integer getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(Integer subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ("ID: " + subscriptionID + "\nDescription: " + description + "\nPrice: " + price);
    }
}
