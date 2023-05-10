package com.example.bookyy.Entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idmedia;
    @Column
    private String docName;

    @Column
    String type;

    @Column
    @Lob
    private byte[] file;
    @OneToOne
    Book book;

}
