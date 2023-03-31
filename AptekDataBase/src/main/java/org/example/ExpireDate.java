package org.example;
public class ExpireDate {
    private int dayW;
    private int monthWrong;
    private int yearWrong;

    @Override
    public String toString() {
        return dayW +
                "." + monthWrong +
                "." + yearWrong;
    }

//    public ExpireDate(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }

    public int getDayW() {
        return dayW;
    }

    public void setDayW(int dayW) {
        this.dayW = dayW;
        if(dayW <0 && dayW >31){
            throw new ExpireDateException();
        }
    }

    public int getMonthWrong() {
        return monthWrong;
    }

    public void setMonthWrong(int monthWrong) {
        this.monthWrong = monthWrong;
    }

    public int getYearWrong() {
        return yearWrong;
    }

    public void setYearWrong(int yearWrong) {
        this.yearWrong = yearWrong;
    }

}
