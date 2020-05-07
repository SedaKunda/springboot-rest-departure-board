package com.zuhlke.trainstations.repository;

import com.zuhlke.trainstations.model.StationDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "station", path = "station")
public interface StationDetailsRepository extends CrudRepository<StationDetails, Long> {
    List<StationDetails> findByAtcocode(@Param("atcocode") String atcocode);
}
