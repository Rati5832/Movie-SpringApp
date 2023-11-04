package DemoMoviesApp.MoviesApplication.Services;

import DemoMoviesApp.MoviesApplication.Commands.CastCommand;
import DemoMoviesApp.MoviesApplication.Converters.CastCommandToCast;
import DemoMoviesApp.MoviesApplication.Converters.CastToCastCommand;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@Service
public class CastServiceImpl implements CastService {

    private final CastCommandToCast castCommandToCast;
    private final CastToCastCommand castToCastCommand;

    private final MovieRepository movieRepository;

    public CastServiceImpl(CastCommandToCast castCommandToCast, CastToCastCommand castToCastCommand, MovieRepository movieRepository) {
        this.castCommandToCast = castCommandToCast;
        this.castToCastCommand = castToCastCommand;
        this.movieRepository = movieRepository;
    }

    @Override
    public CastCommand findByMovieIdAndCastId(Long movieId, Long castId) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if(!movieOptional.isPresent()){
            // impl error handlingi
            log.error("Movie Id Not Found. Id: " + movieId);

        }

        Movie movie = movieOptional.get();

        Optional<CastCommand> castCommandOptional = movie.getCast().stream()
                .filter(cast -> cast.getId().equals(castId))
                .map(cast -> castToCastCommand.convert(cast)).findFirst();

        if(!castCommandOptional.isPresent()){

            log.error("Cast Members With That ID not Found!: " + castId);

        }

        return castCommandOptional.get();

    }

    @Override
    @Transactional
    public CastCommand saveCastCommand(CastCommand castCommand) {

        Optional<Movie> movieOptional = movieRepository.findById(castCommand.getId());

        if(!movieOptional.isPresent()){

            log.error("Movie With Given ID: " + castCommand.getMovieId() + " Not Found!");
            return new CastCommand();

        } else {

            Movie movie = movieOptional.get();

            Optional<Cast> castOptional = movie.getCast().stream()
                    .filter(cast -> cast.getId().equals(castCommand.getId()))
                    .findFirst();

            if(castOptional.isPresent()){

                Cast castFound = castOptional.get();
                castFound.setFirstname(castCommand.getFirstname());
                castFound.setLastname(castCommand.getLastname());

            }

            Movie savedMovie = movieRepository.save(movie);

            Optional<Cast> savedCastOptional = savedMovie.getCast().stream()
                    .filter(movieCasts -> movieCasts.getId().equals(castCommand.getId()))
                    .findFirst();

            if(!savedCastOptional.isPresent()){

                savedCastOptional = savedMovie.getCast().stream()
                        .filter(movieCasts -> movieCasts.getFirstname().equals(castCommand.getFirstname()))
                        .filter(movieCasts -> movieCasts.getLastname().equals(castCommand.getLastname()))
                        .findFirst();
            }

            return castToCastCommand.convert(savedCastOptional.get());
        }



    }




    @Override
    public void deleteById(Long movieId, Long castId) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if(movieOptional.isPresent()) {

            Movie movie = movieOptional.get();

            Optional<Cast> castOptional = movie.getCast().stream()
                    .filter(cast -> cast.getId().equals(castId))
                    .findFirst();

            if (castOptional.isPresent()) {

                Cast castToDelete = castOptional.get();
                castToDelete.setMovie(null);
                movie.getCast().remove(movieOptional.get());
                movieRepository.save(movie);

            }
        } else {

            log.debug("Movie With Given ID Not Found. ID: " + movieId);

        }

        }


    }
