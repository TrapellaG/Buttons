package com.example.buttons;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buttons.ButtonFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Button} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyButtonRecyclerViewAdapter extends RecyclerView.Adapter<MyButtonRecyclerViewAdapter.ViewHolder>
{

    private final List<Button> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyButtonRecyclerViewAdapter(List<Button> items, OnListFragmentInteractionListener listener)
    {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.imageViewColor.setColorFilter(Color.parseColor(mValues.get(position).getColor()));
        holder.textViewClicks.setText(""+mValues.get(position).getClicks());
        holder.textViewClicks.setTextColor(Color.parseColor(mValues.get(position).getColor()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final ImageView imageViewColor;
        public final TextView textViewClicks;
        public Button mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewColor = (ImageView) view.findViewById(R.id.imageViewColor);
            textViewClicks = (TextView) view.findViewById(R.id.textViewClicks);
        }

        /*@Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }*/
    }
}
