package com.example.rivios_.sportapp;

/**
 * Created by Milan on 2.9.2017..
 */

public class PlayerStats {
    private Player player;
    private Stats stats;
    private int gameCount;

    public PlayerStats()
    {
        player = null;
        stats = null;
        gameCount = 0;
    }

    public PlayerStats(Player pl, Stats st, int c)
    {
        this.player = pl;
        this.stats = st;
        this.gameCount = c;
    }

    public Player getPlayer() { return player; }
    public Stats getStats() { return stats; }
    public int getGameCount() { return gameCount; }

    public void setPlayer(Player pl) { player = pl; }
    public void setStats(Stats st) { stats = st; }
    public void setGameCount(int c) { gameCount = c; }

    public String toString()
    {
        return player.getNickname() + "                 " + stats.getPoints() + " PTS   " + stats.getAssists() + " AST   " + stats.getJumps() + " RBD";
    }


}
