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
}
