package DemoMoviesApp.MoviesApplication.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name ="moviecast")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"movie"})
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;


    @ManyToOne
    private Movie movie;


    public Cast() {
    }

    public Cast(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
