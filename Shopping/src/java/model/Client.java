package model;

public class Client {
    private String code, title, phone, wechat, address;
    private boolean manager;
    
    public Client() {}

    public Client(String code, String title, String phone, String wechat, String address, boolean manager) {
        this.code = code;
        this.title = title;
        this.phone = phone;
        this.wechat = wechat;
        this.address = address;
        this.manager= manager;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
    
}
