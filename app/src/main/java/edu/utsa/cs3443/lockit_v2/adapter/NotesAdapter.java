package edu.utsa.cs3443.lockit_v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.lockit_v2.R;
import edu.utsa.cs3443.lockit_v2.controller.NotesController;
import edu.utsa.cs3443.lockit_v2.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesListViewHolder> {

    private ArrayList<Note> notes;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NotesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item_view, parent, false);
        view.setOnClickListener(new NotesController());
        return new NotesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesListViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleView.setText(note.getTitle());
        holder.contentView.setText(note.getContent());
        holder.dateView.setText(note.getDate().toString());
        holder.idView.setText(note.getId());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesListViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        TextView contentView;
        TextView dateView;

        TextView idView;


        ImageButton starButton;

        public NotesListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleView);
            dateView = itemView.findViewById(R.id.dateView);
            contentView = itemView.findViewById(R.id.contentView);
            starButton = itemView.findViewById(R.id.starButton);
            idView = itemView.findViewById(R.id.idView);

        }
    }
}
