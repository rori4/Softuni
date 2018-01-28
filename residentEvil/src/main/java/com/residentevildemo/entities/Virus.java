package com.residentevildemo.entities;

import com.residentevildemo.entities.enums.Magnitude;
import com.residentevildemo.entities.enums.Mutation;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "viruses")
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private String creator;

    private String sideEffect;

    private boolean isDeadly;

    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    private Mutation mutation;

    private double turnoverRate;

    private int hoursToTurn;

    @Enumerated(EnumType.STRING)
    private Magnitude magnitude;

    private Date releasedOn;

    @ManyToMany
    @JoinTable(name = "viruses_capitals",
    joinColumns = @JoinColumn(name = "virus_id"),
    inverseJoinColumns = @JoinColumn(name = "capital_id"))
    private Set<Capital> capitals;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSideEffect() {
        return this.sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return this.isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public double getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public int getHoursToTurn() {
        return this.hoursToTurn;
    }

    public void setHoursToTurn(int hoursToTurn) {
        this.hoursToTurn = hoursToTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
