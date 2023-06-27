package br.com.akaji.dojo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Lookup {
    @Id
    private Integer id;
    @Column(nullable = false)
    private String designation;
    @Column(nullable = false)
    private String value;

}
