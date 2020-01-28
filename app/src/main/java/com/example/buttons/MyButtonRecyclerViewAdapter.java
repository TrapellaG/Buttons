package com.example.buttons;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buttons.ButtonFragment.OnListFragmentInteractionListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void addNewButton(final Button button)
    {
        int idButton = buttonToIndex(button.getId());

        if(idButton == -1)
        {
            mValues.add(button);
        }
        else
        {
            mValues.get(idButton).setClicks(button.getClicks());
        }

    }

    public int buttonToIndex(String id)
    {
        int index = 0;

        for(Button button:mValues)
        {
            if(button.getId().equals(id))
            {
                return index;
            }

            index++;
        }

        return -1;
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
        holder.mItem = mValues.get(position);
        holder.imageViewColor.setColorFilter(Color.parseColor(holder.mItem.getColor()));
        holder.textViewClicks.setText(""+holder.mItem.getClicks());
        holder.textViewClicks.setTextColor(Color.parseColor(holder.mItem.getColor()));

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.textViewClicks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                holder.mItem.addClick();
                ButtonAccess.updateButton(holder.mItem);
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
    }
}
