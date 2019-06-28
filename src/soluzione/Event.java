package src.soluzione;

/**
 * @author SIMONE PAGLINO
 * <p>Event class is the representation of the String lines read from "eventi.txt" file (or similar).</p>
 */
public class Event {

    /**
     * A boolean with value 'true' if the user is registered in the application, otherwise false.
     */
    private boolean signup;

    /**
     * A boolean with value 'true' if the user is active in the application, otherwise false.
     */
    private boolean login;

    /**
     * A Date object representing the date of the creation of this Event instance.
     */
    private Date    date;

    /**
     * The user's id.
     */
    private String  id;

    /**
     * The longitude's value when the Event instance was created.
     */
    private Coordinate coord;

    /**
     * A character representing a specific emotion.
     */
    private char emot;

    /**
     * Constructor to create a new instance Event.
     * @param signup A boolean with value 'true' if the user is registered in the application, otherwise false.
     * @param login A boolean with value 'true' if the user is active in the application, otherwise false.
     * @param date A Date object representing the date of the creation of this Event instance.
     * @param id The user's id.
     * @param lat The latitude's value when the Event instance was created.
     * @param lng The longitude's value when the Event instance was created.
     * @param emot A character representing a specific emotion.
     */
    public Event(boolean signup, boolean login, Date date, String id, double lat, double lng, char emot) {
        this.signup     = signup;
        this.login      = login;
        this.date       = date;
        this.id         = id;
        this.coord 		= new Coordinate(lng, lat);
        this.emot       = emot;
    }

    /**
     * @return It returns true if the user is registered to the application, otherwise false.
     */
    public boolean getSignup ()     { return this.signup;   }

    /**
     * @return It returns true if the user is active to the application, otherwise false.
     */
    public boolean getLogin ()      { return this.login;    }

    /**
     * @return It returns a Date object representing the date of the creation of this Event instance.
     */
    public Date getDate ()          { return this.date;     }

    /**
     * @return It returns the user'id.
     */
    public String getId ()          { return this.id;       }

    /**
     * @return It returns a Coordinate object representing the longitude and latitude when the Event instance was created.
     */
    public Coordinate getCoord() 	{ return this.coord;	}

    /**
     * @return It returns a character representing a specific emotion (A = Arrabbiato, F = Felice, S = Sorpreso, T = Triste, N = Neutro).
     */
    public char getEmotion ()       { return this.emot;     }
}