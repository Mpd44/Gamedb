package cis2206.model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cis2206.model.Game;
import cis2206.model.IGameDAO;

/**
 * GameDAO (Data Access Object) handles all interactions with the data
 store. This version uses a MySQL database to store the data. It is multiuser
 * safe.
 *
 * @author Matt Delosa
 * @version 20160920
 *
 */
public class GameDAO implements IGameDAO {

    protected final static boolean DEBUG = true;

    @Override
    public void createRecord(Game employee) {
        final String QUERY = "insert into employee "
                + "(empId, lastName, firstName, homePhone, salary) "
                + "VALUES (null, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY);) {
            stmt.setString(1, employee.getTitle());
            stmt.setString(2, employee.getGenre());
         
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("createRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public Game retrieveRecordById(int id) {
        final String QUERY = "select gameId, title, genre "
                + "from games where gameId = " + id;
        // final String QUERY = "select empId, lastName, firstName, homePhone,
        // salary from employee where empId = ?";
        Game emp = null;

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            // stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            if (rs.next()) {
                emp = new Game(
                        rs.getInt("gameId"), 
                        rs.getString("Title"),
                        rs.getString("Genre"));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveRecordById SQLException: " 
                    + ex.getMessage());
        }

        return emp;
    }

    @Override
    public List<Game> retrieveAllRecords() {
        final List<Game> myList = new ArrayList<>();
        final String QUERY = "select gameId, title, genre, "
                + " from games";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                myList.add(new Game(
                        rs.getInt("gameId"), 
                        rs.getString("Title"), 
                        rs.getString("Genre")));
            }
        } catch (SQLException ex) {
            System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
        }

        return myList;
    }

    @Override
    public void updateRecord(Game updatedGame) {
        final String QUERY = "update games set Title=?, Genre=?, "
                + " where gameId=?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setString(1, updatedGame.getTitle());
            stmt.setString(2, updatedGame.getGenre());
            
            stmt.setInt(5, updatedGame.getGameId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("updateRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(int id) {
        final String QUERY = "delete from games where gameId = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, id);
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRecord(Game employee) {
        final String QUERY = "delete from games where gameId = ?";

        try (Connection con = DBConnection.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setInt(1, employee.getGameId());
            if (DEBUG) {
                System.out.println(stmt.toString());
            }
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("deleteRecord SQLException: " + ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Game employee : retrieveAllRecords()) {
            sb.append(employee.toString()).append("\n");
        }

        return sb.toString();
    }
}
