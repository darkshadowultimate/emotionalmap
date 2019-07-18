package bin.soluzione;

import java.util.HashMap;

/**
* @author Stefano Cascavilla
* <p>It describes a point of interest</p>
*/
public class PointOfInterest {
    /**
     * It describes the coordinate of this point ot interest
     */
    private Coordinate pointCoordinate;

    /**
     * It describes the emotions related to this point of interest
     */
    private Emotions relatedEmotions;

    /**
     * It desribes the name of this point of interest
     */
    private String poiName;

    /**
     * This is the only one constructor of the PointOfInterest class
     * @param longitude This is the longitude of the point of interest it's creating
     * @param latitude This is the latitude of the point of interest it's creating
     * @param name This is the name of the point of interest it's creating
     */
    public PointOfInterest (double longitude, double latitude, String name) {
        this.pointCoordinate = new Coordinate(longitude, latitude);
        this.relatedEmotions = new Emotions();
        this.poiName = name;
    }

    /**
     * @return It returns the coordinate of this point of interest
     */
    public Coordinate getCoordinatePOI () {
        return this.pointCoordinate;
    }

    /**
     * @return It returns the name of this point of interest
     */
    public String getPOIName () {
        return this.poiName;
    }

    /**
     * @return It returns the emotions related to this point of interest
     */
    public Emotions getRelatedEmotions () {
        return this.relatedEmotions;
    }

    /**
     *  Because there is a 1 to 1 association between events and emotions (each event has associated a single emotion), if I count the number of emotions related to a POI, I'm counting the number of events associated to this POI too.
     * @return It returns the number of events whose POI associated was this
     */
    public int getTotalEvents () {
        HashMap<Character, Integer> occorrenze = this.relatedEmotions.numeroOccorrenze();
        int numeroOccorrenze = 0;

        for (HashMap.Entry<Character, Integer> item : occorrenze.entrySet()) {
            numeroOccorrenze += item.getValue();
        }
        return numeroOccorrenze;
    }
}