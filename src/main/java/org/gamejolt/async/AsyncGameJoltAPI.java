package org.gamejolt.async;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.gamejolt.DataStore;
import org.gamejolt.DataStore.DataStoreType;
import org.gamejolt.GameJoltAPI;
import org.gamejolt.Trophy;

public class AsyncGameJoltAPI {
    private final GameJoltAPI     gjapi;
    private final ExecutorService e;

    public AsyncGameJoltAPI(final GameJoltAPI gjapi) {
        this(Executors.newSingleThreadExecutor(), gjapi);
    }

    public AsyncGameJoltAPI(final ExecutorService e, final GameJoltAPI gjapi) {
        this.e = e;
        this.gjapi = gjapi;
    }

    public AsyncGameJoltAPI(final int gameId, final String privateKey) {
        this(Executors.newSingleThreadExecutor(), gameId, privateKey);
    }

    public AsyncGameJoltAPI(final ExecutorService e, final int gameId,
            final String privateKey) {
        this.gjapi = new GameJoltAPI(gameId, privateKey);
        this.e = e;
    }

    public AsyncGameJoltAPI(final int gameId, final String privateKey,
            final String username, final String userToken) {
        this(Executors.newSingleThreadExecutor(), gameId, privateKey, username,
                userToken);
    }

    public AsyncGameJoltAPI(final ExecutorService e, final int gameId,
            final String privateKey, final String username,
            final String userToken) {
        this.gjapi = new GameJoltAPI(gameId, privateKey, username, userToken);
        this.e = e;
    }

    public Future<Boolean> achieveTrophy(final int trophyId) {
        return this.e.submit(() -> this.gjapi.achieveTrophy(trophyId));
    }

    public Future<Boolean> achieveTrophy(final Trophy t) {
        return this.e.submit(() -> this.gjapi.achieveTrophy(t));
    }

    public Future<Boolean> addHighscore(final String score, final int sort) {
        return this.e.submit(() -> this.gjapi.addHighscore(score, sort));
    }

    public Future<Boolean> addHighscore(final int id, final String score,
            final int sort) {
        return this.e.submit(() -> this.gjapi.addHighscore(id, score, sort));
    }

    public Future<Boolean> addHighscore(final String score, final int sort,
            final String extra) {
        return this.e.submit(() -> this.gjapi.addHighscore(score, sort, extra));
    }

    public Future<Boolean> addHighscore(final String guest_username,
            final String score, final int sort) {
        return this.e.submit(() -> this.gjapi.addHighscore(guest_username,
                score, sort));
    }

    public Future<Boolean> addHighscore(final int id, final String score,
            final int sort, final String extra) {
        return this.e.submit(() -> this.gjapi.addHighscore(id, score, sort,
                extra));
    }

    public Future<Boolean> addHighscore(final int id,
            final String guest_username, final String score, final int sort) {
        return this.e.submit(() -> this.gjapi.addHighscore(id, guest_username,
                score, sort));
    }

    public Future<Boolean> addHighscore(final int id,
            final String guest_username, final String score, final int sort,
            final String extra) {
        return this.e.submit(() -> this.gjapi.addHighscore(id, guest_username,
                score, sort, extra));
    }

    public Future<DataStore> getDataStore(final DataStoreType type,
            final String key) {
        return this.e.submit(() -> this.gjapi.getDataStore(type, key));
    }

    public Future<ArrayList<String>> getDataStoreKeys(final DataStoreType type) {
        return this.e.submit(() -> this.gjapi.getDataStoreKeys(type));
    }

    public Future<ArrayList<DataStore>> getDataStoreObjects(
            final DataStoreType type) {
        return this.e.submit(() -> this.gjapi.getDataStoreObjects(type));
    }

    public Future<Integer> getHighscoreRank(final int score) {
        return this.e.submit(() -> this.gjapi.getHighscoreRank(score));
    }

    public Future<Integer> getHighscoreRank(final int score, final int id) {
        return this.e.submit(() -> this.gjapi.getHighscoreRank(score, id));
    }
}
