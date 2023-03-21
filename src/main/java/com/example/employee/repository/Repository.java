package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.entity.Employee;
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Employee,Long> {

	

}
