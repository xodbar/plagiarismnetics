package entities;

import java.io.Serializable;
import java.util.HashMap;

public class Organization implements Serializable {
    private Integer id;
    private String organizationName;
    private String corporateDomain;
    private int discountPercent;
    private int capacity;
    private String defaultPassword;
    private HashMap<String, String> members;


    public Organization() {
        this.organizationName = "DefaultOrganizationName";
        this.corporateDomain = "@445556666.com";
        this.discountPercent = 0;
        this.capacity = 0;
        this.members = new HashMap<>();
    }

    public Organization(String organizationName, String corporateDomain, int discountPercent, int capacity) {
        this.organizationName = organizationName;
        this.corporateDomain = corporateDomain;
        this.discountPercent = discountPercent;
        this.capacity = capacity;
        this.members = new HashMap<>();
    }

    public Organization(String organizationName, String corporateDomain, int discountPercent, int capacity,
                        HashMap<String, String> members) {
        this.organizationName = organizationName;
        this.corporateDomain = corporateDomain;
        this.discountPercent = discountPercent;
        this.capacity = capacity;
        this.members = members;
    }

    public Organization(Integer id, String organizationName, String corporateDomain, int discountPercent, int capacity,
                        String defaultPassword, HashMap<String, String> members) {
        this.id = id;
        this.organizationName = organizationName;
        this.corporateDomain = corporateDomain;
        this.discountPercent = discountPercent;
        this.capacity = capacity;
        this.defaultPassword = defaultPassword;
        this.members = members;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getCorporateDomain() {
        return corporateDomain;
    }

    public void setCorporateDomain(String corporateDomain) {
        this.corporateDomain = corporateDomain;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<String, String> getMembers() {
        return members;
    }

    public void setMembers(HashMap<String, String> members) {
        this.members = members;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {


        return ("ID: " + id + "\nName: " + organizationName + "\nDiscount: " + discountPercent +
                "%\nCapacity: " + capacity + "\nDefault Password: " + defaultPassword +
                "\nMembers:" + members.toString());
    }
}
