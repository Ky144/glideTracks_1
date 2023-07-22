package hs.os.skaterverwaltung.control;

import hs.os.skaterverwaltung.control.dto.SkaterDTO;
import hs.os.skaterverwaltung.entity.Skater;


import java.util.Collection;

public interface ISkaterManagement {
    public Skater get(long id);
    public Collection<Skater> getAll();
    public Skater add(SkaterDTO dto);
    public Skater edit(long id, SkaterDTO dto);
    public boolean delete(long id);

}
