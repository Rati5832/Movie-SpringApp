package DemoMoviesApp.MoviesApplication.Bootstrap;

import DemoMoviesApp.MoviesApplication.Domain.About;
import DemoMoviesApp.MoviesApplication.Domain.Cast;
import DemoMoviesApp.MoviesApplication.Domain.Genre;
import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Repository.GenreRepository;
import DemoMoviesApp.MoviesApplication.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MovieBootstrap implements ApplicationListener<ContextRefreshedEvent> {


        private final GenreRepository genreRepository;
        private final MovieRepository movieRepository;



         public MovieBootstrap(GenreRepository genreRepository, MovieRepository movieRepository) {
                this.genreRepository = genreRepository;
                this.movieRepository = movieRepository;
           }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

          movieRepository.saveAll(getMovies());
          log.debug("Bootstrap Loading!!!!");
    }


    private List<Movie> getMovies(){

        List<Movie> movies = new ArrayList<>(2);



        // takin inserted genres
        Optional<Genre> actionGenreOptional = genreRepository.findByDescription("Action");

        if(!actionGenreOptional.isPresent()){
            throw new RuntimeException("Expected Genre Not Found!");
        }

        Optional<Genre> dramaGenreOptional = genreRepository.findByDescription("Drama");
        if(!dramaGenreOptional.isPresent()){
            throw new RuntimeException("Expected Genre Not Found!");
        }

        Optional<Genre> crimeGenreOptional = genreRepository.findByDescription("Crime");
        if(!crimeGenreOptional.isPresent()){
            throw new RuntimeException("Expected Genre Not Found!");
        }
        //


        Genre actionGenre = actionGenreOptional.get();
        Genre dramaGenre = dramaGenreOptional.get();
        Genre crimeGenre = crimeGenreOptional.get();


        Movie firstMovie = new Movie();
        firstMovie.setName("The Shawshank Redemption");
        firstMovie.setImdb(9.3);
        firstMovie.setReleasedate(1994);
        firstMovie.setDirector("Frank Darabont");
        firstMovie.setWriter("Frank Darabont");

        About firstAbout = new About();
        firstAbout.setAboutMovie("In The Shawshank Redemption, Andy Dufresne (Tim Robbins) is convicted for the murder of his wife and her lover and sent to prison. It's 1949, and Andy doesn't have the stuff for prison life. Andy befriends \"Red\" Redding (Morgan Freeman) and uses his past as a banker to get a job in the prison library.");
        firstMovie.setAbout(firstAbout);

        firstMovie.addCast(new Cast("Tim", "Robbins"));
        firstMovie.addCast(new Cast("Morgan", "Freeman"));
        firstMovie.addCast(new Cast("Bob", "Gunton"));
        firstMovie.addCast(new Cast("William", "Sadler"));
        firstMovie.addCast(new Cast("Clancy", "Brown"));

        firstMovie.getGenre().add(dramaGenre);

        // for the return
        movies.add(firstMovie);

        // ---------------------------------------------------------------------------------------------------------

        Movie secondMovie = new Movie();
        secondMovie.setName("The Dark Knight");
        secondMovie.setImdb(9.0);
        secondMovie.setReleasedate(2008);
        secondMovie.setDirector("Christopher Nolan");
        secondMovie.setWriter("David S. Goyer");

        About secondAbout = new About();
        secondAbout.setAboutMovie("The plot follows the vigilante Batman, police lieutenant James Gordon, and district attorney Harvey Dent, who form an alliance to dismantle organized crime in Gotham City. Their efforts are derailed by the Joker, an anarchistic mastermind who seeks to test how far the Batman will go to save the city from chaos.");
        secondMovie.setAbout(secondAbout);


        secondMovie.addCast(new Cast("Christian","Bale"));
        secondMovie.addCast(new Cast("Heath","Ledger"));
        secondMovie.addCast(new Cast("Aaron","Eckhart"));
        secondMovie.addCast(new Cast("Michael","Caine"));
        secondMovie.addCast(new Cast("Morgan","Freeman"));


        secondMovie.getGenre().add(actionGenre);
        secondMovie.getGenre().add(crimeGenre);

        movies.add(secondMovie);


        return movies;

    }


}
