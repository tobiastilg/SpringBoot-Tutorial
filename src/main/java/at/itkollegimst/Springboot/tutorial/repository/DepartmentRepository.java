package at.itkollegimst.Springboot.tutorial.repository;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository communicates with the Entity in the Database using JPA (via the id)
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    /**
       Eine Methode wie "findByDepartmentName" stellt unser JpaRepository nicht standardmäßig bereit,
       deshalb muss eine neue Methode geschrieben werden.
       Implementiert wird sie, bei richtiger "Name-Convention" (Syntax) durch JPA aber automatisch.
     */
    public Department findByDepartmentName(String departmentName);

    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
