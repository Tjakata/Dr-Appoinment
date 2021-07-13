package com.example.doctorappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){

        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.age.setText(model.getAge());
        holder.date.setText(model.getDate());
        holder.gender.setText(model.getGender());
        holder.name.setText(model.getName());
        holder.surname.setText(model.getSurname());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView age, date, gender, name, surname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            age = itemView.findViewById(R.id.age_int);
            date = itemView.findViewById(R.id.date_int);
            gender = itemView.findViewById(R.id.gender_char);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
        }
    }

}
