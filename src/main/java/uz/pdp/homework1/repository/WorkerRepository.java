package uz.pdp.homework1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Adress;
import uz.pdp.homework1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhoneNumberAndName(String phoneNumber, String name);
    boolean existsByPhoneNumberAndAdress_IdAndIdNot(String phoneNumber, Long adress_id, Long id);

    boolean existsByPhoneNumberAndAdressId(String phoneNumber, Long adress_id);
}
