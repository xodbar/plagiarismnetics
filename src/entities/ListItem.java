package entities;

import java.util.ArrayList;

public class ListItem {
    private User user;
    private Purchase purchase;
    private Subscription subscription;
    private Organization organization;


    public ListItem() {
        this.user = new User();
        this.purchase = new Purchase();
        this.subscription = new Subscription();
        this.organization = new Organization();
    }

    public ListItem(User user, Purchase purchase, Subscription subscription) {
        this.user = user;
        this.purchase = purchase;
        this.subscription = subscription;
        this.organization = null;
    }

    public ListItem(User user, Purchase purchase, Subscription subscription, Organization organization) {
        this.user = user;
        this.purchase = purchase;
        this.subscription = subscription;
        this.organization = organization;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
