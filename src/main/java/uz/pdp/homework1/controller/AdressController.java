package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.dtoValidation.AdressDto;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.resultReturnClass.Result;
import uz.pdp.homework1.service.AdressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adress")
public class AdressController {

    @Autowired
    AdressService adressService;

    @GetMapping("/get")
    public ResponseEntity<List<Adress>> getAll(){
        List<Adress> all = adressService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Adress> getById(@PathVariable Long id){
        Adress byId = adressService.getById(id);
        return ResponseEntity.ok(byId);
    }

    @PostMapping("/post")
    public HttpEntity<Result> post(@Valid @RequestBody AdressDto adressDto){
        Result post = adressService.post(adressDto);
        if (post.isBolean()){
            return ResponseEntity.status(200).body(post);
        }else {
            return ResponseEntity.status(405).body(post);
        }
    }

    @PutMapping("/put/{id}")
    public HttpEntity<Result> put(@Valid @RequestBody AdressDto adressDto, @PathVariable Long id){
        Result put = adressService.put(id, adressDto);
        if (put.isBolean()){
            return ResponseEntity.status(200).body(put);
        }else {
            return  ResponseEntity.status(405).body(put);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<Result> delete(@Valid @PathVariable Long id){
        Result delete = adressService.delete(id);
        if (delete.isBolean()){
            return ResponseEntity.status(200).body(delete);
        }else {
            return ResponseEntity.status(405).body(delete);
        }
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
