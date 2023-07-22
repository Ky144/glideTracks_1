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
    public Turnier get(long id) {
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
    public Turnier edit(long id, TurnierDTO dto) {
        return this.repository.edit(id,dto);
    }

    @Override
    public boolean delete(long id) {
        return this.repository.delete(id);
    }

    @Override
    public Collection<Turnier> searchByTurnierName(String search) {
        return this.repository.searchByTurnierName(search);
    }

    @Override
    public Collection<Turnier> getTurnierOfSkater(long skaterId) {
        return this.repository.getTurnierOfSkater(skaterId);
    }


}
