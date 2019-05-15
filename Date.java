import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private int gg;
    private int mm;
    private int yyyy;

    /*
        constructor for date
        parameters:
        - int gg    ==> number of the day of the month (1 - 31)
        - int mm    ==> number of the month (1 - 12)
        - int yyyy  ==> number of the year ( XXXX )
    */
    public Date(int gg, int mm, int yyyy) {
        this.gg     = gg;
        this.mm     = mm;
        this.yyyy   = yyyy;
    }

    // return the numeric value representing the number of day of the month
    public int getDay()      { return this.gg;   }

    // return the numeric value representing the number of month
    public int getMonth()    { return this.mm;   }

    // return the numeric value representing the number of year
    public int getYear()     { return this.yyyy; }

    // return a boolean value to check if the timestamp as argument is a validate date
    /*
       A date is valid if ...
    */
    public static boolean isFormatDate(String timestamp) {
        if(timestamp.length() != 8) { return false; }

        if(Validation.isThereCharacter(timestamp)) {
            return false;
        }

        Date date = Date.getDateObj(timestamp);

        return Date.isDate(date.getDay(), date.getMonth(), date.getYear());
    }

    public static boolean isDate(int d, int m, int y) {
        int curr_year = getCurrentDate().getYear();
        if(y < 1900 || y > curr_year) {
            return false;
        }
        if(m < 1 || m > 12) { return false; }
        if(compareTo(new Date(d, m, y), getCurrentDate()) == 1) {
            return false;
        }
        if(m == 4 || m == 6 || m == 9 || m == 11) {
            return (d > 0 && d < 31);
        } else if(m == 2) {
            return (Date.isBisestile(y) ? (d > 0 && d < 30) : (d > 0 && d < 29));
        } else {
            return (d > 0 && d < 32);
        }
    }

    public static Date getCurrentDate() {
        LocalDate lc = LocalDate.now();

        return new Date(lc.getDayOfMonth(), lc.getMonthValue(), lc.getYear());
    }

    public static boolean isBisestile(int y) {
        return (y % 4 == 0 && y % 100 == 0) ? (y % 400 == 0) : (y % 4 == 0);
    }

    public static int compareTo(Date d1, Date d2) {
        if(d1.getYear() > d2.getYear()) {
            return 1;
        } else if(d1.getYear() == d2.getYear()) {
            if(d1.getMonth() > d2.getMonth()) {
                return 1;
            } else if(d1.getMonth() == d2.getMonth()) {
                if(d1.getDay() > d2.getDay()) {
                    return 1;
                } else if(d1.getDay() == d2.getDay()) {
                    return 0;
                }
            }
        }
        return -1;
    }

    // return the Date object representing the timestamp String parameter
    public static Date getDateObj(String date) {
        int g = Integer.parseInt(date.substring(0, 2));
        int m = Integer.parseInt(date.substring(2, 4));
        int y = Integer.parseInt(date.substring(4, 8));

        return new Date(g, m, y);
    }

    public String toString() {
        return this.gg + "/" + this.mm + "/" + this.yyyy;
    }
}