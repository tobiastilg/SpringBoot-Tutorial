package at.itkollegimst.Springboot.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeView {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String departmentName;
}