package com.yashwanth.ems_backend.mapper;

import com.yashwanth.ems_backend.dto.EmployeeDto;
import com.yashwanth.ems_backend.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto maptoDto(Employee employee){
        return new EmployeeDto(employee.getId() ,
                employee.getFirstName() , employee.getLastName(), employee.getEmail());
    }

    public static Employee mapToEntity(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail());
    }
}
