package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user", indexes = {@Index(name = "idx_user_email", columnList = "user_email")})
@SQLDelete(sql = "UPDATE user SET user_deleted = true WHERE user_id = ?")
@Where(clause = "user_deleted=false")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;;
}
