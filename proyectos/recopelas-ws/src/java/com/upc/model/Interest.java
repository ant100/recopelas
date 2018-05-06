/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.model;

import com.upc.entity.Actor;
import com.upc.entity.Director;
import com.upc.entity.Genre;
import java.util.List;

/**
 *
 * @author leo
 */
public class Interest {
    private List<Genre> Genres;
    private List<Actor> Actors;
    private List<Integer> Years;
    private List<Director> Directors;
    
    public List<Actor> getActors() {
        return Actors;
    }

    public void setActors(List<Actor> Actors) {
        this.Actors = Actors;
    }

    public List<Integer> getYears() {
        return Years;
    }

    public void setYears(List<Integer> Years) {
        this.Years = Years;
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
}
