package model;

import java.io.Serializable;

public class Ware implements Serializable {
    private String code, title, model, depict, photo;
    private double price;
    private int amount, type;
    
    public Ware() {}

    public Ware(String code, String title, String model, String depict, String photo, double price, int amount, int type) {
        this.code = code;
        this.title = title;
        this.model = model;
        this.depict = depict;
        this.photo = photo;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

    public Ware(Ware w) {
        this.code = w.code;
        this.title = w.title;
        this.model = w.model;
        this.depict = w.depict;
        this.photo = w.photo;
        this.price = w.price;
        this.amount = 1;
        this.type = w.type;
    }
    
    public Ware cloneWare() {
        return new Ware(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public double getMoney() {
        return this.amount * this.price;
    }
}
