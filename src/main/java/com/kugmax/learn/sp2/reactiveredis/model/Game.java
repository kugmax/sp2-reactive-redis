package com.kugmax.learn.sp2.reactiveredis.model;

public class Game {
    private long gameID;
    private String name;
    private String description;

    public Game() {
    }

    public Game(long gameID) {
        this.gameID = gameID;
    }

    public Game(long gameID, String name, String description) {
        this.gameID = gameID;
        this.name = name;
        this.description = description;
    }

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (gameID != game.gameID) return false;
        if (name != null ? !name.equals(game.name) : game.name != null) return false;
        return description != null ? description.equals(game.description) : game.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (gameID ^ (gameID >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
