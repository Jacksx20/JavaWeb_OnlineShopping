
package model;

public class Order {
    private long code;
    private String owner, date, consignee, phone, address;
    private double total;

    public Order() {
    }

    public Order(long code, String owner, String date, String consignee, String phone, String address, double total) {
        this.code = code;
        this.owner = owner;
        this.address = address;
        this.date = date;
        this.total = total;
        this.phone = phone;
        this.consignee = consignee;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
