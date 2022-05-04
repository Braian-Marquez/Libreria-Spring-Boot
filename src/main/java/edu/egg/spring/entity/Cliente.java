package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cliente", indexes = {@Index(name = "idx_cliente_name", columnList = "cliente_name")})
@SQLDelete(sql = "UPDATE cliente SET cliente_deleted = true WHERE cliente_id = ?")
@Where(clause = "cliente_deleted=false")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cliente_id")
    private Integer id;

    private Long documento;

    @Column(name = "cliente_name")
    private String nombre;

    private String apellido;

    private String telefono;

    @Column(name = "cliente_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;


}