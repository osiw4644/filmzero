package com.zeromovie.filmzero.control;

import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/collection")
public class CollectionController {
    private final MovieService movieService;
    @GetMapping("/te") // 테스트용 주소
    public String collectionTest(Model model) {
        List<MovieInfo> movieList1 = movieService.romanceCollection();
        List<MovieInfo> movieList2 = movieService.AniCollection();
        List<MovieInfo> movieList3 = movieService.StarCollection();
        List<MovieInfo> movieList4 = movieService.AudiCollection();
        List<MovieInfo> movieList5 = movieService.ThrillerCollection();
        List<MovieInfo> movieList6 = movieService.HorrorCollection();
        List<MovieInfo> movieList7 = movieService.ActionCollection();
        List<MovieInfo> movieList8 = movieService.CrimeCollection();
        List<MovieInfo> movieList9 = movieService.DocumentaryCollection();

        model.addAttribute("movieList1", movieList1);
        model.addAttribute("movieList2", movieList2);
        model.addAttribute("movieList3", movieList3);
        model.addAttribute("movieList4", movieList4);
        model.addAttribute("movieList5", movieList5);
        model.addAttribute("movieList6", movieList6);
        model.addAttribute("movieList7", movieList7);
        model.addAttribute("movieList8", movieList8);
        model.addAttribute("movieList9", movieList9);

        return "collectionTest";
    }
    @GetMapping("/romance")
    public String romance(Model model) {
        List<MovieInfo> movieList1 = movieService.romanceCollection();
        model.addAttribute("movieList1", movieList1);

        return "/collection/romanceCollection";
    }

    @GetMapping("/ani")
    public String animation(Model model) {
        List<MovieInfo> movieList2 = movieService.AniCollection();
        model.addAttribute("movieList2", movieList2);

        return "/collection/aniCollection";
    }

    @GetMapping("/star")
    public String star(Model model) {
        List<MovieInfo> movieList3 = movieService.StarCollection();
        model.addAttribute("movieList3", movieList3);

        return "/collection/starCollection";
    }

    @GetMapping("/audi")
    public String audiAcc(Model model) {
        List<MovieInfo> movieList4 = movieService.AudiCollection();
        model.addAttribute("movieList4", movieList4);

        return "/collection/audiCollection";
    }

    @GetMapping("/thriller")
    public String thriller(Model model) {
        List<MovieInfo> movieList5 = movieService.ThrillerCollection();
        model.addAttribute("movieList5", movieList5);

        return "/collection/thrillerCollection";
    }

    @GetMapping("/horror")
    public String horror(Model model) {
        List<MovieInfo> movieList6 = movieService.HorrorCollection();
        model.addAttribute("movieList6", movieList6);

        return "/collection/horrorCollection";
    }

    @GetMapping("/action")
    public String act(Model model) {
        List<MovieInfo> movieList7 = movieService.ActionCollection();
        model.addAttribute("movieList7", movieList7);

        return "/collection/actionCollection";
    }

    @GetMapping("/crime")
    public String crime(Model model) {
        List<MovieInfo> movieList8 = movieService.CrimeCollection();
        model.addAttribute("movieList8", movieList8);

        return "/collection/crimeCollection";
    }

    @GetMapping("/documentary")
    public String documentary(Model model) {
        List<MovieInfo> movieList9 = movieService.DocumentaryCollection();
        model.addAttribute("movieList9", movieList9);

        return "/collection/documentaryCollection";
    }
}
