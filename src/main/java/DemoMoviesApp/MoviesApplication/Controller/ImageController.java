package DemoMoviesApp.MoviesApplication.Controller;


import DemoMoviesApp.MoviesApplication.Commands.MovieCommand;
import DemoMoviesApp.MoviesApplication.Services.ImageService;
import DemoMoviesApp.MoviesApplication.Services.MovieService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final MovieService movieService;

    public ImageController(ImageService imageService, MovieService movieService) {
        this.imageService = imageService;
        this.movieService = movieService;
    }


    @GetMapping("/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){


        model.addAttribute("movie", movieService.findMovieCommandById((id)));

        return "movie/imageuploadform";

    }

    @PostMapping("/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile")MultipartFile multipartFile){

        imageService.saveImageFile(id, multipartFile);


        return "redirect:/" + id + "/show";

    }

    @GetMapping("/{id}/movieimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        MovieCommand movieCommand = movieService.findMovieCommandById(Long.valueOf(id));

        if (movieCommand.getImg() != null) {
            byte[] byteArray = new byte[movieCommand.getImg().length];
            int i = 0;

            for (Byte wrappedByte : movieCommand.getImg()){
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }


}
