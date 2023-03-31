package org.example;
public class ExpireDate {
    private int day;
    private int month;
    private int year;

    @Override
    public String toString() {
        return day +
                "." + month +
                "." + year;
    }

//    public ExpireDate(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
        if(day<0 && day>31){
            throw new ExpireDateException();
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
