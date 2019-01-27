package com.example.grosjean.githubapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grosjean.githubapi.rest.models.Branches;
import com.example.grosjean.githubapi.rest.models.Item;

import java.util.List;

public class MyRepoBranchesAdapter extends RecyclerView.Adapter<MyRepoBranchesAdapter.MyViewHolder> {
    private List<Branches> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;

        public MyViewHolder(View v) {
            super(v);

            mName = v.findViewById(R.id.branches_name);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRepoBranchesAdapter(List<Branches> mItems) {
        mDataset = mItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyRepoBranchesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = (View) inflater.inflate(R.layout.elem_adapter_repo_information, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mName.setText(mDataset.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}