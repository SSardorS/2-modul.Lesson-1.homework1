package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.dtoValidation.CompanyDto;
import uz.pdp.homework1.dtoValidation.DepartmentDto;
import uz.pdp.homework1.entity.Department;
import uz.pdp.homework1.resultReturnClass.Result;
import uz.pdp.homework1.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    @GetMapping("/get")
    public HttpEntity<List<Department>> getAll(){
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/get/{id}")
    public HttpEntity<Department> getById(@PathVariable Long id){
        Department byId = departmentService.getById(id);
        if (byId == null){
            return ResponseEntity.status(409).body(byId);
        }
        return ResponseEntity.status(200).body(byId);
    }

    @PostMapping("/post")
    public HttpEntity<Result> post(@Valid @RequestBody DepartmentDto departmentDto){
        Result post = departmentService.post(departmentDto);
        if (post.isBolean()){
            return ResponseEntity.status(200).body(post);
        }
        return ResponseEntity.status(405).body(post);
    }

    @PutMapping("/put/{id}")
    public HttpEntity<Result> put(@Valid @RequestBody DepartmentDto departmentDto, @PathVariable Long id){
        Result put = departmentService.put(id, departmentDto);
        if (put.isBolean()){
            return ResponseEntity.status(200).body(put);
        }else {
            return ResponseEntity.status(405).body(put);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<Result> delete(@PathVariable Long id){
        Result delete = departmentService.delete(id);
        if (delete.isBolean()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(405).body(delete);
    }
}
