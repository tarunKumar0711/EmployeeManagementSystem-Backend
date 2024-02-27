package com.ems.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.backend.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}