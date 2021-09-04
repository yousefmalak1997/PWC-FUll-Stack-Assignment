package com.pwc.assignment.UsersProjectsMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersProjectsMappingRepository extends JpaRepository<UsersProjectsMapping,Long> {

    List<UsersProjectsMapping> findAllByUserId(Long userId);

    Optional<UsersProjectsMapping> findAllByProjectId(Long projectId);

    Optional<UsersProjectsMapping> findAllByUserIdAndProjectId(Long userId, Long projectId);

    Optional<UsersProjectsMapping> findAllByUserIdAndProjectIdOrderByProjectId(Long userId, Long projectId);
}
