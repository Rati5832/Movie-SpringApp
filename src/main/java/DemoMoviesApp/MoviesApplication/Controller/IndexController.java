package DemoMoviesApp.MoviesApplication.Controller;


import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private final MovieService movieService;

    public IndexController(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Page!");

        Set<Movie> movies = movieService.getMovies();
        log.debug("Retrieved {} movies", movies.size());

        model.addAttribute("movies", movies);

        return "index";
    }


}
