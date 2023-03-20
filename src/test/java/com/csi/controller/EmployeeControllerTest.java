package com.csi.controller;

import com.csi.dao.EmployeeDao;
import com.csi.dto.EmployeeDTO;
import com.csi.exception.EmployeeNotFound;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class EmployeeControllerTest {
    public static List<Employee> employeeList;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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
    void getAllEmployees() throws Exception {
        when(employeeService.getAllData()).thenReturn(employeeList);

        mockMvc.perform(get("/employee/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().json(objectMapper.writeValueAsString(employeeList)))
                .andExpect(jsonPath("$[1].empAddress", is(employeeList.get(1).getEmpAddress())))
                .andExpect(jsonPath("$[3].empEmail", is(employeeList.get(3).getEmpEmail())));

        verify(employeeService, times(1)).getAllData();
    }

    @Test
    void saveData() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        objectMapper.setDateFormat(simpleDateFormat);

        EmployeeDTO employeeDTO = new EmployeeDTO("Akshay K", "United Stated Of America",
                "8523647982", "852365.62", new Date(13 - 11 - 2003), "akshay.k@gmail.com");

        when(employeeService.saveData(ArgumentMatchers.any())).thenReturn(employeeList.get(3));

        mockMvc.perform(post("/employee/save").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json(objectMapper.writeValueAsString(employeeList.get(3))))
                .andExpect(jsonPath("$.empName", is(employeeList.get(3).getEmpName())))
                .andExpect(jsonPath("$.empContactNumber", is(employeeList.get(3).getEmpContactNumber())));

        verify(employeeService, times(1)).saveData(ArgumentMatchers.any());
    }

    @Test
    void updateData() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        objectMapper.setDateFormat(simpleDateFormat);

        EmployeeDTO employeeDTO = new EmployeeDTO("Chaitanya K", "Shri Lanka", "6354289752",
                "95679.68", new Date(2 - 10 - 1997), "chaitanya@gmail.com");

        when(employeeDao.getDataById(employeeList.get(1).getEmpId())).thenReturn(employeeList.get(1));
        when(employeeService.updateData(employeeList.get(1).getEmpId(), employeeDTO)).thenReturn(employeeList.get(1));

        mockMvc.perform(put("/employee/update/{empId}", employeeList.get(1).getEmpId())
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(content().json(objectMapper.writeValueAsString(employeeList.get(1))))
//                .andExpect(jsonPath("$.empName", is(employeeList.get(1).getEmpName())))
//                .andExpect(jsonPath("$.empContactNumber", is(employeeList.get(1).getEmpContactNumber())))
//                 verify(employeeService, times(1)).updateData(employeeList.get(1).getEmpId(), employeeDTO);
    }

    @Test
    void getEmployee() throws Exception {
        when(employeeService.getDataById(employeeList.get(1).getEmpId())).thenReturn(employeeList.get(1));

        mockMvc.perform(get("/employee/get/{empId}", employeeList.get(1).getEmpId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.empName", is(employeeList.get(1).getEmpName())))
                .andExpect(jsonPath("$.empContactNumber", is(employeeList.get(1).getEmpContactNumber())));

        verify(employeeService, times(1)).getDataById(employeeList.get(1).getEmpId());
    }

    @Test
    void employeeNotFound() throws Exception {
        when(employeeService.getDataById(56315646)).thenThrow(new EmployeeNotFound("Employee Not Found"));

        mockMvc.perform(get("/employee/get/{empId}", 56315646))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployeeById() throws Exception {
        mockMvc.perform(delete("/employee/deleteById/{empId}", employeeList.get(0).getEmpId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("Employee Deleted Successfully")));

        verify(employeeService, times(1)).deleteEmployeeById(employeeList.get(0).getEmpId());
    }
}