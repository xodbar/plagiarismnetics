package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
    private ArrayList<User> users;
    private ArrayList<Subscription> subscriptions;
    private ArrayList<Purchase> purchases;
    private ArrayList<ListItem> listItems;
    private ArrayList<Organization> organizations;


    public Database() {
        this.users = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
        this.purchases = new ArrayList<>();
        this.listItems = new ArrayList<>();
        this.organizations = new ArrayList<>();
    }

    public Database(ArrayList<User> users, ArrayList<Subscription> subscriptions, ArrayList<Purchase> purchases,
                    ArrayList<ListItem> listItems, ArrayList<Organization> organizations) {
        this.users = users;
        this.subscriptions = subscriptions;
        this.purchases = purchases;
        this.listItems = listItems;
        this.organizations = organizations;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organization> organizations) {
        this.organizations = organizations;
    }
}
