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

    private List<String> Genres;
    private List<String> Actors;
    private List<String> Years;
    private List<String> Directors;
    private List<String> Studios;
    
    public List<String> getGenres() {
        return Genres;
    }

    public void setGenres(List<String> Genres) {
        this.Genres = Genres;
    }

    public List<String> getActors() {
        return Actors;
    }

    public void setActors(List<String> Actors) {
        this.Actors = Actors;
    }

    public List<String> getYears() {
        return Years;
    }

    public void setYears(List<String> Years) {
        this.Years = Years;
    }

    public List<String> getDirectors() {
        return Directors;
    }

    public void setDirectors(List<String> Directors) {
        this.Directors = Directors;
    }

    public List<String> getStudios() {
        return Studios;
    }

    public void setStudios(List<String> Studios) {
        this.Studios = Studios;
    }    
 
    public String getGenresId() {
        return String.join(",", Genres);
    }
    
    public String getActorsId() {
        return String.join(",", Actors);
    }
    
    public String getDirectorsId() {
        return String.join(",", Directors);
    }

    public String getYearsId() {
        return String.join(",", Years);
    }
    
    public String getStudiosId() {
        return String.join(",", Studios);
    }
}
