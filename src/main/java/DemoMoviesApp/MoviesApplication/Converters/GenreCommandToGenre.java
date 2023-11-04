package DemoMoviesApp.MoviesApplication.Converters;


import DemoMoviesApp.MoviesApplication.Commands.GenreCommand;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreCommandToGenre implements Converter<GenreCommand, Genre> {

    @Synchronized
    @Nullable
    @Override
    public Genre convert(GenreCommand source){
        if(source == null){
            return null;
        }

        final Genre genre = new Genre();
        genre.setId(source.getId());
        genre.setDescription(source.getDescription());

        return genre;

    }

}
