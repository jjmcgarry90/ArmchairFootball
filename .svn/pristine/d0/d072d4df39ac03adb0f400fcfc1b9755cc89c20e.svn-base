package org.gtri.objects;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Participant implements Parcelable {
	private int id, conferenceId;
	private String name, mascot, abbreviation, city, state, stadium, conference;
	private ArrayList<Athlete> roster;

	/*
	 * A team in a sporting event
	 */
	public Participant(int id, String name, String mascot, String abbreviation, int conferenceId, String conference, String city, String state, String stadium, ArrayList<Athlete> roster) {
		this.id = id;
		this.name = name;
		this.mascot = mascot;
		this.abbreviation = abbreviation;
		this.city = city;
		this.state = state;
		this.stadium = stadium;
		this.roster = roster;
		this.conferenceId = conferenceId;
		this.conference = conference;
	}

	/*
	 * Name and mascot name
	 */
	public String getFullName() {
		return name + " " + mascot;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMascot() {
		return mascot;
	}

	public void setMascot(String mascot) {
		this.mascot = mascot;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public ArrayList<Athlete> getRoster() {
		return roster;
	}

	public void setRoster(ArrayList<Athlete> roster) {
		this.roster = roster;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	/*
	 * Override toString method
	 */
	public String toString() {
		return name;
	}

	/*
	 * Parcelable code from here onwards
	 */

	public int describeContents() {
		// Can safely ignore
		return 0;
	}

	// this is used to regenerate your object. All Parcelables must have a
	// CREATOR that implements these two methods
	public static final Parcelable.Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
		public Participant createFromParcel(Parcel in) {
			return new Participant(in);
		}

		public Participant[] newArray(int size) {
			return new Participant[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private Participant(Parcel in) {
		name = in.readString();
	}

	public void writeToParcel(Parcel out, int flags) {
		// Write the object's data to the passed-in Parcel
		out.writeString(name);
	}

}
