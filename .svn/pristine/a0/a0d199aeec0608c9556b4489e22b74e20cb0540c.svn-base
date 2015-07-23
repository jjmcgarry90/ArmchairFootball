package org.gtri.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.gtri.objects.Event;
import org.gtri.objects.Participant;

public class ScheduleHelper {

	private ArrayList<Event> events;
	private ArrayList<Participant> participants;
	private EspnApiHandler handler;
	
	public ScheduleHelper(EspnApiHandler handler) {
		this.handler = handler;
		
		events = new ArrayList<Event>();
		participants = new ArrayList<Participant>();

		// Dynamic generation from ESPN
		loadTeams();
		loadGames();
	}

	/*
	 * Load teams from ESPN API
	 */
	private void loadTeams() {
		// Teams
		participants = handler.getTeams();
		// Sort
		Collections.sort(participants, new TeamComparator());
	}

	/*
	 * Load games from ESPN API
	 */
	private void loadGames() {
		// Games
		events = handler.getEvents();
		// Sort
		Collections.sort(events, new EventComparator());
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public class TeamComparator implements Comparator<Participant> {
		public int compare(Participant team1, Participant team2) {
			return team1.getName().compareTo(team2.getName());
		}
	}
	
	public class EventComparator implements Comparator<Event> {
		public int compare(Event game1, Event game2) {
			return game1.getName().compareTo(game2.getName());
		}
	}

}
