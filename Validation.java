public class Validation {

    public static boolean isThereCharacter(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateCommand(String comm) {
        String[] split1 = comm.split("\\(");
        String[] split2 = split1[1].split("\\)");

        if(!listCommandPossibleScenarios(split1[0])) {
            return false;
        }
        if(split1[0].equals("import") && !(validateImport(split2[0]) && comm.length() > 12)) {
            return false;
        }
        if(split1[0].equals("create_map") && !(validateCreateMap(split2[0]) && comm.length() == 29)) {
            return false;
        }
        return true;
    }

    private static boolean listCommandPossibleScenarios(String str) {
        return (str.equals("import") || str.equals("create_map"));
    }

    private static boolean validateImport(String param) {
        return param.charAt(0) != '.' && !param.equals(".txt") && param.length() > 4 || param.contains(".txt");
    }

    private static boolean validateCreateMap(String param) {
        String[] split_param = param.split("-");
        for(String timestamp : split_param) {
            if(!Date.isFormatDate(timestamp)) {
                return false;
            }
        }
        return true;
    }

    // validate the elements inside the single line representing an event
    public static boolean validateElementsLine(String l) {
        String[] parts = l.split(" ");
        String[] coord = parts[4].split(",");

        if(!(parts.length == 6)) {
            return false;
        }
        for(int i = 0; i < 6; i++) {
            if(!listEventPossibleScenarios(parts[i], i)) {
                return false;
            }
        }
        return true;
    }

    // validation for the user's id
    private static boolean validateUserId(String id) {
        if(id.length() != 5) { return false; }
        for(char c : id.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    // validation for the coordinates
    private static boolean validateCoordinates(String coord) {
        String[] split_coord = coord.split(",");

        if(split_coord.length != 2) { return false; }

        try {
            double lat = Double.parseDouble(split_coord[0]);
            double lon = Double.parseDouble(split_coord[1]);

            return ((lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
        } catch(Exception exc) {
            return false;
        }
    }

    private static boolean validateEmotion(char em) {
        return (em == 'A' || em == 'F' || em == 'S' || em == 'T' || em == 'N');
    }

    // possible elements inside the line
    private static boolean listEventPossibleScenarios(String el, int counter) {
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
                return validateEmotion(el.charAt(0));
            }
        }
        return true;
    }
}