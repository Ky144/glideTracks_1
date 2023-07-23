package hs.os.turnierverwaltung.gateway;


import hs.os.skaterverwaltung.entity.Skater;
import hs.os.turnierverwaltung.control.dto.TurnierDTO;
import hs.os.turnierverwaltung.entity.ITurnierRepository;
import hs.os.turnierverwaltung.entity.Turnier;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*
Implementierungsklasse für das ITurnierRepository-Interface, das die Datenbankoperationen für die Turnier-Entität bereitstellt.
 */
@ApplicationScoped
public class TurnierRepository implements ITurnierRepository, PanacheRepository<Turnier> {
    @Inject
    private EntityManager em;

    @Override
    public Turnier get(long id) {

        return findById(id);
    }

    @Override
    public Collection<Turnier> getAll() {
        return listAll();
    }

    @Override
    public Turnier add(TurnierDTO dto) {
        Turnier turnier= new Turnier(dto.name,dto.ort,dto.name);
        persist(turnier);
        return turnier;
    }

    @Override
    public Turnier edit(long id, TurnierDTO dto) {
        Turnier turnier=findById(id);
        if(turnier != null){
            turnier.setName(dto.name);
            turnier.setDate(dto.datum);
            turnier.setOrt(dto.ort);
        }
        return turnier;
    }

    @Override
    public boolean delete(long id) {

        return deleteById(id);
    }

    @Override
    public Collection<Turnier> searchByTurnierName(String search)
    {
        return find("name", search).list();
    }

    @Override
    public Collection<Turnier> getTurnierOfSkater(long skaterId) {
        Skater skater = this.em.getReference(Skater.class, skaterId);


        if (skater == null) {
            // Wenn der Skater mit der angegebenen ID nicht gefunden wurde,
            // eine leere Liste zurückgeben oder eine Fehlerbehandlung durchführen.
            // Hier wird eine leere Liste zurückgegeben.
            return Collections.emptyList();
        }
        Collection<Turnier> allSkater = listAll();
        Collection<Turnier> turniereOfStudent = new ArrayList<Turnier>();

        return turniereOfStudent;
    }

    @Override
    public boolean signSkaterIn(long turnierId, long skaterId) {
        Turnier turnier = findById(turnierId);
        Skater skater = em.find(Skater.class, skaterId);

        if (turnier == null || skater == null) {
            // Wenn das Turnier oder der Skater nicht gefunden wurde,
            return false;
        }

        // Überprüfen, ob der Skater bereits angemeldet ist.
        if (turnier.getSkaters().contains(skater)) {
            // Der Skater ist bereits angemeldet, keine Aktion erforderlich.
            return true; // Zurückgeben, dass die Anmeldung erfolgreich war (keine Änderungen vorgenommen).
        }

        // Skater zum Turnier hinzufügen.
        turnier.addSkater(skater);
        persist(turnier);
        return true;
    }

    @Override
    public boolean signSkaterOut(long turnierId, long skaterId) {
        Turnier turnier = findById(turnierId);
        Skater skater = em.find(Skater.class, skaterId);

        if (turnier == null || skater == null) {
            // Wenn das Turnier oder der Skater nicht gefunden wurde,
            // die Abmeldung nicht durchführen und false zurückgeben.
            return false;
        }

        // Überprüfen, ob der Skater angemeldet ist.
        if (!turnier.getSkaters().contains(skater)) {
            // Der Skater ist nicht angemeldet, keine Aktion erforderlich.
            return true; // Zurückgeben, dass die Abmeldung erfolgreich war (keine Änderungen vorgenommen).
        }

        // Skater vom Turnier entfernen.
        turnier.removeSkater(skater);
        persist(turnier);
        return true;
    }

}


