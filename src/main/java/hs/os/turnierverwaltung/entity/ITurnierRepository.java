package hs.os.turnierverwaltung.entity;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;

import java.util.Collection;
import java.util.List;

public interface ITurnierRepository {

    public Turnier get(int id);
    public Collection<Turnier> getAll();
    public Turnier add (TurnierDTO dto);
    public Turnier edit (int id, TurnierDTO dto);
    public boolean delete (int id);
    public boolean deleteSkater (int id, Skater skater);

    public List<Skater> getAllSkaters(int id); //id=turnierId
    public Turnier addSkater(int id, Skater skater);
}
