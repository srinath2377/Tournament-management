package pl.markowski.tournament.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "submit")
@NoArgsConstructor
public class Submit {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Please enter your team name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rank", nullable = false)
    private String rank;

    @NotBlank(message = "Please enter you e-mail address")
    @Email(message = "Please enter valid e-mail address")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Please enter a nickname of team leader")
    @Column(name = "leader", nullable = false)
    private String leader;

    @Column(name = "score", columnDefinition = "Integer default 0")
    private Integer score = 0;

    @Column(name = "wins", columnDefinition = "Integer default 0")
    private Integer wins = 0;

    @Column(name = "loses", columnDefinition = "Integer default 0")
    private Integer loses = 0;

    @Column(name = "draws", columnDefinition = "Integer default 0")
    private Integer draws = 0;
}