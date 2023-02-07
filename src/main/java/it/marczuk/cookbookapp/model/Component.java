package it.marczuk.cookbookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "components")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameAndGrammage;

    @ManyToOne
    @JsonIgnore
    private Recipe recipe;

    public Component(String nameAndGrammage) {
        this.nameAndGrammage = nameAndGrammage;
    }

    @Override
    public String toString() {
        return nameAndGrammage;
    }
}
