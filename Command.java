public class Command {

    // name_command ==> it represents the name of the command to execute
    private String name_command = "";
    // parameter    ==> it represents the argument of the function which name is stored in name_command
    private String paramater = "";

    // contructor for the class Command
    public Command (String name_command, String paramater) {
        this.name_command = name_command;
        this.paramater = paramater;
    }

    // returns the value of name_command variable
    public String getNameCommand() { return this.name_command; }

    // returns the value of parameter variable
    public String getParamater() { return this.paramater; }

    /*
        Arguments   :
            String : ('command') ==> represents a single line read from comandi.txt file
        Purpose     :
            Extraction of the name of the command and of its parameters.
            Al last it return an Object command representing the line read.
    */
    public static Command convertStringToCommand (String command) {
        // if 'command' is a string representing a valid command ==> enter the if
        if(Validation.validateCommand(command)) {
            // Example :
            // command  = "import(eventi.txt)"
            // values   = ["import", "eventi.txt)"]
            String[] values = command.split("\\(");
            // assign to 'method' variable the name of the function inside 'command' variable
            String method = values[0];
            // taking off the last bracket and assign the parameter value (Ex: "eventi.txt") to 'param'
            String param  = values[1].split("\\)")[0];

            // return a Command object representing the line read from file 'command'
            return new Command(method, param);
        }
        return null;
    }

    // method for debugging
    public static void print (Command c) {
        System.out.println(c.getNameCommand() + " --- " + c.getParamater());
    }
}