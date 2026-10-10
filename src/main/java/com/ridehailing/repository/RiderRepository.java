package com.ridehailing.repository;

import com.ridehailing.model.Riders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Riders, Integer> {

}
