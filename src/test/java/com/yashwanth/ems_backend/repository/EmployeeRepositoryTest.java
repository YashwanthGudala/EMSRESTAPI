package com.yashwanth.ems_backend.repository;

import com.yashwanth.ems_backend.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
    }

    @Test
    void testSaveAndFindById() {
        Employee savedEmployee = employeeRepository.save(employee);
        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertThat(foundEmployee).isPresent();
        assertThat(foundEmployee.get().getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(foundEmployee.get().getLastName()).isEqualTo(employee.getLastName());
        assertThat(foundEmployee.get().getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindAll() {
        employeeRepository.save(employee);
        List<Employee> allEmployees = employeeRepository.findAll();

        assertThat(allEmployees).isNotEmpty();
        assertThat(allEmployees.get(0).getFirstName()).isEqualTo(employee.getFirstName());
    }

    @Test
    void testDelete() {
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());
        assertThat(foundEmployee).isEmpty();
    }

    @Test
    void testExistsById() {
        Employee savedEmployee = employeeRepository.save(employee);
        boolean exists = employeeRepository.existsById(savedEmployee.getId());

        assertThat(exists).isTrue();
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Employee> foundEmployee = employeeRepository.findById(999L);

        assertThat(foundEmployee).isEmpty();
    }
}
