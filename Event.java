public class Event {
    private boolean signup;
    private boolean login;
    private Date    date;
    private String  id;
    private String  lat;
    private String  lng;
    private char emot;

    public Event(boolean signup, boolean login, Date date, String id, String lat, String lng, char emot) {
        this.signup     = signup;
        this.login      = login;
        this.date       = date;
        this.id         = id;
        this.lat        = lat;
        this.lng        = lng;
        this.emot       = emot;
    }

    public boolean getSignup ()     { return this.signup;   }

    public boolean getLogin ()      { return this.login;    }

    public Date getDate ()          { return this.date;     }

    public String getId ()          { return this.id;       }

    public String getLatitude ()    { return this.lat;      }

    public String getLongitude ()   { return this.lng;      }

    public char getEmotion ()       { return this.emot;     }

    public String toString () {
        return this.signup + " - " + this.login + " - " + this.date + " - " + this.id + " - " + this.lat + " - " + this.lng + " - " + this.emot;
    }

}