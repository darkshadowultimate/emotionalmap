// Import the BigDecimal class. Use this type instead of double because of the precision
import java.math.BigDecimal;

/**
 * @author Stefano Cascavilla
 * It describes a coordinate (long, lat)
 */
public class Coordinate {
    /**
     * It describes the longitude of the coordinate
     */
    private BigDecimal longitude;

    /**
     * It describes the latitude of the coordinate
     */
    private BigDecimal latitude;

    /**
     * It creates an instance of Coordinate with the lognitude and latitude specified
     * @param longitude It is the longitude of the Coordinate that will be created 
     * @param latitude It is the latitude of the Coordinate that will be created
     */
    public Coordinate (double longitude, double latitude) {
        this.longitude = new BigDecimal(longitude);
        this.latitude = new BigDecimal(latitude);
    }

    /**
     * @return It returns the longitude of this Coordinate
     */
    public BigDecimal getLongitude () {
        return this.longitude;
    }

    /**
     * @return It returns the latitude of this Coordinate
     */
    public BigDecimal getLatitude () {
        return this.latitude;
    }

    /**
     * @see https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     * @param point It is the Coordinate obj which we want to calculate the distance from
     * @return It returns the distance between this Coordinate object and the one passed as argument
     */
    public double getDistanceBetweenTwoPoints (Coordinate point) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(this.latitude.doubleValue() - point.latitude.doubleValue());
        double lonDistance = Math.toRadians(this.longitude.doubleValue() - point.longitude.doubleValue());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(this.latitude.doubleValue())) * Math.cos(Math.toRadians(point.latitude.doubleValue()))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2) + Math.pow(0.0, 2);

        return Math.sqrt(distance);
    }
}