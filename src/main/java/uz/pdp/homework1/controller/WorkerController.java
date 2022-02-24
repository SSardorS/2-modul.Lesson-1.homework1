package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.dtoValidation.WorkerDto;
import uz.pdp.homework1.entity.Worker;
import uz.pdp.homework1.resultReturnClass.Result;
import uz.pdp.homework1.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping("/get")
    public HttpEntity<List<Worker>> getAll(){
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/get/{id}")
    public HttpEntity<Worker> getById(@PathVariable Long id){
        Worker byId = workerService.getById(id);
        if (byId.equals(null)){
            return ResponseEntity.status(405).body(byId);
        }
        return ResponseEntity.status(200).body(byId);
    }

    @PostMapping("/post")
    public HttpEntity<Result> post(@RequestBody WorkerDto workerDto){
        Result post = workerService.post(workerDto);
        if (post.isBolean()){
            return ResponseEntity.status(200).body(post);
        }
        return ResponseEntity.status(405).body(post);
    }

    @PutMapping("/put/{id}")
    public HttpEntity<Result> put(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        Result put = workerService.put(workerDto, id);
        if (put.isBolean()){
            return ResponseEntity.status(200).body(put);
        }
        return ResponseEntity.status(405).body(put);
    }

    @DeleteMapping("/delete/{id}")
    public  HttpEntity<Result> delete(@PathVariable Long id){
        Result delete = workerService.delete(id);
        if (delete.isBolean()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(405).body(delete);
    }



}
