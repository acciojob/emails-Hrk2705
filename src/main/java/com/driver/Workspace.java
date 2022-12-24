package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar

        this.calendar.add(meeting);

    }

    public int findMaxMeetings()
    {
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am



        Collections.sort(calendar, (a,b)-> {
            return a.getEndTime().compareTo(b.getEndTime());
        });
        int maxMeetings = 0;
        LocalTime endTime;
        if(calendar.size()>0) {
            maxMeetings++;
            endTime = calendar.get(0).getEndTime();
        }
        else return maxMeetings;
        for(int i=1; i<calendar.size(); i++) {
            if(calendar.get(i).getStartTime().compareTo(endTime)>0) {
                maxMeetings++;
                endTime = calendar.get(i).getEndTime();
            }
        }
        return maxMeetings;
    }
}
