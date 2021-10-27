package com.example.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Note> note;

    public NoteAdapter(Context c, ArrayList<Note> note){
        this.context = c;
        this.note = note;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Note note = this.note.get(position);

        holder.title.setText(note.getTitle());
        holder.time.setText(note.getTime());
        holder.date.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, time, date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitleListItem);
            time = itemView.findViewById(R.id.tvTimeListItem);
            date = itemView.findViewById(R.id.tvDateListItem);
        }
    }
}
