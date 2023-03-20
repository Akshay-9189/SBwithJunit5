package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee saveData(Employee employee);

    Employee getDataById(int empId);

    List<Employee> getAllData();

    void deleteEmployeeById(int empId);
}