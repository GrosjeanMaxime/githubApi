package com.example.grosjean.githubapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grosjean.githubapi.rest.models.Item;

import java.util.List;

public class MyReposListAdapter extends RecyclerView.Adapter<MyReposListAdapter.MyViewHolder> {
    private List<Item> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mRepoFullName;
        public TextView mRepoDescription;
        public TextView mRepoLanguage;
        public View mView;

        public MyViewHolder(View v) {
            super(v);

            mView = v;
            mRepoFullName = v.findViewById(R.id.repoFullName);
            mRepoDescription = v.findViewById(R.id.repoDescription);
            mRepoLanguage = v.findViewById(R.id.repoLanguage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyReposListAdapter(List<Item> mItems) {
        mDataset = mItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyReposListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = (View) inflater.inflate(R.layout.elem_adapter_repo_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mRepoFullName.setText(mDataset.get(position).getFullName());
        holder.mRepoDescription.setText(mDataset.get(position).getDescription());
        holder.mRepoLanguage.setText(mDataset.get(position).getLanguage());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RepoInformationActivity.class);
                intent.putExtra("user_repo", mDataset.get(position).getFullName());
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}