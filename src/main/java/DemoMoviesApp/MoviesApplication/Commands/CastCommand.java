package DemoMoviesApp.MoviesApplication.Commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CastCommand {

    private Long id;
    private Long movieId;


    private String firstname;
    private String lastname;


}
