package shop.household.createemployeeservice.service;

import shop.household.api.employee.EmployeeDto;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.error.ServiceException;

import java.util.Optional;


public interface EmployeeService {

    public void deleteEmployeeById(Long id) throws ServiceException ;
    public void deleteEmployee(Employee employee) throws ServiceException ;
}
