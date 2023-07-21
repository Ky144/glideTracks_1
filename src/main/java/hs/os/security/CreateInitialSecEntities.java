package hs.os.security;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class CreateInitialSecEntities
{
    @Transactional
    public void loadUsers(@Observes StartupEvent event)
    {
        UserLogin.deleteAll();
        UserLogin.addAdmin("admin", "123", "admin");
    }
}