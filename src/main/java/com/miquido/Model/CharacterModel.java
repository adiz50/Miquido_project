package com.miquido.Model;

import com.miquido.Validator.ValidCharacter;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
public class CharacterModel {

    @Id
    @Column(unique = true)
    private Long id;
    @Column(unique = true)
    private String name;
    @NotNull
    @ValidCharacter
    private Long height;
    private Long mass;
}
