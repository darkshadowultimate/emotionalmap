import java.util.ArrayList;

class EmotionalMapMain {
	
	public static void main(String[] args) {
		ArrayList<Event> eve;
		ArrayList<Command> com;
		
		com = Read.readCommands(args[0]);
		
		for(Command c : com) {
			Command.print(c);
		}
	}
}