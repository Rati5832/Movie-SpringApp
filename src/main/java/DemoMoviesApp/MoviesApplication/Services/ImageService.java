package DemoMoviesApp.MoviesApplication.Services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long movieId, MultipartFile file);


}
