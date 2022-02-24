package uz.pdp.homework1.dtoValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdressDto {

    private  Long id;

    @NotNull(message = "street bosh bolmasligi kerak")
    private String street;

    @NotNull(message = "street bosh bolmasligi kerak")
    private String homeNumber;
}
