package DemoMoviesApp.MoviesApplication.Repository;

import DemoMoviesApp.MoviesApplication.Domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
