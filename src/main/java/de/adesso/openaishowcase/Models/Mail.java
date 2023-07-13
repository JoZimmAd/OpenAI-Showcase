package de.adesso.openaishowcase.Models;

import de.adesso.openaishowcase.Enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Entity
public class Mail {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String category;
    @Column
    private Timestamp timestamp;
    @Column
    private String from_person;
    @Column
    private String to_person;

}
