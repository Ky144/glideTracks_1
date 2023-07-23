package hs.os.turnierverwaltung.control.dto;


import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.entity.Turnier;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


import javax.validation.constraints.NotBlank;


import java.util.List;

@Schema(name="TurnierDTO", description="Data transfer object to edit and add a turnier.")
public class TurnierDTO {
    @Schema(title="Turnier name", required = true)
    @NotBlank
    public String name;

    @Schema(title="Turnier ort", required = true)
    @NotBlank
    public String ort;

    @Schema(title="Date when turnier starts", required = true)
    @NotBlank
    public String datum;

    @Schema(title="teilnehmende Skater", required = true)
    @NotBlank
    public List<Skater> Skater;

    public TurnierDTO(String name, String datum, String ort) {
        this.name=name;
        this.ort=ort;
        this.datum=datum;
    }
    public TurnierDTO(){}
}
