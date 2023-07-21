package hs.os.turnierverwaltung.control;

import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.ITurnierRepository;
import hs.os.turnierverwaltung.entity.Turnier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class TurnierManagement implements ITurnierManagement{

    @Inject
    private ITurnierRepository repository;

    @Override
    public Turnier get(int id) {
        return this.repository.get(id);
    }

    @Override
    public Collection<Turnier> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Turnier add(TurnierDTO dto) {
        return this.repository.add(dto);
    }

    @Override
    public Turnier edit(int id, TurnierDTO dto) {
        return this.repository.edit(id,dto);
    }

    @Override
    public boolean delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public boolean deleteSkater(int id, Skater skater) {
        return this.repository.deleteSkater(id,skater);
    }

    @Override
    public List<Skater> getAllSkaters(int id) {
        return this.repository.getAllSkaters(id);
    }

    @Override //id=turnierId
    public Turnier addSkater(int id, Skater skater) {
        return this.repository.addSkater(id,skater);
    }
}
