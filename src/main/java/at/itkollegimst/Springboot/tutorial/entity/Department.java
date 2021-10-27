package at.itkollegimst.Springboot.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity //Class can interact with Database using JPA
@Data //Lombok (es gibt auch @-Notations für zB Getter, falls man nur diese benötigen würde)
@NoArgsConstructor //DefaultConstructor
@AllArgsConstructor //Konstruktor mit allen Datenfeldern
@Builder //entire Builder Pattern (benutzt beim Testing - unterschiedliche Constructors, benutzt zum Mocking)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Please Add Department Name") //Error Message
    /*@Length(max = 5, min = 1)
    @Size(max = 10, min = 0)*/
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}