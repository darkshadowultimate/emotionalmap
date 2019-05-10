public class Event {
	private boolean signUp;
	private boolean login;
	private String date;
	private String id;
	private Coordinate coord;
	private char emot;
	
	public Event(boolean signUp, boolean login, String date, String id, double lat, double lon, char emot) {
		this.signUp = signUp;
		this.login = login;
		this.date = date;
		this.id = id;
		this.cord = new Coordinate(lon, lat);
		this.emot = emot;
	}
	
	public boolean getSignUp() {
		return this.signUp;
	}
	
	public boolean getLogin() {
		return this.login;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Coordinate getCoord() {
		return this.coord;
	}
	
	public char getEmot() {
		return this.emot;
	}
}