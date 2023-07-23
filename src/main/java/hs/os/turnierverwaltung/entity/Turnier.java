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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

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
    private Collection<Skater> teilnehmer;

    public Turnier(String name, String datum, String ort){
        this();
        this.name=name;
        this.datum=datum;
        this.ort=ort;

    }
    public Turnier(){
        this.teilnehmer= new ArrayList<Skater>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Collection<Skater> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(Collection<Skater> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public void addSkater(Skater skater) {
        teilnehmer.add(skater);
    }

    public void removeSkater(Skater skater) {
            teilnehmer.remove(skater);
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
