package DemoMoviesApp.MoviesApplication.Converters;

import DemoMoviesApp.MoviesApplication.Commands.GenreCommand;
import DemoMoviesApp.MoviesApplication.Commands.MovieCommand;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import DemoMoviesApp.MoviesApplication.Domain.Movie;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
public class MovieToMovieCommand implements Converter<Movie, MovieCommand> {

        private CastToCastCommand castConverter;

        private GenreToGenreCommand genreConverter;

        private AboutToAboutCommand aboutConverter;

        public MovieToMovieCommand(CastToCastCommand castConverter, GenreToGenreCommand genreConverter, AboutToAboutCommand aboutConverter) {
            this.castConverter = castConverter;
            this.genreConverter = genreConverter;
            this.aboutConverter = aboutConverter;
    }

    @Override
    public MovieCommand convert(Movie source) {
        if(source == null){
            return null;
        }

        final MovieCommand movieCommand = new MovieCommand();

        movieCommand.setId(source.getId());
        movieCommand.setName(source.getName());
        movieCommand.setImdb(source.getImdb());
        movieCommand.setImg(source.getImg());
        movieCommand.setReleasedate(source.getReleasedate());
        movieCommand.setDirector(source.getDirector());
        movieCommand.setWriter(source.getWriter());

        movieCommand.setAbout(aboutConverter.convert(source.getAbout()));

        if(source.getGenre() != null && source.getGenre().size() > 0){

            source.getGenre().forEach((Genre genre) -> movieCommand.getGenre().add(genreConverter.convert(genre)));

        }

        if(source.getCast() != null && source.getCast().size() > 0){

            source.getCast().forEach((Cast cast) -> movieCommand.getCast().add(castConverter.convert(cast)));

        }


        return movieCommand;
    }
}
