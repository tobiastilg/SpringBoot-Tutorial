package at.itkollegimst.Springboot.tutorial.service;

import at.itkollegimst.Springboot.tutorial.entity.Department;

/**
 * Kommunikationsinterface
 */
public interface DepartmentService {
    public Department saveDepartment(Department department);
}
