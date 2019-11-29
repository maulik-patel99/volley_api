package com.example.volley_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
List<model> personlists;

    public RecyclerViewAdapter(List<model> list) {
    this.personlists=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
View view=layoutInflater.inflate(R.layout.list_item,parent,false);
ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.uid.setText(personlists.get(position).getName());
holder.id.setText(personlists.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return personlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView uid,id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uid=itemView.findViewById(R.id.te_userid);
            id=itemView.findViewById(R.id.te_id);
          }
    }
}
