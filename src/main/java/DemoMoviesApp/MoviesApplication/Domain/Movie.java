package DemoMoviesApp.MoviesApplication.Domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private Double imdb;
    private Integer releasedate;
    private String director;
    private String writer;

    private Byte[] img;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private Set<Cast> cast = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    private About about;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre = new HashSet<>();


    public void setAbout(About about) {
        if (about != null) {
            this.about = about;
            about.setMovie(this);
        }
    }

    public Movie addCast(Cast cast){

        cast.setMovie(this);
        this.cast.add(cast);
        return this;

    }

}
