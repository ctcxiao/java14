package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public void addEmployees() {
        Employee employee = new Employee();
        employee.setAge(18);
        employee.setName("chen");
        employee.setCompanyId(1L);
        employee.setGender("male");
        employee.setSalary(10000);
        employee.setId(100L);
        employeeRepository.save(employee);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee findOne(@PathVariable("id") Long id) {
        return employeeRepository.findOne(id);
    }

    @RequestMapping(value = "/employees/male", method = RequestMethod.GET)
    public List<Employee> findAllByGender() {
        return employeeRepository.findByGender("male");
    }

    @RequestMapping(value = "/employees/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Employee> findCompaniesWithPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public void updateCompanies(@PathVariable("id") Long id, @RequestParam("name") String newName) {
        employeeRepository.modifyEmployeeById(newName, id);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void deleteCompanies(@PathVariable("id") Long id) {
        employeeRepository.delete(id);
    }
}
