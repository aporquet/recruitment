package infra;

class InfraDateForm {

    private int month;
    private int day;
    private int hour;

    InfraDateForm(int month, int day, int hour) {
        this.month = month;
        this.day = day;
        this.hour = hour;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
