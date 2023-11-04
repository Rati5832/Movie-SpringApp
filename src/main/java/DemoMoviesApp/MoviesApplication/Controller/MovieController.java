package DemoMoviesApp.MoviesApplication.Controller;


import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@Slf4j
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}/show")
    public String showMovieById(@PathVariable Long id, Model model){

        model.addAttribute("movie", movieService.findById(id));

        return "show";
    }


    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id){

        movieService.deleteById(id);

        return "redirect:/";


    }


}
