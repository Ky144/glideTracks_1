package hs.os.skaterverwaltung.entity;
import java.util.Collection;
import hs.os.skaterverwaltung.control.dto.SkaterDTO;

public interface ISkaterRepository {
    public Skater get(long id);
    public Collection<Skater> getAll();
    public Skater add (SkaterDTO dto);
    public Skater edit (long id, SkaterDTO dto);
    public boolean delete (long id);


}
