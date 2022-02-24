package uz.pdp.homework1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Long id);
    boolean existsByAdress_Id(Long adress_id);
}
