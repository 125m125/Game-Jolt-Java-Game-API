package org.gamejolt.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.gamejolt.DataStore;
import org.gamejolt.DataStore.DataStoreOperation;
import org.gamejolt.DataStore.DataStoreType;
import org.gamejolt.GameJoltAPI;
import org.gamejolt.GameJoltAPI.Format;
import org.gamejolt.Highscore;
import org.gamejolt.HighscoreTable;
import org.gamejolt.ServerTime;
import org.gamejolt.Trophy;
import org.gamejolt.Trophy.Achieved;
import org.gamejolt.User;

/**
 * this class performs Operations that requests data from the GameJoltServers
 * asynchronously
 *
 * @see GameJoltAPI
 */
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

    public Future<List<Highscore>> getHighscores() {
        return this.e.submit(() -> this.gjapi.getHighscores());
    }

    public Future<List<Highscore>> getHighscores(final boolean all) {
        return this.e.submit(() -> this.gjapi.getHighscores(all));
    }

    public Future<List<Highscore>> getHighscores(final int id) {
        return this.e.submit(() -> this.gjapi.getHighscores(id));
    }

    public Future<List<Highscore>> getHighscores(final boolean all,
            final int limit) {
        return this.e.submit(() -> this.gjapi.getHighscores(all, limit));
    }

    public Future<List<Highscore>> getHighscores(final int id, final boolean all) {
        return this.e.submit(() -> this.gjapi.getHighscores(id, all));
    }

    public Future<List<Highscore>> getHighscores(final int id,
            final boolean all, final int limit) {
        return this.e.submit(() -> this.gjapi.getHighscores(id, all, limit));
    }

    public Future<ArrayList<HighscoreTable>> getHighscoreTables() {
        return this.e.submit(() -> this.gjapi.getHighscoreTables());
    }

    public Future<User> getQuickplayUser() {
        return this.e.submit(() -> this.gjapi.getQuickplayUser());
    }

    public User getQuickplayUserCredientals() {
        return this.gjapi.getQuickplayUserCredientals();
    }

    public Future<ServerTime> getServerTime() {
        return this.e.submit(() -> this.gjapi.getServerTime());
    }

    public Future<ArrayList<Trophy>> getTrophies() {
        return this.e.submit(() -> this.gjapi.getTrophies());
    }

    public Future<ArrayList<Trophy>> getTrophies(final Achieved a) {
        return this.e.submit(() -> this.gjapi.getTrophies(a));
    }

    public Future<Trophy> getTrophy(final int trophyId) {
        return this.e.submit(() -> this.gjapi.getTrophy(trophyId));
    }

    public Future<User> getUser(final int id) {
        return this.e.submit(() -> this.gjapi.getUser(id));
    }

    public Future<User> getUser(final String name) {
        return this.e.submit(() -> this.gjapi.getUser(name));
    }

    public Future<User> getVerifiedUser() {
        return this.e.submit(() -> this.gjapi.getVerifiedUser());
    }

    public String getVersion() {
        return this.gjapi.getVersion();
    }

    public boolean hasQuickplay() {
        return this.gjapi.hasQuickplay();
    }

    public boolean isVerbose() {
        return this.gjapi.isVerbose();
    }

    public boolean isVerified() {
        return this.gjapi.isVerified();
    }

    public String MD5(final String input) {
        return this.gjapi.MD5(input);
    }

    public Future<String> openURLAndGetResponse(final String urlString) {
        return this.e.submit(() -> this.gjapi.openURLAndGetResponse(urlString));
    }

    public Future<String> openURLAndGetResponseUsingPost(
            final String urlString, final HashMap<String, String> postParams) {
        return this.e.submit(() -> this.gjapi.openURLAndGetResponseUsingPost(
                urlString, postParams));
    }

    public void reloadQuickplay() {
        this.gjapi.reloadQuickplay();
    }

    public Future<Boolean> removeDataStore(final DataStoreType type,
            final String key) {
        return this.e.submit(() -> this.gjapi.removeDataStore(type, key));
    }

    public Future<String> request(final String method,
            final HashMap<String, String> params) {
        return this.e.submit(() -> this.gjapi.request(method, params));
    }

    public Future<String> request(final String method, final String paramsLine) {
        return this.e.submit(() -> this.gjapi.request(method, paramsLine));
    }

    public Future<String> request(final String method, final String paramsLine,
            final boolean requireVerified) {
        return this.e.submit(() -> this.gjapi.request(method, paramsLine,
                requireVerified));
    }

    public Future<Boolean> sessionCheck() {
        return this.e.submit(() -> this.gjapi.sessionCheck());
    }

    public Future<Boolean> sessionClose() {
        return this.e.submit(() -> this.gjapi.sessionClose());
    }

    public Future<Boolean> sessionOpen() {
        return this.e.submit(() -> this.gjapi.sessionOpen());
    }

    public Future<Boolean> sessionUpdate() {
        return this.e.submit(() -> this.gjapi.sessionUpdate());
    }

    public Future<Boolean> sessionUpdate(final boolean active) {
        return this.e.submit(() -> this.gjapi.sessionUpdate(active));
    }

    public Future<DataStore> setDataStore(final DataStoreType type,
            final String key, final String data) {
        return this.e.submit(() -> this.gjapi.setDataStore(type, key, data));
    }

    public void setFormat(final Format format) {
        this.gjapi.setFormat(format);
    }

    public void setVerbose(final boolean b) {
        this.gjapi.setVerbose(b);
    }

    public void setVersion(final String version) {
        this.gjapi.setVersion(version);
    }

    public Future<DataStore> updateDataStore(final DataStoreType type,
            final String key, final DataStoreOperation operation,
            final int value) {
        return this.e.submit(() -> this.gjapi.updateDataStore(type, key,
                operation, value));
    }

    public Future<DataStore> updateDataStore(final DataStoreType type,
            final String key, final DataStoreOperation operation,
            final String value) {
        return this.e.submit(() -> this.gjapi.updateDataStore(type, key,
                operation, value));
    }

    public Future<Boolean> verifyUser(final String username,
            final String userToken) {
        return this.e.submit(() -> this.gjapi.verifyUser(username, userToken));
    }
}
