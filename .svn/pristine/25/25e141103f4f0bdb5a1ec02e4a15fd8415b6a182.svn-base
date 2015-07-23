package org.gtri.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

public class Huddle implements Parcelable {

	private ArrayList<User> members;
	private Event event;
	private String name;
	private User founder;
	
	/*
	 * A group of users interacting for a particular event
	 */	
	public Huddle(Event event, User founder) {
		this.event = event;
		this.name = event.getName();
		this.members = new ArrayList<User>();
		this.founder = founder;
	}
	
	public User getFounder() {
		return founder;
	}

	public void setFounder(User founder) {
		this.founder = founder;
	}

	public void addMember(User member) {
		if (checkMembers(member) == false) {
			members.add(member);
			// Sort after adding
			Collections.sort(members, new MemberComparator());
		}
	}

	public void addMembers(ArrayList<User> members) {
		for (User member : members) {
			if (checkMembers(member) == false) {
				members.add(member);
			}
		}
		// Sort after adding
		Collections.sort(members, new MemberComparator());
	}

	public boolean checkMembers(User newMember) {
		for (User member : members) {
			if (member.getName().equals(newMember.getName())) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<User> getMembers() {
		return members;
	}
	
	public void removeMember(User user) {
		members.remove(user);
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getName() {
		return name;
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
	public static final Parcelable.Creator<Huddle> CREATOR = new Parcelable.Creator<Huddle>() {
		public Huddle createFromParcel(Parcel in) {
			return new Huddle(in);
		}

		public Huddle[] newArray(int size) {
			return new Huddle[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private Huddle(Parcel in) {
		name = in.readString();
	}

	public void writeToParcel(Parcel out, int flags) {
		// Write the object's data to the passed-in Parcel
		out.writeString(name);
	}
	
	/*
	 * Comparators
	 * --------------------------------------------------------------
	 */
	public class MemberComparator implements Comparator<User> {
		public int compare(User member1, User member2) {
			return member1.getName().compareTo(member2.getName());
		}
	}
	
}
