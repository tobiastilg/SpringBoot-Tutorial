package at.itkollegimst.Springboot.tutorial.controller;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //can create RestAPI over here
public class DepartmentController {

    @Autowired //Referenz zu meinem Spring Container (benutzen der Dependency Injection)
    private DepartmentService departmentService;

    /**
     * API zum Speichern eines Departments
     * @param department wird als json übergeben (Requestbody - wird automatisch von Spring gemappt)
     * @return das gespeicherte Department
     */
    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department) {
        //muss nicht erstellt werden, da bereits im Spring Container (@Component)
        /*DepartmentService service = new DepartmentServiceImpl(); --> erspart man sich (Kopplung)*/
        return departmentService.saveDepartment(department);
    }

    /**
     * @return Liste aller Departments
     */
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    /**
     * @param departmentId gesuchte ID
     * @return Department mit der ID
     */
    @GetMapping("/departments/{id}") //verknüpft mit PathVariable, siehe nächste Zeile
    public Department fetchDepartmentById (@PathVariable("id") Long departmentId) {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully!";
    }

    @PutMapping("/departments/{id}") //used for update
    public Department updateDepartment(@PathVariable("id") Long departmentId, //nur wenn das Department mit der ID existiert
                                       @RequestBody Department department) {  //dann update mit den neue mitgegebenen Daten
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }
}
