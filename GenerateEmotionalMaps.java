// Import the two data structures needed in this class: ArrayList for the events and HashMap for the emotions related to each POI
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Stefano Cascavilla
 * It describes the class that contains the methods needed to create the emotional maps
 */
public class GenerateEmotionalMaps {
    /**
     * It creates the generic emotional map (no checks active users, only dates)
     * @param eventsList It is the list (described by an ArrayList) which contains all the events read from the file
     * @param pointsOfInterest It is the array which contains all the POI read from the file
     * @param firstLimit It is the the first date limit
     * @param secondLimit It is the second date limit
     */
    public static void generateFullEmotionalMaps (ArrayList<Event> eventsList, ArrayList<PointOfInterest> pointsOfInterest, Date firstLimit, Date secondLimit) {
        double[] distances = new double[pointsOfInterest.size()]; // Defining distances array for getting distances between POIs and coordinates related to an event
        int pointOfInterestIndex = 0; // This variable is used to understand which is the index of the smallest item into the distances array

        ArrayList<Event> filteredEventsListDates = filterDates(eventsList, firstLimit, secondLimit);

        for (Event singleEvent : filteredEventsListDates) {
            // For each event, the function calculates the distances between each POI and the event
            for (int count = 0; count < pointsOfInterest.size(); count++) {
                distances[count] = pointsOfInterest.get(count).getCoordinatePOI().getDistanceBetweenTwoPoints(singleEvent.getCoord());
            }
            pointOfInterestIndex = findSmallestIndex(distances); // Getting the index of the smallest item into the distances array
            pointsOfInterest.get(pointOfInterestIndex).getRelatedEmotions().increase(singleEvent.getEmotion()); // With the found index, the function is going to get the nearest POI at the event's coordinate. Then it increases the emotion related to that POI
        }

        // For each POI the function prints the line with the percentages for each kind of emotion
        for (PointOfInterest singlePOI : pointsOfInterest) {
            printSinglePOILine(singlePOI, singlePOI.getTotalEvents());
        }
    }

    /**
     * It creates the filtered emotional map (it checks that event is related to an active user and it checks that the event has been made between the two argument dates)
     * @param eventsList It is the list (described by an ArrayList) which contains all the events read from the file
     * @param pointsOfInterest It is the array which contains all the POI read from the file
     * @param firstLimit It is the the first date limit
     * @param secondLimit It is the second date limit
     */
    public static void generateFilteredEmotionalMaps (ArrayList<Event> eventsList, ArrayList<PointOfInterest> pointsOfInterest, Date firstLimit, Date secondLimit) {
        double[] distances = new double[pointsOfInterest.size()]; // Defining distances array for getting distances between POIs and coordinates related to an event
        int pointOfInterestIndex = 0; // This variable is used to understand which is the index of the smallest item into the distances array

        ArrayList<Event> filteredEventsListDates = filterDates(eventsList, firstLimit, secondLimit); // Getting the array which contains only the events filtered by date
        ArrayList<Event> filteredEventsList = filterActiveUsers(filteredEventsListDates);

        for (Event singleEvent : filteredEventsList) {
            // For each event, the function calculates the distances between each POI and the event
            for (int count = 0; count < pointsOfInterest.size(); count++) {
                distances[count] = pointsOfInterest.get(count).getCoordinatePOI().getDistanceBetweenTwoPoints(singleEvent.getCoord());
            }
            pointOfInterestIndex = findSmallestIndex(distances); // Getting the index of the smallest item into the distances array
            pointsOfInterest.get(pointOfInterestIndex).getRelatedEmotions().increase(singleEvent.getEmotion()); // With the found index, the function is going to get the nearest POI at the event's coordinate. Then it increases the emotion related to that POI
        }

        // For each POI the function prints the line with the percentages for each kind of emotion
        for (PointOfInterest singlePOI : pointsOfInterest) {
            printSinglePOILine(singlePOI, singlePOI.getTotalEvents());
        }
    }


    /*
     Helper functions: functions which I've created for divide the code better
     These functions are available only from methods within this class
    */

    /**
     * @param eventsList It is the list (described by an ArrayList) which contains all the events read from the file
     * @param firstLimit It is the the first date limit
     * @param secondLimit It is the second date limit
     * @return It filters the events and returns only those which have been made between two dates
     */
    private static ArrayList<Event> filterDates (ArrayList<Event> eventsList, Date firstLimit, Date secondLimit) {
        ArrayList<Event> returnArray = new ArrayList<Event>();

        for (Event item : eventsList) {
            if ((Date.compareTo(item.getDate(), firstLimit) == 1 || Date.compareTo(item.getDate(), firstLimit) == 0) && (Date.compareTo(item.getDate(), secondLimit) == -1 || Date.compareTo(item.getDate(), secondLimit) == 0)) {
                returnArray.add(item);
            }
        }

        return returnArray;
    }

    /**
     * @param eventsList It is the list (described by an ArrayList) which contains all the events read from the file
     * @return It filters the events and returns onlu those which have been made by active users
     */
    private static ArrayList<Event> filterActiveUsers (ArrayList<Event> eventsList) {
        ArrayList<Event> returnArray = new ArrayList<Event>();

        for (Event item : eventsList) {
            if (item.getLogin()) {
                returnArray.add(item);
            }
        }

        return returnArray;
    }

    /**
     * @param distances It is the array that contains distances that will be compared by this method
     * @return It returns the index of the smallest value into the array
     */
    private static int findSmallestIndex (double[] distances) {
        int index = 0;
        double min = distances[index];
        for (int i = 1; i < distances.length; i++){
            if (distances[i] < min ){
                min = distances[i];
                index = i;
            }
        }
        return index ;
    }

    /**
     * It prints a single line of the emotional maps. Example: POI1 - 67% A, 33% F, 0% S, 0% T, 0% N
     * @param singlePOI It is the single point of interest the function is printing the info about
     * @param totalEvents It is the number of total events related to the singlePOI
     */
    private static void printSinglePOILine (PointOfInterest singlePOI, int totalEvents) {
        HashMap<Character, Integer> emotions = singlePOI.getRelatedEmotions().numeroOccorrenze();

        if (totalEvents != 0) {
            System.out.println(singlePOI.getPOIName() + " - " + ((emotions.get(Emotions.EMOTION_A))/(double)totalEvents)*100 + "%" + " " + Emotions.EMOTION_A + ", "
                    + ((emotions.get(Emotions.EMOTION_F))/(double)totalEvents)*100 + "%" + " " + Emotions.EMOTION_F + ", "
                    + ((emotions.get(Emotions.EMOTION_S))/(double)totalEvents)*100 + "%" + " " + Emotions.EMOTION_S + ", "
                    + ((emotions.get(Emotions.EMOTION_T))/(double)totalEvents)*100 + "%" + " " + Emotions.EMOTION_T + ", "
                    + ((emotions.get(Emotions.EMOTION_N))/(double)totalEvents)*100 + "%" + " " + Emotions.EMOTION_N + ", ");
        } else {
            System.out.println(singlePOI.getPOIName() + " - " + 0.0 + "%" + " " + Emotions.EMOTION_A + ", "
                    + 0.0 + "%" + " " + Emotions.EMOTION_F + ", "
                    + 0.0 + "%" + " " + Emotions.EMOTION_S + ", "
                    + 0.0 + "%" + " " + Emotions.EMOTION_T + ", "
                    + 0.0 + "%" + " " + Emotions.EMOTION_N + ", ");
        }
    }
}