package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CarGuessingGame {
    // instance array of about 20 car models to be parameters in the api call
    private static String[] models = {"camry", "civic", "mustang", "corolla", "accord", "altima", "impala", "fusion", "malibu", "charger", "mdx", "rav4", "sienna", "charger", "escalade", "911", "pilot", "f150", "e63", "m5", "x5", "gt", "i8", "sienna", "grand cherokee"}
    ;

    public int startGame() {
        Scanner scanner = new Scanner(System.in);
        String selectedModel = getRandomModel(); //calls the method to get a random car model from the array

        JSONArray cars = API.getCarDataByModel(selectedModel); //json array for the specific car
        
        if (cars.isEmpty()) { //checks if the json array is empty if the api doesnt have that model
            System.out.println("This car does not exist in the api: " + selectedModel);
            return 0;
        }

        JSONObject car = cars.getJSONObject(0); // gets the first value in the json array for the car model (only one car is outputted from the api)
        System.out.println("Guess the model of this car:");

        //displays all the important descriptions and values of the car
        System.out.println("Make: " + car.getString("make"));
        System.out.println("Year: " + car.getInt("year"));
        System.out.println("Fuel Type: " + car.getString("fuel_type"));
        System.out.println("Transmission: " + car.getString("transmission"));
        System.out.println("Num Cylinders: " + car.getInt("cylinders"));
        System.out.println("Engine Size: " + car.getDouble("displacement"));
        System.out.println("Drivetrain: " + car.getString("drive"));

        //checks if the player got the car model right
        String guess = scanner.nextLine().toLowerCase();
        if (guess.equals(selectedModel.toLowerCase())) {
            System.out.println("Correct!");
        } else {
            System.out.println("That is wrong. The model was: " + selectedModel);
        }    
        // saves the number of cylinders and the model and returns it to the main method
        String summary = "Model: " + selectedModel + ", Cylinders: " + car.getInt("cylinders");
        saveCarData(summary);
        return car.getInt("cylinders");
    }

    // returns a random model in the array
    public String getRandomModel() {
        return models[(int) (Math.random() * (models.length))];
    }

    //saves the number of cylinders and the model into car_history.txt
    public void saveCarData(String carData) {
        try (FileWriter writer = new FileWriter("car_history.txt", true)) {
            writer.write(carData + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // loads the number of cylinders and the model fromc car_history.txt
    public void loadCarData() {
        System.out.println("All Cars played:");
        try (BufferedReader reader = new BufferedReader(new FileReader("car_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("No cars have been played yet");
        }
    }
}
