package com.example.bookyy.Entites;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int idbook;
    String title;
    String description;
    String status;
    int rate;
    int isPublic;
    Date publish_date;
    @Enumerated(EnumType.STRING)
    Categories categories;
    int nbrpage;
    @Enumerated(EnumType.STRING)
    LangueBook langueBook;


    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Attachment attachment;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE )
    private List<Rating> rating;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    }


