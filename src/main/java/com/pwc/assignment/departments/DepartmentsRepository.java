package com.pwc.assignment.departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Long> {

}
