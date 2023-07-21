package hs.os.skaterverwaltung.control;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;


import java.util.Collection;

public interface ISkaterManagement {
    public Skater get(int id);
    public Collection<Skater> getAll();
    public Skater add(SkaterDTO dto);
    public Skater edit(int id, SkaterDTO dto);
    public boolean delete(int id);

}
