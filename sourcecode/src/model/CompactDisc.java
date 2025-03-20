package model;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>(); 
	private static int nbCompactDiscs = 0;
	
	public CompactDisc(String title, String category, double cost, int length, String director, String artist, ArrayList<Track> tracks) {
		super("CD" + (++nbCompactDiscs), title, category, cost, length, director);
		this.artist = artist;
		this.tracks = tracks;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void addTrack(Track track) {
		if (!tracks.contains(track)) {
			tracks.add(track);
		}
	}
	
	public void removeTrack(Track track) {
		if (tracks.contains(track)) {
			tracks.remove(track);
		}
	}
	
	@Override 
	public String toString() {
		return super.toString() +
				"ARTIST: " + this.artist + "\n" +
				"TRACKS: " + this.tracks.toString() + "\n";
	}
	
	@Override 
	public void play() {
		System.out.println("🎵 CD '" + this.getTitle() + "' is playing 🎵");
	}
}
