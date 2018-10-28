package com.samuel.house.manager.repository;

import com.samuel.house.manager.model.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface HouseRepository extends JpaRepository<House, Long> {
}
