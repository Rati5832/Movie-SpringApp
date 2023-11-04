package DemoMoviesApp.MoviesApplication.Repository;

import DemoMoviesApp.MoviesApplication.Domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository  extends CrudRepository<Genre, Long> {

    Optional<Genre> findByDescription(String description);

}
