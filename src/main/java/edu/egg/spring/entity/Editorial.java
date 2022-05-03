package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "editorial", indexes = {@Index(name = "idx_editorial_name", columnList = "editorial_name")})
@SQLDelete(sql = "UPDATE editorial SET editorial_deleted = true WHERE editorial_id = ?")
@Where(clause = "editorial_deleted=false")
@Getter
@Setter
@NoArgsConstructor
public class Editorial {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "editorial_id")
    private Integer id;
    @Column(name="editorial_name")
    private String nombre;


    @Column(name = "editorial_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;;



}
