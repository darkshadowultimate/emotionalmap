package src.soluzione;

// Import HashMap data structure
import java.util.HashMap;

// Import IllegalArgumentException class
import java.lang.IllegalArgumentException;

/**
 * @author Stefano Cascavilla
 * <p>It describes an emotion object</p>
 */
public class Emotions {
    // Defining the five possible emotion types
    /**
     * The number of A emotions got
     */
    private int countA;

    /**
     * The number of F emotions got
     */
    private int countF;

    /**
     * The number of S emotions got
     */
    private int countS;

    /**
     * The number of T emotions got
     */
    private int countT;

    /**
     * The number of N emotions got
     */
    private int countN;

    // Defining five static variables for avoid hard-coding
    /**
     * A stands for: "Arrabbiato"
     */
    static final char EMOTION_A = 'A';

    /**
     * F stands for: "Felice"
     */
    static final char EMOTION_F = 'F';

    /**
     * S stands for: "Sorpreso"
     */
    static final char EMOTION_S = 'S';

    /**
     * T stands for: "Triste"
     */
    static final char EMOTION_T = 'T';

    /**
     * N stands for: "Neutro"
     */
    static final char EMOTION_N = 'N';

    /**
     * The constructor sets all the fields to 0
     */
    public Emotions () {
        this.countA = 0;
        this.countF = 0;
        this.countS = 0;
        this.countT = 0;
        this.countN = 0;
    }

    /**
     * 
     * @return It returns an HashMap which has five keys (each one identifies a specific emotion), each of these has attached the correct number of occurrences
     */
    public HashMap<Character, Integer> numeroOccorrenze () {
        HashMap<Character, Integer> mapOccorrenze = new HashMap<Character, Integer>();

        mapOccorrenze.put(EMOTION_A, this.countA);
        mapOccorrenze.put(EMOTION_F, this.countF);
        mapOccorrenze.put(EMOTION_S, this.countS);
        mapOccorrenze.put(EMOTION_T, this.countT);
        mapOccorrenze.put(EMOTION_N, this.countN);

        return mapOccorrenze;
    }

    /**
     * It looks for the specified emotion and increases the number of occurrences related. If the emotion is invalid, it throws an exception
     * @param emotion It identifies the emotion char
     */
    public void increase (char emotion) {
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
        }
    }
}