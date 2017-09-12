package com.example.rivios_.sportapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rivios_.sportapp.data.Athlete;
import com.example.rivios_.sportapp.data.BasketballGame;
import com.example.rivios_.sportapp.data.BasketballStats;
import com.example.rivios_.sportapp.data.FootballGame;
import com.example.rivios_.sportapp.data.JoggingRace;
import com.example.rivios_.sportapp.data.JoggingStats;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 27.5.2016..
 */
public class GameDBHelper extends SQLiteOpenHelper {

    private static final String DTATBASE_NAME = "sportStatsDB";
    private static final int SCHEMA = 8;

    private static GameDBHelper instance;

    private GameDBHelper(Context ctx) {
        super(ctx, DTATBASE_NAME, null, SCHEMA);
    }

    public static GameDBHelper getInstance(Context ctx) {
        if (instance == null)
            instance = new GameDBHelper(ctx);
        return instance;
    }

    static final String TABLE_BASKETBALL_GAMES = "basketball_games";
    static final String BASKETBALL_GAME_ID = "id";
    static final String BASKETBALL_TEAM1 = "team1";
    static final String BASKETBALL_TEAM2 = "team2";
    static final String BASKETBALL_RESULT1 = "result1";
    static final String BASKETBALL_RESULT2 = "result2";
    static final String BASKETBALL_DATUM = "datum";
    static final String BASKETBALL_WINNER = "winner";

    static final String TABLE_FOOTBALL_GAMES = "football_games";
    static final String FOOTBALL_GAME_ID = "id";
    static final String FOOTBALL_TEAM1 = "team1";
    static final String FOOTBALL_TEAM2 = "team2";
    static final String FOOTBALL_RESULT1 = "result1";
    static final String FOOTBALL_RESULT2 = "result2";
    static final String FOOTBALL_DATUM = "datum";
    static final String FOOTBALL_WINNER = "winner";

    static final String TABLE_TENNIS_GAMES = "tennis_games";
    static final String TENNIS_GAME_ID = "id";
    static final String TENNIS_TEAM1 = "player1";
    static final String TENNIS_TEAM2 = "player2";
    static final String TENNIS_RESULT1 = "result1";
    static final String TENNIS_RESULT2 = "result2";
    static final String TENNIS_WINNER = "winner";
    static final String TENNIS_DATUM = "datum";
    static final String TENNIS_SET1 = "set1";
    static final String TENNIS_SET2 = "set2";
    static final String TENNIS_SET3 = "set3";
    static final String TENNIS_SET4 = "set";
    static final String TENNIS_SET5 = "set5";


    static final String TABLE_ATHLETES = "basketball_players";
    static final String ATHLETE_ID = "id";
    static final String ATHLETE_NAME = "name";
    static final String ATHLETE_NICKNAME = "nickname";
    static final String ATHLETE_DISCIPLINE = "discipline";

    static final String TABLE_BASKETBALL_STATS = "basketball_stats";
    static final String BASKETBALL_STATS_GAME_ID = "game_id";
    static final String BASKETBALL_STATS_PLAYER_ID = "player_id";
    static final String BASKETBALL_STATS_POINTS = "points";
    static final String BASKETBALL_STATS_ASSISTS = "assists";
    static final String BASKETBALL_STATS_JUMPS = "jumps";
    static final String BASKETBALL_STATS_TEAM = "team";

    static final String TABLE_FOOTBALL_STATS = "football_stats";
    static final String FOOTBALL_STATS_GAME_ID = "game_id";
    static final String FOOTBALL_STATS_PLAYER_ID = "player_id";
    static final String FOOTBALL_STATS_GOALS = "goals";
    static final String FOOTBALL_STATS_ASSISTS = "assists";
    static final String FOOTBALL_STATS_TEAM = "team";

    static final String TABLE_JOGGING_RACES = "table_jogging_races";
    static final String JOGGING_RACE_ID = "id";
    static final String JOGGING_RACE_NAME = "name";
    static final String JOGGING_RACE_START = "start";
    static final String JOGGING_RACE_FINISH = "finish";
    static final String JOGGING_RACE_DISTANCE = "distance";

    static final String TABLE_JOGGING_STATS = "jogging_stats";
    static final String JOGGING_STATS_RACE_ID = "race_id";
    static final String JOGGING_STATS_RUNNER_ID = "runner_id";
    static final String JOGGING_STATS_TIME = "time";
    static final String JOGGING_STATS_PLACE = "place";


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_BASKETBALL_GAMES =
                "CREATE TABLE " + TABLE_BASKETBALL_GAMES +
                        " (" + BASKETBALL_GAME_ID + " INTEGER PRIMARY KEY," +
                        BASKETBALL_TEAM1 + " TEXT," +
                        BASKETBALL_TEAM2 + " TEXT," +
                        BASKETBALL_RESULT1 + " INTEGER," +
                        BASKETBALL_RESULT2 + " INTEGER," +
                        BASKETBALL_DATUM + " INTEGER," +
                        BASKETBALL_WINNER + " TEXT);";

        final String CREATE_TABLE_FOOTBALL_GAMES =
                "CREATE TABLE " + TABLE_FOOTBALL_GAMES +
                        " (" + FOOTBALL_GAME_ID + " INTEGER PRIMARY KEY," +
                        FOOTBALL_TEAM1 + " TEXT," +
                        FOOTBALL_TEAM2 + " TEXT," +
                        FOOTBALL_RESULT1 + " INTEGER," +
                        FOOTBALL_RESULT2 + " INTEGER," +
                        FOOTBALL_DATUM + " INTEGER;";

        final String CREATE_TABLE_ATHLETES =
                "CREATE TABLE " + TABLE_ATHLETES +
                        " (" + ATHLETE_ID + " INTEGER PRIMARY KEY," +
                        ATHLETE_NAME + " TEXT," +
                        ATHLETE_NICKNAME + " TEXT,"  +
                        ATHLETE_DISCIPLINE + "TEXT);";

        final String CREATE_TABLE_BASKETBALL_STATS =
                "CREATE TABLE " + TABLE_BASKETBALL_STATS +
                        " (" + BASKETBALL_STATS_GAME_ID + " INTEGER," +
                        BASKETBALL_STATS_PLAYER_ID + " INTEGER," +
                        BASKETBALL_STATS_POINTS + " INTEGER," +
                        BASKETBALL_STATS_ASSISTS + " INTEGER," +
                        BASKETBALL_STATS_JUMPS + " INTEGER," +
                        BASKETBALL_STATS_TEAM + " TEXT," +
                        //"PRIMARY KEY(" + BASKETBALL_STATS_GAME_ID + "," + BASKETBALL_STATS_PLAYER_ID + ")," +
                        "FOREIGN KEY(" + BASKETBALL_STATS_GAME_ID + ") REFERENCES " + TABLE_BASKETBALL_GAMES + "(" + BASKETBALL_GAME_ID + ")," +
                        "FOREIGN KEY(" + BASKETBALL_STATS_PLAYER_ID + ") REFERENCES " + TABLE_ATHLETES + "(" + ATHLETE_ID + "));";

        final String CREATE_TABLE_FOOTBALL_STATS =
                "CREATE TABLE " + TABLE_FOOTBALL_STATS +
                        " (" + BASKETBALL_STATS_GAME_ID + " INTEGER," +
                        FOOTBALL_STATS_PLAYER_ID + " INTEGER," +
                        FOOTBALL_STATS_GOALS + " INTEGER," +
                        FOOTBALL_STATS_ASSISTS + " INTEGER," +
                        FOOTBALL_STATS_TEAM + " TEXT," +
                        //"PRIMARY KEY(" + BASKETBALL_STATS_GAME_ID + "," + BASKETBALL_STATS_PLAYER_ID + ")," +
                        "FOREIGN KEY(" + BASKETBALL_STATS_GAME_ID + ") REFERENCES " + TABLE_BASKETBALL_GAMES + "(" + BASKETBALL_GAME_ID + ")," +
                        "FOREIGN KEY(" + BASKETBALL_STATS_PLAYER_ID + ") REFERENCES " + TABLE_ATHLETES + "(" + ATHLETE_ID + "));";

        final String CREATE_TABLE_JOGGING_RACES =
                "CREATE TABLE " + TABLE_JOGGING_RACES +
                        " (" + JOGGING_RACE_ID + " INTEGGER PRIMARY KEY,"  +
                        JOGGING_RACE_NAME  + " TEXT," +
                        JOGGING_RACE_START + " TEXT," +
                        JOGGING_RACE_FINISH + " TEXT," +
                        JOGGING_RACE_DISTANCE + " INTEGER);";

        final String CREATE_TABLE_JOGGING_STATS =
                "CREATE TABLE " + TABLE_JOGGING_STATS +
                        " (" + JOGGING_STATS_RACE_ID + " INTEGGER,"  +
                        JOGGING_STATS_RUNNER_ID  + " INTEGER," +
                        JOGGING_STATS_TIME + " INTEGER," +
                        JOGGING_STATS_PLACE + " INTEGER);";

        db.execSQL(CREATE_TABLE_BASKETBALL_GAMES);
        db.execSQL(CREATE_TABLE_FOOTBALL_GAMES);
        db.execSQL(CREATE_TABLE_ATHLETES);
        db.execSQL(CREATE_TABLE_BASKETBALL_STATS);
        db.execSQL(CREATE_TABLE_FOOTBALL_STATS);
        db.execSQL(CREATE_TABLE_JOGGING_RACES);
        db.execSQL(CREATE_TABLE_JOGGING_STATS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE_BASKETBALL_GAMES =
                "DROP TABLE IF EXISTS " + TABLE_BASKETBALL_GAMES;
        final String DROP_TABLE_BASKETBALL_PLAYERS =
                "DROP TABLE IF EXISTS " + TABLE_ATHLETES;
        final String DROP_TABLE_BASKETBALL_STATS =
                "DROP TABLE IF EXISTS " + TABLE_BASKETBALL_STATS;
        final String DROP_TABLE_FOOTBALL_STATS =
                "DROP TABLE IF EXISTS " + TABLE_FOOTBALL_STATS;
        final String DROP_TABLE_JOGGING_RACES =
                "DROP TABLE IF EXISTS " + TABLE_JOGGING_RACES;
        final String DROP_TABLE_JOGGING_STATS =
                "DROP TABLE IF EXISTS " + TABLE_JOGGING_STATS;
        final String DROP_TABLE_FOOTBALL_GAMES =
                "DROP TABLE IF EXISTS " + TABLE_FOOTBALL_GAMES;

        db.execSQL(DROP_TABLE_BASKETBALL_GAMES);
        db.execSQL(DROP_TABLE_FOOTBALL_GAMES);
        db.execSQL(DROP_TABLE_BASKETBALL_PLAYERS);
        db.execSQL(DROP_TABLE_BASKETBALL_STATS);
        db.execSQL(DROP_TABLE_FOOTBALL_STATS);
        db.execSQL(DROP_TABLE_JOGGING_RACES);
        db.execSQL(DROP_TABLE_JOGGING_STATS);

        onCreate(db);
    }

    public void addBasketballGame(BasketballGame g) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BASKETBALL_TEAM1, g.getTeam1());
        values.put(BASKETBALL_TEAM2, g.getTeam2());
        values.put(BASKETBALL_RESULT1, g.getResult1());
        values.put(BASKETBALL_RESULT2, g.getResult2());
        values.put(BASKETBALL_DATUM, g.getDatum().getTime());
        values.put(BASKETBALL_WINNER, g.getWinner());
        db.insert(TABLE_BASKETBALL_GAMES, BASKETBALL_TEAM1, values);
        db.close();
    }

    public void addFootballGame(FootballGame g) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FOOTBALL_TEAM1, g.getTeam1());
        values.put(FOOTBALL_TEAM2, g.getTeam2());
        values.put(FOOTBALL_RESULT1, g.getResult1());
        values.put(FOOTBALL_RESULT2, g.getResult2());
        values.put(FOOTBALL_DATUM, g.getDatum().getTime());
        db.insert(TABLE_BASKETBALL_GAMES, BASKETBALL_TEAM1, values);
        db.close();
    }

    public void addAthlete(Athlete p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ATHLETE_NAME, p.getName());
        values.put(ATHLETE_NICKNAME, p.getNickname());
        values.put(ATHLETE_DISCIPLINE, p.getDiscipline());
        db.insert(TABLE_ATHLETES, ATHLETE_NAME, values);
        db.close();
    }

    public void addStats(BasketballStats s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BASKETBALL_STATS_GAME_ID, s.getGameId());
        values.put(BASKETBALL_STATS_PLAYER_ID, s.getPlayerId());
        values.put(BASKETBALL_STATS_POINTS, s.getPoints());
        values.put(BASKETBALL_STATS_ASSISTS, s.getAssists());
        values.put(BASKETBALL_STATS_JUMPS, s.getJumps());
        values.put(BASKETBALL_STATS_TEAM, s.getTeam());
        db.insert(TABLE_BASKETBALL_STATS, BASKETBALL_STATS_GAME_ID, values);
        db.close();
    }

    public ArrayList<BasketballGame> getGames() {
        ArrayList<BasketballGame> basketballGames = new ArrayList<BasketballGame>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_BASKETBALL_GAMES,
                new String[]{BASKETBALL_GAME_ID,
                        BASKETBALL_TEAM1,
                        BASKETBALL_TEAM2,
                        BASKETBALL_RESULT1,
                        BASKETBALL_RESULT2,
                        BASKETBALL_DATUM,
                        BASKETBALL_WINNER
                },
                null, null, null, null, null
        );

        if (c.moveToFirst()) {
            do {
                basketballGames.add(new BasketballGame(c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getInt(4),
                        new Date(c.getLong(5)),
                        c.getString(6)
                ));
            } while (c.moveToNext());
        }
        db.close();
        return basketballGames;
    }

    public ArrayList<BasketballStats> getPlayerStats(long id, boolean playerOrGame)  {
        ArrayList<BasketballStats> stats = new ArrayList<BasketballStats>();
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(id)};

        Cursor c = db.query(TABLE_BASKETBALL_STATS,
                new String[]{BASKETBALL_STATS_GAME_ID,
                        BASKETBALL_STATS_PLAYER_ID,
                        BASKETBALL_STATS_POINTS,
                        BASKETBALL_STATS_ASSISTS,
                        BASKETBALL_STATS_JUMPS,
                        BASKETBALL_STATS_TEAM},
                (playerOrGame ? BASKETBALL_STATS_PLAYER_ID : BASKETBALL_STATS_GAME_ID) + "=?", args,
                null, null, null);

        if (c.moveToFirst()) {

            do {
                stats.add(new BasketballStats(c.getLong(1),
                        c.getLong(0),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4),
                        c.getString(5))
                );
            } while (c.moveToNext());
        }
        db.close();
        return stats;
    }

    public Athlete getPlayer(long playerId) {
        Athlete p = null;
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(playerId)};

        Cursor c = db.query(TABLE_ATHLETES,
                new String[]{ATHLETE_ID,
                        ATHLETE_NAME,
                        ATHLETE_NICKNAME},
                ATHLETE_ID + "=?", args,
                null, null, null);

        if (c.moveToFirst()) {
            p = new Athlete(c.getLong(0), c.getString(1), c.getString(2));
        }
        db.close();
        return p;
    }

    public ArrayList<Athlete> getPlayers() {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_ATHLETES,
                new String[]{ATHLETE_ID,
                        ATHLETE_NAME,
                        ATHLETE_NICKNAME},
                null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                athletes.add(new Athlete(c.getLong(0), c.getString(1), c.getString(2)));
            } while (c.moveToNext());
        }

        return athletes;
    }


    public long getGameID(String team1, String team2, Date datum)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{team1, team2, Long.toString(datum.getTime())};
        Cursor c = db.query(TABLE_BASKETBALL_GAMES,
                new String[]{BASKETBALL_GAME_ID},
                BASKETBALL_TEAM1 + "=? AND " + BASKETBALL_TEAM2 + "=? AND " + BASKETBALL_DATUM + "=?",
                args,
                null, null, null);

        if (c.moveToFirst())
        {
            db.close();
            return c.getLong(0);
        }
        else
        {
            db.close();
            return -1;
        }
    }

    public long getPlayerID(String nickname)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{nickname};
        Cursor c = db.query(TABLE_ATHLETES,
                new String[]{BASKETBALL_GAME_ID},
                ATHLETE_NICKNAME + "=?",
                args,
                null, null, null);

        if (c.moveToFirst()) {
            db.close();
            return c.getLong(0);
        }
        else {
            db.close();
            return -1;
        }
    }

    public boolean deleteBasketballGame(long gameID)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(gameID)};
        if (db.delete(TABLE_BASKETBALL_GAMES, BASKETBALL_GAME_ID + "=?", args) == 0) {
            return false;
        }

        db.delete(TABLE_BASKETBALL_STATS, BASKETBALL_STATS_GAME_ID + "=?", args);
        return true;
    }

    public boolean deleteFoottballGame(long gameID)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(gameID)};
        if (db.delete(TABLE_FOOTBALL_GAMES, FOOTBALL_GAME_ID + "=?", args) == 0) {
            return false;
        }

        db.delete(TABLE_FOOTBALL_STATS, FOOTBALL_STATS_GAME_ID + "=?", args);
        return true;
    }

    public boolean deletePlayer(long playerID)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(playerID)};
        if (db.delete(TABLE_ATHLETES, ATHLETE_ID + "=?", args) == 0) {
            return false;
        }

        db.delete(TABLE_BASKETBALL_STATS, BASKETBALL_STATS_PLAYER_ID + "=?", args);
        return true;
    }

    public boolean deleteStats(long gameID, long playerID)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(gameID), Long.toString(playerID)};
        if (db.delete(TABLE_BASKETBALL_STATS, BASKETBALL_STATS_GAME_ID + "=? AND " + BASKETBALL_STATS_PLAYER_ID + "=?", args) == 0) {
            return false;
        }

        return true;
    }

    public void addJoggingRace (JoggingRace r)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(JOGGING_RACE_NAME, r.getName());
        values.put(JOGGING_RACE_START, r.getStart());
        values.put(JOGGING_RACE_FINISH, r.getFinish());
        values.put(JOGGING_RACE_DISTANCE, r.getDistance());
        db.insert(TABLE_JOGGING_RACES, JOGGING_RACE_NAME, values);
        db.close();
    }

    public boolean deleteBJoggingRace(long raceID)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(raceID)};
        if (db.delete(TABLE_JOGGING_RACES, JOGGING_RACE_ID + "=?", args) == 0) {
            return false;
        }

        db.delete(TABLE_JOGGING_RACES, JOGGING_RACE_ID + "=?", args);
        return true;
    }

    public void addJoggingStats (JoggingStats st)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(JOGGING_STATS_RACE_ID, st.getRaceId());
        values.put(JOGGING_STATS_RUNNER_ID, st.getRunnerId());
        values.put(JOGGING_STATS_TIME, st.getTime());
        values.put(JOGGING_STATS_PLACE, st.getPlace());
        db.insert(TABLE_JOGGING_RACES, JOGGING_RACE_NAME, values);
        db.close();
    }

    public long getFootballGameID(String team1, String team2, Date datum) {

        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{team1, team2, Long.toString(datum.getTime())};
        Cursor c = db.query(TABLE_FOOTBALL_GAMES,
                new String[]{FOOTBALL_GAME_ID},
                FOOTBALL_TEAM1 + "=? AND " + FOOTBALL_TEAM2 + "=? AND " + FOOTBALL_DATUM + "=?",
                args,
                null, null, null);

        if (c.moveToFirst())
        {
            db.close();
            return c.getLong(0);
        }
        else
        {
            db.close();
            return -1;
        }
    }
}



















