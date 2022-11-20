package com.demoqa.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookStoreApiUtils {

    public static void storeInfoToFile(String userID){
        File outFile;
        PrintWriter output;
        // writing userID to a regular resources file
        // defining a file: we need to provide absolute path of the file
        outFile = new File("C:\\Users\\Oscar\\IdeaProjects\\BookStoreApi\\src\\test\\resources\\userID.out");
        if(outFile.exists()){
            outFile.delete();
        }

        try {
            output = new PrintWriter(outFile);
            output.println(userID);
            output.close();  // this step crucial to actually finalize writing function
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static String readFromFile(){
        // Read the file for userID
        File outFile;
        String userID = "";
        outFile = new File("C:\\Users\\Oscar\\IdeaProjects\\BookStoreApi\\src\\test\\resources\\userID.out");
        Scanner scanner = null;
        try {
           scanner = new Scanner(outFile);
           userID  = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userID;
    }
}
