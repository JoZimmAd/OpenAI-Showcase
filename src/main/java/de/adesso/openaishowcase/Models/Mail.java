package de.adesso.openaishowcase.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "mails")
public class Mail {

    @jakarta.persistence.Id
    @GeneratedValue
    private Long id;

    @Column
    private String category;
    @Column
    private Date timestamp;
    @Column
    private String from_person;
    @Column
    private String to_person;

    @Column
    private String sentiment;

    @Column(length = 4000)
    private String text;

    public Mail(String from_person, String to_person, Date date, String text){
        this.timestamp = date;
        this.from_person = from_person;
        this.to_person = to_person;
        this.text = text;
    }

    public Mail(){

    }

    public String getCategory(){
        return this.category;
    }

    public String getText(){
        return this.text;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
