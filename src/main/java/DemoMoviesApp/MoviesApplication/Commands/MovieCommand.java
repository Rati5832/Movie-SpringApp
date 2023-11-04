package DemoMoviesApp.MoviesApplication.Commands;


import DemoMoviesApp.MoviesApplication.Domain.About;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MovieCommand {

    private Long id;


    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @Min(1)
    @Max(10)
    private double imdb;

    private Integer releasedate;


    private String director;
    private String writer;

    private Set<CastCommand> cast = new HashSet<>();
    private AboutCommand about;
    private Set<GenreCommand> genre = new HashSet<>();
    private Byte[] img;


}
