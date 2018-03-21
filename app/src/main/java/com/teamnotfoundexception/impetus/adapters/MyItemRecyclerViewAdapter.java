package com.teamnotfoundexception.impetus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnotfoundexception.impetus.Databases.EventItem;
import com.teamnotfoundexception.impetus.Databases.StatusManager;
import com.teamnotfoundexception.impetus.R;
import com.teamnotfoundexception.impetus.activities.DescriptionActivity;
import com.teamnotfoundexception.impetus.fragments.EventsFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<EventItem> mEventItems;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;

    public MyItemRecyclerViewAdapter(Context context,List<EventItem> items, OnListFragmentInteractionListener listener) {
        mEventItems = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final EventItem eventItem;
        holder.mItem = mEventItems.get(position);
        eventItem = holder.mItem;
        holder.mEventNameHolder.setText(eventItem.getName());
        holder.mEventTypeHolder.setText(eventItem.getType());
        holder.mEventCostHolder.setText(eventItem.getPrice()+"");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
                Intent intent = new Intent(mContext, DescriptionActivity.class);
                intent.putExtra("msg",eventItem);
                mContext.startActivity(intent);

            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                StatusManager.get(mContext).addEventToStarred(eventItem);
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
        public final ImageView mEventImageHolder;
        public EventItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mEventCostHolder = (TextView) view.findViewById(R.id.eventPriceHolder);
            mEventNameHolder = (TextView) view.findViewById(R.id.eventNameHolder);
            mEventTypeHolder = (TextView) view.findViewById(R.id.eventTypeHolder);
            mEventImageHolder = (ImageView) view.findViewById(R.id.eventImageSquare);

        }


    }
}
