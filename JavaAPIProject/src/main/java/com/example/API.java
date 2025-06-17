package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;

public class API {
    private static String api_key = "i1XBSqikZ06lxEU5cLJDQw==lji9Vr7OTF1FlTBl"; // api key

    public static JSONArray getCarDataByModel(String model) { // parameter model picked out from the array
        try {
            String endpoint = "https://api.api-ninjas.com/v1/cars?model=" + model; // api endpoint with the url and the desired model
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // connects to the url
            connection.setRequestMethod("GET"); // sends a get request to the api
            connection.setRequestProperty("X-Api-Key", api_key); // used to authenticate the request, was in the api documentation that said this had to be used

            BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream())); // wraps the text in a buffer so that we could read line by line
            String inputLine; // variable that stores the text
            StringBuilder content = new StringBuilder();

            while ((inputLine = buff.readLine()) != null) {
                content.append(inputLine);
            }

            buff.close(); // closes the bufer
            connection.disconnect();

            return new JSONArray(content.toString()); // returns the content as a string

        } catch (Exception e) { // catchs the execption if the api sends a response like 400
            System.out.println("Error: " + e.getMessage());
            return new JSONArray();
        }
    }
}
