package uz.pdp.homework1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.homework1.dtoValidation.AdressDto;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.repository.AdressRepository;
import uz.pdp.homework1.resultReturnClass.Result;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AdressService {
    @Autowired
    AdressRepository adressRepository;

    public List<Adress> getAll() {
        List<Adress> all = adressRepository.findAll();
        return all;
    }

    public Adress getById(Long id) {
        Optional<Adress> byId = adressRepository.findById(id);
        if (byId.isPresent()) {
            Adress adress = byId.get();
            return adress;
        }
        return null;
    }

    public Result post(AdressDto adressDto) {
        boolean b = adressRepository.existsByHomeNumber(adressDto.getHomeNumber());
        if (b) {
            return new Result(false, "this phone number is already exists!");
        }
        Adress adress = new Adress(null, adressDto.getStreet() ,adressDto.getHomeNumber());
        adressRepository.save(adress);
        return new Result(true, "Succesfull Added");
    }

    public Result put(Long id, AdressDto adressDto){
        boolean b = adressRepository.existsByHomeNumberAndIdNot(adressDto.getHomeNumber(), id);
        if (b){
            return new Result(false, "this phone number is already exists!");
        }
        Optional<Adress> byId = adressRepository.findById(id);
        Adress adress = byId.get();
        adress.setHomeNumber(adressDto.getHomeNumber());
        adress.setStreet(adressDto.getStreet());
        adressRepository.save(adress);
        return new Result(true, "Edited");

    }

    public Result delete(Long id){
        Optional<Adress> byId = adressRepository.findById(id);
        if (byId.isPresent()){
            adressRepository.deleteById(id);
            return new Result(true,"deleted");
        }else {
            return new Result(false, "This id is not found");
        }
    }




}
