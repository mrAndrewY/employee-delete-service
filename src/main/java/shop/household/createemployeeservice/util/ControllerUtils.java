package shop.household.createemployeeservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.api.employee.EmployeeCreateResponseDto;
import shop.household.api.employee.ErrorDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.createemployeeservice.impl.EmployeeServiceImpl;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;

@Component
@RequiredArgsConstructor
public class ControllerUtils {
    private final EmployeeServiceImpl employeeService;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;

    public ServiceResponseDto prepareResponseDeleteByObject(EmployeeCreateRequestDto requestDto,
                                              BindingResult result) {
        var responseDto = new ServiceResponseDto();
        if (result.hasErrors()) {
            responseDto.setStatus(false);
            responseDto.setError(ErrorDto
                    .builder()
                    .name("non correct fields")
                    .status("status1")
                    .build());
        } else {
            var employeeModel = requestMapper.mapDtoToModelEmployee(requestDto);
            Try.of1(() -> {
                        employeeService.deleteEmployee(employeeModel);
                    })
                    .onSuccess((e) -> {
                        responseDto.setStatus(true);
                        responseDto.employeeCreateResponse(EmployeeCreateResponseDto.builder()
                                .employee(responseMapper
                                        .mapEmployee(employeeModel))
                                .build());
                    })
                    .onFailure((e) -> {
                        responseDto.setError(ErrorDto.builder()
                                .status("status10")
                                .name(e.getMessage())
                                .build());
                        responseDto.setStatus(false);
                    });

        }
        return responseDto;
    }

    public ServiceResponseDto prepareResponseDeleteById(String id) {
      var longId = Long.parseLong(id);
        var responseDto = new ServiceResponseDto();
            Try.of1(() -> {
                        employeeService.deleteEmployeeById(longId);
                    })
                    .onSuccess((e) -> {
                        responseDto.setStatus(true);
                    })
                    .onFailure((e) -> {
                        responseDto.setError(ErrorDto.builder()
                                .status("status10")
                                .name(e.getMessage())
                                .build());
                        responseDto.setStatus(false);
                    });
        return responseDto;
        }

    }

