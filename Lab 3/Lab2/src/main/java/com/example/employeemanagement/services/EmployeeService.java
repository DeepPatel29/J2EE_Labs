package com.example.employeemanagement.services;

import com.example.employeemanagement.models.Employee;
import jakarta.ejb.Stateless;
import java.util.*;

@Stateless
public class EmployeeService {

    private static Map<Integer, Employee> employeeMap = new HashMap<>();
    private static int currentId = 1; // Auto-assign ID counter


    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

    public Employee getById(int id) {
        return employeeMap.get(id);
    }

    public Employee create(Employee employee) {
        employee.setId(currentId++); // Auto-assigns the ID
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Employee update(int id, Employee employee) {
        if (!employeeMap.containsKey(id)) return null;
        employee.setId(id);
        employeeMap.put(id, employee);
        return employee;
    }

    public boolean delete(int id) {
        return employeeMap.remove(id) != null;
    }
}