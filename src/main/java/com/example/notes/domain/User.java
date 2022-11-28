package com.example.notes.domain;

import com.example.notes.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "hibernate_sequence_us", allocationSize = 1)
    private int id;

    @Column(name = "\"uuid\"", nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "\"login\"", nullable = false, unique = true)
    @Size(min = 4, message = "Не меньше 5 знаков")
    private String login;

    @Column(name = "\"password\"")
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String password;

    @Column(name = "\"user_name\"", nullable = false)
    @Size(min = 2, message = "Не меньше 5 знаков")
    private String userName;

    @Column(name = "\"id_role\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role idRole;

    @CreatedDate
    @Column(name = "\"created\"", updatable = false)
    protected LocalDateTime created = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "\"modified\"")
    protected LocalDateTime modified = LocalDateTime.now();

    public User(String login, String password, String userName, Role idRole) {
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.idRole = idRole;
    }
}
