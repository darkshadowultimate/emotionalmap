// Import the BigDecimal class. Use this type instead of double because of the precision
import java.math.BigDecimal;

public class Coordinate {
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Coordinate (double longitude, double latitude) {
        this.longitude = new BigDecimal(longitude);
        this.latitude = new BigDecimal(latitude);
    }

    public BigDecimal getLongitude () {
        return this.longitude;
    }

    public BigDecimal getLatitude () {
        return this.latitude;
    }

    // This method returns the distance between this Coordinate and the one passed as argument
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
