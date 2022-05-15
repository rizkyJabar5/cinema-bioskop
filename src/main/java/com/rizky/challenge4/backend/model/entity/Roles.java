package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.AbstractEntity;
import com.rizky.challenge4.backend.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
@AllArgsConstructor
public class Roles extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "rolename")
    private ERole roleName;

    public Roles(ERole roleName) {
        this.roleName = roleName;
    }

    private String description;
}
