package at.itkollegimst.Springboot.tutorial.service;

import at.itkollegimst.Springboot.tutorial.entity.Employee;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeDTO;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeView;
import at.itkollegimst.Springboot.tutorial.error.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(EmployeeDTO employeeDto);
    List<EmployeeView> fetchEmployeeListView();
    List<Employee> fetchEmployeeList();
    EmployeeView fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException;
    void deleteEmployeeById(Long employeeId);
    EmployeeView updateEmployee(Long employeeId, EmployeeDTO employeeDTO);
}
