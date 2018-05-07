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

    public String getPrizes() {
        return Prizes;
    }

    public void setPrizes(String Prizes) {
        this.Prizes = Prizes;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    public List<Actor> getActors() {
        return Actors;
    }

    public void setActors(List<Actor> Actors) {
        this.Actors = Actors;
    }

    public List<Director> getDirectors() {
        return Directors;
    }

    public void setDirectors(List<Director> Directors) {
        this.Directors = Directors;
    }

    public List<Genre> getGenres() {
        return Genres;
    }

    public void setGenres(List<Genre> Genres) {
        this.Genres = Genres;
    }

    public List<Language> getLanguages() {
        return Languages;
    }

    public void setLanguages(List<Language> Languages) {
        this.Languages = Languages;
    }
    
    private int Id;
    private String Name;
    private String Rated;
    private String Realease;
    private String PosterPath;
    private String Synopsis;
    private List<Rating> Ratings;
    private String Prizes;
    private String Duration;
    private String Studio;
    private List<Actor> Actors;
    private List<Director> Directors;
    private List<Genre> Genres;
    private List<Language> Languages;
    
}
