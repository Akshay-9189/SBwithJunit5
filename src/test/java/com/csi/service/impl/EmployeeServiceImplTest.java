package com.csi.service.impl;

import com.csi.dao.EmployeeDao;
import com.csi.dto.EmployeeDTO;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {
    public static List<Employee> employeeList;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    @BeforeAll
    public static void setUp() {
        employeeList = Stream.of(
                new Employee(5, "Akshay K", "I n d i a", 9852364712L,
                        85699.68, new Date(23 - 5 - 2001), "akshay@gmail.com"),
                new Employee(87, "Chaitanya K", "Shri Lanka", 6354289752L,
                        95679.68, new Date(2 - 10 - 1997), "chaitanya@gmail.com"),
                new Employee(60, "Omar S", "C a n a d a", 7523641280L,
                        235699.95, new Date(15 - 8 - 1999), "omar@gmail.com"),
                new Employee(24, "Akshay K", "United Stated Of America", 8523647982L,
                        852365.62, new Date(13 - 11 - 2003), "akshay.k@gmail.com")
        ).toList();
    }

    @Test
    void saveData() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Chaitanya K", "Shri Lanka", "6354289752",
                "95679.68", new Date(2 - 10 - 1997), "chaitanya@gmail.com");

        when(employeeDao.saveData(ArgumentMatchers.any())).thenReturn(employeeList.get(1));

        Employee employee = employeeService.saveData(employeeDTO);

        verify(employeeDao, times(1)).saveData(ArgumentMatchers.any());
        assertEquals(employee, employeeList.get(1));
        assertEquals(employee.getEmpName(), employeeList.get(1).getEmpName());
        assertEquals(employee.getEmpId(), employeeList.get(1).getEmpId());
    }

    @Test
    void updateData() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Chaitanya K", "Shri Lanka", "6354289752",
                "95679.68", new Date(2 - 10 - 1997), "chaitanya@gmail.com");

        when(employeeDao.getDataById(employeeList.get(1).getEmpId())).thenReturn(employeeList.get(0));
        when(employeeDao.saveData(ArgumentMatchers.any())).thenReturn(employeeList.get(1));

        Employee employee = employeeService.updateData(employeeList.get(1).getEmpId(), employeeDTO);

        verify(employeeDao, times(1)).saveData(ArgumentMatchers.any());
        verify(employeeDao, times(1)).getDataById(employeeList.get(1).getEmpId());
        assertEquals(employee, employeeList.get(1));
        assertEquals(employee.getEmpName(), employeeList.get(1).getEmpName());
        assertEquals(employee.getEmpId(), employeeList.get(1).getEmpId());
    }

    @Test
    void getDataById() {
        when(employeeDao.getDataById(employeeList.get(0).getEmpId())).thenReturn(employeeList.get(0));

        Employee employee = employeeService.getDataById(employeeList.get(0).getEmpId());

        verify(employeeDao, times(1)).getDataById(employeeList.get(0).getEmpId());
        assertEquals(employee, employeeList.get(0));
        assertEquals(employee.getEmpSalary(), employeeList.get(0).getEmpSalary());
        assertEquals(employee.getEmpAddress(), employeeList.get(0).getEmpAddress());
    }

    @Test
    void getAllData() {
        when(employeeDao.getAllData()).thenReturn(employeeList);

        List<Employee> employees = employeeService.getAllData();

        verify(employeeDao, times(1)).getAllData();
        assertEquals(employees.size(), employeeList.size());
        assertEquals(employees.get(3), employeeList.get(3));
        assertEquals(employees.get(0).getEmpName(), employeeList.get(0).getEmpName());
        assertEquals(employees.get(3).getEmpId(), employeeList.get(3).getEmpId());
        assertEquals(employees.get(2).getEmpSalary(), employeeList.get(2).getEmpSalary());
    }

    @Test
    void deleteEmployeeById() {
        employeeService.deleteEmployeeById(employeeList.get(2).getEmpId());
        verify(employeeDao, times(1)).deleteEmployeeById(employeeList.get(2).getEmpId());
    }
}