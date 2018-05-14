package com.example.sino.foodyv1.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;

import java.util.List;

/**
 * Created by SINO on 4/9/2017.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView imageView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_moinhat);
            imageView = (ImageView) itemView.findViewById(R.id.img_moinhat);

        }
    }
    private List<ListItem> mListItem;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ListItemAdapter(Context context, List<ListItem> contacts) {
        mListItem = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }
    @Override
    public ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View listitemView = inflater.inflate(R.layout.list_custom, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(listitemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListItemAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ListItem item = mListItem.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(item.getName());

        viewHolder.imageView.setImageResource(item.getmImageView());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mListItem == null ? 0 : mListItem.size();
    }
}
