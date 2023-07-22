package hs.os.turnierverwaltung.entity;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;

import java.util.Collection;
import java.util.List;

public interface ITurnierRepository {

    public Turnier get(long id);
    public Collection<Turnier> getAll();
    public Turnier add (TurnierDTO dto);
    public Turnier edit (long id, TurnierDTO dto);
    public boolean delete (long id);

    public Collection<Turnier> searchByTurnierName(String search);
    public Collection<Turnier> getTurnierOfSkater(long skaterId);
}
