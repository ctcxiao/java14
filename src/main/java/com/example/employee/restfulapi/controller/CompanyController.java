package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    //在此处完成Company API

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = "/companies")
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    @GetMapping(value = "/companies/{id}")
    public Company findOne(@PathVariable("id") Long id){
        return companyRepository.findOne(id);
    }

    @GetMapping(value = "/companies/{companyId}/employees")
    public List<Employee> findEmployeesInCompany(@PathVariable("companyId") Long companyId){
        return companyRepository.findEmployeesInCompany(companyId);
    }

    @GetMapping(value = "/companies/page/{page}/pageSize/{pageSize}")
    public Page<Company> findCompaniesWithPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @PostMapping(value = "/companies")
    public Company addCompanies(){
        Company company = new Company();
        company.setCompanyName("baidu");
        company.setId(0L);
        company.setEmployeesNumber(10);
        return companyRepository.save(company);
    }

    @PutMapping(value = "/companies/{id}")
    public void updateCompanies(@PathVariable("id") Long id,@RequestParam("name")String newName){
        companyRepository.modifyCompanyById(newName, id);
    }

    @DeleteMapping(value = "/companies/{id}")
    public void deleteCompanies(@PathVariable("id") Long id){
        companyRepository.delete(id);
    }
}
