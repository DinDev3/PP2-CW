package model;

public class Date {
    private int year;
    private int month;
    private int day;


    public Date(int year, int month, int day) {         //this order of parameters needs to be maintained to properly validate day
        this.year = year;
        setMonth(month);           //validate month
        setDay(day);             //validate day

        System.out.printf("Date entered is : %s\n", this);           //checking input date
    }

    private void setMonth(int month) {               //validate month
        if (month > 0 && month <= 12) {
            this.month = month;
        } else {
            System.out.printf("Invalid month (%d); set to 1\n", month);
            this.month = 1;             //inserted to maintain object in consistent state
        }
    }

    private void setDay(int day) {               //validate day

        int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 2 && day == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            this.day = day;
        } else if (day > 0 && day <= daysPerMonth[month]) {                             //check if date is within range of month
            this.day = day;
        } else {
            System.out.printf("Invalid day (%d); set to 1\n", day);
            this.day = 1;             //inserted to maintain object in consistent state
        }
    }

    @Override
    public String toString() {
        return "" + day +
                "/" + month +
                "/" + year +
                "";
    }
}
