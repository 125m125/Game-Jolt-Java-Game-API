package org.gamejolt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameJoltAPI_JSON {
	private static final String BASE = "http://gamejolt.com/api/game/v1/";
	
	private final int gameId;
	private final String privateKey;
	
	private String username;
	private String token;
	private boolean verified;
	
	
	private boolean verbose=false;

	/**
	 * Create a new GameJoltAPI object without trying to verify the user.
	 * 
	 * This method will try to read the Game Jolt Quick Play file (gjapi-credentials.txt).
	 * If that file exists, then it will attempt to verify the user.
	 * 
	 * @param gameId Your Game's Unique ID.
	 * @param privateKey Your Game's Unique (Private) Key.
	 */
	public GameJoltAPI_JSON(int gameId, String privateKey) 
	{
		this.gameId = gameId;
		this.privateKey = privateKey;
		File f = new File("gjapi-credentials.txt");
		if (f.exists()) {
			try(Scanner sc = new Scanner(f)) {
				this.username = sc.nextLine();
				this.token = sc.nextLine();		
			} catch(FileNotFoundException exc) {
			} catch(NoSuchElementException exc) {
			}
		}
	}
	
	/**
	 * Create a new GameJoltAPI and tries to verify the user.
	 * @param gameId Your Game's Unique ID.
	 * @param privateKey Your Game's Unique (Private) Key.
	 */
	public GameJoltAPI_JSON(int gameId, String privateKey, String username, String userToken) 
	{
		this.gameId = gameId;
		this.privateKey = privateKey;
		this.verifyUser(username, userToken);
	}
	

	/**
	 * Attempt to verify the Players Credentials.
	 * @param username The Player's Username.
	 * @param userToken The Player's User Token.
	 * @return true if the User was successfully verified, false otherwise.
	 */
	public boolean verifyUser(String username, String userToken)
	{
		this.verified = false;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("user_token", userToken);
    	String response = this.request("users/auth/", params, false);
    	if (verbose) { System.out.println(response); }
    	String[] lines = response.split("\n");
    	for (String line : lines) {
    		try {
    			if (line.split(":")[1].substring(1, line.split(":")[1].length()-2).equals("true")) {
	    			this.username = username;
	    			this.token = userToken;
	    			this.verified = true;
	    			return true;
	    		}
    		} catch (ArrayIndexOutOfBoundsException e) {
    			if (verbose) { System.err.println("GameJoltAPI: Could not verify user because the response from GJ was invalid."); }
    			return false;
    		}
    	}

    	return false;
	}
	
	
	
	private void request(String operation, HashMap<String,String> params, boolean requiresAuth){
		if (requiresAuth){
			if (!verified){
				if (requiresAuth && !this.verified) {
					return "REQUIRES_AUTHENTICATION";
				}
			}
		}
	}
}
