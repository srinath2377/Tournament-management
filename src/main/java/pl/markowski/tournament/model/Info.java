package pl.markowski.tournament.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter new information")
    @Column(columnDefinition = "TEXT")
    private String text;

    public Info() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}