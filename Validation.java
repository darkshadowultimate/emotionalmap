/**
 * @author SIMONE PAGLINO
 * <p>Validation class provides several method to validate lines read from different files with very specific informations and formats.</p>
 */
public class Validation {

    /**
     * @param str A string of any format and content.
     * @return It returns true if there's at least one character inside the string passed as argument, otherwise false.
     */
    public static boolean isThereCharacter(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param poi A string read from a text file that represents a PointOfInterest object.
     * @return It returns true if the argument passed is a valid PointOfInterest object, otherwise false.
     */
    public static boolean validatePOI(String poi) {
        String[] split_poi = poi.split("-");
        if(split_poi.length != 2 || !poi.contains(",") || !poi.contains("-")) {
            return false;
        }
        String name = split_poi[0];
        if(!isThereCharacter(name)) {
            return false;
        }
        String[] coord = split_poi[1].split(",");
        try {
            double lon = Double.parseDouble(coord[0]);
            double lat = Double.parseDouble(coord[1]);
        } catch(Exception exc) {
            return false;
        }
        return true;
    }

    /**
     * @param comm A string read from a text file that represents a Command object.
     * @return It returns true if the argument passed is a valid command, otherwise false.
     */
    public static boolean validateCommand(String comm) {
        /*
            Example :
                comm = "import(eventi.txt)"
                split1 = ["import", "eventi.txt)"]
                split2 = ["eventi.txt", ""]
        */
        String[] split1 = comm.split("\\(");
        String[] split2 = split1[1].split("\\)");

        // return true if the name of the method is equal to "import" or "create_map"
        return validateCommandPossibleScenarios(comm, split1[0], split2[0]);

    }

    /**
     * @param comm A string read from a text file that represents a Command object.
     * @param str_method The name of the method.
     * @param str_param The value of the method's paramater.
     * @return It returns true if the the name of the method exists and if its arguments are valid, otherwise false.
     */
    private static boolean validateCommandPossibleScenarios(String comm, String str_method, String str_param) {
        switch(str_method) {
            // if the name of the method is "import" :
            case "import" : {
                /*
                    IF
                    - the length of 'comm' is less than 12 (the min length has to be 12 ==> Ex: "import(.txt)")
                        N.B. ==> on Linux type OS you can create a file like ".txt" without any trouble
                    - 'validateImport' returns false

                    SO THE "import" METHOD IS VALID
                */
                return (validateImport(str_param) && comm.length() > 12);
            }
            // if the name of the method is "create_map" :
            case "create_map" : {
                /*
                    IF
                    - the length of 'comm' is different than 29 (the length has to be 29 ==> Ex: "create_map(01012001-02012001)")
                    - 'validateCreateMap' returns false

                    SO THE "create_map" METHOD IS VALID
                */
                return (validateCreateMap(str_param) && comm.length() == 29);
            }
            // the name of the method doesn't exist
            default : { return false; }
        }
    }

    /**
     * @param param The value of import method paramater (the name of the file to load) --- Example: eventi.txt .
     * @return It returns true if the argument of "import" method is valid, otherwise false.
     */
    private static boolean validateImport(String param) {
        /*
            - the first character hasn't to be a '.' ;
            - the 'param' argument has to have an extension of .txt (txt file) ;
        */
        return param.charAt(0) != '.' && param.contains(".txt");
    }

    /**
     * @param param The value of create_map method paramater (the time interval composed by two timestamp) --- Example: 01012001-01022001 .
     * @return It returns true if the argument of "create_map" method is valid, otherwise false.
     */
    private static boolean validateCreateMap(String param) {
        // separate the first timestamp from the second one
        String[] split_param = param.split("-");
        // if the both timestamps are valid (validated by isFormatDate) the function returns true, otherwise false
        for(String timestamp : split_param) {
            // isFormateDate check if a date is valid or not
            if(!Date.isFormatDate(timestamp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param elem_line A line read from a text file representing an Event object.
     * @return It returns true if the parameter follows the correct format for a line text representing an Event object, otherwise false.
     */
    public static boolean validateElementsLine(String elem_line) {
        // separating all the info inside 'elem_line' argument
        /*
            Example:
                elem_line   = "IN LOGIN 09042019 ac11b 45.463,9.188 A"
                parts       = ["IN", "LOGIN", "09042019", "ac11b", "45.463,9.188", "A"]
        */
        String[] parts = elem_line.split(" ");
        // a single line representing an event has to contain six elements to be valid
        if(parts.length != 6) {
            return false;
        }
        // validating every single information inside parts array
        for(int i = 0; i < 6; i++) {
            // if all info are correct the function returns true, otherwise false
            if(!listEventPossibleScenarios(parts[i], i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param id The user's id.
     * @return It return's true if the id respects the correct format for an user's id.
     */
    private static boolean validateUserId(String id) {
        // the length of the id has to be 5
        if(id.length() != 5) { return false; }
        // every character of the id must be a letter (a-zA-Z) or a number (0-9)
        for(char c : id.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param coord A string which contain latitude and longitude separated from a comma.
     * @return It returns true if the coordinates are valid, otherwise false.
     */
    private static boolean validateCoordinates(String coord) {
        // separating latitude from longitude
        /*
            Example:
                coord       = "45.463,9.188"
                split_coord = ["45.463", "9.188"]
        */
        String[] split_coord = coord.split(",");
        // 'split_coord' has to contain latitude and longitude (length == 2)
        if(split_coord.length != 2) { return false; }

        try {
            // converting latitude and longitude to double (the precision is more accurate)
            double lat = Double.parseDouble(split_coord[0]);
            double lon = Double.parseDouble(split_coord[1]);

            // to be valid the latutide's and longitude's values hava to be contained inside the following values
            return ((lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
        } catch(Exception exc) {
            return false;
        }
    }

    /**
     * @param em A character representing an emotion.
     * @return It returns true if the parameter is equal to one of the predefined characters only uppercase (A, F, S, T, N).
     */
    private static boolean validateEmotion(char em) {
        return (em == 'A' || em == 'F' || em == 'S' || em == 'T' || em == 'N');
    }

    /**
     * @param el A value contained inside a line read from eventi.txt file (or similar) --- Example: IN/OUT, LOGIN/LOGOUT, timestamp ecc.
     * @param counter A value from 0 to 5 to distinguish the right scenario for the current element "el" passed as argument.
     * @return It returns true if the info with its counter (arguments) are valid --- just for events, otherwise false.
     */
    private static boolean listEventPossibleScenarios(String el, int counter) {
        /*
            0 : IN/OUT ;
            1 : LOGIN/LOGOUT ;
            2 : timestamp ;
            3 : user's id ;
            4 : coordinates ;
            5 : emotion (A/F/S/T/N)
        */
        switch(counter) {
            case 0: {
                return (el.equals("IN") || el.equals("OUT"));
            }
            case 1: {
                return (el.equals("LOGIN") || el.equals("LOGOUT"));
            }
            case 2: {
                return Date.isFormatDate(el);
            }
            case 3: {
                return validateUserId(el);
            }
            case 4: {
                return validateCoordinates(el);
            }
            case 5: {
                // conversion of the character from String to char
                return validateEmotion(el.charAt(0));
            }
        }
        // the counter's value cannot be superior to 5
        return false;
    }
}