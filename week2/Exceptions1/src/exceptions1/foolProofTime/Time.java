package exceptions1.foolProofTime;

import java.util.Calendar;

public class Time {

    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int day, int month, int year, int hours, int minutes, int seconds) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        validationDay(day);
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 0 || month > 12) {
            throw new IllegalArgumentException(" 1 <-> 12 for month");
        }

        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("0 <-> 23 for hours");
        }

        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("0 <-> 59 for minutes");
        }

        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("0 <-> 59 for seconds");
        }

        this.seconds = seconds;
    }

    public static Time now() {
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        return new Time(day, month, year, hours, minutes, seconds);
    }

    @Override
    public String toString() {
        String res = String.format("%d:%d:%d %d.%d.%d", this.getHours(), this.getMinutes(), this.getSeconds(),
                this.getDay(), this.getMonth(), this.getYear());

        return res;
    }

    private void validationDay(int day) {
        if ((day < 0 || day > 31)
                || (day == 31 && (this.getMonth() == 2 || this.getMonth() == 4 || this.getMonth() == 6
                        || this.getMonth() == 9 || this.getMonth() == 11))
                || (day == 29 && this.getMonth() == 2 && !(isLeapYear(this.getYear())))) {
            throw new IllegalArgumentException("Invalid day");
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        }

        if (year % 100 == 0 && year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }
}
