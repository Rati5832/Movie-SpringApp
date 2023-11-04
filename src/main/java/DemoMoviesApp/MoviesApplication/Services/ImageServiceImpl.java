package DemoMoviesApp.MoviesApplication.Services;

import DemoMoviesApp.MoviesApplication.Domain.Movie;
import DemoMoviesApp.MoviesApplication.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final MovieRepository movieRepository;

    public ImageServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long movieId, MultipartFile file) {

        try{

            Movie movie = movieRepository.findById(movieId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for(byte b : file.getBytes()){

                byteObjects[i++] = b;


            }

            movie.setImg(byteObjects);
            movieRepository.save(movie);

        } catch (IOException e){

            log.error("Error Occurred", e);

            e.printStackTrace();

        }

    }
}
