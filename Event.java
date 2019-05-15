public class Event {
    private boolean signup;
    private boolean login;
    private Date    date;
    private String  id;
    private Coordinate coord;
    private char emot;

    public Event(boolean signup, boolean login, Date date, String id, double lat, double lng, char emot) {
        this.signup     = signup;
        this.login      = login;
        this.date       = date;
        this.id         = id;
        this.cord 		= new Coordinate(lng, lat);
        this.emot       = emot;
    }

    public boolean getSignup ()     { return this.signup;   }

    public boolean getLogin ()      { return this.login;    }

    public Date getDate ()          { return this.date;     }

    public String getId ()          { return this.id;       }

    public Coordinate getCoord() 	{ return this.coord;	}

    public char getEmotion ()       { return this.emot;     }

    public String toString () {
        return this.signup + " - " + this.login + " - " + this.date + " - " + this.id + " - " + this.lat + " - " + this.lng + " - " + this.emot;
    }
}