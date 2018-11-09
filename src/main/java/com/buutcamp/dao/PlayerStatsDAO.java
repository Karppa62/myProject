package com.buutcamp.dao;

import com.buutcamp.entity.Player;
import com.buutcamp.entity.PlayerStats;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PlayerStatsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void savePlayerStats(PlayerStats playerStats) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(playerStats);
    }


    @Transactional
    public PlayerStats getPlayerStats(int statsId) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from PlayerStats where stats_id=:statsId",
                PlayerStats.class);

        query.setParameter("statsId", statsId);

        List<PlayerStats> playerStats = query.getResultList();

        return playerStats.get(0);
    }
}
