package edu.egg.spring.entity;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "autor_id")
    private Integer id;

    @Column
    private String nombre;
    @Column
    private Boolean alta;


    public Autor() {
    }

    public Autor(Integer id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}