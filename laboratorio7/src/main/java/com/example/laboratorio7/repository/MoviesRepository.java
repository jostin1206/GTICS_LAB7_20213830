package com.example.laboratorio7.repository;

import com.example.laboratorio7.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {
}
