package com.example.rivios_.sportapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 27.5.2016..
 */
public class GameDBHelper extends SQLiteOpenHelper {

    private static final String DTATBASE_NAME = "sportStatsDB";
    private static final int SCHEMA = 2;

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

    static final String TABLE_BASKETBALL_PLAYERS = "basketball_players";
    static final String BASKETBALL_PLAYER_ID = "id";
    static final String BASKETBALL_PLAYER_NAME = "name";
    static final String BASKETBALL_PLAYER_NICKNAME = "nickname";

    static final String TABLE_BASKETBALL_STATS = "basketball_stats";
    static final String BASKETBALL_STATS_GAME_ID = "game_id";
    static final String BASKETBALL_STATS_PLAYER_ID = "player_id";
    static final String BASKETBALL_STATS_POINTS = "points";
    static final String BASKETBALL_STATS_ASSISTS = "assists";
    static final String BASKETBALL_STATS_JUMPS = "jumps";

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_BASKETBALL_GAMES =
                "CREATE TABLE " + TABLE_BASKETBALL_GAMES +
                        " (" + BASKETBALL_GAME_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY," +
                        BASKETBALL_TEAM1 + " TEXT," +
                        BASKETBALL_TEAM2 + " TEXT," +
                        BASKETBALL_RESULT1 + " INTEGER," +
                        BASKETBALL_RESULT2 + " INTEGER," +
                        BASKETBALL_DATUM + " INTEGER," +
                        BASKETBALL_WINNER + " TEXT);";

        final String CREATE_TABLE_BASKETBALL_PLAYERS =
                "CREATE TABLE " + TABLE_BASKETBALL_PLAYERS +
                        " (" + BASKETBALL_PLAYER_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY," +
                        BASKETBALL_PLAYER_NAME + " TEXT," +
                        BASKETBALL_PLAYER_NICKNAME + " TEXT);";

        final String CREATE_TABLE_BASKETBALL_STATS =
                "CREATE TABLE " + TABLE_BASKETBALL_STATS +
                        " (" + BASKETBALL_STATS_GAME_ID + " INTEGER," +
                        BASKETBALL_STATS_PLAYER_ID + " INTEGER," +
                        BASKETBALL_STATS_POINTS + " INTEGER," +
                        BASKETBALL_STATS_ASSISTS + " INTEGER," +
                        BASKETBALL_STATS_JUMPS + " INTEGER," +
                        "CONSTRAINT pk PRIMARY KEY(" + BASKETBALL_STATS_GAME_ID + "," + BASKETBALL_STATS_PLAYER_ID + "));";

        db.execSQL(CREATE_TABLE_BASKETBALL_GAMES);
        db.execSQL(CREATE_TABLE_BASKETBALL_PLAYERS);
        db.execSQL(CREATE_TABLE_BASKETBALL_STATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE_BASKETBALL_GAMES =
                "DROP TABLE IF EXISTS " + TABLE_BASKETBALL_GAMES;
        final String DROP_TABLE_BASKETBALL_PLAYERS =
                "DROP TABLE IF EXISTS " + TABLE_BASKETBALL_PLAYERS;
        final String DROP_TABLE_BASKETBALL_STATS =
                "DROP TABLE IF EXISTS " + TABLE_BASKETBALL_STATS;

        db.execSQL(DROP_TABLE_BASKETBALL_GAMES);
        db.execSQL(DROP_TABLE_BASKETBALL_PLAYERS);
        db.execSQL(DROP_TABLE_BASKETBALL_STATS);

        onCreate(db);
    }

    public void addGame(Game g) {
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

    public void addPlayer(Player p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BASKETBALL_PLAYER_NAME, p.getName());
        values.put(BASKETBALL_PLAYER_NICKNAME, p.getNickname());
        db.insert(TABLE_BASKETBALL_PLAYERS, BASKETBALL_PLAYER_NAME, values);
        db.close();
    }

    public void addStats(Stats s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BASKETBALL_PLAYER_ID, s.getPlayerId());
        values.put(BASKETBALL_GAME_ID, s.getGameId());
        values.put(BASKETBALL_STATS_POINTS, s.getPoints());
        values.put(BASKETBALL_STATS_ASSISTS, s.getAssists());
        values.put(BASKETBALL_STATS_JUMPS, s.getJumps());
        db.insert(TABLE_BASKETBALL_PLAYERS, BASKETBALL_STATS_ASSISTS, values);
        db.close();
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<Game>();
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
                games.add(new Game(c.getLong(0),
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
        return games;
    }

    public ArrayList<Stats> getPlayerStats(long gameId) {
        ArrayList<Stats> stats = new ArrayList<Stats>();
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(gameId)};

        Cursor c = db.query(TABLE_BASKETBALL_STATS,
                new String[]{BASKETBALL_STATS_GAME_ID,
                        BASKETBALL_STATS_PLAYER_ID,
                        BASKETBALL_STATS_POINTS,
                        BASKETBALL_STATS_ASSISTS,
                        BASKETBALL_STATS_JUMPS},
                BASKETBALL_STATS_GAME_ID + "=?", args,
                null, null, null);

        if (c.moveToFirst()) {
            do {
                stats.add(new Stats(c.getLong(1),
                        c.getLong(0),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4))
                );
            } while (c.moveToNext());
        }
        db.close();
        return stats;
    }

    public Player getPlayer(long playerId) {
        Player p = null;
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{Long.toString(playerId)};

        Cursor c = db.query(TABLE_BASKETBALL_PLAYERS,
                new String[]{BASKETBALL_PLAYER_ID,
                        BASKETBALL_PLAYER_NAME,
                        BASKETBALL_PLAYER_NICKNAME},
                BASKETBALL_PLAYER_ID + "=?", args,
                null, null, null);

        if (c.moveToFirst()) {
            p = new Player(c.getLong(0), c.getString(1), c.getString(2));
        }
        db.close();
        return p;
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
            return c.getLong(0);
        }
        else
        {
            return -1;
        }
    }

    public long getPlayerID(String nickname)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] args = new String[]{nickname};
        Cursor c = db.query(TABLE_BASKETBALL_PLAYERS,
                new String[]{BASKETBALL_GAME_ID},
                BASKETBALL_PLAYER_NICKNAME + "=?",
                args,
                null, null, null);

        if (c.moveToFirst()) {
            return c.getLong(0);
        }
        else {
            return -1;
        }
    }
}



















