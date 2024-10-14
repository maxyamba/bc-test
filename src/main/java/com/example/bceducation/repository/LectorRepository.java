package com.example.bceducation.repository;

import com.example.bceducation.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LectorRepository extends JpaRepository<Lector, UUID> {
  @Query(value = "from Lector l " +
      "inner join LectorDepartment ld on l.id = ld.lector.id " +
      "inner join Department d on d.id = ld.department.id " +
      "where ld.isHead = true and d.name = ?1")
  Optional<Lector> findHeadOfDepartment(String departmentName);

  @Query(value = "from Lector l " +
      "inner join LectorDepartment ld on l.id=ld.lector.id " +
      "inner join Department d on d.id = ld.department.id " +
      "where d.name = ?1")
  List<Lector> findByDepartment(String departmentName);

  @Query(value = "select avg(l.salary) from Lector l " +
      "inner join LectorDepartment ld on l.id=ld.lector.id " +
      "inner join Department d on d.id = ld.department.id " +
      "where  d.name = ?1")
  Double findAverageSalaryByDepartment(String departmentName);

  @Query(value = "select distinct l.fullName from Lector l " +
      "inner join LectorDepartment ld on l.id=ld.lector.id " +
      "inner join Department d on d.id = ld.department.id " +
      "where lower(l.fullName) like lower(concat('%', ?1, '%'))")
  List<String> globalSearchByTemplate(String template);
}
