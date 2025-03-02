package com.mycompany.tennis.core.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short annee;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER : fait des jointures systématiquement (peu performant). FetchType.LAZY : fait des jointures uniquement si nécessaire (plus performant).
    @JoinColumn(name = "ID_TOURNOI")
    private Tournoi tournoi;

    @Column(name = "TYPE_EPREUVE")
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
