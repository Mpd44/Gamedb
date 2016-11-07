
package cis2206.viewui;

import cis2206.util.Validator;
import java.util.Scanner;

import cis2206.model.Game;
//import cis2206.model.datastore.file.GameDAO;
import cis2206.model.datastore.mysql.GameDAO;
import cis2206.model.IGameDAO;

/**
 * GameApp is the starting point for running this console-oriented
 menu-driven employee management program. This program demonstrates two
 solutions. The Genre is file based. The second is MySQL based.
 *
 * @author Matt Delosa
 * @version 20160920
 *
 */
public class GameApp {

    IGameDAO empList = new GameDAO();
    Scanner sc = new Scanner(System.in);

    public GameApp() {
        menuLoop();
    }

    private void menuLoop() {
        int id;
        String Title, Genre;
        
        String choice = "1";
        while (!choice.equals("0")) {
            System.out.println("\nGame App");
            System.out.println("0 = Quit");
            System.out.println("1 = List All Records");
            System.out.println("2 = Create New Record");
            System.out.println("3 = Retrieve Record");
            choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

            switch (choice) {
                case "1":
                    System.out.println(empList.toString());
                    break;
                case "2":
                    id = Validator.getInt(sc, "Game ID: ");
                    Title = Validator.getLine(sc, "Title: ");
                    Genre = Validator.getLine(sc, "Genre: ");
                  
                    empList.createRecord(new Game(id, Title, Genre));
                    break;
                case "3":
                    id = Validator.getInt(sc, "Game id to retrieve: ");
                    System.out.println(empList.retrieveRecordById(id));
                    break;
                case "4":
                    id = Validator.getInt(sc, "Game ID to update: ");
                    Title = Validator.getLine(sc, "Last name: ");
                    Genre = Validator.getLine(sc, "First name: ");
                    
                    empList.updateRecord(new Game(id, Title, Genre));
                    break;
                case "5":
                    id = Validator.getInt(sc, "Game ID to delete: ");
                    System.out.println(empList.retrieveRecordById(id));
                    String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
                    if (ok.equalsIgnoreCase("Y")) {
                        empList.deleteRecord(id);
                    }
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GameApp();
    }
}
