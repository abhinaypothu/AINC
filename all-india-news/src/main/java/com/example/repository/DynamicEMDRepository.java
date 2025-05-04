package com.example.repository;

import com.example.model.DynamicEMD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicEMDRepository extends JpaRepository<DynamicEMD, Long> {
}
