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
    private String subject;

    @Column(length = 4000)
    private String text;

    @Column()
    private String mood;

    @Column()
    private String origin;

    public Mail(String from_person, String to_person, Date date, String text, String subject, String origin){
        this.timestamp = date;
        this.from_person = from_person;
        this.to_person = to_person;
        this.text = text;
        this.subject = subject;
        this.origin = origin;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFrom_person() {
        return from_person;
    }

    public void setFrom_person(String from_person) {
        this.from_person = from_person;
    }

    public String getTo_person() {
        return to_person;
    }

    public void setTo_person(String to_person) {
        this.to_person = to_person;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
