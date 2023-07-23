package hs.os.security;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import io.quarkus.runtime.StartupEvent;

/**
 * Dient der Erzeugung von Default-Anmeldeinformationen beim Start der API.
 */
@Singleton
public class CreateInitialSecEntities
{
    @Transactional
    public void loadUsers(@Observes StartupEvent event)
    {
        UserLogin.deleteAll();
        //Admin einfügen
        UserLogin.addAdmin("admin", "123");
        //Skater einfügen
        UserLogin.addSkater("Katharina", "Schmidt", "Einzel", 20);
        UserLogin.addSkater("Max", "Müller", "Doppel", 16);
        UserLogin.addSkater("Andreas", "wiegand", "Doppel", 17);
    }

}