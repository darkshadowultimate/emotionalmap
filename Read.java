import java.util.ArrayList;
import java.io.*;

public class Read {

    // return an ArrayList object representing all the lines read from the comomands name file passed as parameter
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
        } catch(FileNotFoundException exc) {
            System.out.println("File not found...");
        } catch(IOException exc) {
            System.out.println("Problem with IO operations...");
        }
        return commands;
    }

    // return an ArrayList object representing all the lines read from the event name file passed as parameter
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

    // return an Event Object representing the current line read from the file
    private static Event convertLineToEventObj(String event) {
        String[] parts = event.split(" ");
        String[] coord = parts[4].split(",");

        return decodeValueToFinalType(parts, coord);
    }

    // the function converts all the data of the line readed from String type to the right type to create an instance of an Event Object
    private static Event decodeValueToFinalType(String[] e, String[] c) {
        // if a user is register in the application ==> store value true
        boolean s   = e[0].equals("IN");
        // if a user is active in the application ==> store value true
        boolean l   = e[1].equals("LOGIN");
        // get a Date object passing the timestamp value of the date
        Date d      = Date.getDateObj(e[2]);

        /*
            Returning the Event object representing the line read

            PARAMETERS :

            - s     ==> if the user is register into the application
            - l     ==> if the user is currently active on the platform
            - d     ==> the date of the event
            - e[3]  ==> the id of the user
            - c[0]  ==> the latitudea of the position
            - c[1]  ==> the longitude of the position
            - e[5]  ==> the type of emotion
        */
        return new Event(s, l, d, e[3], c[0], c[1], e[5].charAt(0));
    }

    // method for debugging
    public static void print_events (ArrayList<Event> l) {
        for(Event e : l) {
            System.out.println(e + "\n");
        }
    }
    // method for debugging
    public static void print_commands (ArrayList<Command> l) {
        for(Command c : l) {
            Command.print(c);
        }
    }

    public static void main (String[] args) {
        ArrayList<Event> arr;
        ArrayList<Command> comm;

        comm = Read.readCommands(args[0]);
        Read.print_commands(comm);
        arr = Read.readEvents("./eventi.txt");
        Read.print_events(arr);
    }
}