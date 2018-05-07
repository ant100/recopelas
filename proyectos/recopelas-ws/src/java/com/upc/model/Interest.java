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
import java.util.stream.Collectors;

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

    public String getGenresId() {
        List<Integer> list = Genres.stream().map(Genre::getId).collect(Collectors.toList());
        return list.toString().substring(1, list.toString().length()-1);
    }
    
    public String getActorsId() {
        List<Integer> list = Actors.stream().map(Actor::getId).collect(Collectors.toList());
        return list.toString().substring(1, list.toString().length()-1);
    }
    
    public String getDirectorsId() {
        List<Integer> list = Directors.stream().map(Director::getId).collect(Collectors.toList());
        return list.toString().substring(1, list.toString().length()-1);
    }

    public String getYearsId() {
        return Years.toString().substring(1, Years.toString().length()-1);
    }
}
