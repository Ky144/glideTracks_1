package hs.os.skaterverwaltung.control.dto;

public class SkaterDTO {
    public String vorname;
    public String nachname;

    public  String disziplin;
    public int alter;

    public SkaterDTO(String vorname, String nachname, String disziplin, int alter){
        this.vorname=vorname;
        this.nachname = nachname;
        this.disziplin=disziplin;
        this.alter=alter;
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
}
