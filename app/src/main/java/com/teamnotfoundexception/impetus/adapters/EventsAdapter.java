package com.teamnotfoundexception.impetus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teamnotfoundexception.impetus.Databases.EventItem;
import com.teamnotfoundexception.impetus.Databases.EventsManager;
import com.teamnotfoundexception.impetus.Databases.StatusManager;
import com.teamnotfoundexception.impetus.R;
import com.teamnotfoundexception.impetus.activities.DescriptionActivity;
import com.teamnotfoundexception.impetus.fragments.EventsFragment;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private ArrayList<EventItem> mEventItems;
    private  EventsFragment.OnListFragmentInteractionListener mListener;

    public Context context;



    public EventsAdapter(ArrayList<EventItem> items, EventsFragment.OnListFragmentInteractionListener listener, Context c) {
        mEventItems = items;
        mListener = listener;
        context = c;

    }
    public EventsAdapter(ArrayList<EventItem> items, Context c) {
        mEventItems = items;
        context = c;

    }

    public void updateData(ArrayList<EventItem> items){
        mEventItems = new ArrayList<>();
        mEventItems.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override



    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.mItem = mEventItems.get(position);
        holder.setIsRecyclable(false);
        final EventItem eventItem = holder.mItem;
        holder.mEventNameHolder.setText(eventItem.getName());
        holder.mEventTypeHolder.setText(eventItem.getType());
        holder.mEventCostHolder.setText(eventItem.getPrice()+"");
        Glide.with(context).load(eventItem.getImagePath()).into(holder.mEventImageHolder);

        if (holder.mItem.isStarred() == 1){
            holder.mStar.setVisibility(View.VISIBLE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    //StatusManager.get(context).addToRegistered(mEventItems.get(position));

                    mListener.onListFragmentInteraction(holder.mItem);
                }
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("msg",eventItem);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);

            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(eventItem.isStarred() == 0) {
                    eventItem.setStarred(1);
                    StatusManager.get(context).addToStarred(eventItem);
                    holder.mStar.setVisibility(View.VISIBLE);
                    updateData(EventsManager.get(context).getEventItemsList());
                }else{
                    eventItem.setStarred(0);
                    StatusManager.get(context).removeFromStarred(eventItem);
                    holder.mStar.setVisibility(View.INVISIBLE);
                    updateData(EventsManager.get(context).getEventItemsList());
                    Log.d("dope ", "onLongClick: "+StatusManager.get(context).getStarredIdList().size());
                }
                return true;
            }
        });

    }
    @Override
    public int getItemCount() {
        return mEventItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mEventNameHolder,mEventCostHolder;
        public final TextView mEventTypeHolder;
        public final ImageView mEventImageHolder,mStar;
        public EventItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mEventCostHolder = (TextView) view.findViewById(R.id.eventPriceHolder);
            mEventNameHolder = (TextView) view.findViewById(R.id.eventNameHolder);
            mEventTypeHolder = (TextView) view.findViewById(R.id.eventTypeHolder);
            mEventImageHolder = (ImageView) view.findViewById(R.id.eventImageSquare);
            mStar = (ImageView) view.findViewById(R.id.startContainer);
        }

    }
}
