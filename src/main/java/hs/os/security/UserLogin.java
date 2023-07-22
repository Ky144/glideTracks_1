package hs.os.security;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.Table;

import hs.os.skaterverwaltung.entity.Skater;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
/**
 * Dient der Verwaltung der Anmeldeinformationen von Benutzern.
 * **/

@Entity
// @Table(name = "user_login", schema = "SEC")
@UserDefinition
public class UserLogin extends PanacheEntity
{
    @JsonbTransient
    @Username
    public String nutzername;
    @JsonbTransient
    @Password
    public String passwort;
    @JsonbTransient
    @Roles
    public String rolle;

    /**
     * Fügt eine neue Anmeldeinformation des Typs "admin" hinzu.
     * @param nutzername Der Benutzername.
     * @param passwort Das Passwort.
     * @param rolle Die Rolle des Nutzers.
     */
    public static void addAdmin(String nutzername, String passwort)
    {
        UserLogin login = new UserLogin();
        login.nutzername = nutzername;
        login.passwort = BcryptUtil.bcryptHash(passwort);
        login.rolle = "admin";
        login.persist();

    }
    /**
     * Fügt eine neue Anmeldeinformation des Typs "admin" hinzu.
     * @param vorname Vorname.
     * @param nachname Nachname.
     */

    public static void addSkater(String vorname, String nachname, String disziplin, long alter){
        new Skater(vorname, nachname,disziplin,alter).persist();
    }

    /**
     * Gibt das UserLogin anhand des Benutzernamens zurück.
     * @param nutzername Der Benuztername.
     * @return Gibt das UserLogin zurück.
     */
    public static UserLogin get(String nutzername)
    {
        return find("nutzername", nutzername).firstResult();
    }
}
