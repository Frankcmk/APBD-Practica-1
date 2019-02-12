package com.edu.mx.practica.uno.persona;

import lombok.Data;

@Data
public class Persona {

    //Atributos de una persona
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String genero;
    private String nacionalidad;

    public Persona(String nom,String aP, String aM,String gen,String nacio){
        this.nombre = nom;
        this.apellidoPaterno = aP;
        this.apellidoMaterno = aM;
        this.genero = gen;
        this.nacionalidad = nacio;
    }

}
