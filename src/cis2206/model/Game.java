package cis2206.model;

/**
 * The Employee class represents a single employee.
 *
 * @author Matt Delosa
 * @version 20151015
 *
 */
public class Game {

    private int gameId;
    private String Title;
    private String Genre;

    public Game() {
        gameId = 0;
        Title = "";
        Genre = "";
    }

    public Game(int gameId, String Title, String Genre ) {
        this.gameId = gameId;
        this.Title = Title;
        this.Genre = Genre;

    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

 



    @Override
    public String toString() {
        return String.format("%5d : %s, %s, %s, %.2f", this.getGameId(), this.getTitle(),
                this.getGenre() );
    }

    public int getgameId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
