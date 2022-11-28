package hr.algebra.tvtime.controller;

import hr.algebra.tvtime.domain.TvShowData;
import hr.algebra.tvtime.service.TvShowService;
import hr.algebra.tvtime.service.dto.TvShowDataSearchDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class StreamingController {

    private final TvShowService tvShowService;

    public StreamingController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping("")
    public String redirectToHome(TvShowData tvShowData, Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String start(TvShowData tvShowData, Model model) {
        model.addAttribute("tvShowData", tvShowData);
        return "pages/home";
    }

    @GetMapping("/home/show")
    public String searchProduct(@Valid TvShowData show, Errors errors, Model model){
        if(errors.hasErrors()){
            return "redirect:/home?error";
        }
        List<Optional<TvShowData>> result = tvShowService.getCheapestStreamingService(show.getName());
        if(result.isEmpty()) {
            return "redirect:/home?error";
        }
        model.addAttribute("shows", result);
        return "pages/services";
    }

    @GetMapping("/home/search")
    public String searchForm(TvShowDataSearchDTO tvShowDataSearchDTO) {
        return "pages/search";
    }

    @PostMapping("/home/search")
    public String searchResults(Model model, TvShowDataSearchDTO tvShowDataSearchDTO) {
        model.addAttribute("showSet", tvShowService.findProductByStreamingService(tvShowDataSearchDTO));
        return "pages/search";
    }

}
