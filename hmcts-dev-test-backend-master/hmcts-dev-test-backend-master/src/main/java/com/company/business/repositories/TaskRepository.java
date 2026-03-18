package com.company.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.Task;

/**
 * Repository layer for Task entity.
 *
 * Responsibilities:
 * - Provides CRUD operations for Task entity.
 * - Extends JpaRepository to leverage Spring Data JPA.
 * - No custom methods required for this assessment.
 */

public interface TaskRepository extends JpaRepository<Task, Long> {


}
