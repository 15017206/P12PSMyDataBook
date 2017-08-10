package com.example.a15017206.p12psmydatabook;

import java.io.Serializable;

/**
 * Created by 15017206 on 18/05/2017.
 */

public class Note implements Serializable {
    private int id;
    private String bioContent;

    public Note(int id, String noteContent) {
        this.id = id;
        this.bioContent = noteContent;
    }

    public int getId() {
        return id;
    }

    public String getNoteContent() {
        return bioContent;
    }

    public void setNoteContent(String noteContent) {
        this.bioContent = noteContent;
    }

}
