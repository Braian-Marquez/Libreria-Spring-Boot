package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "libro", indexes = {@Index(name="idx_libro_isbn",columnList="libro_isbn")})
public class Libro {

    @Id
    @GeneratedValue(strategy  =IDENTITY)
    @Column(name="libro_id")
    private Integer id;


    @Column(name = "libro_isbn")
    private String isbn;

    @Column(name = "libro_nombre")
    private String nombre;

    @Column(name = "libro_anio", columnDefinition = "YEAR")
    private Integer anio;

    @Column(name = "libro_ejemplares")
    private Integer ejemplares;

    private Integer ejemplaresPrestados;

    private Integer ejemplaresRestantes;

    @Column(name = "libro_alta")
    private Boolean alta;

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id", nullable = false)
    private Editorial editorial;

}
