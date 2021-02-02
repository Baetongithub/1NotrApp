package com.geektech.noteapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Comparator;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String date;

    public Note(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public static final Comparator<Note> BY_TITLE_ASCENDING = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static final Comparator<Note> BY_TITLE_DESCENDING = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };

}
