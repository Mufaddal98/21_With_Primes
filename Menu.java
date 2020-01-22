
import java.util.InputMismatchException;
import java.util.Scanner;
/*
The purpose of this Menu class is to display the landing menu of this game. Once the project is executed
it will display this menu to the user. The user will select one of the options which will them be directed to Game
or Player class using switch-cases. This class doesn't have an actual role to play in the actual gameplay.
Menu class is only there to display menu and input user's choice and direct the gameplay to other classes accordingly
 */


public class Menu {
    Scanner scanner = new Scanner(System.in);
    public boolean isPlayerRegistered = false;

    public Menu() {
        start();
    }
    
    public void displayStartMenu(){
        System.out.println("\n  ");
        System.out.println("Please Select From The Following Options");
        System.out.println("Press 1 to Register Player");
        System.out.println("Press 2 to Start New Game");
        System.out.println("Press 3 to View Help Menu");
        System.out.println("Press 4 to Exit");

    }
    
   
    public void start() {
        String choice = "";
        Game game = new Game();        
        while (true) {


            while (true) {
                displayStartMenu();
                choice = scanner.next();
                while ((!choice.equalsIgnoreCase("1"))  && (!choice.equalsIgnoreCase("2"))
                        && (!choice.equalsIgnoreCase("3"))  && (!choice.equalsIgnoreCase("4"))){
                    System.out.println("Please only enter a number between 1 and 4");
                    choice = scanner.next();
                }
                break;

            }

            switch (choice){
                case "1":
                    game.ifCase1();
                    isPlayerRegistered = true;
                    break;

                case "2":
                    checkOption1();
                    game.ifCase2();

                    break;

                case "3":
                    game.ifCase3();
                    break;

                case "4":
                    System.exit(1);
                    break;
            }


        }
    }

    public void checkOption1(){
        if (!isPlayerRegistered){
            System.out.println("Please select option 1 and register player first");
            start();
        }
    }


}
