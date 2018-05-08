/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.model;

import com.upc.entity.Actor;
import com.upc.entity.Director;
import com.upc.entity.Genre;
import com.upc.entity.Studio;
import java.time.Year;
import java.util.List;

/**
 *
 * @author leo
 */
public class Filter {
    
    private List<Actor> Actors;
    private List<Director> Directors;
    private List<Year> Years;
    private List<Genre> Genres;
    private List<Studio> Studios;

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

    public List<Year> getYears() {
        return Years;
    }

    public void setYears(List<Year> Years) {
        this.Years = Years;
    }

    public List<Genre> getGenres() {
        return Genres;
    }

    public void setGenres(List<Genre> Genres) {
        this.Genres = Genres;
    }

    public List<Studio> getStudios() {
        return Studios;
    }

    public void setStudios(List<Studio> Studios) {
        this.Studios = Studios;
    }
    
    
}
