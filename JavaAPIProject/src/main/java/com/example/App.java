package com.example;
import org.json.JSONObject;
/**
 * Hello world!
 *
 */


public class App {
    //main method
    public static void main(String[] args) {
        CarGuessingGame game = new CarGuessingGame(); //initalizes a new carguessing game object where startgame() will be called
        int totalCylinders = 0; // the total number of cylinders to be added to
        int numGames = 0; // number of games changed in the while loop
        game.loadCarData(); // calls the method to show all the previous car models and their number of cylinders

        while (numGames < 3){ // loop to play 3 games of guessing
            totalCylinders += game.startGame();
            System.out.println(" ");
            numGames++;
            
        }
        System.out.println("Average Num of Cylinders: " + (double) totalCylinders/numGames); // basic statistics where the average number of cylinders are calculated

    }
}

