package com.geektech.noteapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.noteapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Note> list;

    public NoteAdapter(Context context) {
        this.context = context;
        // then, here the miracle happened
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.textTitle.setOnLongClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Remove this item?");
            alertDialog.setPositiveButton("NO, CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setIcon(R.drawable.ic_delete_forever_24);
            dialog.show();
            return true;
        });

        holder.textDate.setOnLongClickListener(v->{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Delete");
            alertDialog.setMessage("Remove this item?");
            alertDialog.setPositiveButton("NO, CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.setIcon(R.drawable.ic_delete_forever_24);
            dialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Note note) {
        list.add(note);
        notifyDataSetChanged();
    }

    public void addList(List<Note> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text_title);
            textDate = itemView.findViewById(R.id.text_date);
        }

        public void bind(Note note) {
            textDate.setText(note.getDate());
            textTitle.setText(note.getTitle());
        }


    }

}
