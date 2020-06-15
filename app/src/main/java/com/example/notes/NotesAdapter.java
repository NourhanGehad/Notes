package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private ArrayList<NoteModel> notesArray;
    private SelectionPropagator observer;

    public NotesAdapter(ArrayList<NoteModel> notesArray, SelectionPropagator observer) {
        this.notesArray = notesArray;
        this.observer = observer;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NoteModel currentNote = notesArray.get(position);

        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               observer.propagateSelection(currentNote);

            }
        });
    }
    @Override
    public int getItemCount() {
        return notesArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

        }
    }

    public interface SelectionPropagator{
        void propagateSelection(NoteModel note);
    }
}
