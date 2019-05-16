// Import HashMap data structure
import java.util.HashMap;

// Import IllegalArgumentException class
import java.lang.IllegalArgumentException;

public class Emotions {
    // Defining the five possible emotion types
    private int countA;
    private int countF;
    private int countS;
    private int countT;
    private int countN;

    // Defining five static variables for avoid hard-coding
    static final char EMOTION_A = 'A';
    static final char EMOTION_F = 'F';
    static final char EMOTION_S = 'S';
    static final char EMOTION_T = 'T';
    static final char EMOTION_N = 'N';

    // Constructor sets all the parameters to 0
    public Emotions () {
        this.countA = 0;
        this.countF = 0;
        this.countS = 0;
        this.countT = 0;
        this.countN = 0;
    }

    // numeroOccorrenze creates an HashMap which has five keys (each one identifies a specific emotion) and it attaches to each key the correct number of occurrences
    public HashMap<Character, Integer> numeroOccorrenze () {
        HashMap<Character, Integer> mapOccorrenze = new HashMap<Character, Integer>();

        mapOccorrenze.put(EMOTION_A, this.countA);
        mapOccorrenze.put(EMOTION_F, this.countF);
        mapOccorrenze.put(EMOTION_S, this.countS);
        mapOccorrenze.put(EMOTION_T, this.countT);
        mapOccorrenze.put(EMOTION_N, this.countN);

        return mapOccorrenze;
    }

    // increase looks for the specified emotion and increases the number of occurrences related. If the emotion is invalid, it throws an exception
    public void increase (char emotion) throws IllegalArgumentException {
        emotion = Character.toUpperCase(emotion);

        switch (emotion) {
            case EMOTION_A:
                this.countA++;
                break;
            case EMOTION_F:
                this.countF++;
                break;
            case EMOTION_S:
                this.countS++;
                break;
            case EMOTION_T:
                this.countT++;
                break;
            case EMOTION_N:
                this.countN++;
                break;
            default:
                throw new IllegalArgumentException("The specified emotion doesn't exist...");
        }
    }
}