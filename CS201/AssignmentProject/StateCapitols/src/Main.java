/*The assignment instructions only requested a text document, if a .java file is needed please let me know and I will provide one. */

/* Part 1: Sorting Arrays
1. Develop a program that asks the user to enter a capital for a U.S. state. 
2. Upon receiving the user input, the program reports whether the user input is correct. 
3. For this application, the 50 states and their capitals are stored in a two-dimensional array in order by state name.
4. Display the current contents of the array
5. use a bubble sort to sort the content by capital. 
6. Next, prompt the user to enter answers for all the state capitals and then display the total correct count. The user's answer is not case-sensitive.*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Create a 2D array of capitols and states, alphabetized by state. (req 3)
        // row 0 is the state, row 1 is the capital. A capital and State will have the same index in their respective array. for example the index of Alabama and Montgomery are both 0.
        String[][] capitalCities = {
            {"Montgomery", "Juneau", "Phoenix", "Little Rock", "Sacramento", "Denver", "Hartford", "Dover", "Tallahassee", "Atlanta", "Honolulu", "Boise", "Springfield", "Indianapolis", "Des Moines", "Topeka", "Frankfort", "Baton Rouge", "Augusta", "Annapolis", "Boston", "Lansing", "Saint Paul", "Jackson", "Jefferson City", "Helena", "Lincoln", "Carson City", "Concord", "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck", "Columbus", "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre", "Nashville", "Austin", "Salt Lake City", "Montpelier", "Richmond", "Olympia", "Charleston", "Madison", "Cheyenne"},
            {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"}  
        };
        System.out.println("Welcome to the SDC CS201 State Capital Assignment!");
        // Initialize a scanner to collet user input and prompt the user for an input.(req 1)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a US State Capital: ");
        String userInput = scanner.nextLine();
        
        // variables to hold the array of cities and truth state for matching
        String[] cities = capitalCities[0];
        boolean matchFound = false;

        //search the city array for a match, report to the user if they were correct. (req 2)
        for (String city : cities) {
            if (city.equalsIgnoreCase(userInput)) {
                matchFound = true;
                break;
            }
        }
        if (matchFound) {
            System.out.println("Correct!");
        } else {
            System.out.println("incorrect!");
        }

        // Display the current contents of the 2D array (req 4)
        System.out.println("the capitals of the US states are:");
        for (int i = 1; i < 51; i++) {
            System.out.println(String.valueOf(i) + ": " + capitalCities[0][i-1] + "," + capitalCities[1][i-1]);
        }

        // Sort the array by capital rather than state using Bubble sort (req 5)
        // the outer for loop begins at the first element of the array, cityI
        for (int j = 0; j < capitalCities[0].length; j++) {
            // the inner for loop will begin at the second element of the array, cityJ
            for (int i = j + 1; i < capitalCities[0].length; i++) {
                //variables for city and state
                String tempCity;
                String tempState;
                // If cityI comes before cityJ alphabetically
                if (capitalCities[0][i].compareTo(capitalCities[0][j]) < 0) {
                    /* 1. Assign cityJ's value to tempCity
                     * 2. assign cityI's value to CityJ's index
                     * 3. assign tempCity's value(cityJ's value) to what was previously
                     *    cityI's Index
                     * 
                     * this results in the two cities switching places.
                    */
                    tempCity = capitalCities[1][j];
                    capitalCities[1][j] = capitalCities[1][i];
                    capitalCities[1][i] = tempCity;
                    // Perform same operations on states, to maintain state/capital columns matching within the array
                    tempState = capitalCities[0][j];
                    capitalCities[0][j] = capitalCities[0][i];
                    capitalCities[0][i] = tempState;

                    //Repeat this process across every value to complete the bubble sort and end with an 2D array alphabetized by capital name.
                }
            }
        } 

        //prompt the user to enter answers for all the state capitals and then display the total correct count. (req 6)
        System.out.println("please enter as many state capitals as you can. Enter 'done' when you are finished. Press enter after each capital you type");

        //variable to track if user has entered "done"
        boolean userDone = false;
        Scanner loopInputScn = new Scanner(System.in);
        ArrayList<String> userAnswers = new ArrayList<>();
        //get input, check for "done", otherwise add to userAnswers
        while (!userDone) {
            String loopInputStr = loopInputScn.nextLine();
            if (loopInputStr.equalsIgnoreCase("done")) {
                System.out.println("Lets see how many you got right!");
                userDone = true;
            }
            else{
                userAnswers.add(loopInputStr);
            }
        }

        // Tell the user how many they got right 
        int correctCounter = 0;
        //For each attempted answer, check the array of capitals for a match and increment correctCounter by 1 if found
        for (String attempt : userAnswers) {
            for (String city : cities) {
                if (attempt.equalsIgnoreCase(city))
                    correctCounter += 1;
            }
        }

        System.out.println("you got " + correctCounter + " correct!");


        /* Part 2: Sorting & Searching HashMap
        7. Now revise the code to store the pairs of each state and its capital in a Map using the HashMap function. 
        8. Display the content of the Map. 
        9. then use the TreeMap class to sort the map while using a binary search tree for storage. 
        10. Next, your program should prompt the user to enter a state and it should then display the capital for the state. */
        
        //initialize a hashmap
        HashMap<String, String> capitalHashMap = new HashMap<>(capitalCities[0].length);
        
        //populate the hashmap with the contents of the 2D array (req 7)
        for (int i = 0; i < capitalCities[0].length; i++) {
            capitalHashMap.put(capitalCities[1][i], capitalCities[0][i]);
        }

        // display the content of the Map (req 8)
        System.out.println("The state capitals are:");
        for (String key : capitalHashMap.keySet()) {
            System.out.println( capitalHashMap.get(key) + "," + key);
        }

        // use the TreeMap class to sort the map (req 9)
        TreeMap<String, String> capitalsTreeMap = new TreeMap<>(capitalHashMap);

        //Prompt the user for a state and display the capital (req 10)
        //reuse the earlier userDone for this loop
        userDone = false;
        while (!userDone) {
            System.out.println("Please enter a state and I will tell you the capital. Enter 'done' when you are finished: ");
            String loopInputStr = loopInputScn.nextLine(); 

            if (capitalsTreeMap.containsKey(loopInputStr)) {
                System.out.println(capitalsTreeMap.get(loopInputStr));
            } else {
                System.out.println("That was not a state. Try again, or enter 'done'");
            }
        }
      
        loopInputScn.close();
        scanner.close();
    }

}

