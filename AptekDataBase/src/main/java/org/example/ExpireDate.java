package org.example;
public class ExpireDate {
    private int day;
    private int month;
    private int yearWrong;

    @Override
    public String toString() {
        return day +
                "." + month +
                "." + yearWrong;
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

    public int getYearWrong() {
        return yearWrong;
    }

    public void setYearWrong(int yearWrong) {
        this.yearWrong = yearWrong;
    }

}
