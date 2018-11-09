package com.buutcamp.dao;

import com.buutcamp.entity.Line;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LineDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Line> getLines() {
        Session session = sessionFactory.getCurrentSession();

        Query<Line> query = session.createQuery("from Line", Line.class);

        return query.getResultList();
    }

    @Transactional
    public void createLines() {
        List<Line> lines = getLines();

        if (lines.size() == 0 || lines == null) {

            Session session = sessionFactory.getCurrentSession();

            session.save(new Line("First line"));
            session.save(new Line("Second line"));
            session.save(new Line("Third line"));
            session.save(new Line("Fourth line"));

        }
    }

    @Transactional
    public Line getLine(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Line.class, id);
    }

    @Transactional
    public void saveLine(Line line) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(line);
    }

    @Transactional
    public void removePlayerFromRoster(int playerId) {

        Session session = sessionFactory.getCurrentSession();

        List<Line> lines = getLines();

        boolean updateLine = false;

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.getLD() == playerId) {
                line.setLD(0);
                updateLine = true;
            }
            if (line.getRD() == playerId) {
                line.setRD(0);
                updateLine = true;
            }
            if (line.getLW() == playerId) {
                line.setLW(0);
                updateLine = true;
            }
            if (line.getRW() == playerId) {
                line.setRW(0);
                updateLine = true;
            }
            if (line.getC() == playerId) {
                line.setC(0);
                updateLine = true;
            }
            if (updateLine)
                saveLine(line);
        }
    }
}
