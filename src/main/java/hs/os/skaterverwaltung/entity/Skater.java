package hs.os.skaterverwaltung.entity;

import hs.os.security.UserLogin;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skater extends UserLogin {

    private String vorname;
    private String nachname;
    private String disziplin;
    private long alter;

    public Skater(String vorname, String nachname, String disziplin, long alter){
        this.vorname=vorname;
        this.nachname=nachname;
        this.disziplin=disziplin;
        this. alter=alter;
    }
    public Skater(String vorname, String nachname){
        this.vorname=vorname;
        this.nachname=nachname;
        this.vorname = vorname + "-" + nachname;
        // Passwort besteht aus dem ersten Zeichen des Vor- und Nachnamens
        // Bsp.: Katharina Schmidt = KS
        // Dies ist nur temporär
        this.passwort = BcryptUtil.bcryptHash(vorname.substring(0, 1) + nachname.substring(0, 1));
    }

    public Skater(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
