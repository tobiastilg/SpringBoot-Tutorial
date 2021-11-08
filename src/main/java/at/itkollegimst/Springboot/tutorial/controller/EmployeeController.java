package at.itkollegimst.Springboot.tutorial.controller;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.entity.Employee;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeDTO;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeView;
import at.itkollegimst.Springboot.tutorial.error.EmployeeNotFoundException;
import at.itkollegimst.Springboot.tutorial.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody EmployeeDTO employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("/employees")
    public List<EmployeeView> fetchEmployeeList() {
        return employeeService.fetchEmployeeList();
    }

    @GetMapping("/employees/{id}")
    public EmployeeView fetchEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeById(id);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "Employee deleted successfully!";
    }

    @PutMapping("/employees/{id}")
    public EmployeeView updateEmployee(@PathVariable("id") Long employeeId, //nur wenn das Department mit der ID existiert
                                       @RequestBody EmployeeDTO employeeDTO) {  //dann update mit den neue mitgegebenen Daten
        return employeeService.updateEmployee(employeeId, employeeDTO);
    }
}