package hr.algebra.tvtime.controller.rest;

import hr.algebra.tvtime.domain.TvShowData;
import hr.algebra.tvtime.service.TvShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
@RestController
@RequestMapping(path = "api/show")
public class StreamingRestController {

    private final TvShowService tvShowService;

    public StreamingRestController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public Iterable<TvShowData> findAll() {
        return tvShowService.findAll();
    }

    @GetMapping("{id}")
    public TvShowData findOne(@PathVariable Long id) {
        return tvShowService.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show was not found")
                );
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TvShowData save(@Valid @RequestBody TvShowData tvShowData) {
        if(tvShowData.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show ID must be left empty when creating it");
        }
        TvShowData result = tvShowService.saveJpa(tvShowData);
        return result;
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if(!tvShowService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show doesn't exist so it can't be deleten");
        }
        tvShowService.deleteById(id);
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}/seasons")
    public TvShowData updateSeasons(@PathVariable Long id, @RequestParam Integer seasons) {
        TvShowData tvShowData = tvShowService.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show with the provided ID does not exist")
                );
        tvShowData.setSeasons(seasons);
        return tvShowService.saveJpa(tvShowData);
    }
}
