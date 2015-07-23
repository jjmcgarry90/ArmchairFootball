package org.gtri.objects;

import java.util.ArrayList;

import org.gtri.helper.EspnApiHandler;
import org.gtri.helper.ScheduleHelper;

/*
 * A particular day's lineup of events and games
 * i.e. That Saturday's matchups
 */
public class Schedule {

	private ArrayList<Event> events;
	private ArrayList<Participant> participants;

	public Schedule(EspnApiHandler handler) {
		this.events = new ScheduleHelper(handler).getEvents();
		this.participants = new ScheduleHelper(handler).getParticipants();
	}
	
	public Schedule(ArrayList<Event> events, ArrayList<Participant> participants) {
		this.events = events;
		this.participants = participants;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}
	
	public Participant getParticipantByName(String name) {
		for (Participant team : participants) {
			if (team.getName().equals(name)) {
				return team;
			}
		}
		return null;
	}
	
	public boolean hasParticipant(String name) {
		for (Participant team : participants) {
			if (name.equals(team.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public Participant getParticipant(String name) {
		for (Participant team : participants) {
			if (name.equals(team.getName())) {
				return team;
			}
		}
		return null;
	}

}
