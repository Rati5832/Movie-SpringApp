package DemoMoviesApp.MoviesApplication.Converters;

import DemoMoviesApp.MoviesApplication.Commands.AboutCommand;
import DemoMoviesApp.MoviesApplication.Domain.About;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class AboutToAboutCommand implements Converter<About, AboutCommand> {


    @Override
    @Synchronized
    @Nullable
    public AboutCommand convert(About source) {
        if(source == null){
            return null;
        }

        final AboutCommand aboutCommand = new AboutCommand();
        aboutCommand.setId(source.getId());
        aboutCommand.setAboutMovie(source.getAboutMovie());
        return aboutCommand;

    }
}
