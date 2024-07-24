import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Assessment {
	
	
	  int capacity;    // Maximum number of songs per user
	  Map<String, LinkedHashMap<String, Integer>> userSongsMap; // User to songs map

	    public Assessment(int capacity)
	    {
	        this.capacity = capacity;
	        this.userSongsMap = new HashMap<>();
	    }

	    // Method to play a song for a user
	    public void playSong(String user, String song) {
	        // Get or create the user's song list
	        LinkedHashMap<String, Integer> songs = userSongsMap.getOrDefault(user, new LinkedHashMap<>());

	        // If the song is already in the list, remove it to reinsert it later
	        if (songs.containsKey(song)) {
	            songs.remove(song);
	        } else if (songs.size() >= capacity) {
	            // If capacity is reached, remove the least recently played song
	            Iterator<Map.Entry<String, Integer>> iterator = songs.entrySet().iterator();
	            if (iterator.hasNext()) {
	                iterator.next();
	                iterator.remove();
	            }
	        }

	        // Add the song to the user's song list
	        songs.put(song, 0); 
	        userSongsMap.put(user, songs);
	    }

	    // Method to fetch recently played songs for a user
	    public List<String> getRecentlyPlayedSongs(String user) {
	        LinkedHashMap<String, Integer> songs = userSongsMap.get(user);
	        return songs != null ? new ArrayList<>(songs.keySet()) : Collections.emptyList();
	    }

	    public static void main(String[] args) {
	    	Assessment rps = new Assessment(3);

	        rps.playSong("user1", "S1");
	        rps.playSong("user1", "S2");
	        rps.playSong("user1", "S3");
	        System.out.println("Recently played songs for user1: " + rps.getRecentlyPlayedSongs("user1"));

	        rps.playSong("user1", "S4");
	        System.out.println("Recently played songs for user1 after playing S4: " + rps.getRecentlyPlayedSongs("user1"));

	        rps.playSong("user1", "S2");
	        System.out.println("Recently played songs for user1 after playing S2 again: " + rps.getRecentlyPlayedSongs("user1"));

	        rps.playSong("user1", "S1");
	        System.out.println("Recently played songs for user1 after playing S1: " + rps.getRecentlyPlayedSongs("user1"));
	    }
	}


