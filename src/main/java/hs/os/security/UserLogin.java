package hs.os.security;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
// @Table(name = "user_login", schema = "SEC")
@UserDefinition
public class UserLogin extends PanacheEntity
{
    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String role;

    public static void addAdmin(String username, String password, String role)
    {
        UserLogin login = new UserLogin();
        login.username = username;
        login.password = BcryptUtil.bcryptHash(password);
        login.role = role;
        login.persist();


    }

    public static UserLogin get(String username)
    {
        return find("username", username).firstResult();
    }
}
