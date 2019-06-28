package src.soluzione;
/**
 * @author SIMONE PAGLINO
 * <p>Command class represents a single line read from text file "comandi.txt" (or similar).</p>
 */
public class Command {

    /**
     * It represents the name of the command to execute.
     */
    private String name_command = "";

    /**
     * It represents the argument's value of the function which name is stored in name_command.
     */
    private String paramater = "";

    /**
     * Contructor to create a new instance of Command class.
     * @param name_command It's the name of the command.
     * @param paramater It's the value of the argument passed to the function which name is stored in 'name_command'.
     */
    public Command (String name_command, String paramater) {
        this.name_command = name_command;
        this.paramater = paramater;
    }

    /**
     * @return It returns the name of the function of Command class.
     */
    public String getNameCommand() { return this.name_command; }

    /**
     * @return It returns the argument's value of Command class.
     */
    public String getParamater() { return this.paramater; }

    /*
        Arguments   :
            String : ('command') ==> represents a single line read from comandi.txt file
        Purpose     :
            Extraction of the name of the command and of its parameters.
            Al last
    */

    /**
     * @param command It represents a single line read from comandi.txt file (or similar).
     * @return It returns a Command object representing the line read.
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
}