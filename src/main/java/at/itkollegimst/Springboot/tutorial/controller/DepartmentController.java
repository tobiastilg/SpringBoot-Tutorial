package at.itkollegimst.Springboot.tutorial.controller;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        /*muss nicht erstellt werden, da bereits im Spring Container (@Component)
        DepartmentService service = new DepartmentServiceImpl();*/
        return departmentService.saveDepartment(department);
    }

}
