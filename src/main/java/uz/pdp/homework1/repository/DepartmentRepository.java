package uz.pdp.homework1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}
