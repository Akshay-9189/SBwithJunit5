package com.csi.service;

import com.csi.dto.EmployeeDTO;
import com.csi.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveData(EmployeeDTO employeeDTO);

    Employee updateData(int empId, EmployeeDTO employeeDTO);

    Employee getDataById(int empId);

    List<Employee> getAllData();

    void deleteEmployeeById(int empId);
}