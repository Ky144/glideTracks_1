package hs.os.turnierverwaltung.control.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Schema(name="TurnierDTO", description="Data transfer object to edit and add a turnier.")
public class TurnierDTO {
    @Schema(title="Turnier name", required = true)
    @NotBlank
    public String name;

    @Schema(title="Turnier ort", required = true)
    @NotBlank
    public String ort;

    @Schema(title="Date when turnier starts, required = true")
    @NotBlank
    public String datum;

}
