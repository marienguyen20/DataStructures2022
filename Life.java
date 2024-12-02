/** 
* Life file for the Game of Life with methods to determine whether 
* the cell is alive or dead as well as generating generation in each step of simulation
* based on original array. 
*
* In Game of Life, the cell values at each step of the simulation determine cell values at next timestep  
* following the rules, if a cell is dead, it does nothing.
* If a cell is live and has 2 live neighbors, then it will die next timestep.
* iF a cell does not have 2 live neighbors, then any dead neighbors of that cell will become alive. 
*
* @author  Marie Nguyen 
* @version 02/17/2022
*/

import java.util.Random; 
import java.io.*;
import java.util.*;

public class Life{
    // Instance variables:
    private int size;
    private int[] gameArray;

    // Constructor: requires one parameter, the size of the array
    public Life(int initSize){
        size = initSize;
        // Generate an array 
        gameArray = new int[size];
        // Loops through the gameArray and randomly assign whether it's 0 or 1
        // O for dead cell and 1 for live cell :)
        for (int i = 0; i < gameArray.length; i++){
            // Create Random object named as random
            Random random = new Random();
            // Generate random number 0 or 1 
            int number = random.nextInt(2);
            // Assign that number to the value of the cell in the gameArray. 
            gameArray[i] = number;
        }

    }
    
    // Methods:
    /**
     * isAlive method takes an index i as a parameter. 
     * 
     * @param index the index of the array 
     * @return True if the cell at position i is alive, otherwise returns False
     */
    public boolean isAlive(int index){
        // if the cell in the given index is alive, return true
        if (gameArray[index] == 1){
            return true;
        }
        // Otherwise, return false as the cell is dead
        else{
            return false;
        }
    }

    /**
     * shouldDie method takes an index i as a parameter
     * @param index the index of the array 
     * @return true if cell i has 2 live neighbors, false if not, 
     * and null if the index is the edge of the array 
     */
    public String shouldDie(int index){
        // If the given index is the edge of the array (first or final index)
        if (index == 0 || index == size-1){
            return "null";
        }
        // else if the cell has 2 live neighbors
        else if (gameArray[index+1] == 1 && gameArray[index-1] == 1){
            return "true";
        }
        // The cell does not have 2 live neighbors
        else{
            return "false";
        }
    }

    /**
     * copyArray function: copying the values of the first array into the second array.
     * The method should be static, as it does not use any instance variable or any non-static array 
     * @param firstArray
     * @param secondArray
     * not return anything. 
     */
    public static void copyArray(int[] firstArray, int[] secondArray){
        // Loops through the first array 
        for (int i = 0; i < firstArray.length; i++){
            // Assign the values in the first array to the values in second array 
            secondArray[i] = firstArray[i];
        }
    }

    /**
     * toString method takes no parameters
     * return the result of invoking the Arrays.toString method on the array
     */
    public String toString(){
        return Arrays.toString(gameArray);
    }

    /**
     * advanceTime method takes no parameters, carrying out one simulation of the Game of Life
     * @return the array at the next point in time 
     */
    public int[] advanceTime(){
        // Initialize another array for next timestep, same length with gameArray
        int[] newGameArray = new int[gameArray.length];
        // Call the copy Array of Life class:
        copyArray(gameArray, newGameArray);
        // Life.copyArray(gameArray, newGameArray);
        for (int i = 0; i < gameArray.length; i++){
            // If the cell is dead, it does nothing
            if (isAlive(i) == false){
                continue;
            }
            // If the cell is alive
            else{
                // if the index is 0 and -1, the cell is in the edge of the array 
                if (shouldDie(i) == "null"){
                    // if the cell's neighbor is dead, the neighbor will become alive
                    // if the cell at index = 0 and the cell at index 1 is dead
                    if (i == 0 && gameArray[i+1] == 0){
                        // the cell at index 1 will become alive next timestep
                        newGameArray[i+1] = 1;
                    }
                    // if it's the final index and the previous cell is dead
                    else if (i == gameArray.length - 1 && gameArray[gameArray.length - 2] == 0){
                        // the previous neighbor will become alive next timestep
                        newGameArray[gameArray.length - 2] = 1;
                    }
                    // if the edge doesn't have any dead neighbor, it remain the same 
                    else{
                        continue;
                    }
                }
                // if the cell is alive and have 2 live neighbors, it will die next timestep
                else if (shouldDie(i) == "true"){
                    newGameArray[i] = 0; 
                }
                else { 
                    // If the live cell has a dead neighbor, that neighbor will become alive next timestep
                    // If it's the previous dead neighbor
                    if (gameArray[i-1] == 0){
                    // This neighbor will become alive next
                    newGameArray[i-1] = 1;
                    }
                    // If it's the behind dead neighbor
                    if (gameArray[i+1] == 0){
                    // This neighbor will become alive next
                    newGameArray[i+1] = 1;
                    }
                }
            }
        }
        // Copy the newGameArray to the gameArray for next simulation 
        copyArray(newGameArray, gameArray);
        return gameArray;    // return the array at the end of each simulation 
    }    
    // Main method: It's time to play the Game of Life :)
    public static void main(String args[]){
        // Set the timeSteps or "generations" to 10:
        int timeSteps = 10; 
        // Set the size of the Game of Life to 10:
        int gameSize = 10; 
        // Generate a new Life object named as game 
        Life game = new Life(gameSize); 
        // Print the intial values held in the array
        System.out.println(game.toString());
        // Loops though each step and print out the array at the end of each step 
        for (int i = 0; i < timeSteps; i++){
            // Invoke the advanceTime method for an updated array in each simulation
            game.advanceTime();
            // Print the updated array in this step 
            System.out.println(game.toString());
        }       
    }
}