import java.util.ArrayList;

/**
 * @author SIMONE PAGLINO - 736763 | STEFANO CASCAVILLA - 736820
 * <p>EmotionalMaps class contains the main method used to execute the entire program 'emotionalmaps'.</p>
 */
public class EmotionalMaps {

    /**
     * The main method is used to execute the entire program.
     * @param args data passed from terminal to execute the program.
     * @throws Exception An Exception is thrown if the content of "poi.txt" file is wrong formatted.
     */
    public static void main(String[] args) throws Exception {
        ArrayList<PointOfInterest> listPoi = new ArrayList<PointOfInterest>();
        ArrayList<Command> listCommand = new ArrayList<Command>();
        ArrayList<Event> listEvent = new ArrayList<Event>();

        listPoi = Read.readPOI("poi.txt");
        //listCommand = Read.readCommands(args[0]);
        listCommand = Read.readCommands("comandi.txt");

        for(Command comm : listCommand) {
            callMethod(listEvent, listPoi, comm);
        }
    }

    /**
     * callMethod execute every command contained inside "comandi.txt" (or similar).
     * @param listEvent An ArrayList of Event objects which contains all the events read from "eventi.txt" (or similar) text file until now.
     * @param listPoi An ArrayList of PointOfInterest objects which contains all the predefined points of interest.
     * @param comm A Command object representing the a line read from "comandi.txt" (or similar) text file to be executed.
     */
    public static void callMethod (ArrayList<Event> listEvent, ArrayList<PointOfInterest> listPoi, Command comm) {
        switch(comm.getNameCommand()) {
            case "import" : {
                ArrayList<Event> temp = Read.readEvents(comm.getParamater());
                listEvent.addAll(temp);
                break;
            }
            case "create_map" : {
                if(listEvent.size() == 0) { break; }
                String[] dates = comm.getParamater().split("-");
                GenerateEmotionalMaps.generateFilteredEmotionalMaps(listEvent, listPoi, Date.getDateObj(dates[0]), Date.getDateObj(dates[1]));
                System.out.println("\n----------------------------------\n");
                GenerateEmotionalMaps.generateFullEmotionalMaps(listEvent, listPoi, Date.getDateObj(dates[0]), Date.getDateObj(dates[1]));
                break;
            }
        }
    }
}
