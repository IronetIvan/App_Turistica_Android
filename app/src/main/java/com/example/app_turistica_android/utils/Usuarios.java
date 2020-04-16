package com.example.app_turistica_android.utils;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String email,nombre,nombre_usuario,tipo,uid;
    private boolean intro;

    public Usuarios() {
    }

    public Usuarios(String email, String nombre, String nombre_usuario, String tipo, String uid, boolean intro) {
        this.email = email;
        this.nombre = nombre;
        this.nombre_usuario = nombre_usuario;
        this.tipo = tipo;
        this.uid = uid;
        this.intro = intro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isIntro() {
        return intro;
    }

    public void setIntro(boolean intro) {
        this.intro = intro;
    }
}
