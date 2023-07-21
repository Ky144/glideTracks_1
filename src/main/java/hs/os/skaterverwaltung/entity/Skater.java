package hs.os.skaterverwaltung.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Vetoed
public class Skater {
    @Id
    @GeneratedValue
    private int id;
    private String vorname;
    private String nachname;
    private String disziplin;
    private int alter;

    public Skater(int id, String vorname, String nachname, String disziplin, int alter){
        this.id=id;
        this.vorname=vorname;
        this.nachname=nachname;
        this.disziplin=disziplin;
        this. alter=alter;
    }

    public Skater(){

    }



    public Skater(String vorname, String nachname, String disziplin, int alter) {
        this.vorname=vorname;
        this.nachname=nachname;
        this.disziplin=disziplin;
        this. alter=alter;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getDisziplin() {
        return disziplin;
    }

    public void setDisziplin(String disziplin) {
        this.disziplin = disziplin;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
