package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.dtoValidation.CompanyDto;
import uz.pdp.homework1.entity.Company;
import uz.pdp.homework1.resultReturnClass.Result;
import uz.pdp.homework1.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/get")
    public HttpEntity<List<Company>> getAll() {
        return ResponseEntity.status(200).body(companyService.getAll());
    }

    @GetMapping("/get/{id}")
    public HttpEntity<Company> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(companyService.getById(id));
    }

    @PostMapping("/post")
    public HttpEntity<Result> put(@Valid @RequestBody CompanyDto companyDto) {
        Result post = companyService.post(companyDto);
        if (post.isBolean()){
            return ResponseEntity.status(200).body(post);
        }else {
            return ResponseEntity.status(405).body(post);
        }
    }

    @PutMapping("/put/{id}")
    public HttpEntity<Result> put(@Valid @RequestBody CompanyDto companyDto, @PathVariable Long id){
        Result put = companyService.put(id, companyDto);
        if (put.isBolean()){
            return ResponseEntity.status(200).body(put);
        }else {
            return ResponseEntity.status(405).body(put);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<Result> delete(@Valid @PathVariable Long id){
        Result delete = companyService.delete(id);
        if (delete.isBolean()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(405).body(delete);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }




}
