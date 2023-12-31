package com.sandeep.hostelcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<ComplaintDetails> list;

    public MyAdapter(Context context, ArrayList<ComplaintDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ComplaintDetails user =list.get(position);
        holder.Name.setText(user.getName());
        holder.rno.setText(user.getRoomNumber());
        holder.bno.setText(user.getBlock());
        holder.complaint.setText(user.getComplaintText());

    }

    @Override
    public int getItemCount() {
        return list.size(); //total number elements to be displayed in recycler view
    }
    //this adapter is for recycler view

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView Name,rno,bno,complaint;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.nameComplaint);
            rno=itemView.findViewById(R.id.roomnoComplaints);
            bno=itemView.findViewById(R.id.blocknoComplaint);
            complaint=itemView.findViewById(R.id.theComplaint);

        }
    }

}
