package sample;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {


    private File currFile;
    private FileWriter fw;
    private Scanner in;

    // File Names
    private String coinFile = "coins";
    private String soundOnFile = "sound-on";
    private String musicOnFile = "music-on";
    private String diffFile = "difficulty";
    private String hsFile = "highscore";
    private String creditsFile = "credits";

    // Default values
    private final int DEFAULT_COINS = 0;
    private final String DEFAULT_SOUND_ON = "true";
    private final String DEFAULT_MUSIC_ON = "true";
    private final ArrayList<String> DEFAULT_HIGHSCORE = new ArrayList<>();
    private final String DEFAULT_CREDITS = "";
    private final int DEFAULT_DIFFICULTY = 0;

    public FileManager() {
    }

    /***********************************************************************************************************/

    // Getters

    /*
        Gets the Coins of the User.
    */
    public int getCoins() {

        // Open the Scanner
        openScanner(coinFile);

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
    public boolean getSoundOn(){

        // Open the Scanner
        openScanner("SoundOn");

        // Get Data
        String bool = DEFAULT_SOUND_ON;
        while(in.hasNext()) {
            bool = in.next();
        }

        closeScanner();                 // Close Scanner

        if (bool.equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    /*
        Gets the Music settings of the User.
    */
    public boolean getMusicOn(){
        openScanner(musicOnFile);         // Open the Scanner

        // Get Data
        String bool = DEFAULT_MUSIC_ON;
        while(in.hasNext()) {
            bool = in.next();
        }

        // Close Scanner
        closeScanner();

        if (bool.equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    /*
        Gets the Difficulty settings of the User.
    */
    public int getDifficulty(){
        openScanner(diffFile);         // Open the Scanner

        // Get Data
        int diff = DEFAULT_DIFFICULTY;
        while(in.hasNextInt()) {
            diff = in.nextInt();
        }

        closeScanner();                 // Close Scanner
        return diff;
    }

    /*
        Gets the High Scores of the User.
    */
    public ArrayList<String> getHighScore(){
        openScanner(hsFile);         // Open the Scanner

        // Get Data
        String bool = "false";
        ArrayList<String> hs = DEFAULT_HIGHSCORE;
        while(in.hasNext()) {
            hs.add(in.next());
        }

        closeScanner();                 // Close Scanner
        return hs;
    }

    /*
        Gets the Credits of the Game.
    */
    public String getCredits(){
        openScanner(creditsFile);         // Open the Scanner

        // Get Data
        String credits = DEFAULT_CREDITS;
        while(in.hasNextLine()) {
            credits += in.nextLine();
        }

        closeScanner();                 // Close Scanner

        return credits;
    }

    /***********************************************************************************************************/

    // Setters

    /*
        Updates the coins of the User
    */
    public void updateCoins(int coins) {

        // open the File Writere
        openFileWriter(coinFile);

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
        Updates the Sound Option for the User
    */
    public void updateSound(boolean soundOn){

        // Open File Writer
        openFileWriter(soundOnFile);

        try {
             fw.flush();                // Remove the previus value
             fw.append(soundOn + "");   // Add New Value
        }

        catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

        // Close File
        closeFileWriter();
    }

    /*
        Updates the Music Option for the User
    */
    public void updateMusic(boolean musicOn){
        openFileWriter(musicOnFile);    // Open the File

        try {
            fw.flush();                // Remove the previus value
            fw.append(musicOn + "");   // Add New Value
        }

        catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

        closeFileWriter();              // Close File
    }

    /*
        Updates the Difficulty of the Game.
    */
    public void updateDifficulty(int difficulty) {
        openFileWriter(diffFile);    // Open the File

        try {
            fw.flush();                   // Remove the previus value
            fw.append(difficulty + "");   // Add New Value
        }

        catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

        closeFileWriter();              // Close File
    }

    /*
        Updates the High Scores.
    */
    public void updateHighScore(ArrayList<String> hs){
        openFileWriter(hsFile);    // Open the File

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

    // Open File
    private void openFile(String title){
        // Create File
        this.currFile = new File(title + ".txt");
    }

    // Open File Writer
    private void openFileWriter(String title) {

        openFile(title);

        try {
            this.fw = new FileWriter(currFile);
        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }

    }

    // Open Scanner
    private void openScanner(String title) {

        openFile(title);

        try {
            this.in = new Scanner(currFile);
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            System.exit(11);
        }
    }

    // Close File
    private void closeFileWriter() {
        try {
            this.fw.close();
        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(12);
        }
    }

    private void closeScanner() {
        this.in.close();
    }
}
