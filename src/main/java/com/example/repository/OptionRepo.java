package com.example.repository;

import com.example.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepo extends JpaRepository<Option, Long> {
}
