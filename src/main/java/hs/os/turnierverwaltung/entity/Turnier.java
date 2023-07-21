package hs.os.turnierverwaltung.entity;

import hs.os.skaterverwaltung.entity.Skater;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class Turnier {
    private String name;
    @Id
    @GeneratedValue
    private int id;
    private String date;
    private String ort;

    //@ManyToOne
    //private Skater skater;
    @ManyToMany
    private List<Skater> skaters = new ArrayList<>();

    public Turnier(String name){
        this.name=name;
    }
    public Turnier(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    /*public Skater getSkater() {
        return skater;
    }

    public void setSkater(Skater skater) {
        this.skater = skater;
    }*/
    public void addSkater(Skater skater) {
        if (skaters == null) {
            skaters = new ArrayList<>();
        }
        skaters.add(skater);
    }

    public void removeSkater(Skater skater) {
        if (skaters != null) {
            skaters.remove(skater);
        }
    }

    public List<Skater> getSkaters() {
        return skaters;
    }
}
