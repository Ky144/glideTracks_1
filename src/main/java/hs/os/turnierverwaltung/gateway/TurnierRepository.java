package hs.os.turnierverwaltung.gateway;

import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.ITurnierRepository;
import hs.os.turnierverwaltung.entity.Turnier;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class TurnierRepository implements ITurnierRepository, PanacheRepository<Turnier> {

    @Override
    public Turnier get(int id) {
        return find("id",id).firstResult();
    }

    @Override
    public Collection<Turnier> getAll() {
        return listAll();
    }

    @Override
    public Turnier add(TurnierDTO dto) {
        Turnier turnier= new Turnier(dto.name);
        persist(turnier);
        return turnier;
    }

    @Override
    public Turnier edit(int id, TurnierDTO dto) {
        Turnier turnier=find("id",id).firstResult();
        if(turnier != null){
            turnier.setName(dto.name);
            turnier.setDate(dto.date);
            turnier.setOrt(dto.ort);

        }
        return turnier;
    }

    @Override
    public boolean delete(int id) {
        long deletedRowsCount = delete("id",id);
        if(deletedRowsCount == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSkater(int id, Skater skater) {
        Turnier turnier = find("id", id).firstResult();
        if (turnier != null) {
            // Überprüfen, ob der Skater im Turnier vorhanden ist
            if (turnier.getSkaters().contains(skater)) {
                // Skater aus dem Turnier entfernen
                turnier.removeSkater(skater);
                return true;
            } else {
                // Skater ist nicht im Turnier vorhanden
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Skater> getAllSkaters(int id) {
        Turnier turnier = get(id);
        if (turnier != null) {
            return turnier.getSkaters();
        }
        return null;
    }
@Override
    public Turnier addSkater(int id, Skater skater) {
        Turnier turnier = get(id);
        if (turnier != null) {
            // Füge den Skater zum Turnier hinzu
            turnier.addSkater(skater);
            return turnier;
        }
        return null;
    }
}
