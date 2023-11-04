package DemoMoviesApp.MoviesApplication.Converters;


import DemoMoviesApp.MoviesApplication.Commands.CastCommand;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
public class CastCommandToCast implements Converter<CastCommand, Cast>{

    @Nullable
    @Override
    public Cast convert(CastCommand source) {
        if(source == null){
            return null;
        }

        final Cast cast = new Cast();
        cast.setId(source.getId());
        cast.setFirstname(source.getFirstname());
        cast.setLastname(source.getLastname());

        return cast;

    }
}
