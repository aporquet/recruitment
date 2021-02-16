package infra;

public class InfraDateForm {

    private int month;
    private int day;
    private int hour;

    public InfraDateForm(int month, int day, int hour) {
        this.month = month;
        this.day = day;
        this.hour = hour;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

}
