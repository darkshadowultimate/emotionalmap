// Import the two data structures needed in this class: ArrayList for the events and HashMap for the emotions related to each POI
import java.util.ArrayList;
import java.util.HashMap;

public class EmotionalMaps {
    // This method creates the generic emotional map (no checks for date or active users)
    public static void generateFullEmotionalMaps (ArrayList<Event> eventsList, PointOfInterest[] pointsOfInterest) {
        double[] distances = new double[pointsOfInterest.length]; // Defining distances array for getting distances between POIs and coordinates related to an event
        int pointOfInterestIndex = 0; // This variable is used to understand which is the index of the smallest item into the distances array

        for (Event singleEvent : eventsList) {
            // For each event, the function calculates the distances between each POI and the event
            for (int count = 0; count < pointsOfInterest.length; count++) {
                distances[count] = pointsOfInterest[count].getCoordinatePOI().getDistanceBetweenTwoPoints(singleEvent.getEventCoordinate());
            }
            pointOfInterestIndex = findSmallestIndex(distances); // Getting the index of the smallest item into the distances array
            pointsOfInterest[pointOfInterestIndex].getRelatedEmotions().increase(singleEvent.getEmotion()); // With the found index, the function is going to get the nearest POI at the event's coordinate. Then it increases the emotion related to that POI
        }

        // For each POI the function prints the line with the percentages for each kind of emotion
        for (PointOfInterest singlePOI : pointsOfInterest) {
            printSinglePOILine(singlePOI, eventsList.size());
        }
    }


    /*
     Helper functions: functions which I've created for divide the code better
     These functions are available only from methods within this class
    */

    // This functions returns the index of the smallest value into the array
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

    // This functions prints a single line of the emotional maps. Example: POI1 - 67% A, 33% F, 0% S, 0% T, 0% N
    private static void printSinglePOILine (PointOfInterest singlePOI, int totalEvents) {
        HashMap<Character, Integer> emotions = singlePOI.getRelatedEmotions().numeroOccorrenze();

        System.out.println(singlePOI.getPOIName() + " - " + ((emotions.get(Emotions.EMOTION_A))/(double)totalEvents) + "%" + " " + Emotions.EMOTION_A + ", "
                                                          + ((emotions.get(Emotions.EMOTION_F))/(double)totalEvents) + "%" + " " + Emotions.EMOTION_F + ", "
                                                          + ((emotions.get(Emotions.EMOTION_S))/(double)totalEvents) + "%" + " " + Emotions.EMOTION_S + ", "
                                                          + ((emotions.get(Emotions.EMOTION_T))/(double)totalEvents) + "%" + " " + Emotions.EMOTION_T + ", "
                                                          + ((emotions.get(Emotions.EMOTION_N))/(double)totalEvents) + "%" + " " + Emotions.EMOTION_N + ", ");
    }
}