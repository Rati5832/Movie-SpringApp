package DemoMoviesApp.MoviesApplication.Services;


import DemoMoviesApp.MoviesApplication.Commands.CastCommand;

public interface CastService {

    CastCommand findByMovieIdAndCastId(Long movieId, Long castId);

    CastCommand saveCastCommand(CastCommand castCommand);

    void deleteById(Long movieId, Long castId);

}
