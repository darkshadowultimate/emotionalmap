public class Command {
    private String name_command = "";
    private String paramater = "";

    public Command (String name_command, String paramater) {
        this.name_command = name_command;
        this.paramater = paramater;
    }

    public String getNameCommand() { return this.name_command; }

    public String getParamater() { return this.paramater; }

    public static Command convertStringToCommand (String command) {
        if(Validation.validateCommand(command)) {
            String[] values = command.split("\\(");
            String method = values[0];
            String param  = values[1].split("\\)")[0];

            return new Command(method, param);
        }
        return null;
    }

    public static void print (Command c) {
        System.out.println(c.getNameCommand() + " --- " + c.getParamater());
    }
}