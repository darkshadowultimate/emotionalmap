// Import the BigDecimal class. Use this type instead of double because of the precision
import java.math.BigDecimal;

/**
 * @author Stefano Cascavilla
 * <p>It describes a coordinate (long, lat)</p>
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
     * It creates an instance of Coordinate with the longitude and latitude specified
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
     * @see "https://www.mat.unical.it/calimeri/wiki/InformaticaCDLmatematica?action=AttachFile&do=get&target=20100113-Esercitazione-LAB-Punti-Cartesiani-e-Cerchi-SOLUZIONI.txt"
     * @param point It is the Coordinate obj which we want to calculate the distance from
     * @return It returns the distance between this Coordinate object and the one passed as argument
     */
    public double getDistanceBetweenTwoPoints (Coordinate point) {
        double p1 = this.latitude.doubleValue() - point.getLatitude().doubleValue();
        double p2 = this.longitude.doubleValue() - point.getLongitude().doubleValue();

        return Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2));
    }
}