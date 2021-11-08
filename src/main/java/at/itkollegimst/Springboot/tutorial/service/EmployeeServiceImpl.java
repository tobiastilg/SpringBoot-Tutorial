package at.itkollegimst.Springboot.tutorial.service;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.entity.Employee;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeDTO;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeView;
import at.itkollegimst.Springboot.tutorial.error.EmployeeNotFoundException;
import at.itkollegimst.Springboot.tutorial.repository.DepartmentRepository;
import at.itkollegimst.Springboot.tutorial.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee saveEmployee(EmployeeDTO employeeDto) {
        Long departmentId = employeeDto.getDepartmentId();
        Employee employee = Employee.builder()
                .department(departmentRepository.findById(departmentId).get())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .emailAddress(employeeDto.getEmailAddress())
                .build();
        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public List<EmployeeView> fetchEmployeeListView() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeView> employeeViewList = new ArrayList<>();

        for (Employee e: employeeList) {
            Department department = e.getDepartment();
            EmployeeView employeeView = EmployeeView.builder()
                    .employeeId(e.getEmployeeId())
                    .firstName(e.getFirstName())
                    .lastName(e.getLastName())
                    .emailAddress(e.getEmailAddress())
                    .departmentName(department.getDepartmentName())
                    .build();
            employeeViewList.add(employeeView);
        }

        return employeeViewList;
    }

    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeView fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (!employeeOptional.isPresent()) {
            throw new EmployeeNotFoundException("Employee Not Available");
        }

        Employee employee = employeeOptional.get();
        Department department = employee.getDepartment();

        EmployeeView employeeView = EmployeeView.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .emailAddress(employee.getEmailAddress())
                .departmentName(department.getDepartmentName())
                .build();

        return employeeView;
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeView updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee employeeDB = employeeRepository.findById(employeeId).get();

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId()).get();

        if (Objects.nonNull(employeeDTO.getDepartmentId()))
            employeeDB.setDepartment(department);

        if (Objects.nonNull(employeeDTO.getEmailAddress()) &&
                !"".equalsIgnoreCase(employeeDTO.getEmailAddress()))
            employeeDB.setEmailAddress(employeeDTO.getEmailAddress());

        if (Objects.nonNull(employeeDTO.getFirstName()) &&
                !"".equalsIgnoreCase(employeeDTO.getFirstName()))
            employeeDB.setFirstName(employeeDTO.getFirstName());

        if (Objects.nonNull(employeeDTO.getLastName()) &&
                !"".equalsIgnoreCase(employeeDTO.getLastName()))
            employeeDB.setLastName(employeeDTO.getLastName());

        employeeRepository.save(employeeDB);

        EmployeeView employeeView = EmployeeView.builder()
                .employeeId(employeeDB.getEmployeeId())
                .departmentName(employeeDB.getDepartment().getDepartmentName())
                .firstName(employeeDB.getFirstName())
                .lastName(employeeDB.getLastName())
                .emailAddress(employeeDB.getEmailAddress())
                .build();

        return employeeView;
    }
}