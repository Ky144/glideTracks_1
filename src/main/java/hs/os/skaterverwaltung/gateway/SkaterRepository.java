package hs.os.skaterverwaltung.gateway;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.ISkaterRepository;
import hs.os.skaterverwaltung.entity.Skater;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * Datenbankanbindung für Skater Objekte
 */
@ApplicationScoped
public class SkaterRepository implements ISkaterRepository,PanacheRepository<Skater> {


    @Override
    public Skater get(long id) {
        return findById(id);
    }

    @Override
    public Collection<Skater> getAll() {
        return listAll();
    }

    @Override
    public Skater add(SkaterDTO dto) {
        Skater skater= new Skater(dto.vorname,dto.nachname,dto.disziplin,dto.alter);
        persist(skater);
        return skater;
    }

    @Override
    public Skater edit(long id, SkaterDTO dto) {

        Skater skater = findById(id);
        if(skater !=null){
            skater.setVorname(dto.vorname);
            skater.setNachname(dto.nachname);
            skater.setDisziplin(dto.disziplin);
            skater.setAlter(dto.alter);
        }
        return skater;
    }

    @Override
    public boolean delete(long id) {
       return deleteById(id);
    }
}
