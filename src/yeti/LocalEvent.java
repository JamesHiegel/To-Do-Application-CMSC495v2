/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeti;

import java.time.LocalDate;

/**
 *
 * @author JT
 */
public class LocalEvent {

    private String description;
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalEvent( LocalDate date, String description) {
        this.setDate( date );
        this.setDescription( description );
    }

    @Override
    public String toString(){
        return "On : " + this.getDate() + " " + this.getDescription();
    }

}