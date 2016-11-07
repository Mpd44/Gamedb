package cis2206.model;

import java.util.List;

/**
 * IGameDAO is the interface for the Employee Data Access Object. The
 * interface defines the methods that will be used in all DAO implementations
 * for this application. This program currently has both file and database DAO
 * implementations. However, the application code does not care which is used as
 * everything is designed to work through this interface.
 *
 * @author Matt Delosa
 * @version 20151009
 *
 */
public interface IGameDAO {

    void createRecord(Game employee);

    Game retrieveRecordById(int id);

    List<Game> retrieveAllRecords();

    void updateRecord(Game updatedEmployee);

    void deleteRecord(int id);

    void deleteRecord(Game employee);

    @Override
    String toString();

}
