package at.itkollegimst.Springboot.tutorial.repository;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //da es sich um ein Repo handelt (JPA - Datenbank)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("ME")
                .departmentCode("ME-011")
                .departmentAddress("Zams")
                .build();

        entityManager.persist(department);
        /*während der Laufzeit des Tests wird dieses Department in der Datenbank gespeichert (vom Test gefunden,
        daher erfolgreich) und danach wieder entfernt --> so muss nicht mit tatsächlichen Daten getestet werden*/
    }

    @Test
    public void whenFindById_thenReturnDepartment() {
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "ME");
    }
}