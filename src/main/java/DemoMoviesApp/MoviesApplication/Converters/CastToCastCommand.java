package DemoMoviesApp.MoviesApplication.Converters;


import DemoMoviesApp.MoviesApplication.Commands.CastCommand;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CastToCastCommand implements Converter<Cast,CastCommand>{

    @Override
    @Nullable
    @Synchronized
    public CastCommand convert(Cast source) {
        if(source == null){
            return null;
        }

        final CastCommand castCommand = new CastCommand();
        castCommand.setId(source.getId());
        castCommand.setFirstname(source.getFirstname());
        castCommand.setLastname(source.getLastname());

        return castCommand;

    }
}
