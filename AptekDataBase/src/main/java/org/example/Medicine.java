package org.example;
import java.time.LocalTime;
import java.util.Date;

public class Medicine {
    private int id;
    private String name;
    private int stock;
    private double price;
    private LocalTime expireDate2;
    private ExpireDate expireDate;

    public LocalTime getExpireDate2() {
        return expireDate2;
    }

    public void setExpireDate2(LocalTime expireDate2) {
        this.expireDate2 = expireDate2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ExpireDate getExpireDate() {
        return expireDate;
    }

    public Medicine (){
        ExpireDate expireDate1 = new ExpireDate();
        this.expireDate=expireDate1;
    }
    public void setExpireDateDay(int day) {
        expireDate.setDay(day);
    }
    public void setExpireDateMonth(int month) {
        expireDate.setMonth(month);
    }
    public void setExpireDateYear(int year) {
        expireDate.setYear(year);
    }




    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", expireDate=" + expireDate +
                '}';
    }
}
