package bin.soluzione;

import java.util.ArrayList;
import java.io.*;

/**
 * @author SIMONE PAGLINO
 * <p>Read class provides several method to read from three specific type of files: "comandi.txt", "eventi.txt", "poi.txt" (these are the theorical names except "poi.txt").</p>
 */
public class Read {

    /**
     * @param name_file The name of the file which contains all the commands to execute.
     * @return It returns an ArrayList of Command objects representing all the lines read from the file (passed as argument), containing all the commands to execute (all the invalid lines will be ignored).
     */
    public static ArrayList<Command> readCommands (String name_file) {
        ArrayList<Command> commands = new ArrayList<Command>();
        String line;

        try {
            // creating BufferReader to read all the lines inside of the specified file passed as parameter
            BufferedReader buff = new BufferedReader(new FileReader(name_file));

            // read each line of the file until there are no left
            while((line = buff.readLine()) != null) {
                Command c = Command.convertStringToCommand(line);
                if(c != null) {
                    commands.add(c);
                }
            }
        // handle exception in case some errors occur
        } catch(FileNotFoundException exc) {
            System.out.println("File not found...");
        } catch(IOException exc) {
            System.out.println("Problem with IO operations...");
        }
        return commands;
    }

    /**
     * @param name_file The name of the file which contains all the events.
     * @return It returns an ArrayList of Event objects representing all the lines read from the file containing all the events (all the invalid lines will be ignored).
     */
    public static ArrayList<Event> readEvents (String name_file) {
        ArrayList<Event> list = new ArrayList<Event>();
        String line;

        try {
            // creating BufferReader to read all the lines inside of the specified file passed as parameter
            BufferedReader buff = new BufferedReader(new FileReader(name_file));

            // read each line of the file until there are no left
            while((line = buff.readLine()) != null) {
                if(Validation.validateElementsLine(line)) {
                    list.add(convertLineToEventObj(line));
                }
            }
        // handle exception in case some errors occur
        } catch(FileNotFoundException exc) {
            System.out.println("File not found...");
        } catch(IOException exc) {
            System.out.println("Problem with IO operations...");
        }
        return list;
    }

    /**
     * @param name_file The name of the file which contains all the predefined points of interest.
     * @return It returns an ArrayList of PointOfInterest objects representing all the lines read from the file containing all the predefined points of interest.
     * @throws Exception An Exception can thrown if the filed read contains at least a point of interest not valid.
     */
    public static ArrayList<PointOfInterest> readPOI (String name_file) throws Exception {
        ArrayList<PointOfInterest> list = new ArrayList<PointOfInterest>();
        String line;

        try {
            // creating BufferReader to read all the lines inside of the specified file passed as parameter
            BufferedReader buff = new BufferedReader(new FileReader(name_file));

            // read each line of the file until there are no left
            while((line = buff.readLine()) != null) {
                list.add(convertStringToPOI(line));
            }
            // handle exception in case some errors occur
        } catch(FileNotFoundException exc) {
            System.out.println("File not found...");
        } catch(IOException exc) {
            System.out.println("Problem with IO operations...");
        }
        return list;
    }

    /**
     * @param event A String line read from "eventi.txt" file (or similar), representing an event.
     * @return It returns an Event object representing the current line passed by argument.
     */
    private static Event convertLineToEventObj(String event) {
        String[] parts = event.split(" ");
        String[] coord = parts[4].split(",");

        /*
            String[] parts ==>  elements contained inside a String line representing an event
                                Example:
                                    string line event read = "IN LOGIN 10042019 bc78x 45.465,9.191 F"
                                    String e[] = ["IN", "LOGIN", "10042019", "bc78x", "45.465,9.191", "F"]

            String[] coord ==>  latitude and longitude (Example: String[] c = ["45.465", "9.191"])
        */

        // if a user is register in the application ==> store value true
        boolean s   = parts[0].equals("IN");
        // if a user is active in the application ==> store value true
        boolean l   = parts[1].equals("LOGIN");
        // get a Date object passing the timestamp value of the date
        Date d      = Date.getDateObj(parts[2]);

        /*
            Returning the Event object representing the line read

            PARAMETERS :

            - s         ==> if the user is register into the application
            - l         ==> if the user is currently active on the platform
            - d         ==> the date of the event
            - parts[3]  ==> the id of the user
            - c[0]      ==> the latitudea of the position
            - c[1]      ==> the longitude of the position
            - parts[5]  ==> the type of emotion
        */
        return new Event(s, l, d, parts[3], Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), parts[5].charAt(0));
    }

    /**
     * @param poi A String object that represents a point of interest.
     * @return It returns a PointOfInterest object by passing as argument a String read from file representing a point of interest.
     * @throws Exception An Exception can thrown if the filed read contains at least a point of interest not valid.
     */
    public static PointOfInterest convertStringToPOI (String poi) throws Exception {
        if(!Validation.validatePOI(poi)) {
            throw new Exception("The format of the points of interest it's wrong. Check them, then restart the program.");
        }
        String[] split_poi = poi.split("-");
        String name = split_poi[0];
        String[] coord = split_poi[1].split(",");


        return new PointOfInterest(Double.parseDouble(coord[1]), Double.parseDouble(coord[0]), name);
    }
}