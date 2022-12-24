package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.

    TreeMap<Date, ArrayList<ArrayList<String>>> inbox;
    TreeMap<Date, ArrayList<ArrayList<String>>> trash;
    int countInbox;
    int countTrash;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new TreeMap<>();
        this.trash = new TreeMap<>();
        this.countInbox = 0;
        this.countInbox = 0;
}

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(this.countInbox == this.inboxCapacity)
        {
            Date key = this.inbox.lastKey();
            ArrayList<ArrayList<String>> value = this.inbox.get(key);
            ArrayList<String> v = value.remove(value.size() - 1);

            ArrayList<ArrayList<String>> a = new ArrayList<>();
            if(trash.containsKey(key)) {
                this.trash.remove(key);
                a = trash.get(key);
            }
            a.add(new ArrayList<String>(v));
            this.trash.put(key, a);

            if(date.equals(key))
            {
                this.inbox.remove(key);
                ArrayList<String> s = new ArrayList<>();
                s.add(message);
                s.add(sender);
                value.add(new ArrayList<>());
                value.add(s);
                this.inbox.put(date, value);
            }
            else
            {
                if(this.inbox.get(key).size() > 1)
                    this.inbox.put(key, value);
                ArrayList<ArrayList<String>> b = new ArrayList<>();
                ArrayList<String> s = new ArrayList<>();
                s.add(message);
                s.add(sender);
                b.add(s);
                this.inbox.put(date, b);
            }
            this.countTrash++;
        }
        else
        {
            ArrayList<ArrayList<String>> a = new ArrayList<>();
            a.add(new ArrayList<String>());
            a.get(0).add(message);
            a.get(0).add(sender);
            this.inbox.put(date, a);
            this.countInbox++;
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing



        for(Date d : this.inbox.keySet())
        {
            ArrayList<ArrayList<String>> st = inbox.get(d);
            int x = 0;
            for(ArrayList<String> s : st)
            {
                if(s.get(0).equals(message))
                {
                    if(this.trash.containsKey(d))
                    {
                        ArrayList<ArrayList<String>> value = this.trash.get(d);
                        this.trash.remove(d);
                        value.add(s);
                        this.trash.put(d, value);

                    }
                    else
                    {
                        ArrayList<ArrayList<String>> value = new ArrayList<>();
                        value.add(s);
                        this.trash.put(d, value);
                    }
                    if(this.inbox.get(d).size() > 1)
                    {
                        ArrayList<ArrayList<String>> value = this.inbox.remove(d);
                        value.remove(x);
                        this.inbox.put(d, value);
                    }
                    else
                        this.inbox.remove(d);
                    this.countInbox--;
                    this.countTrash++;
                }
                x++;
            }
        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(this.inbox.size() == 0)
            return null;

        Date date = this.inbox.firstKey();
        ArrayList<ArrayList<String>> value = this.inbox.get(date);
        ArrayList<String> message = value.get(value.size() - 1);

        return message.get(message.size() - 2);
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(this.inbox.size() == 0)
            return null;

        Date date = this.inbox.lastKey();
        ArrayList<ArrayList<String>> value = this.inbox.get(date);
        ArrayList<String> message = value.get(value.size() - 1);

        return message.get(message.size() - 2);
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int countMail = 0;

        long startTime = start.getTime();
        long endTime = end.getTime();
        for(Date d : this.inbox.keySet())
        {
            long dateTime = d.getTime();
            if(dateTime > startTime && dateTime <= endTime)
            {
                ArrayList<ArrayList<String>> value = this.inbox.get(d);
                countMail += value.size();
            }
        }
       return countMail;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.countInbox;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return this.countTrash;
    }

    public void emptyTrash(){
        // clear all mails in the trash
        this.trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
