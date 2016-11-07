package cis2206.model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cis2206.model.Game;
import cis2206.model.IGameDAO;

/**
 * GameDAO (Data Access Object) handles all interactions with the data
 store. This version uses a file to store the data. It is not multiuser safe.
 *
 * @author Matt Delosa
 * @version 20160920
 *
 */
public abstract class GameDAO implements IGameDAO {

    protected String fileName = null;
    protected final List<Game> myList;

    public GameDAO() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("res/file/db.properties"));
            this.fileName = props.getProperty("DB_FILENAME");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.myList = new ArrayList<>();
        try {
            Files.createFile(Paths.get(fileName));
        } catch (FileAlreadyExistsException fae) {
            ;
        } catch (IOException ioe) {
            System.out.println("Create file error with " + ioe.getMessage());
        }
        readList();
    }

    @Override
    public void createRecord(Game employee) {
        myList.add(employee);
        writeList();
    }

    @Override
    public Game retrieveRecordById(int id) {
        for (Game employee : myList) {
            if (employee.getgameId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Game> retrieveAllRecords() {
        return myList;
    }

    @Override
    public void updateRecord(Game updatedEmployee) {
        for (Game employee : myList) {
            if (employee.getgameId() == updatedEmployee.getgameId()) {
                employee.setTitle(updatedEmployee.getTitle());
                employee.setGenre(updatedEmployee.getGenre());
                
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(int id) {
        for (Game employee : myList) {
            if (employee.getgameId() == id) {
                myList.remove(employee);
                break;
            }
        }
        writeList();
    }

    @Override
    public void deleteRecord(Game employee) {
        myList.remove(employee);
        writeList();
    }

    private void readList() {
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String genre = data[2];
                
                
                Game employee = new Game(id, title, genre);
                myList.add(employee);
            }
        } catch (IOException ioe) {
            System.out.println("Read file error with " + ioe.getMessage());
        }
    }

    private void writeList() {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Game employee : myList) {
                writer.write(String.format("%d,%s,%s,%s,%.2f\n",
                        employee.getgameId(),
                        employee.getTitle(),
                        employee.getGenre()
                        
                      ));
            }
        } catch (IOException ioe) {
            System.out.println("Write file error with " + ioe.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Game employee : myList) {
            sb.append(String.format("%5d : %s, %s, %s, %.2f\n",
                   employee.getgameId(),
                        employee.getTitle(),
                        employee.getGenre()));
                        
        }

        return sb.toString();
    }

    /**
     *
     * @param employee
     */
 
 
}