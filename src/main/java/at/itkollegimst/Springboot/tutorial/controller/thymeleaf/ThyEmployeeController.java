package at.itkollegimst.Springboot.tutorial.controller.thymeleaf;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.entity.Employee;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeDTO;
import at.itkollegimst.Springboot.tutorial.entity.EmployeeView;
import at.itkollegimst.Springboot.tutorial.error.EmployeeNotFoundException;
import at.itkollegimst.Springboot.tutorial.service.DepartmentService;
import at.itkollegimst.Springboot.tutorial.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ThyEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("thyemployees")
    public String fetchEmployeeList(Model model) {
        List<EmployeeView> employeeList = employeeService.fetchEmployeeListView();
        model.addAttribute("employeeList", employeeList);
        return "employees";
    }

    @GetMapping("/thyemployees/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("pageTitle", "Add New User");
        return "employee_form";
    }

    @PostMapping("/thyemployees/save")
    public String saveEmployee(EmployeeDTO employeeDTO, RedirectAttributes ra) {
        employeeService.saveEmployee(employeeDTO);
        ra.addFlashAttribute("message", "The employee has been saved successfully.");
        return "redirect:/thyemployees";
    }

    @GetMapping ("/thyemployees/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) throws EmployeeNotFoundException {
        EmployeeView employeeView = employeeService.fetchEmployeeById(id);

        Department department = departmentService.fetchDepartmentByName(employeeView.getDepartmentName());

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(employeeView.getEmployeeId())
                .emailAddress(employeeView.getEmailAddress())
                .firstName(employeeView.getFirstName())
                .lastName(employeeView.getLastName())
                .departmentId(department.getDepartmentId())
                .build();

        model.addAttribute("employee", employeeDTO);
        model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
        return "employee_form_edit";
        /* anderes Formular --> es kann nicht an das gleiche Formular gesendet werden wie es unsere saveEmployee Methode
        * tut, da wir dann immer nur auf die save Methode im Repo zugreifen würden. Auch wenn diese erkennt, ob ein
        * Objekt geupdated wird oder nicht, würde es hier nicht funktionieren, weil wir mit einem DTO Objekt arbeiten,
        * das nicht in der DB gespeichert ist. Daher handelt es sich um ein neues Objekt, das nicht erkannt wird.*/

    }

    @PostMapping("/thyemployees/update")
    public String updateEmployee(EmployeeDTO employeeDTO, RedirectAttributes ra) {
        employeeService.updateEmployee(employeeDTO.getEmployeeId(), employeeDTO);
        ra.addFlashAttribute("message", "The employee has been edited successfully.");
        return "redirect:/thyemployees";
    }

    @GetMapping ("/thyemployees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, RedirectAttributes ra) {
        employeeService.deleteEmployeeById(id);
        ra.addAttribute("message", "The user ID " + id + " has been deleted.");
        return "redirect:/thyemployees";
    }
}