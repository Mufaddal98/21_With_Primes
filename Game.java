
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner((System.in));
    RNG rng = new RNG();

    Player player = new Player(),
            computer = new Player();

    int numOfRounds,
            gameTotal = 0;
    int[] totalTiles = new int[]{1, 2, 3, 5, 7};
    
    public Game(){
        //Menu menu = new Menu();
        
        //menu.start();
    }
    

    private void playRound(int roundNo) {
        String prompt = generatePrompt(roundNo);
        System.out.println(prompt);

        //Initializing
        gameTotal = 0;
        player.tiles = new Tile[5];
        player.count = 0;
        computer.tiles = new Tile[5];
        computer.count = 0;

        boolean turn = playerGoesFirst();
        for (int i = 0; i < 5; i++) {
            if (gameTotal < 21) {
                System.out.println("\nTurn no " + (i + 1) + "\n");
                if (turn) {
                    playTurnUser();
                    if (gameTotal < 21)
                        playTurnComputer();
                } else {
                    playTurnComputer();
                    if (gameTotal < 21)
                        playTurnUser();
                }
            } else {
                System.out.println("Game total is greater than or equal to 21. Game is over.");
                break;
            }
        }
        //Score finalizing
        //User
        boolean used5 = false;
        for (int h = 0; h < player.count; h++) {
            if (player.tiles[h].value == 5) {
                used5 = true;
                break;
            }
        }
        if (!used5) {
            System.out.println("Penalty of 3 on User for not using 5");
            player.setScore(player.getScore() - 3);
        }
        //Computer
        boolean used5C = false;
        for (int h = 0; h < computer.count; h++) {
            if (computer.tiles[h].value == 5) {
                used5C = true;
                break;
            }
        }
        if (!used5C) {
            System.out.println("Penalty of 3 on Computer for not using 5");
            computer.setScore(computer.getScore() - 3);
        }
        //Comparision
        if (player.getScore() > computer.getScore()) {
            System.out.println("Player has higher score");
            player.setScore(player.getScore() + 5);
        } else {
            System.out.println("Computer has higher score");
            computer.setScore(computer.getScore() + 5);
        }
        //Score display
        System.out.println("\n\nFinal Score");
        System.out.println("User Score: " + player.getScore());
        System.out.println("Computer Score: " + computer.getScore());
    }

    /*
    Asks the human player to enter his tile value. Checks after every turn whether the game score is less than
    or equal to 21. If not, the round ends and scores are assigned accordingly.
     */
    private void playTurnUser() {
        if (gameTotal <= 21) {
            System.out.println("User! Please select a tile from {" + getPlayerTiles(player) + "}");
            int inputTile = inputTile(player);
            gameTotal = gameTotal + inputTile;
            if (gameTotal <= 21) {
                player.addTile(inputTile, compareTileValue(inputTile));
                player.setScore(player.getScore() + compareTileValue(inputTile));
                System.out.println("You play tile " + inputTile + ". Game total is " + gameTotal + ". Your total score is: " + player.getScore());
            }
        }

        else {
            System.out.println("Game is over!");
        }
    }

    /*
    Plays the computer's turn by deciding a tilr value using math.random logic. Ensures that the game total tile value
    stays within 21 otherwise the round ends and scores are assigned
     */
    private void playTurnComputer() {
        //Computer Logic will come here
        if (gameTotal < 21) {
            System.out.println("Computer! Please select a tile from {" + getPlayerTiles(computer) + "}");
            int inputTile = computeTileInput();
            gameTotal = gameTotal + inputTile;
            if (gameTotal <= 21) {
                computer.addTile(inputTile, compareTileValue(inputTile));
                computer.setScore(computer.getScore() + compareTileValue(inputTile));
                System.out.println("Computer plays tile " + inputTile + ". Game total is " + gameTotal + ". Your total score is: " + computer.getScore());
            }


        } else {
            System.out.println("Game is over!");

        }
    }

    /*
    Generates a tile value on behalf of a computer using logical oprations.
     */
    private int computeTileInput() {
        int value = 0;
        int gTot = gameTotal;
        int totLeft = 21 - gTot;

        //Avoid penalty and to cover maximum area
        if (totLeft >= 5) {
            if (!checkIfTileIsUsed(5, computer))
                return 5;
        }
        //Choose top score
        if (!checkIfTileIsUsed(1, computer)) {
            if (totLeft >= 1)
                return 1;
        }
        if (!checkIfTileIsUsed(2, computer)) {
            if (totLeft >= 2)
                return 2;
        }
        if (!checkIfTileIsUsed(3, computer)) {
            if (totLeft >= 3)
                return 3;
        }
        if (!checkIfTileIsUsed(7, computer)) {
            if (totLeft >= 7)
                return 7;
        }

        return value;
    }

    /*
    Checks if the tile has already been used by the human player or not every time the player makes a move
     */
    private boolean checkIfTileIsUsed(int tile, Player player) {
        for (int i = 0; i < player.count; i++) {
            if (player.tiles[i].value == tile) {
                return true;
            }
        }
        return false;
    }

    /*
    Takes human player's tile input and ensures that the player only enter the specific
     tile values that exist in the game. Throws exception if user enters a wrong value
     */
    private int inputTile(Player player) {
        int value = 0;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value < 1 || value > 7 || value == 4 || value == 6) {
                    System.out.println("ERROR: Please select a valid tile!");
                    continue;
                }
                for (int h = 0; h < player.count; h++) {
                    if (value == player.tiles[h].value) {
                        System.out.println("ERROR: Tile has already been used");
                        continue;
                    }
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid Integer");
                e.printStackTrace();
            }
        }
    }

    /*

     */
    private String generatePrompt(int roundNo) {
        String prompt = "Round " + (roundNo + 1);
        return prompt;
    }

    private String getPlayerTiles(Player player) {
        int[] allTiles = totalTiles;
        for (int h = 0; h < player.count; h++) {
            for (int i = 0; i < allTiles.length; i++) {
                if (allTiles[i] == player.tiles[h].value) {
                    allTiles[i] = 0;
                }
            }
        }
        String tiles = "";
        for (int i : allTiles) {
            if (i != 0) {
                tiles = tiles + i + ",";
            }
        }
        tiles = tiles.substring(0, tiles.length() - 1);

        totalTiles = new int[]{1, 2, 3, 5, 7};

        return tiles;
    }

    /*
    Compares the tile value entered by human or computer and return its respective score accordingly
     */
    public int compareTileValue(int value) {
        if (value == 1) {
            return 5;
        } else if (value == 2) {
            return 4;
        } else if (value == 3) {
            return 3;
        } else if (value == 5) {
            return 2;
        } else {
            return 1;
        }
    }

    /*
    This method is called in Menu class where case is 1.
    basically registers the player
     */
    public void ifCase1() {
        registerPlayer();
    }

    //Starting game
    public void ifCase2() {
        inputNumOfRounds();
        for (int i = 0; i < numOfRounds; i++) {
            playRound(i);
        }
        System.out.println("Game is over.\n\nTotal Scores:");
        System.out.println("Player Score: " + player.getScore());
        System.out.println("Computer Score: " + computer.getScore());
        if (player.getScore() > computer.getScore()) {
            System.out.println("The Player wins this game.");
        } else {
            System.out.println("The Computer wins this game.");
        }

    }

    public void ifCase3() {
        viewHelpMenu();
    }

    /*
    Decides who will play the first turn using randomly generated numbers in the RNG class
     */
    public boolean playerGoesFirst() {
        if (rng.randomNumGen(1, 100) > 50) {
            return true;
        } else {
            return false;
        }
    }

    /*
    Takes input from human player for the number of rounds they wish to play. The number should be between
    0 to 10 otherwise the user will be asked to enter the number of roumns again
     */
    public void inputNumOfRounds() {
        System.out.println(player.getName() + ", How many rounds do you wish to play?: ");
        numOfRounds = scanner.nextInt();
        while (numOfRounds < 0 || numOfRounds > 10) {
            System.out.println("The number of rounds should be between 0 and 10. Please enter again: ");
            numOfRounds = scanner.nextInt();
        }
    }

    public void registerPlayer() {
        player.setName(player.takeName());
    }

    /*
    Views help menu for players who wish to understand the game
     */
    public void viewHelpMenu() {
        System.out.println("The aim of 21 With Primes is for a player and the computer to compete against each other to gather\n" +
                "the most points while playing a single tile each turn with the goal of ensuring that the total for each\n" +
                "round is less than or equal to 21. ");
        System.out.println("To play the game, each player (human and computer) are given a set of 5 tiles. Each tile has an value\n" +
                "and an associated score. For playing a tile, the player gets the associated score for that tile provided\n" +
                "the total is less than 21.");
        System.out.println();
    }

    

    //public static void main(String[] args) {
        // write your code here
      //  Menu menu = new Menu();
      //  menu.start();

   // }
}
