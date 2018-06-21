package com.thoughtmechanix.specialroutes.repository;

import com.thoughtmechanix.specialroutes.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute,String>  {
    Optional<AbTestingRoute> findByServiceName(String serviceName);
}
