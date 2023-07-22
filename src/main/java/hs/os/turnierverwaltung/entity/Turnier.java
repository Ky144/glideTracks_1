package hs.os.turnierverwaltung.entity;

import hs.os.skaterverwaltung.entity.Skater;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "turnier")
public class Turnier {
    private String name;
    @Id
    @GeneratedValue
    private long id;
    private String datum;
    private String ort;

    /**
     * Gibt die Teilnehmer/angemeldeten Skater des Turniers an.
     */
    @JsonbTransient
    @ManyToMany
    @JoinTable(
            name = "turnier_teilnehmer",
            joinColumns = {@JoinColumn(name= "turnier_id")},
            inverseJoinColumns = {@JoinColumn(name = "skater_id")}
    )
    private Collection<Skater>  teilnehmer;
    public Turnier(String name, String datum, String ort){
        this.name=name;
        this.datum=datum;
        this.ort=ort;

    }
    public Turnier(){

    }

    public void addSkater(Skater skater) {

        teilnehmer.add(skater);
    }

    public void removeSkater(Skater skater) {
        if (teilnehmer != null) {
            teilnehmer.remove(skater);
        }
    }

    public Collection<Skater> getSkaters() {
        return teilnehmer;
    }




        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return datum;
    }

    public void setDate(String date) {
        this.datum = date;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
