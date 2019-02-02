package assignment4;

import java.util.ArrayList;

public class MusicStore {
	//Osman Warsi COMP 250
    //ADD YOUR CODE BELOW HERE
    private MyHashTable<String, Song> TitleList = new MyHashTable<String, Song>(100);
    private MyHashTable<String, ArrayList<Song>> ArtistList = new MyHashTable<String, ArrayList<Song>>(100);
    private MyHashTable<Integer, ArrayList<Song>> YearList = new MyHashTable<Integer, ArrayList<Song>>(100);
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	for( Song currentsong: songs) {
    		TitleList.put(currentsong.getTitle(), currentsong);
    	}
         
    	for( Song currentsong: songs) {
    		//loopong through the songs list
    		String artist = currentsong.getArtist();
    		//get the artist and check if its null or the size is 0
    		if(ArtistList.get(artist)==null || ArtistList.get(artist).size()==0 ) {
    			ArrayList<Song> songbucket = new ArrayList<Song>();
    			songbucket.add(currentsong);
    //add the current song in the slot of array
    			ArtistList.put(artist, songbucket);
    		}else {
    			//if not null then add the currentsong in the artist
    			ArtistList.get(artist).add(currentsong);
    		}
    	
    	}
    	//looping through the songs
    	for(Song currentsong: songs) {
    		int year= currentsong.getYear();
    		//get the current year
    		if(YearList.get(year)==null || YearList.get(year).size()==0 ) {
    			ArrayList<Song> songArray = new ArrayList<Song>();
    			songArray.add(currentsong);
    			//adding the current song in the array list
    			YearList.put(year, songArray);
    		}else {
    			YearList.get(year).add(currentsong);
    		}
    	}
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
        this.TitleList.put(s.getTitle(), s);
        
        String artist = s.getArtist();
        //get the artist of s object
        if (this.ArtistList.get(artist)==null) {
        	//if the artist is empty then make a new ArrayList of Songs and add s
        	ArrayList<Song> arrOfSongs =  new ArrayList<Song>();
        	arrOfSongs.add(s);
        	this.ArtistList.put(artist, arrOfSongs);
        } else {
        	//then add s in the artist
        	this.ArtistList.get(artist).add(s);
        }
        
        
        //year of the song
        Integer year = Integer.valueOf(s.getYear());
        if (this.YearList.get(year) == null) {
        	
        	//if the year is empty then
        	ArrayList<Song> arrOfSongs = new ArrayList<Song>();
        	arrOfSongs.add(s);
        	//add s object in the year Array list
        	this.YearList.put(year, arrOfSongs);
        } else {
        	this.YearList.get(year).add(s);
        }
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	
    	
    	return this.TitleList.get(title);
        
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
        return ArtistList.get(artist);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
        return YearList.get(year);
        //ADD CODE ABOVE HERE
        
    }
}
