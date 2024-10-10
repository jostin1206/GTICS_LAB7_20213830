package com.example.laboratorio7.repository;

import com.example.laboratorio7.entity.Projections;
import com.example.laboratorio7.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
}
