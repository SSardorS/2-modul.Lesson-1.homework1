package uz.pdp.homework1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework1.dtoValidation.CompanyDto;
import uz.pdp.homework1.dtoValidation.DepartmentDto;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.entity.Company;
import uz.pdp.homework1.entity.Department;
import uz.pdp.homework1.repository.CompanyRepository;
import uz.pdp.homework1.repository.DepartmentRepository;
import uz.pdp.homework1.resultReturnClass.Result;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll(){
        List<Department> all = departmentRepository.findAll();
        return all;
    }

    public Department getById(Long id){
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    public Result post(DepartmentDto departmentDto){
        Optional<Company> byId = companyRepository.findById(departmentDto.getCompanyId());
        boolean b = departmentRepository.existsByName(departmentDto.getName());

        if (b || !byId.isPresent()){
            return new Result(false, "This name is alredy exists");
        }
        Department department = new Department(null, departmentDto.getName(), byId.get());
        return new Result(true, "Succesfull Added");
    }

    public Result put(Long id, DepartmentDto departmentDto) {

        Optional<Company> byIdCompany = companyRepository.findById(departmentDto.getCompanyId());
        Optional<Department> byIdDepartment = departmentRepository.findById(id);
        if (!byIdCompany.isPresent() || !byIdDepartment.isPresent()) {
            return new Result(false, "Error information");
        }
        Department departmentEdit = byIdDepartment.get();
        departmentEdit.setCompany(byIdCompany.get());
        departmentEdit.setName(departmentDto.getName());
        departmentRepository.save(departmentEdit);
        return new Result(true, "Edited");
    }

    public Result delete(Long id){
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isPresent()){
            departmentRepository.deleteById(id);
            return new Result(true, "Deleted");
        }
        return new Result(false, "This id isnot found");
    }

}
