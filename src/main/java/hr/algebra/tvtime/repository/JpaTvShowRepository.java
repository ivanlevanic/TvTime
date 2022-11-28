package hr.algebra.tvtime.repository;

import hr.algebra.tvtime.domain.TvShowData;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.Optional;

public interface JpaTvShowRepository extends CrudRepository<TvShowData, Long> {

    Set<TvShowData> findAll();

    Optional<TvShowData> findById(Long id);

    Set<TvShowData> findAllByStreamingserviceContainingIgnoreCase(String streamingservice);
}
