package com.residentevildemo.models;

import com.residentevildemo.annotatons.IsInTheFuture;
import com.residentevildemo.entities.enums.Magnitude;
import com.residentevildemo.entities.enums.Mutation;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class VirusBindingModel {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, max = 30, message = "Invalid Name size.")
    private String name;

    @NotBlank(message = "Cannot be blank")
    @Size(min = 5,max = 100, message = "Invalid Desc size.")
    private String description;

    @Pattern(regexp = "^.*[Cc]orp.*$", message = "Does not contain Corp")
    private String creator;

    @Size(max = 50)
    private String sideEffect;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull(message = "Should have a mutation")
    private Mutation mutation;

    @Range(min = 0, max = 100)
    private double turnoverRate;

    @Range(min = 0, max = 12)
    private int hoursToTurn;

    @NotNull(message = "Cannot be null")
    private Magnitude magnitude;

    @IsInTheFuture(message = "This should be a valid date in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releasedOn;

    @NotEmpty(message = "Should pick capitals")
    private String[] capitals;

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

    public String[] getCapitals() {
        return this.capitals;
    }

    public void setCapitals(String[] capitals) {
        this.capitals = capitals;
    }
}
