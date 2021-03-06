package org.gtri.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	private String email, name;
	private LoginType loginType;

	private ArrayList<Huddle> listHuddles;
	private ArrayList<User> listFriends;
	private ArrayList<Group> listGroups;
	private ArrayList<Participant> listWatched; // 'watched' teams
	private Participant favoriteTeam; // the true one-favorite
	private Bitmap profilePic;

	public enum LoginType {
		FacebookLogin, EmailLogin
	}

	/*
	 * Facebook constructor
	 */
	public User() {
		this.email = null;
		this.name = null;
		this.loginType = LoginType.FacebookLogin;
		setup();
	}

	/*
	 * Facebook friend constructor
	 */
	public User(String name) {
		this.email = null;
		this.name = name;
		this.loginType = LoginType.FacebookLogin;
		setup();
	}

	/*
	 * Email/Username constructor
	 */
	public User(String email, String username) {
		this.email = email;
		this.name = username;
		this.loginType = LoginType.EmailLogin;
		setup();
	}

	private void setup() {
		favoriteTeam = null;
		listHuddles = new ArrayList<Huddle>();
		listFriends = new ArrayList<User>();
		listGroups = new ArrayList<Group>();
		listWatched = new ArrayList<Participant>();
	}
	
	public Object checkCommunities(String name) {
		Object retv = null;
		// favorite
		if (favoriteTeam.getName().equals(name)) {
			return favoriteTeam;
		}
		// watched
		for (Participant participant : listWatched) {
			if (participant.getName().equals(name)) {
				return participant;
			}
		}
		// huddles
		for (Huddle huddle : listHuddles) {
			if (huddle.getName().equals(name)) {
				return huddle;
			}
		}
		return retv;
	}

	public Group getGroupByName(String name) {
		for (Group group : listGroups) {
			if (group.getName().equals(name)) {
				return group;
			}
		}
		return new Group(name);
	}

	public Huddle getHuddleByName(String name) {
		for (Huddle huddle : listHuddles) {
			if (huddle.getName().equals(name)) {
				return huddle;
			}
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Participant getFavorite() {
		return favoriteTeam;
	}

	public void setFavorite(Participant favorite) {
		this.favoriteTeam = favorite;
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	/*
	 * ArrayList manipulation from here onwards
	 */

	public ArrayList<Huddle> getHuddles() {
		return listHuddles;
	}

	public boolean getHuddleByEvent(Event event) {
		for (Huddle huddle : listHuddles) {
			if (huddle.getEvent().equals(event)) {
				return true;
			}
		}
		return false;
	}

	public void removeHuddleByEvent(Event event) {
		for (Huddle huddle : listHuddles) {
			if (huddle.getEvent().equals(event)) {
				listHuddles.remove(event);
			}
		}
	}

	public void addHuddle(Huddle huddle) {
		if (checkHuddles(huddle) == false) {
			listHuddles.add(huddle);
		}
	}

	public boolean checkHuddles(Huddle newHuddle) {
		for (Huddle huddle : listHuddles) {
			if (huddle.getName().equals(newHuddle.getName())) {
				return true;
			}
		}
		return false;
	}

	public void removeHuddle(Huddle huddle) {
		listHuddles.remove(huddle);
	}

	public ArrayList<User> getFriendsList() {
		return listFriends;
	}

	public void addFriend(User friend) {
		if (checkFriends(friend) == false) {
			listFriends.add(friend);
			// Sort after adding
			Collections.sort(listFriends, new FriendComparator());
		}
	}

	public void addFriend(ArrayList<User> friends) {
		for (User friend : friends) {
			if (checkFriends(friend) == false) {
				listFriends.add(friend);
			}
		}
		// Sort after adding
		Collections.sort(listFriends, new FriendComparator());
	}

	public boolean hasFriend(String name) {
		for (User friend : listFriends) {
			if (name.equals(friend.getName())) {
				return true;
			}
		}
		return false;
	}

	public User getFriend(String name) {
		for (User friend : listFriends) {
			if (name.equals(friend.getName())) {
				return friend;
			}
		}
		return null;
	}

	public boolean checkFriends(User newFriend) {
		for (User Friend : listFriends) {
			if (Friend.getName().equals(newFriend.getName())) {
				return true;
			}
		}
		return false;
	}

	public void removeFriend(User user) {
		listFriends.remove(user);
	}

	public Bitmap getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Bitmap profilePic) {
		this.profilePic = profilePic;
	}

	public ArrayList<Participant> getWatched() {
		return listWatched;
	}

	public void setWatched(ArrayList<Participant> watched) {
		this.listWatched = watched;
	}
	
	public void addWatched(Participant watched) {
		if (checkWatched(watched) == false) {
			listWatched.add(watched);
		}
	}

	public boolean checkWatched(Participant newWatched) {
		for (Participant watched : listWatched) {
			if (watched.getName().equals(newWatched.getName())) {
				return true;
			}
		}
		return false;
	}

	public void removeWatched(Participant participant) {
		listWatched.remove(participant);
	}

	public ArrayList<Group> getGroups() {
		return listGroups;
	}

	public void addGroup(Group Group) {
		if (checkGroups(Group) == false) {
			listGroups.add(Group);
		}
	}

	public boolean checkGroups(Group newGroup) {
		for (Group Group : listGroups) {
			if (Group.getName().equals(newGroup.getName())) {
				return true;
			}
		}
		return false;
	}

	public void removeGroup(Group group) {
		listGroups.remove(group);
	}
	
	public boolean hasGroup(String name) {
		for (Group group : listGroups) {
			if (name.equals(group.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEventForWatched(Event event) {
		String name1 = event.getParticipants().get(0).getName();
		String name2 = event.getParticipants().get(1).getName();
		for (Participant participant : listWatched) {
			if (participant.getName().equals(name1) || participant.getName().equals(name2)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEventForFavorite(Event event) {
		String name1 = event.getParticipants().get(0).getName();
		String name2 = event.getParticipants().get(1).getName();
		if (favoriteTeam.getName().equals(name1) || favoriteTeam.getName().equals(name2)) {
			return true;
		}
		return false;
	}

	/*
	 * Override toString method
	 */
	public String toString() {
		return name;
	}

	/*
	 * Parcelable code from here onwards
	 * --------------------------------------------------------------
	 */

	public int describeContents() {
		// Can safely ignore
		return 0;
	}

	// this is used to regenerate your object. All Parcelables must have a
	// CREATOR that implements these two methods
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};

	/*
	 * For the constructor and the writeToParcel, the objects must be in the
	 * same order - FIFO
	 */
	private User(Parcel in) {
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

	public class FriendComparator implements Comparator<User> {
		public int compare(User friend1, User friend2) {
			return friend1.getName().compareTo(friend2.getName());
		}
	}



}
