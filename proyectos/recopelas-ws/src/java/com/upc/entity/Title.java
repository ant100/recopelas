/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.entity;

import java.util.List;

/**
 *
 * @author leo
 */
public class Title {

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    public String getRealease() {
        return Realease;
    }

    public void setRealease(String Realease) {
        this.Realease = Realease;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String PosterPath) {
        this.PosterPath = PosterPath;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String Synopsis) {
        this.Synopsis = Synopsis;
    }
    
    public List<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Rating> Ratings) {
        this.Ratings = Ratings;
    }

    public String getPrices() {
        return Prices;
    }

    public void setPrices(String Prices) {
        this.Prices = Prices;
    }
    
    private int Id;
    private String Name;
    private String Rated;
    private String Realease;
    private String PosterPath;
    private String Synopsis;
    private List<Rating> Ratings;
    private String Prices;
    
}
