import java.util.ArrayList;

public class EmotionalMaps {
    public static void main(String[] args) throws Exception {
        ArrayList<PointOfInterest> listPoi = new ArrayList<PointOfInterest>();
        ArrayList<Command> listCommand = new ArrayList<Command>();
        ArrayList<Event> listEvent = new ArrayList<Event>();

        listPoi = Read.readPOI("./poi.txt");
        listCommand = Read.readCommands(args[0]);

        for(Command comm : listCommand) {
            callMethod(listEvent, listPoi, comm);
        }
    }
    public static void callMethod (ArrayList<Event> listEvent, ArrayList<PointOfInterest> listPoi, Command comm) {
        switch(comm.getNameCommand()) {
            case "import" : {
                ArrayList<Event> temp = Read.readEvents(comm.getParamater());
                listEvent.addAll(temp);
                break;
            }
            case "create_map" : {
                String[] dates = comm.getParamater().split("-");
                GenerateEmotionalMaps.generateFilteredEmotionalMaps(listEvent, listPoi, Date.getDateObj(dates[0]), Date.getDateObj(dates[1]));
                System.out.println("\n----------------------------------\n");
                GenerateEmotionalMaps.generateFullEmotionalMaps(listEvent, listPoi, Date.getDateObj(dates[0]), Date.getDateObj(dates[1]));
                break;
            }
        }
    }
}
