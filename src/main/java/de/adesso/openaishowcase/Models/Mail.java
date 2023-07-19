package de.adesso.openaishowcase.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.util.Date;


@Entity
public class Mail {

    @jakarta.persistence.Id
    @Id
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

    public Mail(String from_person, String to_person, Date date, String category){
        this.category = category;
        this.timestamp = date;
        this.from_person = from_person;
        this.to_person = to_person;
    }

    public Mail(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
