package pl.markowski.tournament.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter a new information")
    @Column(columnDefinition = "TEXT")
    private String text;

    @Temporal(TemporalType.DATE)
    private Date date;

    @PrePersist
    private void onCreate() {
        date = new Date();
    }

    @PreUpdate
    void updatedAt() {
        date = new Date();
    }


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
