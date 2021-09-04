package com.pwc.assignment.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UsersRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByEmail(String email);

    Optional<Users> findById(Long id);

    Optional<Users> getByEmail(String email);

    List<Users> findAllByDepartmentsId(Long depId);

    Optional<Users> findOneByDepartmentsId(Long depId);
}
