package com.hmh.moviegenie.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.moviegenie.backend.domain.Reviews;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long>{

	List<Reviews> findAllByMovie_MovieId(Long movieId);

}
