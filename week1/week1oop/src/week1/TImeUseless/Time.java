package week1.TImeUseless;

import java.util.Calendar;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;
    private int day;
    private int month;
    private int year;

    public Time(int h, int m, int s, int d, int mm, int y) {
        setHours(h);
        setMinutes(m);
        setSeconds(s);
        setDay(d);
        setMonth(mm);
        setYear(y);

    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public String toString() {
        // return String.format(hours + ":" + minutes + ":" + seconds + " " +
        // day + "." + month + "." + year);
        return String.format("%d:%d:%d %d.%d.%d", hours, minutes, seconds, day, month, year);
    }

    public static Time now() {
        Calendar cal = Calendar.getInstance();
        // cal.setTime(new Date());
        return new Time(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND),
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }

    public static void main(String[] args) {
        System.out.println(Time.now());
    }

}
