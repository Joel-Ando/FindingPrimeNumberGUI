package com.example.findingprimenumbergui;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class HelloController {

    // Injecting UI components using @FXML annotation
    @FXML
    private Label primeLabel;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;

    @FXML
    private GridPane gridPane;

    //method to initialize event
    @FXML
    private void initialize() {
        // Generate numbers from 1 to 100 dynamically
        generateNumbers(1, 100);
    }



    //method to generate and populate the gridPane with numbers dynamically

    private void generateNumbers(int start, int end) {
        int number = 1;
        int cols = 10; // Number of columns in the grid
        int rows = 10; // Calculate rows based on range


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (number > end) {
                    break; // Stop if number exceeds end
                }

                Button numberButton = new Button(String.valueOf(number));
                numberButton.setMinWidth(30); // Set button width
                numberButton.setMinHeight(30); // Set button height
                gridPane.add(numberButton, col, row); // Add button to grid

                number++;

            }
        }

    }

    // method for the reset button
    public void resetGridPane(){
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button resetButton) {
                resetButton.setStyle(null);


            }
        }
        primeLabel.setText("");
        input1.clear();
        input2.clear();
    }

    // method to generate prime number base on user start and end value

    public void generatePrimeNumber() {
        //get input values
        String startText = input1.getText().trim();
        String endText = input2.getText().trim();

        int start, end;

        // Input validation
        try {
            if (startText.isEmpty() || endText.isEmpty()) {
                primeLabel.setText("Please!! Both start and end numbers are required.");
                return;
            }

            //store the two input values into a size variable for easy use
            start = Integer.parseInt(startText);
            end = Integer.parseInt(endText);

            if (start > end) {
                primeLabel.setText("Input Error!! Start number must be less than or equal to end number.");
                return;
            }

            if (start < 0 || end < 0) {
                primeLabel.setText("Input Error Please enter non-negative numbers.");
                return;
            }

        } catch (NumberFormatException e) {
            primeLabel.setText("Input Error!! Please enter valid integer numbers.");
            return;
        }

        //convert the input value from strong to integer

        int size = end - start + 1;


            //defined an array of prime
            boolean[] prime = new boolean[size];

            //initialize prime[i] to true
            for (int i = 0; i < size; i++) {
                prime[i] = true;
            }

        // Handle cases where start <= 1 (0 and 1 are not prime)
        if (start <= 1) {
            for (int i = 0; i < Math.min(2 - start, size); i++) {
                prime[i] = false;
            }
        }

            // Sieve of Eratosthenes
            for (int p = 2; p * p <= end; p++) {
                // Find the minimum multiple of p within [start, end]
                int multiple = Math.max(p * p, ((start + p - 1) / p) * p);

                //removing all multiples
                for (int j = multiple; j <= end; j += p) {
                    if (j >= start) {
                        prime[j - start] = false;
                    }
                }
            }

            //store prime in an arraylist
            List<String> primes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (prime[i]) {
                    primes.add(String.valueOf(start + i));
                }
            }


            //printing all prime numbers
            primeLabel.setText(""); // Clear the label
            if (primes.isEmpty()) {
                primeLabel.setText("No prime numbers found in the given range.");
            } else {
                primeLabel.setText(String.join(", ", primes));
            }

            // code to iterate over gridPane get the number than text and convert them to integer
            // and check if primes generate contains the numbers and apply the style to them
            for (Node node : gridPane.getChildren()) {
                if (node instanceof Button numberButton) {
                    int number = Integer.parseInt(numberButton.getText());
                    if (primes.contains(String.valueOf(number))) {
                        numberButton.setStyle("-fx-background-color: rgba(255, 0, 0, 0.5)");

                    }
                }
            }


            // Clears the input field
            input1.clear();
            input2.clear();
        }
}



