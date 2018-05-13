package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Override
    List<Company> findAll();

    @Override
    Company findOne(Long id);

    @Query(value = "select new com.example.employee.restfulapi.entity.Employee(id, name, age, gender, salary, companyId) from Employee where companyId=?1")
    List<Employee> findEmployeesInCompany(Long companyId);

    @Override
    Page<Company> findAll(Pageable pageable);

    @Override
    void delete(Long id);

    @Modifying
    @Transactional
    @Query("update Company set companyName=?1 where id=?2")
    void modifyCompanyById(String name, Long id);
}
