package DemoMoviesApp.MoviesApplication.Services;

import DemoMoviesApp.MoviesApplication.Commands.MovieCommand;
import DemoMoviesApp.MoviesApplication.Converters.MovieCommandToMovie;
import DemoMoviesApp.MoviesApplication.Converters.MovieToMovieCommand;
import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieToMovieCommand movieToMovieCommand;

    private final MovieCommandToMovie movieCommandToMovie;

    public MovieServiceImpl(MovieRepository movieRepository, MovieToMovieCommand movieToMovieCommand, MovieCommandToMovie movieCommandToMovie) {
        this.movieRepository = movieRepository;
        this.movieToMovieCommand = movieToMovieCommand;
        this.movieCommandToMovie = movieCommandToMovie;
    }

    @Override
    public Set<Movie> getMovies() {

        log.debug("Into a Service!");

        Set<Movie> movieSet = new HashSet<>();
        movieRepository.findAll().iterator().forEachRemaining(movieSet::add);

        return movieSet;
    }

    @Override
    public Movie findById(Long l) {

        Optional<Movie> movieOptional = movieRepository.findById(l);

        if(!movieOptional.isPresent()){

            throw new RuntimeException("Movie With Given ID Does'not Exist!");

        }


        return movieOptional.get();

    }

    @Override
    @Transactional
    public MovieCommand findMovieCommandById(Long l) {

      return movieToMovieCommand.convert(findById(l));

    }

    @Override
    @Transactional
    public MovieCommand saveMovieCommand(MovieCommand command) {
        Movie detachedMovie = movieCommandToMovie.convert(command);

        Movie savedMovie = movieRepository.save(detachedMovie);
        log.debug("Saved Movie ID: " + savedMovie.getId());

        return movieToMovieCommand.convert(savedMovie);

    }

    @Override
    public void deleteById(Long delete) {

        movieRepository.deleteById(delete);

    }
}
