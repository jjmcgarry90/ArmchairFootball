package org.gtri.objects;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {

	private String name;
	private ArrayList<User> members;

	public Group(String name) {
		this.name = name;
		this.members = new ArrayList<User>();
	}

	public void addMember(User user) {
		members.add(user);
	}
	
	public void removeMember(User user) {
		members.remove(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User> getMembers() {
		return members;
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
	public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
		public Group createFromParcel(Parcel in) {
			return new Group(in);
		}

		public Group[] newArray(int size) {
			return new Group[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private Group(Parcel in) {
		name = in.readString();
		// members = (ArrayList<User>)
		// in.readValue(ArrayList.class.getClassLoader());
	}

	public void writeToParcel(Parcel out, int flags) {
		// Write the object's data to the passed-in Parcel
		out.writeString(name);
		// out.writeValue(members);
	}

}
