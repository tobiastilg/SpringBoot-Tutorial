package at.itkollegimst.Springboot.tutorial.repository;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository communicates with the Entity in the Database using JPA (via the id)
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
