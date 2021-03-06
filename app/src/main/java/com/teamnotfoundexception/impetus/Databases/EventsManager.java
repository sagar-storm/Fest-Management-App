package com.teamnotfoundexception.impetus.Databases;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.teamnotfoundexception.impetus.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class EventsManager {

    public static final String TAG = "EventsManager";



    private static EventsManager mEventsManager = null;

    private static EventsDatabaseHelper mDatabaseHelper ;

    private static ArrayList<EventItem> mEventItemsList = null;

    private Context mAppContext;

    private SharedPreferences mSharedPref;


    private EventsManager(Context context) {

        mAppContext = context;
        mDatabaseHelper = new EventsDatabaseHelper(mAppContext);
        mEventItemsList = new ArrayList<>();
        mSharedPref = mAppContext.getSharedPreferences("user_data", Context.MODE_PRIVATE);
    }


    public static void setEventsManager(EventsManager dishRepository) {
        mEventsManager = dishRepository;
    }

    public void insertAllEventItems() {

        Log.i("i", "in inserting function");


        //  String currentUser = CartManager.get(mAppContext).getUser().getEmail();

        boolean isInsertedBefore = mSharedPref.getString("inserted_before", "0").equals("0") ? false: true;

        if(!isInsertedBefore) {

            System.out.println("in inserting function " + mAppContext.getResources().getString(R.string.steal_jobs).toString());

//<<<<<<< HEAD
//            mDatabaseHelper.insertEventItem((new EventItem(1001,"Steal Jobs", "Mock placement",30, mAppContext.getResources().getString(R.string.steal_jobs).toString(),"http://www.impetus18.com/img/event-img/IMG1.jpg", "1521862200","1521894600", "Multiple Locations",1,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1002,"It wiz", "Quiz",30,  mAppContext.getResources().getString(R.string.itwiz).toString(),"http://www.impetus18.com/img/event-img/IMG2.jpg", "1521783000","1521790200", "Main Stage",2,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1003,"Tech Charads", "Charads",30,  mAppContext.getResources().getString(R.string.tech_charades).toString(),"http://www.impetus18.com/img/event-img/IMG3.jpg", "1521784800","1521793800", "Seminar Hall",3,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1004,"Vaaksamara", "Debate",30,  mAppContext.getResources().getString(R.string.vaksamara).toString(),"http://www.impetus18.com/img/event-img/IMG4.jpg", "1521793800","1521797400","Lecture Complex",1,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1005,"Tech Mark", "Quiz",30,  mAppContext.getResources().getString(R.string.tech_mark).toString(),"http://www.impetus18.com/img/event-img/IMG5.jpg", "1521792000","1521804000", "Lecuture Hall 3",4,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1006,"Automania", "Mech",30,  mAppContext.getResources().getString(R.string.automania).toString(),"http://www.impetus18.com/img/event-img/IMG6.jpg", "1521786600","1521790200", "Centenary Hall",3,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1007,"Electrophilia", "Fun",30,  mAppContext.getResources().getString(R.string.electrophila).toString(),"http://www.impetus18.com/img/event-img/IMG7.jpg", "1521802800","1521804600", "Lecture Hall 3",2,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1008,"Spektrom", "Quiz",30,  mAppContext.getResources().getString(R.string.sepktrom).toString(),"http://www.impetus18.com/img/event-img/IMG8.jpg", "1521790200","1521795600", "Centenary Seminar Hall",3,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1009,"So you think it is over?", "TreasureHunt",30,  mAppContext.getResources().getString(R.string.so_you_think_its_over).toString(),"http://www.impetus18.com/img/event-img/IMG9.jpg", "1521790200","1521804600","Entire College",2,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1010,"Appathon", "Android",00,  mAppContext.getResources().getString(R.string.appathon).toString(),"http://www.impetus18.com/img/event-img/IMG10.jpg", "1521876600","1521792000","Seminal Hall",2,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1011,"Sumo Wars", "Fighting",30, mAppContext.getResources().getString(R.string.sumowar).toString(),"http://www.impetus18.com/img/event-img/IMG11.jpg", "1521792000","1521797400", "Multiple Locations",1,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1012,"Ucypher", "Gaming",30, mAppContext.getResources().getString(R.string.ucypher).toString(),"http://www.impetus18.com/img/event-img/IMG12.jpg", "1521783000","1521804600", "Minchu",1,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1013,"Cyborg", "Robotics",30, mAppContext.getResources().getString(R.string.cyborg).toString(),"http://www.impetus18.com/img/event-img/IIMG1.jpg", "1521869400","1521873000", "Lecture Hall 3",2,0,0)));
//            mDatabaseHelper.insertEventItem((new EventItem(1014,"Neutral Oxide", "Fun",30, mAppContext.getResources().getString(R.string.neutraloxide).toString(),"http://www.impetus18.com/img/event-img/IIMG2.jpg", "1521883800","1521887400", "Multiple Locations",4,0,0)));
//=======
            mDatabaseHelper.insertEventItem((new EventItem(1001,"StealJobs", "MockPlacement",30, mAppContext.getResources().getString(R.string.steal_jobs).toString(),"http://www.impetus18.com/img/event-img/IMG1.jpg", "1521862200","1521894600", "Multiple Locations",1,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1002,"Itwiz", "Quiz",30,  mAppContext.getResources().getString(R.string.itwiz).toString(),"http://www.impetus18.com/img/event-img/IMG2.jpg", "1521783000","1521790200", "Main Stage",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1003,"TechCharads", "Charads",30,  mAppContext.getResources().getString(R.string.tech_charades).toString(),"http://www.impetus18.com/img/event-img/IMG3.jpg", "1521784800","1521793800", "Seminar Hall",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1004,"Vaaksamara", "Debate",30,  mAppContext.getResources().getString(R.string.vaksamara).toString(),"http://www.impetus18.com/img/event-img/IMG4.jpg", "1521793800","1521797400","Lecture Complex",1,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1005,"TechMark", "Quiz",30,  mAppContext.getResources().getString(R.string.tech_mark).toString(),"http://www.impetus18.com/img/event-img/IMG5.jpg", "1521792000","1521804000", "Lecuture Hall 3",4,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1006,"Automania", "Mech",30,  mAppContext.getResources().getString(R.string.automania).toString(),"http://www.impetus18.com/img/event-img/IMG6.jpg", "1521786600","1521790200", "Centenary Hall",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1007,"Electrophilia", "Fun",30,  mAppContext.getResources().getString(R.string.electrophila).toString(),"http://www.impetus18.com/img/event-img/IMG7.jpg", "1521790200","1521804600", "Lecture Hall 3",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1008,"Spektrom", "Quiz",30,  mAppContext.getResources().getString(R.string.sepktrom).toString(),"http://www.impetus18.com/img/event-img/IMG8.jpg", "1521802800","1521795600", "Centenary Seminar Hall",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1009,"Soyouthinkitisover?", "TreasureHunt",30,  mAppContext.getResources().getString(R.string.so_you_think_its_over).toString(),"http://www.impetus18.com/img/event-img/IMG9.jpg", "1521790200","1521804600","Entire College",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1010,"Appathon", "Android",00,  mAppContext.getResources().getString(R.string.appathon).toString(),"http://www.impetus18.com/img/event-img/IMG10.jpg", "1521876600","1521792000","Seminal Hall",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1011,"Sumo Wars", "Fighting",30, mAppContext.getResources().getString(R.string.sumowar).toString(),"http://www.impetus18.com/img/event-img/IMG11.jpg", "1521792000","1521797400", "Multiple Locations",1,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1012,"Ucypher", "Gaming",30, mAppContext.getResources().getString(R.string.ucypher).toString(),"http://www.impetus18.com/img/event-img/IMG12.jpg", "1521783000","1521804600", "Minchu",1,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1013,"Cyborg", "Robotics",30, mAppContext.getResources().getString(R.string.cyborg).toString(),"http://www.impetus18.com/img/event-img/IIMG1.jpg", "1521869400","1521873000", "Lecture Hall 3",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1014,"NeutralOxide", "Fun",30, mAppContext.getResources().getString(R.string.neutraloxide).toString(),"http://www.impetus18.com/img/event-img/IIMG2.jpg", "1521883800","1521887400", "Multiple Locations",4,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1015,"Summit", "Debate",30, mAppContext.getResources().getString(R.string.summit).toString(),"http://www.impetus18.com/img/event-img/IIMG3.jpg", "1521862200","1521876600", "Seminar Hall",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1016,"CodeStorm", "Coding",30, mAppContext.getResources().getString(R.string.codestorm).toString(),"http://www.impetus18.com/img/event-img/IIMG4.jpg", "1521873000","1521887400", "Minchu",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1017,"Enigma", "Treasure Hunt",30, mAppContext.getResources().getString(R.string.enigma).toString(),"http://www.impetus18.com/img/event-img/IIMG5.jpg", "1521883800","1521891000", "Multiple Locations",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1018,"MarvelMania", "Quiz",30, mAppContext.getResources().getString(R.string.marvel_man).toString(),"http://www.impetus18.com/img/event-img/IIMG6.jpg", "1521880200","1521883800", "Centenary Hall",3,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1019,"RoboSoccer", "Robotics",30, mAppContext.getResources().getString(R.string.robo_soccer).toString(),"http://www.impetus18.com/img/event-img/IIMG7.jpg", "1521862200","1521891000", "Multiple Locations",2,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1020,"MechorBreak", "Mech",30, mAppContext.getResources().getString(R.string.mech_or_break).toString(),"http://www.impetus18.com/img/event-img/IIMG7.jpg", "1521775800","1521891000", "Quadrangle",5,0,0)));
            mDatabaseHelper.insertEventItem((new EventItem(1021,"LaserTag", "Fun",30, mAppContext.getResources().getString(R.string.lasertag).toString(),"http://www.impetus18.com/img/event-img/IIMG8.jpg", "1521775800","1521891000", "Quadrangle",5,0,0)));

            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putString("inserted_before", "1");
            editor.apply();
            editor.commit();

        }
        else {
            Log.i("i", "it was inserted already");
        }
    }

    public void initializeEventItemsList() {
        if(EventsManager.get(mAppContext).getEventItemsList().size() == 0) {

            EventsDatabaseHelper.EventItemCursor mEventItemCursor = this.mDatabaseHelper.getAllEventItems();


            Log.i("i", "database helper inited");


            if (mEventItemCursor.getCount() != 0) {
                Log.i("i", "mDishcursor size" + mEventItemCursor.getCount());
                mEventItemCursor.moveToFirst();
                while (!mEventItemCursor.isAfterLast()) {
                    EventItem dishItem = mEventItemCursor.getEventItem();
                    Log.i("i", dishItem.getImagePath());
                    mEventItemsList.add(dishItem);
                    mEventItemCursor.moveToNext();
                }
            }
            mEventItemCursor.close();
        }
    }

    public static EventsManager  get(Context c) {
        if(mEventsManager == null) {
            mEventsManager = new EventsManager(c);
            Log.i("i", "dish repo initialized");
        }
        return mEventsManager;
    }


    public ArrayList<EventItem> getEventItemsList() {
        return mEventItemsList;
    }

    public void setEventItemsList(ArrayList<EventItem> list) {mEventItemsList = list;}

    public Time convertSecondsToTime(long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
        Time time = new Time(hours, minute, second);
        return time;
    }

}

