package com.example.rivios_.sportapp.data;

/**
 * Created by rivios_ on 9/18/2017.
 */

public class FootballPlayersStats {
    private Athlete athlete;
    private FootballStats stats;
    private int gameCount;

    public FootballPlayersStats()
    {
        athlete = null;
        stats = null;
        gameCount = 0;
    }

    public FootballPlayersStats(Athlete pl, FootballStats st, int c)
    {
        this.athlete = pl;
        this.stats = st;
        this.gameCount = c;
    }

    public Athlete getAthlete() { return athlete; }
    public FootballStats getStats() { return stats; }
    public int getGameCount() { return gameCount; }

    public void setAthlete(Athlete pl) { athlete = pl; }
    public void setStats(FootballStats st) { stats = st; }
    public void setGameCount(int c) { gameCount = c; }

    public String toString()
    {
        return athlete.getNickname() + "       " + stats.getGoals() + " GOALS  " + stats.getAssists() + " AST  ";
    }


}
