package DemoMoviesApp.MoviesApplication.Converters;

import DemoMoviesApp.MoviesApplication.Commands.AboutCommand;
import DemoMoviesApp.MoviesApplication.Domain.About;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class AboutCommandToAbout implements Converter<AboutCommand,About> {


    @Override
    @Synchronized
    @Nullable
    public About convert(AboutCommand source) {
        if(source == null){
            return null;
        }

        final About about = new About();
        about.setId(source.getId());
        about.setAboutMovie(source.getAboutMovie());
        return about;

    }
}

