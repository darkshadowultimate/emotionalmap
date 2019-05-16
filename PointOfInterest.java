public class PointOfInterest {
    private Coordinate pointCoordinate;
    private Emotions relatedEmotions;
    private String poiName;

    public PointOfInterest (double longitude, double latitude, String name) {
        this.pointCoordinate = new Coordinate(longitude, latitude);
        this.relatedEmotions = new Emotions();
        this.poiName = name;
    }

    public Coordinate getCoordinatePOI () {
        return this.pointCoordinate;
    }

    public String getPOIName () {
        return this.poiName;
    }

    public Emotions getRelatedEmotions () {
        return this.relatedEmotions;
    }
}