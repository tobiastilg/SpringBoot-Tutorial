package at.itkollegimst.Springboot.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(
        name = "emailaddress_unique",
        columnNames = "email_address"))
public class Employee {

    @Id
    @Column(name = "employee_id")
    @SequenceGenerator(name = "employee_sequence" , sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    private Long employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;
}