package com.teamnotfoundexception.impetus.Databases;

/**
 * Created by sagar on 3/21/18.
 */

import android.content.Context;
import android.provider.Telephony;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.teamnotfoundexception.impetus.activities.DescriptionActivity;
import com.teamnotfoundexception.impetus.activities.MainActivity;
import com.teamnotfoundexception.impetus.adapters.MyEventsAdapter;
import com.teamnotfoundexception.impetus.adapters.StarredAdapter;
import com.teamnotfoundexception.impetus.fragments.MyEventsFragment;
import com.teamnotfoundexception.impetus.fragments.OrgoPlayerFragment;
import com.teamnotfoundexception.impetus.fragments.StarredFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class FirebaseHelper {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference1, mDatabaseReference2, mDatabaseReference3;
    private Context mAppContext;
    public static int FETCHING_PARTICIPANTS = 0;
    private Participant p;

    public FirebaseHelper(Context c) {
        mFirebaseDatabase = null;
        mAppContext = c;
    }

    public void setFirebaseDatabase(FirebaseDatabase firebaseDatabase, int type) {
        mFirebaseDatabase = firebaseDatabase;
        if (firebaseDatabase != null){
            mDatabaseReference1 = mFirebaseDatabase.getReference("users_private/");
        mDatabaseReference2 = mFirebaseDatabase.getReference("users_public/");
        mDatabaseReference3
                = mFirebaseDatabase.getReference("events");
    }

    }


    public void updateStarredList(ArrayList<Integer> starredListItem, FirebaseUser user) {

        String emailId = getEmailStripped(user.getEmail());

        Map<String, ArrayList<Integer>> starredListMap = new HashMap<>();
        starredListMap.put("event_ids", starredListItem);

        try {

            mDatabaseReference1.child(user.getUid()).child("starred").setValue(starredListItem);

        } catch (Exception e) {

            Log.i("favoriteError", "error in placing the order");

        }

    }




    public void updateRegisteredList(ArrayList<Integer> registeredListItem, FirebaseUser user, EventItem currentRegisteredItem, Participant participant) {

        Log.i("favorite", "update favorite caleld");


        Map<String, ArrayList<Integer>> registeredListMap = new HashMap<>();

        registeredListMap.put("event_ids", registeredListItem);

        String emailId = user.getEmail().split("@")[0];

        try {

            mDatabaseReference1.child(user.getUid()).child("registered").setValue(registeredListMap);
            mDatabaseReference3.child(currentRegisteredItem.getName().toUpperCase()).child(emailId).setValue(participant);
            DescriptionActivity.notifyMe();

        } catch (Exception e) {

            Log.i("favoriteError", "error in placing the order" + e.getMessage());

        }

    }




    public void fetchRegisteredList(FirebaseUser user) {


        Log.i("ini", "fetch registeerd list called");

        String emailId = getEmailStripped(user.getEmail());
        DatabaseReference databaseReference = mDatabaseReference1.child(user.getUid()).child("registered").child("event_ids");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator<List<Integer>>() {
                };
                System.out.println(dataSnapshot.toString());
                ArrayList<Integer> registeredListIds = (ArrayList<Integer>) dataSnapshot.getValue(t);
                if (registeredListIds != null) {
                    StatusManager.get(mAppContext).setRegisteredIdList(new ArrayList<Integer>(registeredListIds));

                    ArrayList<Integer> rea = StatusManager.get(mAppContext).getRegisteredIdList();

                    System.out.println("done, fetching, the size  of registered is " + rea.size());

                    ArrayList<EventItem> registeredevents = StatusManager.get(mAppContext).getRegisteredEventsList();
                    ArrayList<EventItem> allEvents = EventsManager.get(mAppContext).getEventItemsList();
                    for(int i = 0; i < allEvents.size(); i++) {
                        if(rea.contains(allEvents.get(i).getId())) {
                            allEvents.get(i).setRegistered(1);

                            registeredevents.add(allEvents.get(i));
                        }
                    }
                    System.out.println("The size of registered list is" + registeredevents.size());
                    MyEventsFragment.notifyMe();
                    StatusManager.get(mAppContext).initializeNotifications();
                } else {
                    Log.i("ini", "not fetched registred lst");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });


    }


    public void fetchStarredList(FirebaseUser user) {

        System.out.println("In fetch starred list");
        String emailId = getEmailStripped(user.getEmail());
        DatabaseReference databaseReference = mDatabaseReference1.child(user.getUid()).child("starred");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Integer>> t = new GenericTypeIndicator<List<Integer>>() {
                };
                ArrayList<Integer> starredListIds = (ArrayList<Integer>) dataSnapshot.getValue(t);
                if (starredListIds != null) {
                 StatusManager.get(mAppContext).setStarredIdList(new ArrayList<Integer>(starredListIds));
                    System.out.println("done, fetching, the size of starred is " + starredListIds.size());


                    ArrayList<Integer> starredlistids = StatusManager.get(mAppContext).getStarredIdList();

                    System.out.println("done, fetching, the size  of starred is " + starredlistids.size());

                    ArrayList<EventItem> starredevents = StatusManager.get(mAppContext).getStarredEventsList();
                    ArrayList<EventItem> allEvents = EventsManager.get(mAppContext).getEventItemsList();
                    for(int i = 0; i < allEvents.size(); i++) {
                        if(starredListIds.contains(allEvents.get(i).getId())) {
                            allEvents.get(i).setStarred(1);
                            starredevents.add(allEvents.get(i));

                        }
                    }

                    StarredFragment.notifyMe();

                } else {
                    StarredFragment.notifyMe();
                    System.out.println("done, fetching, the size of starred is zero no starred");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });


    }


    public String getEmailStripped(String emailId) {
        String emailIdSplit[] = emailId.split("@");
        String _emailId = emailIdSplit[0];
        return _emailId;
    }



/*
 ORGANIZERS API START FROM HERE

 */

    public void fetchParticipantsList(EventItem eventOrganized) {

        DatabaseReference databaseReference = mDatabaseReference3.child(eventOrganized.getName().toUpperCase());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //TODO get the primary keys of all the users and do a refetch again for those users.

                /*

                events: {
                    0: "user1@gmail.com",
                    1: "user2@gmail.com",
                    2: "user3@gmail.com"
                },
                users: {
                    "user1@gmail.com": {
                        "name": "sagar",
                        "college":"uvce",
                    }
                }
                 */


                GenericTypeIndicator<Map<String, Participant>> t = new GenericTypeIndicator<Map<String, Participant>>() {
                };

                System.out.println(dataSnapshot.getValue());

               Map<String, Participant> participantsEmailsList = (HashMap<String, Participant>) dataSnapshot.getValue(t);

               ArrayList<Participant> participants  = StatusManagerForOrganizer.get(mAppContext).getParticipants();

               if(participantsEmailsList != null) {

                   for (String k : participantsEmailsList.keySet()) {
                       participants.add(participantsEmailsList.get(k));
                   }
               }

             //  StatusManagerForOrganizer.get(mAppContext).setParticipants(participants);
               System.out.println("size of participatns list after fetching" + StatusManagerForOrganizer.get(mAppContext).getParticipants().size());
               FETCHING_PARTICIPANTS = 1;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
    }








   public static  class Participant {

      //  public String name;
        public String teamName;
        public String collegeName;
        public String teamMembers;

        public Participant() {

        }


        public Participant(String name, String collegeName, String teamMembers) {

            this.teamName = name;
            this.collegeName = collegeName;
            this.teamMembers = teamMembers;

        }


    }


    public void addListenerForParticipants(EventItem eventItem) {
        System.out.println("inside add listerner for participants");
        DatabaseReference ref = mDatabaseReference3.child(eventItem.getName().toUpperCase());
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                GenericTypeIndicator<Map<String, Participant>> t = new GenericTypeIndicator<Map<String, Participant>>() {
                };

                Participant participant = dataSnapshot.getValue(Participant.class);

                StatusManagerForOrganizer.get(mAppContext).getParticipants().add(participant);

                OrgoPlayerFragment.notifyMe();
               // System.out.println("hey new participant registered" + participantNew.keySet());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    class FavoriteObject {
        public int itemId;

        public FavoriteObject() {

        }

        public FavoriteObject(int dishItemId) {
            itemId = dishItemId;
        }
    }



}

