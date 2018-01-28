package com.example.demo.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "giveaways")
public class Giveaway {
    private Long id;
    private String sponsor;
    private String sweepstakesName;
    private String description;
    private String url;
    private Date endDate;
    private String frequency;
    private String restrictions;
    private String prize;
    private Double prizeValue;
    private String personalName;
    private String email;
    private String giveawayImgUrl;
    private String comment;

    public Giveaway() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
