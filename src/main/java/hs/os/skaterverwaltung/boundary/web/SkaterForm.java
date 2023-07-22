package hs.os.skaterverwaltung.boundary.web;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class SkaterForm {
    @FormParam("vorname")
    @PartType(MediaType.TEXT_PLAIN)
    public String vorname;

    @FormParam("nachname")
    @PartType(MediaType.TEXT_PLAIN)
    public String nachname;

    @FormParam("disziplin")
    @PartType(MediaType.TEXT_PLAIN)
    public String disziplin;


    @QueryParam("alter")
    @PartType(MediaType.TEXT_PLAIN)
    public int alter;


    public SkaterDTO convertIntoSkaterDTO(){
        SkaterDTO dto = new SkaterDTO();
        dto.vorname = vorname;
        dto.nachname = nachname;
        dto.disziplin = disziplin;
        dto.alter = alter;
        return dto;
    }
}
