package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = "email"),
        @UniqueConstraint(name = "username", columnNames = "username")
})
public class Users extends AbstractEntity {

    @Column(name ="username")
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "join_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate = new Date();


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Roles> roles = new HashSet<>();

    public Users(String username, String email, String address, String password, Collection<Roles> roles) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.roles = roles;
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", roles=" + roles +
                '}';
    }
}
