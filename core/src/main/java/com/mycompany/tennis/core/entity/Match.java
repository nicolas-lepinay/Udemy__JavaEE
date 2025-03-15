package com.mycompany.tennis.core.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "MATCH_TENNIS")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_VAINQUEUR")
    private Joueur vainqueur;

    @ManyToOne
    @JoinColumn(name = "ID_FINALISTE")
    private Joueur finaliste;

    @OneToOne(fetch = FetchType.LAZY) // Relation 1-1
    @JoinColumn(name = "ID_EPREUVE")
    private Epreuve epreuve;

    //@Transient // Exclut cet attribut du mapping Hibernate
    @OneToOne(mappedBy = "match", fetch = FetchType.LAZY) // Attribut "match" de la classe Score
    private Score score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}


