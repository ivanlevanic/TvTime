package hr.algebra.tvtime.service;

import hr.algebra.tvtime.domain.TvShowData;
import hr.algebra.tvtime.repository.JpaTvShowRepository;
import hr.algebra.tvtime.repository.TvShowRepository;
import hr.algebra.tvtime.service.dto.TvShowDataSearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TvShowService {
    private final TvShowRepository tvShowRepository;
    private final JpaTvShowRepository jpaTvShowRepository;

    public TvShowService(TvShowRepository tvShowRepository, JpaTvShowRepository jpaTvShowRepository) {
        this.tvShowRepository = tvShowRepository;
        this.jpaTvShowRepository = jpaTvShowRepository;
    }

    public List<Optional<TvShowData>> getCheapestStreamingService(String name) {
        return tvShowRepository.getCheapestStreamingService(name);
    }

    public Set<TvShowData> findAll() {return jpaTvShowRepository.findAll();}

    public Optional<TvShowData> findById(Long id) {
        return jpaTvShowRepository.findById(id);
    }

    public TvShowData saveJpa(TvShowData tvShowData) { return jpaTvShowRepository.save(tvShowData); }

    public boolean existsById(Long id) {
        return jpaTvShowRepository.existsById(id);
    }

    public void deleteById(Long id) {
        jpaTvShowRepository.deleteById(id);
    }

    public Set<TvShowData> findProductByStreamingService(TvShowDataSearchDTO tvShowDataSearchDTO) {
        return jpaTvShowRepository.findAllByStreamingserviceContainingIgnoreCase(tvShowDataSearchDTO.getStreamingservice());
    }
}
