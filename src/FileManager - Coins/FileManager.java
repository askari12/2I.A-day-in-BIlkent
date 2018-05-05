package sample;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {


    private static File highscoreFile = new File ("C:\\Users\\MONSTER\\IdeaProjects\\src\\sample\\highscore.txt");
    private static File currFile = new File( "C:\\Users\\MONSTER\\IdeaProjects\\src\\sample\\saveFile.txt");
    private static FileWriter fw;
    private static Scanner in;

    // File Names

    // Default values
    private static final int DEFAULT_COINS = 0;
    private final ArrayList<String> DEFAULT_HIGHSCORE = new ArrayList<>();

    private static FileManager fm;

    private FileManager() {
    }

    public static FileManager getFileManager() {
        if (fm == null) {
            fm = new FileManager();
        }

        return fm;
    }

    /***********************************************************************************************************/

    // Getters

    /*
        Gets the Coins of the User.
    */
    public int getCoins() {

        // Open the Scanner
        openScanner(currFile);
        // Get Data
        int coins = DEFAULT_COINS;
        while(in.hasNextInt()) {
            coins = in.nextInt();
        }

        // Close the Scanner
        closeScanner();

        return coins;
    }

    /*
        Gets the sound settings of the User.
    */



    /*
        Gets the High Scores of the User.
    */
    public ArrayList<String> getHighScore(){
        openScanner(highscoreFile);         // Open the Scanner

        // Get Data
        String bool = "false";
        ArrayList<String> hs = DEFAULT_HIGHSCORE;
        while(in.hasNext()) {
            hs.add(in.next());
        }

        closeScanner();                 // Close Scanner
        return hs;
    }


    /***********************************************************************************************************/

    // Setters

    /*
        Updates the coins of the User
    */
    public void updateCoins(int coins) {

        // open the File Writer
        openFileWriter(currFile);

        try {
            // Clear The value
            fw.flush();

            // Write to file
            fw.append(coins + "");
        }

        catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

        closeFileWriter();
    }

    /*
        Updates the High Scores.
    */
    public void updateHighScore(ArrayList<String> hs){
        openFileWriter(highscoreFile);    // Open the File

        try {
            fw.flush();                             // Remove the previus value

            // Add New Value
            String data = "";
            for (int i = 0; i < hs.size(); i = i + 2) {
                data += hs.get(i) + " " + hs.get(i + 1) + "\n";
            }

            fw.append(data);
        }

        catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

        closeFileWriter();              // Close File
    }


    /***********************************************************************************************************/

    // Private Methods



    // Open File Writer
    private void openFileWriter(File title) {


        try {
            fw = new FileWriter(title);
        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

    }

    // Open Scanner
    private void openScanner(File title) {


        try {
            in = new Scanner(title);
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            System.exit(11);
        }
    }

    // Close File
    private void closeFileWriter() {
        try {
            fw.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(12);
        }
    }

    private void closeScanner() {
        in.close();
    }
}
