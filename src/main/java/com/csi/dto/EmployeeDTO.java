package com.csi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Full name should not blank")
    @Size(min = 4, max = 18, message = "Name should contain 4 to 18 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Special character & number are not allowed")
    private String empName;

    @Size(min = 10, max = 100, message = "Address should be 10 to 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9+.,:=\\s]+$", message = "Please enter valid address")
    private String empAddress;

    @Size(min = 10, max = 10, message = "10 digits number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Invalid contact number")
    private String empContactNumber;

    @Pattern(regexp = "^[0-9]{1,9}+[.]{1}+[0-9]{2}+$", message = "Please enter salary in valid format (mm.mm eg- 254685.23))")
    private String empSalary;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @NotBlank(message = "Email id should not be blank")
    @Pattern(regexp = "^[a-z0-9+.]+@[a-z]+[(.){1}]+[a-z]+$", message = "Please enter valid email id")
    private String empEmail;
}