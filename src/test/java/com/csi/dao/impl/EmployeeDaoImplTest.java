package com.csi.dao.impl;

import com.csi.dao.EmployeeDao;
import com.csi.exception.EmployeeNotFound;
import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeDaoImplTest {
    public static List<Employee> employeeList;
    @Autowired
    private EmployeeDao employeeDao;
    @MockBean
    private EmployeeRepository employeeRepository;

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
        when(employeeRepository.save(employeeList.get(2))).thenReturn(employeeList.get(2));

        Employee employee = employeeDao.saveData(employeeList.get(2));

        verify(employeeRepository, times(1)).save(employeeList.get(2));
        assertEquals(employee, employeeList.get(2));
        assertEquals(employee.getEmpName(), employeeList.get(2).getEmpName());
        assertEquals(employee.getEmpId(), employeeList.get(2).getEmpId());
    }

    @Test
    void getDataById() {
        when(employeeRepository.findById(employeeList.get(0).getEmpId())).thenReturn(Optional.ofNullable(employeeList.get(0)));

        Employee employee = employeeDao.getDataById(employeeList.get(0).getEmpId());

        verify(employeeRepository, times(1)).findById(employeeList.get(0).getEmpId());
        assertEquals(employee, employeeList.get(0));
        assertEquals(employee.getEmpSalary(), employeeList.get(0).getEmpSalary());
        assertEquals(employee.getEmpAddress(), employeeList.get(0).getEmpAddress());
    }

    @Test
    void getAllData() {
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> employees = employeeDao.getAllData();

        verify(employeeRepository, times(1)).findAll();
        assertEquals(employees.size(), employeeList.size());
        assertEquals(employees.get(3), employeeList.get(3));
        assertEquals(employees.get(0).getEmpName(), employeeList.get(0).getEmpName());
        assertEquals(employees.get(3).getEmpId(), employeeList.get(3).getEmpId());
        assertEquals(employees.get(2).getEmpSalary(), employeeList.get(2).getEmpSalary());
    }

    @Test
    void deleteEmployeeById() {
        employeeDao.deleteEmployeeById(employeeList.get(2).getEmpId());
        verify(employeeRepository, times(1)).deleteById(employeeList.get(2).getEmpId());
    }

    @Test
    void getDataByIdException() {
        assertThrows(EmployeeNotFound.class, () -> employeeDao.getDataById(34534), "Employee Not Found !!!");
        verify(employeeRepository, times(1)).findById(34534);
    }
}