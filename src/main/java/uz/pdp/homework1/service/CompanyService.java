package uz.pdp.homework1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework1.dtoValidation.CompanyDto;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.entity.Company;
import uz.pdp.homework1.repository.AdressRepository;
import uz.pdp.homework1.repository.CompanyRepository;
import uz.pdp.homework1.resultReturnClass.Result;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AdressRepository adressRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(Long id) {
        return companyRepository.getById(id);
    }

    public Result post(CompanyDto companyDto) {
        boolean b = companyRepository.existsByCorpName(companyDto.getCorpName());
        Optional<Adress> byId = adressRepository.findById(companyDto.getAdressId());
        boolean adressId = companyRepository.existsByAdress_Id(companyDto.getAdressId());
        if (b || !byId.isPresent() || adressId) {
            return new Result(false, "Error information");
        }
        Adress adress = byId.get();
        Company company = new Company(null, companyDto.getCorpName(), companyDto.getDirectName(), adress);
        companyRepository.save(company);
        return new Result(true, "Added");
    }

    public Result put(Long id, CompanyDto companyDto) {
        boolean b = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        Optional<Adress> byIdAdress = adressRepository.findById(companyDto.getAdressId());
        Optional<Company> byIdCompany = companyRepository.findById(id);
        boolean adressId = companyRepository.existsByAdress_Id(companyDto.getAdressId());
        if (b && !byIdCompany.isPresent() || !byIdAdress.isPresent() || adressId) {
            return new Result(false, "Error information");
        }
        Company company = byIdCompany.get();
        company.setAdress(byIdAdress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectName(companyDto.getDirectName());
        companyRepository.save(company);
        return new Result(true, "Edited");
    }

    public Result delete(Long id){
        Optional<Company> byId = companyRepository.findById(id);

        if (byId.isPresent()){
            Company company = byId.get();companyRepository.deleteById(id);
            adressRepository.deleteById(company.getAdress().getId());
            return new Result(true, "Deleted");
        }
        return new Result(false, "this id is not found");
    }



}
