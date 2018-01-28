package com.example.demo.models;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class AddGiveawayModel {
    private Long id;
    private String sponsor;
    private String sweepstakesName;
    private String description;

    @NotNull

    private String url;

    private Date endDate;
    private String frequesncy;
    private String restrictions;
    private String prize;
    private Double prizeValue;
    private String personalName;
    private String email;
    private String giveawayUrl;
    private String giveawayImgUrl;
    private String comment;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSponsor() {
        return this.sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getSweepstakesName() {
        return this.sweepstakesName;
    }

    public void setSweepstakesName(String sweepstakesName) {
        this.sweepstakesName = sweepstakesName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFrequesncy() {
        return this.frequesncy;
    }

    public void setFrequesncy(String frequesncy) {
        this.frequesncy = frequesncy;
    }

    public String getRestrictions() {
        return this.restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getPrize() {
        return this.prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Double getPrizeValue() {
        return this.prizeValue;
    }

    public void setPrizeValue(Double prizeValue) {
        this.prizeValue = prizeValue;
    }

    public String getPersonalName() {
        return this.personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGiveawayUrl() {
        return this.giveawayUrl;
    }

    public void setGiveawayUrl(String giveawayUrl) {
        this.giveawayUrl = giveawayUrl;
    }

    public String getGiveawayImgUrl() {
        return this.giveawayImgUrl;
    }

    public void setGiveawayImgUrl(String giveawayImgUrl) {
        this.giveawayImgUrl = giveawayImgUrl;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
