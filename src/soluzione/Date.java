package src.soluzione;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author SIMONE PAGLINO
 * <p>Date class represents a date and provides several methods on dates.</p>
 */
public class Date {
    /**
     * The numeric value of the day of the month.
     */
    private int gg;

    /**
     * The numeric value of the month.
     */
    private int mm;

    /**
     * The numeric value of the year.
     */
    private int yyyy;

    /**
     * Constructor to create a new instance of Date class.
     * @param gg The numeric value of the day of the month.
     * @param mm The numeric value of the month.
     * @param yyyy The numeric value of the year.
     */
    public Date(int gg, int mm, int yyyy) {
        this.gg     = gg;
        this.mm     = mm;
        this.yyyy   = yyyy;
    }

    /**
     * @return It returns the numeric value of the day of the month.
     */
    public int getDay()      { return this.gg;   }

    /**
     * @return It returns the numeric value of the month.
     */
    public int getMonth()    { return this.mm;   }

    /**
     * @return It returns the numeric value of the year.
     */
    public int getYear()     { return this.yyyy; }

    /**
     * @param timestamp A timestamp having format (gg/mm/aaaa --- Example: 01012001 - 1° gennaio 2001).
     * @return It returns true if the timestamp passed as argument is a validate date, otherwise false.
     */
    /*
       A timestamp is valid as date if it matches these requirements :
        - length == 8 ;
        - there are only numbers ;
       A date to be valid has to matches the following requirements :
        - 1990 <= year <= current year ;
        - 1 <= month <= 12 ;
        - the date has to be less big or equal than the current date ;
        - if the month is (April or June or September or November) so (1 <= day <= 30) ;
        - if the month is February so (1 <= day <= 29 if bissextile, otherwise 1 <= day <= 28) ;
        - if the month is different than previous (1 <= day <= 31)
    */
    public static boolean isFormatDate(String timestamp) {
        if(timestamp.length() != 8) { return false; }

        if(Validation.isThereCharacter(timestamp)) {
            return false;
        }

        // get a Date object passed as argument a timestamp
        Date date = Date.getDateObj(timestamp);

        // return a boolean of the last validation
        return Date.isDate(date.getDay(), date.getMonth(), date.getYear());
    }


    /**
     * @param d The numeric value of the day of the month.
     * @param m The numeric value of the month.
     * @param y The numeric value of the year.
     * @return It returns true if the arguments passed represent a valid Date object, otherwise false.
     */
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

    /**
     * @return It returns a Date object representing the date of the current day.
     */
    public static Date getCurrentDate() {
        // Obtaining the current date
        LocalDate lc = LocalDate.now();

        return new Date(lc.getDayOfMonth(), lc.getMonthValue(), lc.getYear());
    }

    /**
     * @param y The numeric value of the year.
     * @return return a boolean to check if the year argument is a leap year.
     * @see <a href="https://support.microsoft.com/en-au/help/214019/method-to-determine-whether-a-year-is-a-leap-year">When a year is a leap year?</a>
     */
    /*
        1) If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
        2) If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
        3) If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
        4) The year is a leap year (it has 366 days).
        5) The year is not a leap year (it has 365 days).
    */
    public static boolean isBisestile(int y) {
        return (y % 4 == 0 && y % 100 == 0) ? (y % 400 == 0) : (y % 4 == 0);
    }

    /**
     * The method compares the first Date object with the second one.
     * @param d1 Date object passed as first argument.
     * @param d2 Date object passed as second argument.
     * @return It returns 0 if the dates are equal, 1 if the first date is bigger than the other, and -1 in the opposite scenario.
     */
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
        // in any other possible case the first date will be less than the second one
        return -1;
    }

    /**
     * @param date A timestamp of format (gg/mm/aaaa).
     * @return It returns a Date object representing the timestamp 'date' passed as argument.
     */
    public static Date getDateObj(String date) {
        int g = Integer.parseInt(date.substring(0, 2));
        int m = Integer.parseInt(date.substring(2, 4));
        int y = Integer.parseInt(date.substring(4, 8));

        return new Date(g, m, y);
    }
}