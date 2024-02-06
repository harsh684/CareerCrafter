package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
