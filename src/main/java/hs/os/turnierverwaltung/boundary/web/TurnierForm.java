package hs.os.turnierverwaltung.boundary.web;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class TurnierForm {
    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    public String name;

    @FormParam("datum")
    @PartType(MediaType.TEXT_PLAIN)
    public String datum;

    @FormParam("ort")
    @PartType(MediaType.TEXT_PLAIN)
    public String ort;



    public TurnierDTO convertIntoTurnierDTO(){
        TurnierDTO dto = new TurnierDTO();
        dto.name = name;
        dto.datum = datum;
        dto.ort = ort;
        return dto;
    }
}
