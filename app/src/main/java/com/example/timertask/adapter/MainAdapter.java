package com.example.timertask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timertask.R;
import com.example.timertask.model.TaskModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RvViewHolder> {
    Context context;
    ArrayList<TaskModel>    models;

    public MainAdapter(Context context, ArrayList<TaskModel> models) {
        this.context = context;
        this.models = models;

    }
    View view;
    @Override
    public MainAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }
    @Override
    public void onBindViewHolder(MainAdapter.RvViewHolder holder, final int position) {
        final TaskModel model = models.get(position);

            holder.time.setText(model.getTaskTime());
            holder.taskname.setText(model.getTaskName());




    }
    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView taskname, time;
        ImageView removeImg;
        LinearLayout llItem;

        public RvViewHolder(View itemView) {
            super(itemView);
            taskname = itemView.findViewById(R.id.add_task);
            time = itemView.findViewById(R.id.add_time);

        }
    }
}
