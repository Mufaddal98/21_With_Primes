
public class Tile {

    int value;
    int score;

    public Tile() {

    }

    public Tile(int value, int score) {
        this.value = value;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "value=" + value +
                ", score=" + score +
                '}';
    }
}
