package com.csi.service.impl;

import com.csi.dao.EmployeeDao;
import com.csi.dto.EmployeeDTO;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee saveData(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmpAddress(employeeDTO.getEmpAddress());
        employee.setEmpContactNumber(Long.parseLong(employeeDTO.getEmpContactNumber()));
        employee.setEmpSalary(Double.parseDouble(employeeDTO.getEmpSalary()));
        employee.setEmpDOB(employeeDTO.getEmpDOB());
        employee.setEmpEmail(employeeDTO.getEmpEmail());
        return employeeDao.saveData(employee);
    }

    @Override
    public Employee updateData(int empId, EmployeeDTO employeeDTO) {
        Employee employee = employeeDao.getDataById(empId);
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmpAddress(employeeDTO.getEmpAddress());
        employee.setEmpContactNumber(Long.parseLong(employeeDTO.getEmpContactNumber()));
        employee.setEmpSalary(Double.parseDouble(employeeDTO.getEmpSalary()));
        employee.setEmpDOB(employeeDTO.getEmpDOB());
        employee.setEmpEmail(employeeDTO.getEmpEmail());
        return employeeDao.saveData(employee);
    }

    @Override
    public Employee getDataById(int empId) {
        return employeeDao.getDataById(empId);
    }

    @Override
    public List<Employee> getAllData() {
        return employeeDao.getAllData();
    }

    @Override
    public void deleteEmployeeById(int empId) {
        employeeDao.deleteEmployeeById(empId);
    }
}