package uz.pdp.homework1.dtoValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    private Long id;

    @NotNull(message = "name bosh bolmasligi kerak")
    private String name;

    @NotNull(message = "companyName bosh bolmasligi kerak")
    private Long companyId;

}
