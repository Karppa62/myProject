package com.buutcamp.dao;

import com.buutcamp.entity.Player;
import com.buutcamp.entity.PlayerStats;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LineDAO lineDAO;

    @Autowired
    private PlayerStatsDAO playerStatsDAO;

    @Transactional
    public List<Player> getPlayers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Player> query = session.createQuery("from Player p order by p.lastName, p.firstName, p.jerseyNumber", Player.class);

        return query.getResultList();
    }

    @Transactional
    public Player getPlayer(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Player.class, id);
    }

    @Transactional
    public String getPlayerName(int playerId) {

        Session session = sessionFactory.getCurrentSession();

        Query<Player> query = session.createQuery("from Player where id=:playerId",
                Player.class);

        query.setParameter("playerId", playerId);

        List<Player> player = query.getResultList();

        if (player.size() != 0)
            return player.get(0).getJerseyNumber() + " " + player.get(0).getFirstName() + " " + player.get(0).getLastName();
        else
            return "-";
    }

    @Transactional
    public void savePlayer(Player player) {

        Session session = sessionFactory.getCurrentSession();

        Query<Player> query = session.createQuery("from Player where id=:playerId",
                Player.class);

        query.setParameter("playerId", player.getId());

        List<Player> player_data = query.getResultList();

        if (player_data.size() == 0 || player_data.get(0).getPlayerStats() == null) {
            player.setPlayerStats(new PlayerStats(0, 0, 0 ,false));
            session.save(player);
        }
        else {
            player.setPlayerStats(playerStatsDAO.getPlayerStats(player_data.get(0).getPlayerStats().getStats_id()));

            session.merge(player);
        }

    }

    @Transactional
    public void deletePlayer(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Player where id=:playerId");

        query.setParameter("playerId", id);

        query.executeUpdate();

        lineDAO.removePlayerFromRoster(id);
    }

    @Transactional
    public List<Player> searchPlayers(String searchVal, String position) {
        Session session = sessionFactory.getCurrentSession();

        Query<Player> query;

        if (searchVal != null && searchVal.trim().length() > 0) {

            if (position == "") {
                query = session.createQuery("from Player p where lower(lastName) like :searchValue " +
                        "order by p.lastName, p.firstName, p.jerseyNumber", Player.class);

                //Using wildcards, to allow people to search with only part of the name.
                query.setParameter("searchValue", searchVal.toLowerCase() + "%");
            }
            else {
                //using lower() to make sure that capital letters won't affect search results
                query = session.createQuery("from Player p where position=:position lower(lastName) " +
                        "like :searchValue order by p.lastName, p.firstName, p.jerseyNumber", Player.class);

                //Using wildcards, to allow people to search with only part of the name.
                query.setParameter("searchValue", searchVal.toLowerCase() + "%");
                query.setParameter("position", position);
            }

        } else {
            if (position == "") {
                //if you for some reason try to search with "nothing", return everything in the list
                query = session.createQuery("from Player p order by p.lastName, p.firstName, p.jerseyNumber", Player.class);
            }
            else {
                query = session.createQuery("from Player p where position=:position order by p.lastName, p.firstName, p.jerseyNumber", Player.class);
                query.setParameter("position", position);
            }
        }
        return query.getResultList();
    }

    @Transactional
    public List<Player> getEligiblePlayersByPosition(String position) {

        Session session = sessionFactory.getCurrentSession();

        Query<Player> query = session.createQuery("from Player p where position=:position order by p.jerseyNumber",
                Player.class);

        query.setParameter("position", position);

        List<Player> players = query.getResultList();
        List<Player> tempPlayers = new ArrayList<Player>();
        for (Player player : players) {
            if (!player.getPlayerStats().isInjured()) {
                tempPlayers.add(player);
            }
        }

        return tempPlayers;
    }

    @Transactional
    public void addDumbDataPlayers() {

        List<Player> players = getPlayers();

        if (players.size() == 0 || players == null) {

            Session session = sessionFactory.getCurrentSession();

            session.save(new Player("Sebastian", "Aho", 20, "F"));
            session.save(new Player("Lasse", "Kukkonen", 5, "D"));
            session.save(new Player("Hannu", "Hanhi", 79, "D"));
            session.save(new Player("Aku", "Ankka", 31, "F"));
            session.save(new Player("Saku", "Koivu", 11, "F"));

            session.save(new Player("Oliver", "Suni", 7, "F"));
            session.save(new Player("Jesse", "Hanhi", 88, "F"));
            session.save(new Player("Petr", "Tenkrat", 62, "F"));
            session.save(new Player("Reijo", "Ruotsalainen", 10, "D"));
            session.save(new Player("Teemu", "Kivihalme", 61, "D"));

            session.save(new Player("Mikko", "Lehtonen", 64, "F"));
            session.save(new Player("Jussi", "Jokinen", 36, "F"));
            session.save(new Player("Mikko", "Koivu", 9, "F"));
            session.save(new Player("Mikko", "Lehtonen", 28, "D"));
            session.save(new Player("Jason", "Demers", 55, "D"));

            session.save(new Player("Joonas", "Donskoi", 72, "F"));
            session.save(new Player("Julius", "Junttila", 13, "F"));
            session.save(new Player("Joonas", "Kemppainen", 23, "F"));
            session.save(new Player("Jani", "Hakanpää", 58, "D"));
            session.save(new Player("Markus", "Nutivaara", 65, "D"));

            session.save(new Player("Ivan", "Huml", 71, "F"));
            session.save(new Player("Jari", "Viuhkola", 24, "F"));
            session.save(new Player("Mikael", "Ruohomaa", 33, "F"));
            session.save(new Player("Ville", "Pokka", 2, "D"));
            session.save(new Player("Shaun", "Heska", 26, "D"));
//
//            session.save(new Player("Pekka", "Rinne", 35, "G"));
//            session.save(new Player("Justus", "Annunen", 1, "G"));
        }
    }
}
