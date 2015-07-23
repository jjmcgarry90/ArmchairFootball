package org.gtri.objects;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * A sporting event
 * The first team is assumed to be the home team unless the neutral variable is set true
 * Then the game is a neutral site game - such as a bowl, playoff, etc.
 */
public class Event implements Parcelable {

	private String name, abbreviation;
	private ArrayList<Participant> participants;
	private String date;
	private Boolean neutral;

	/*
	 * Null constructor for later construction via methods
	 */
	public Event() {
		name = null;
		abbreviation = null;
		date = null;
		neutral = null;
		participants = new ArrayList<Participant>();
	}
	
	/*
	 * A sporting event
	 * Alabama at Georgia
	 * First team is away team
	 */
	public Event(ArrayList<Participant> participants) {
		this.participants = participants;
		this.neutral = false;
		this.name = createName();
		this.abbreviation = createAbbreviation();
	}

	/*
	 * A sporting event
	 * Alabama at Georgia or could be Alabama vs Georgia
	 */
	public Event(ArrayList<Participant> participants, boolean neutral) {
		this.participants = participants;
		this.neutral = neutral;
		this.name = createName();
		this.abbreviation = createAbbreviation();
	}

	public String createName() {
		String temp = "";
		temp += participants.get(0).getName();
		if (neutral == true) {
			temp += " vs. ";
		} else {
			temp += " at ";
		}
		temp += participants.get(1).getName();
		return temp;
	}
	
	public String getFormattedName() {
		String temp = "";
		temp += participants.get(0).getName();
		if (neutral == true) {
			temp += "\nvs.\n";
		} else {
			temp += "\nat\n";
		}
		temp += participants.get(1).getName();
		return temp;
	}
	
	public String createAbbreviation() {
		String temp = "";
		temp += participants.get(0).getAbbreviation();
		if (neutral == true) {
			temp += " vs. ";
		} else {
			temp += " at ";
		}
		temp += participants.get(1).getAbbreviation();
		return temp;
	}

	/*
	 * A sporting event
	 * Alabama vs Georgia with a special name (Chick-fil-a Bowl)
	 */
	public Event(String name, ArrayList<Participant> participants) {
		this.name = name;
		this.participants = participants;
		this.neutral = true;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Boolean getNeutral() {
		return neutral;
	}

	public void setNeutral(Boolean neutral) {
		this.neutral = neutral;
	}

	public void setName(String name) {
		this.name = name;
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
	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private Event(Parcel in) {
		name = in.readString();
		// participants = (ArrayList<Participant>)
		// in.readValue(ArrayList.class.getClassLoader());
	}

	public void writeToParcel(Parcel out, int flags) {
		// Write the object's data to the passed-in Parcel
		out.writeString(name);
		// out.writeValue(participants);
	}

}
