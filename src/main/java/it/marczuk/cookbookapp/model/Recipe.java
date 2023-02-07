package it.marczuk.cookbookapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "recipe")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Set<Component> components;

    @Column(columnDefinition="TEXT")
    private String prepare;

    private String addDate;

    public Recipe(String name, String prepare, String addDate) {
        this.name = name;
        this.prepare = prepare;
        this.addDate = addDate;
    }
}
