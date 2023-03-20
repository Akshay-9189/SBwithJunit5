package com.csi.controller;

import com.csi.dto.EmployeeDTO;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllData());
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveData(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveData(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.updateData(empId, employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.getDataById(empId));
    }

    @DeleteMapping("/deleteById/{empId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int empId) {
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}