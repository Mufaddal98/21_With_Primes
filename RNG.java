
public class RNG {
    int minimumValue;
    int maximumValue;

    public RNG(int minimumValue, int maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public RNG(){

    }

    /*
    Generates a random integer between the range given, that is, minimum and maximum value
     */
    public int randomNumGen(int minimumValue, int maximumValue){
        int time = (int) (Math.random()*maximumValue) + minimumValue;
        return time;
    }
}
