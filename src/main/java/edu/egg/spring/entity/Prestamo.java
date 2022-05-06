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
@Table(name = "prestamo")
@SQLDelete(sql = "UPDATE prestamo SET prestamo_deleted = true WHERE prestamo_id = ?")
@Where(clause = "prestamo_deleted=false")
@Getter
@Setter
@NoArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "prestamo_id")
    private Integer id;

    @Column(name = "prestamo",  columnDefinition = "DATE", nullable = false)
    private LocalDate fechaPrestamo;

    @Column(name = "devolucion",  columnDefinition = "DATE", nullable = false)
    private LocalDate fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "libro_libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "cliente_cliente_id")
    private Cliente cliente;

    private Integer aPrestar;

    @Column(name = "prestamo_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;



    }
