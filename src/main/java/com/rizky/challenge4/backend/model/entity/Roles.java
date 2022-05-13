package com.rizky.challenge4.backend.model.entity;

import com.rizky.challenge4.backend.model.AbstractEntity;
import com.rizky.challenge4.backend.model.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "rolename")
    private ERole roleName;

    private String description;
}
