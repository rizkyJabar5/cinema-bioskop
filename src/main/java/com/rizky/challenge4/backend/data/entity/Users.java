package com.rizky.challenge4.backend.data.entity;

import com.rizky.challenge4.backend.data.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Users extends AbstractEntity{

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

    public Users(String username, String email, String address, String password) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "\nUser [\nusername=" + username +
                ", \nemail=" + email +
                ",\naddress=" + address +
                ",\npassword=" + password +
                ",\njoin date=" + joinDate +
                "\n]";
    }
}
