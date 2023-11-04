package DemoMoviesApp.MoviesApplication.Converters;

import DemoMoviesApp.MoviesApplication.Commands.AboutCommand;
import DemoMoviesApp.MoviesApplication.Commands.GenreCommand;
import DemoMoviesApp.MoviesApplication.Commands.MovieCommand;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import DemoMoviesApp.MoviesApplication.Domain.Movie;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovie implements Converter<MovieCommand, Movie> {


    private CastCommandToCast castConverter;

    private GenreCommandToGenre genreConverter;

    private AboutCommandToAbout aboutConverter;

    public MovieCommandToMovie(CastCommandToCast castConverter, GenreCommandToGenre genreConverter, AboutCommandToAbout aboutConverter) {
        this.castConverter = castConverter;
        this.genreConverter = genreConverter;
        this.aboutConverter = aboutConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Movie convert(MovieCommand source) {
        if(source == null){
            return null;
        }

        final Movie movie = new Movie();

        movie.setId(source.getId());
        movie.setName(source.getName());
        movie.setImdb(source.getImdb());
        movie.setImg(source.getImg());
        movie.setReleasedate(source.getReleasedate());
        movie.setDirector(source.getDirector());
        movie.setWriter(source.getWriter());

        movie.setAbout(aboutConverter.convert(source.getAbout()));

        if(source.getGenre() != null && source.getGenre().size() > 0){

            source.getGenre().forEach(genre -> movie.getGenre().add(genreConverter.convert(genre)));

        }

        if(source.getCast() != null && source.getCast().size() > 0){

            source.getCast().forEach(cast -> movie.getCast().add(castConverter.convert(cast)));

        }
        return movie;
    }
}


