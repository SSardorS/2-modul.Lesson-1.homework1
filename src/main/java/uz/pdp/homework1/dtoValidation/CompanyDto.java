package uz.pdp.homework1.dtoValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.homework1.entity.Adress;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {

    private Long id;

    @NotNull(message = "corpName bosh bolmasligi kerak")
    private String corpName;

    @NotNull(message = "directName bosh bolmasligi kerak")
    private String directName;

    @NotNull(message = "adres bosh bolmasligi kerak")
    private Long adressId;
}
