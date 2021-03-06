package com.teamnotfoundexception.impetus.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.teamnotfoundexception.impetus.Databases.EventItem;
import com.teamnotfoundexception.impetus.Databases.EventsManager;
import com.teamnotfoundexception.impetus.Databases.Time;
import com.teamnotfoundexception.impetus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    private TextView mTimeContainer,mEventStartTime,mEventEndTime,mEventLocation,mEventTeamMax,mEventPrice,mEventType, mEventName;
    private long timeToEvent;
    private Handler handler;
    private Runnable myRunnable;
    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        mTimeContainer = (TextView) view.findViewById(R.id.foClock);
        mEventType = (TextView) view.findViewById(R.id.eventTypeContainer);
        mEventTeamMax = (TextView) view.findViewById(R.id.maxTeamMemberContainer);
        mEventPrice = (TextView) view.findViewById(R.id.ticketCostContainer);
        mEventLocation = (TextView) view.findViewById(R.id.eventLocationContainer);
        mTimeContainer = (TextView) view.findViewById(R.id.foClock);
        mEventName = (TextView) view.findViewById(R.id.foEventNameHolder) ;
        //mEventStartTime = (TextView) view.findViewById(R.id.eventStartTimeContainer);
        //mEventEndTime = (TextView) view.findViewById(R.id.eventEndTimeContainer);

        Bundle bundle = getArguments();
        if (bundle==null) {
            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            return view;
        }

        EventItem item = (EventItem) bundle.getSerializable("dope");


        long unixTime = System.currentTimeMillis() / 1000L;
        long timeofevent = Long.parseLong(item.getStartTime());
         timeToEvent = timeofevent - unixTime;
        if(timeToEvent < 0) {
            mTimeContainer.setText("EVENT STARTED");
            timeToEvent = 0;
        } else {

            Time time = EventsManager.get(getActivity().getApplicationContext()).convertSecondsToTime(timeToEvent);




          handler = new Handler();

            myRunnable = new Runnable() {
                @Override
                public void run() {
                    timeToEvent--;
                    if(timeToEvent > 0 && handler != null) {
                        if(getActivity() != null) {
                            Time time = EventsManager.get(getActivity().getApplicationContext()).convertSecondsToTime(timeToEvent);
                            mTimeContainer.setText(time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds());
                            handler.postDelayed(this, 1000);
                        } else {
                            handler = null;
                        }
                    } else {
                        handler = null;
                    }
                }
            };
            handler.postDelayed(myRunnable, 1000);
        }


        mEventName.setText(item.getName());

        mEventType.setText(item.getType());
        Time startTime = EventsManager.get(getActivity().getApplicationContext()).convertSecondsToTime(Long.parseLong(item.getStartTime()));
        Time endTime = EventsManager.get(getActivity().getApplicationContext()).convertSecondsToTime(Long.parseLong(item.getEndTime()));
       // mEventStartTime.setText(startTime.getHours()+":"+startTime.getMinutes());
       // mEventEndTime.setText(endTime.getHours()+":"+endTime.getMinutes());
        mEventTeamMax.setText(item.getMaxTeamSize()+"");
        mEventPrice.setText(item.getPrice()+"");
        mEventLocation.setText(item.getLocation());
        return view;
    }


    @Override
    public void onDestroy() {
        handler.removeCallbacks(myRunnable);
        super.onDestroy();

    }


    @Override
    public void onDetach() {
        if(handler != null) {
            handler.removeCallbacks(myRunnable);
        }
        super.onDetach();
    }
}
