////handling incoming HTTP requests and returning an appropriate response

package com.apdev.imbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    //    TODO: add search queries
    @GetMapping("/movies")
    public String searchMovies(@RequestParam(name = "query", required = false) String
     query, Model model) {
        List<Movie> searchResults;
        if (query != null && !query.isEmpty()) {
            searchResults = movieRepository.findByPrimaryTitleContainingIgnoreCase(query);
        } else {
            searchResults = List.of();
        }
        model.addAttribute("listMovies", searchResults);
        return "movies";
    }


}