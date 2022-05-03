package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE libro SET libro_deleted = true WHERE libro_id = ?")
@Where(clause = "libro_deleted=false")
@Table(name = "libro", indexes = {@Index(name="idx_libro_name",columnList="libro_name")})
public class Libro {

    @Id
    @GeneratedValue(strategy  =IDENTITY)
    @Column(name="libro_id")
    private Integer id;


    @Column(name = "libro_isbn")
    private String isbn;

    @Column(name="libro_name")
    private String nombre;

    @Column(name = "libro_anio",  columnDefinition = "DATE", nullable = false)
    private LocalDate anio;

    @Column(name = "libro_ejemplares")
    private Integer ejemplares;

    private Integer ejemplaresPrestados;

    private Integer ejemplaresRestantes;


    @Column(name = "libro_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;;

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editorial_id", referencedColumnName = "editorial_id", nullable = false)
    private Editorial editorial;

}
