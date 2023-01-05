package com.tenpo.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tenpo.api.entity.PercentageEntity;

@Repository
public interface PercentageRepository extends CrudRepository<PercentageEntity, Integer>{
	
	Optional<PercentageEntity> findTopByOrderByIdDesc();

}
