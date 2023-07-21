package hs.os.turnierverwaltung.control.dto;

public class TurnierDTO {
    public String name;
    public String date;
    public String ort;

    public TurnierDTO(String name){
        this.name=name;
    }

    public TurnierDTO(String name, String ort, String date)
    {
        this.name=name;
        this.ort=ort;
        this.date=date;
    }


}
