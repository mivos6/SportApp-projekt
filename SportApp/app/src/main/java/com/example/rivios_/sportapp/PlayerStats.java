package com.example.rivios_.sportapp;

/**
 * Created by Milan on 2.9.2017..
 */

public class PlayerStats {
    private Player player;
    private Stats stats;

    public PlayerStats()
    {
        player = null;
        stats = null;
    }

    public PlayerStats(Player pl, Stats st)
    {
        this.player = pl;
        this.stats = st;
    }

    public Player getPlayer() { return player; }
    public Stats getStats() { return stats; }

    public void setPlayer(Player pl) { player = pl; }
    public void setStats(Stats st) { stats = st; }

    public String toString()
    {
        return player.getNickname() + "                 " + stats.getPoints() + " PTS   " + stats.getAssists() + " AST   " + stats.getJumps() + " RBD";
    }


}
