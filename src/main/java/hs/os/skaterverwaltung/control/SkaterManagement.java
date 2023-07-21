package hs.os.skaterverwaltung.control;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.ISkaterRepository;
import hs.os.skaterverwaltung.entity.Skater;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
@ApplicationScoped
public class SkaterManagement implements ISkaterManagement{
    @Inject
    private ISkaterRepository repository;

    @Override
    public Skater get(int id) {
        return this.repository.get(id);
    }

    @Override
    public Collection<Skater> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Skater add(SkaterDTO dto) {
        return this.repository.add(dto);
    }

    @Override
    public Skater edit(int id, SkaterDTO dto) {
        return this.repository.edit(id, dto);
    }

    @Override
    public boolean delete(int id) {
        return this.repository.delete(id);
    }
}
