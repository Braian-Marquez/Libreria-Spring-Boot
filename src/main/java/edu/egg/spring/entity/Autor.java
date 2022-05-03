package edu.egg.spring.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "autor", indexes = {@Index(name = "idx_autor_name", columnList = "autor_name")})
@SQLDelete(sql = "UPDATE autor SET autor_deleted = true WHERE autor_id = ?")
@Where(clause = "autor_deleted=false")
@Getter
@Setter
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "autor_id")
    private Integer id;

    @Column(name="autor_name")
    private String nombre;

    @Column(name = "autor_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;;

}
