/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author MANUEL
 */
public class UserSystem implements Serializable{
    private int usuario_id;
    private String usuario_nombre;
    private String usuario_apellido_materno;
    private String usuario_apellido_paterno;
    private String usuario_dni;
    private String usuario_password;
    private String usuario_correo;
    private String usuario_celular;
    private String usuario_telefono;
    private String usuario_direccion;
    private String usuario_tipo;
    private String usuario_estado;
    private Date usuario_fech_registro;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_apellido_materno() {
        return usuario_apellido_materno;
    }

    public void setUsuario_apellido_materno(String usuario_apellido_materno) {
        this.usuario_apellido_materno = usuario_apellido_materno;
    }

    public String getUsuario_apellido_paterno() {
        return usuario_apellido_paterno;
    }

    public void setUsuario_apellido_paterno(String usuario_apellido_paterno) {
        this.usuario_apellido_paterno = usuario_apellido_paterno;
    }

    public String getUsuario_dni() {
        return usuario_dni;
    }

    public void setUsuario_dni(String usuario_dni) {
        this.usuario_dni = usuario_dni;
    }

    public String getUsuario_password() {
        return usuario_password;
    }

    public void setUsuario_password(String usuario_password) {
        this.usuario_password = usuario_password;
    }

    public String getUsuario_correo() {
        return usuario_correo;
    }

    public void setUsuario_correo(String usuario_correo) {
        this.usuario_correo = usuario_correo;
    }

    public String getUsuario_celular() {
        return usuario_celular;
    }

    public void setUsuario_celular(String usuario_celular) {
        this.usuario_celular = usuario_celular;
    }

    public String getUsuario_telefono() {
        return usuario_telefono;
    }

    public void setUsuario_telefono(String usuario_telefono) {
        this.usuario_telefono = usuario_telefono;
    }

    public String getUsuario_direccion() {
        return usuario_direccion;
    }

    public void setUsuario_direccion(String usuario_direccion) {
        this.usuario_direccion = usuario_direccion;
    }

    public String getUsuario_tipo() {
        return usuario_tipo;
    }

    public void setUsuario_tipo(String usuario_tipo) {
        this.usuario_tipo = usuario_tipo;
    }

    public String getUsuario_estado() {
        return usuario_estado;
    }

    public void setUsuario_estado(String usuario_estado) {
        this.usuario_estado = usuario_estado;
    }

    public Date getUsuario_fech_registro() {
        return usuario_fech_registro;
    }

    public void setUsuario_fech_registro(Date usuario_fech_registro) {
        this.usuario_fech_registro = usuario_fech_registro;
    }
    
    
    
}
