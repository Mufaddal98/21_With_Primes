
import java.util.Scanner;

public class Player {
    Scanner scanner = new Scanner(System.in);

    String name;
    int score = 0;
    int roundsWon;
    Tile lastTilePlayed;
    Tile[] tiles = new Tile[5];
    int count = 0;

    public Player(String name, int score, int roundsWon) {
        this.name = name;
        this.score = score;
        this.roundsWon = roundsWon;
    }

    public Player(){

    }

    public void addTile(int value, int score) {
        tiles[count] = new Tile(value, score);
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    Inputs name from user according to the requirements given
     */
    public String takeName(){
        System.out.println("Player, please enter your name: ");
        String playerName = scanner.next();
        while ((!playerName.matches( "[A-Za-z]+"))  ||  playerName.length() > 10  || playerName.length() < 3)
        {
            System.out.println( "Your name can only contain characters and length should be b/w 3 to 10. Enter your name again: " );
            playerName = scanner.next();
        }
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }
}
