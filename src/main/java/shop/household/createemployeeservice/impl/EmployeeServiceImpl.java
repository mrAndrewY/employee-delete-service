package shop.household.createemployeeservice.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.household.createemployeeservice.error.ServiceException;
import shop.household.createemployeeservice.entity.Employee;
import shop.household.createemployeeservice.repository.EmployeeRepository;
import shop.household.createemployeeservice.service.EmployeeService;

import java.util.Optional;

import static shop.household.createemployeeservice.error.ExceptionCode.ENTITY_NOT_FOUND;

@Service
@Data
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    @Transactional
    public void deleteEmployeeById(Long id) throws ServiceException {
        employeeRepository
                .findEmployeeById(id)
                .orElseThrow(() -> new ServiceException(String.format("Entity with id %s not found in base", id), ENTITY_NOT_FOUND));
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) throws ServiceException {
        var res = employeeRepository.
                findEmployeeByNameAndLastNameAndEmail(
                        employee.getName(),
                        employee.getLastName(),
                        employee.getEmail())
                .orElseThrow(() -> new ServiceException(String.format("Entity with name %s not found in base",
                        employee.getName()), ENTITY_NOT_FOUND));
        employeeRepository.delete(res);
    }
}
