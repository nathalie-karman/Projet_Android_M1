package com.example.todoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.todoapp.DatabaseAlarm.DatabaseAlarm;
import com.example.todoapp.Fragments.AddTaskFragment;
import com.example.todoapp.Model.EventAlarm;
import com.example.todoapp.R;

import java.util.List;
import java.util.Objects;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> implements View.OnClickListener{
    Context context;
    List<EventAlarm> my_list;
    private EditText my_et_task;

    public AlarmAdapter(Context context, List<EventAlarm> newList) {
        this.context = context;
        this.my_list = newList;
    }

    @Override
    public void onClick(View v) {

    }
    public static AlarmAdapter newInstance(Context context, List<EventAlarm> newList){
        return new AlarmAdapter(context, newList);
    }
    /**
     * En théorie, cette classe devrait afficher la liste des "Rappels" mais le routage n'est pas au point
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView msg, time;
        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.tv_event);
            time = (TextView) itemView.findViewById(R.id.tv_time_date);
            layout = (LinearLayout) itemView.findViewById(R.id.notificationLayout);
        }
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_listing_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.msg.setText(my_list.get(position).getMsg_alarm());
        holder.time.setText(my_list.get(position).getDate_alarm() + " " + my_list.get(position).getTime_alarm());
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }


}
