package hs.os.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;


/*
 * Leere Klasse zur Definition für OpenAPI.
 */
@OpenAPIDefinition(
        info = @Info(
                title="Kursverwaltung-API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Turnierverwaltung-API Support, katharina.schmidt.1@hs-osnabrueck.de")
        )

)

public class TurnierAPIApplication extends Application {
}
