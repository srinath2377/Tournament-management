package pl.markowski.tournament.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter new information")
    @Column(columnDefinition = "TEXT")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date timestamp;

    @PrePersist
    private void onCreate() {
        timestamp = new Date();
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
