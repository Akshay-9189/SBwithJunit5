package com.csi.dao.impl;

import com.csi.dao.EmployeeDao;
import com.csi.exception.EmployeeNotFound;
import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveData(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getDataById(int empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFound("Employee Not Found !!!!"));
    }

    @Override
    public List<Employee> getAllData() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(int empId) {
        employeeRepository.deleteById(empId);
    }
}