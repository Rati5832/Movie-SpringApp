package DemoMoviesApp.MoviesApplication.Services;

import DemoMoviesApp.MoviesApplication.Commands.MovieCommand;
import DemoMoviesApp.MoviesApplication.Domain.Movie;

import java.util.Set;

public interface MovieService {

    Set<Movie> getMovies();

    Movie findById(Long l);

    MovieCommand findMovieCommandById(Long l);

    MovieCommand saveMovieCommand(MovieCommand command);

    void deleteById(Long delete);



}
