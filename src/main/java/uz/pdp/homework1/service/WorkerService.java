package uz.pdp.homework1.service;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework1.dtoValidation.WorkerDto;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.entity.Department;
import uz.pdp.homework1.entity.Worker;
import uz.pdp.homework1.repository.AdressRepository;
import uz.pdp.homework1.repository.DepartmentRepository;
import uz.pdp.homework1.repository.WorkerRepository;
import uz.pdp.homework1.resultReturnClass.Result;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getAll(){
        List<Worker> all = workerRepository.findAll();
        return all;
    }

    public Worker getById(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()){
            Worker worker = byId.get();
            return worker;
        }
        return null;
    }

    public Result post(WorkerDto workerDto){
        Optional<Department> byIdDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        Optional<Adress> byIdAdress = adressRepository.findById(workerDto.getAdressId());
        boolean b = workerRepository.existsByPhoneNumberAndAdressId(workerDto.getPhoneNUmber(), workerDto.getAdressId());
        if (b || !byIdAdress.isPresent() || !byIdDepartment.isPresent()){
            return new Result(false, "Error information");
        }
        Adress adress = byIdAdress.get();
        Department department = byIdDepartment.get();
        Worker worker = new Worker(null, workerDto.getName(), workerDto.getPhoneNUmber(), adress, department);
        workerRepository.save(worker);
        return new Result(true, "Added");
    }

    public Result put(WorkerDto workerDto, Long id){
        Optional<Worker> byIdWorker = workerRepository.findById(id);
        Optional<Adress> byIdAdress = adressRepository.findById(workerDto.getAdressId());
        Optional<Department> byIdDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        boolean b = workerRepository.existsByPhoneNumberAndAdress_IdAndIdNot(workerDto.getPhoneNUmber(), workerDto.getAdressId(), id);
        if (b || !byIdWorker.isPresent() || !byIdDepartment.isPresent()|| !byIdAdress.isPresent()){
            return new Result(false, "Error Information");
        }

        Department department = byIdDepartment.get();
        Adress adress = byIdAdress.get();
        Worker workerEdit = byIdWorker.get();
        workerEdit.setName(workerDto.getName());
        workerEdit.setPhoneNumber(workerDto.getPhoneNUmber());
        workerEdit.setAdress(adress);
        workerEdit.setDepartment(department);
        workerRepository.save(workerEdit);
        return new Result(true, "Edited");
    }


    public Result delete(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent()){
            Worker worker = byId.get();
            workerRepository.deleteById(id);
            adressRepository.deleteById(worker.getAdress().getId());
            departmentRepository.deleteById(worker.getDepartment().getId());
            return new Result(true, "Deleted");
        }
        return new Result(false, "errorInformation");
    }











}
