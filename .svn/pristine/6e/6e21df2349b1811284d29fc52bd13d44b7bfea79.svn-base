package org.gtri.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Athlete implements Parcelable {

	private String name;
	
	public Athlete(String name) {
		this.name = name;
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
	public static final Parcelable.Creator<Athlete> CREATOR = new Parcelable.Creator<Athlete>() {
		public Athlete createFromParcel(Parcel in) {
			return new Athlete(in);
		}

		public Athlete[] newArray(int size) {
			return new Athlete[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private Athlete(Parcel in) {
		name = in.readString();
	}

	public void writeToParcel(Parcel out, int flags) {
		// Write the object's data to the passed-in Parcel
		out.writeString(name);
	}
	
}
