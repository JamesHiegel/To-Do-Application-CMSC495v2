package com.sad.yeti;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JT
 */
public class LocalEvent {

    private int priority;
    private LocalDate date;
    private SimpleStringProperty description;
    private boolean personal;
    private boolean notify;
    private SimpleStringProperty tag;


    public LocalEvent () {
    }

    public LocalEvent(int priority, LocalDate date, String description, boolean personal) {
        this.priority = priority;
        this.date = date;
        this.description = new SimpleStringProperty(description);
        this.personal = personal;
    }

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

    public void setNotify(boolean notify) { this.notify = notify; }

    public boolean getNotify() { return notify; }

    public void setTag(String tag) { this.tag = new SimpleStringProperty(tag); }

    public String getTag() { return tag.get(); }

    @Override
    public String toString(){
        return "On : " + this.getDate() + " " + this.getDescription();
    }

}