package uz.pdp.homework1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
    boolean existsByHomeNumber(String homeNumber);
    boolean existsByHomeNumberAndIdNot(String homeNumber, Long id);
}
