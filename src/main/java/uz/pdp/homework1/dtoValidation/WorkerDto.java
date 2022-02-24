package uz.pdp.homework1.dtoValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    private Long id;

    @NotNull(message = "Name bosh bolmasligi kerak")
    private String name;

    @NotNull(message = "PhoneNumber bosh bolmasligi kerak")
    private String phoneNUmber;

    @NotNull(message = "Adress bosh bolmasligi  kerak")
    private Long adressId;

    @NotNull(message = "Department bosh bolmasligi  kerak")
    private Long departmentId;
}
