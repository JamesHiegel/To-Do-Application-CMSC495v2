package com.sad.yeti;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JT
 */
public class LocalEvent {

    private int recordID;
    private int priority;
    private LocalDate date;
    private SimpleStringProperty description;
    private boolean personal;
    private SimpleStringProperty notify;
    private SimpleStringProperty tag;


    public LocalEvent () {
    }

    public LocalEvent(int recordID, int priority, LocalDate date, String description, boolean personal, String tag, String notify) {
        this.recordID = recordID;
        this.priority = priority;
        this.date = date;
        this.description = new SimpleStringProperty(description);
        this.personal = personal;
        this.tag = new SimpleStringProperty(tag);
        this.notify = new SimpleStringProperty(notify);
    }

    public int getRecordID() { return recordID; }

    public void setRecordID(int recordID) { this.recordID = recordID; }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public boolean getPersonal() {
        return personal;
    }

    public void setNotify(String notify) { this.notify = new SimpleStringProperty(notify); }

    public String getNotify() { return notify.get(); }

    public void setTag(String tag) { this.tag = new SimpleStringProperty(tag); }

    public String getTag() { return tag.get(); }

    public String getPriorityText() {
        switch (priority) {
            case 1:
                return "High";

            case 2:
                return "Medium";

            case 3:
                return "Low";

        }
        return "Unknown";
    }

    @Override
    public String toString(){
        return "On : " + this.getDate() + "\nDescription: " + this.getDescription() + " \nPriority: (" + this.getPriorityText() + ")";
    }

}