package at.itkollegimst.Springboot.tutorial.service;

import at.itkollegimst.Springboot.tutorial.entity.Department;
import at.itkollegimst.Springboot.tutorial.error.DepartmentNotFoundException;
import at.itkollegimst.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //also a Component
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department); //Methode aus JpaRepository
    }

    /**
     * Liefert alle Departments (getAllDepartments)
     * @return Liste aller Departments
     */
    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    /**
     * Liefert das Department zurück mit der gesuchten ID
     * @param departmentId gesuchte ID
     * @return Department mit der gewünschten ID
     */
    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId); //getById(departmentId)

        if (!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    /**
     * Löscht das Department mit der angegebenen ID
     * @param departmentId
     */
    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get(); //lokale Variable - Eintrag aus der Datenbank

        //Jeweils überprüfen, ob wirklich ein Parameter verändert wurde - falls ja, depDB überschreiben
        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName()))
            depDB.setDepartmentName(department.getDepartmentName());

        if (Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode()))
            depDB.setDepartmentCode(department.getDepartmentCode());

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress()))
            depDB.setDepartmentAddress(department.getDepartmentAddress());

        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}