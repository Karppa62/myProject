package com.buutcamp.entity;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "stats_table")
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private int stats_id;

    @Column(name = "goals")
    private int goals;

    @Column(name = "assists")
    private int assists;

    @Column(name = "games_played")
    private int gamesPlayed;

    @Column(name = "injured")
    private boolean injured;

    //random values for dumbDataplayers
    public PlayerStats() {
        Random rand = new Random();

        this.goals = rand.nextInt(50);
        this.assists = rand.nextInt(50) + 0;
        this.gamesPlayed = rand.nextInt(100);
        this.injured = rand.nextInt(100) < 20 ? true : false;
    }

    public PlayerStats(int goals, int assists, int gamesPlayed, boolean injured) {

        this.goals = goals;
        this.assists = assists;
        this.gamesPlayed = gamesPlayed;
        this.injured = injured;
    }

    public int getStats_id() {
        return stats_id;
    }

    public void setStats_id(int stats_id) {
        this.stats_id = stats_id;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public void toggleInjured() {
        this.injured = !injured;
    }

    public String isPlayerInjured() {
        if (injured)
            return "Yes";
        else
            return "No";
    }
}
