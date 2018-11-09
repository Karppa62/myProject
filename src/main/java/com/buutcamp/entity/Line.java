package com.buutcamp.entity;

import javax.persistence.*;

@Entity
@Table(name = "line_table")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private int id;

    @Column(name = "line_name")
    private String lineName;

    @Column(name = "LD")
    private int LD;

    @Column(name = "RD")
    private int RD;

    @Column(name = "LW")
    private int LW;

    @Column(name = "RW")
    private int RW;

    @Column(name = "C")
    private int C;

    public Line() {
    }

    public Line(String lineName) {
        this.lineName = lineName;
        this.LD = this.RD = this.LW = this.RW = this.C = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getLD() {
        return LD;
    }

    public void setLD(int LD) {
        this.LD = LD;
    }

    public int getRD() {
        return RD;
    }

    public void setRD(int RD) {
        this.RD = RD;
    }

    public int getLW() {
        return LW;
    }

    public void setLW(int LW) {
        this.LW = LW;
    }

    public int getRW() {
        return RW;
    }

    public void setRW(int RW) {
        this.RW = RW;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        this.C = c;
    }
}
