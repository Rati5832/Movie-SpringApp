package DemoMoviesApp.MoviesApplication.Converters;


import DemoMoviesApp.MoviesApplication.Commands.GenreCommand;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


import java.lang.annotation.Annotation;

@Component
public class GenreToGenreCommand implements Converter<Genre, GenreCommand> {

    @Synchronized
    @Nullable
    @Override
    public GenreCommand convert(Genre source) {
        if(source == null){
            return null;
        }

        final GenreCommand genreCommand = new GenreCommand();
        genreCommand.setId(source.getId());
        genreCommand.setDescription(source.getDescription());

        return genreCommand;

    }

}
