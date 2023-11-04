package DemoMoviesApp.MoviesApplication.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"movie"})
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Movie movie;

    @Lob
    @Column(name = "aboutmovie")
    private String aboutMovie;

}
