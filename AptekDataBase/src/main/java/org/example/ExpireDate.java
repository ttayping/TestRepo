package org.example;
public class ExpireDate {
    private int dayWrong;
    private int monthWrong;
    private int WyearWrong;

    @Override
    public String toString() {
        return dayWrong +
                "." + monthWrong +
                "." + WyearWrong;
    }

//    public ExpireDate(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }

    public int getDayWrong() {
        return dayWrong;
    }

    public void setDayWrong(int dayWrong) {
        this.dayWrong = dayWrong;
        if(dayWrong <0 && dayWrong >31){
            throw new ExpireDateException();
        }
    }

    public int getMonthWrong() {
        return monthWrong;
    }

    public void setMonthWrong(int monthWrong) {
        this.monthWrong = monthWrong;
    }

    public int getWyearWrong() {
        return WyearWrong;
    }

    public void setWyearWrong(int wyearWrong) {
        this.WyearWrong = wyearWrong;
    }

}
