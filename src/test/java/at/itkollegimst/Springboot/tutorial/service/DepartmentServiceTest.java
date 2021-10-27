package at.itkollegimst.Springboot.tutorial.service;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //damit Spring weiß, es handelt sich um eine Testklasse
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean //mocking
    private DepartmentRepository departmentRepository;

    @BeforeEach //immer wenn eine Testklasse ausgeführt wird, wird auch diese Methode zuvor aufgerufen
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Landeck")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();

        /*wenn an meinem Repo die folgende Methode aufgerufen wird, dann gib das zuvor erstelle Department zurück und
        greif nicht auf die Methode selber zu -> kein auslesen aus der Datenbank*/
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
        //also wenn
    }

    @Test //damit Spring die Testfunktion ausführen kann
    @DisplayName("Get Data based on Valid Department Name")
    @Disabled //wenn die ganze Klasse ausgeführt wird, wird diese Methode ausgelassen
    public void whenValidDepartmentName_thenDepartmentShouldFind () {
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}