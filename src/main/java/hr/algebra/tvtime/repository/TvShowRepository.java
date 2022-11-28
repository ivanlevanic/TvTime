package hr.algebra.tvtime.repository;

import hr.algebra.tvtime.domain.TvShowData;

import java.util.List;
import java.util.Optional;

public interface TvShowRepository {

    List<Optional<TvShowData>> getCheapestStreamingService(String name);

}
