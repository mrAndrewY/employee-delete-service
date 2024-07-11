package shop.household.createemployeeservice.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.household.api.employee.EmployeeCreateResponseDto;
import shop.household.api.employee.ErrorDto;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.api.employee.EmployeeCreateRequestDto;
import shop.household.createemployeeservice.mapper.RequestMapper;
import shop.household.createemployeeservice.mapper.ResponseMapper;
import shop.household.createemployeeservice.impl.EmployeeServiceImpl;
import shop.household.createemployeeservice.util.FieldChecker;
import shop.household.createemployeeservice.util.MyClass;
import shop.household.createemployeeservice.util.ThrowableOperation;
import shop.household.createemployeeservice.util.Try;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Component
public class Controller {

    private final EmployeeServiceImpl employeeService;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;

    @PostMapping("${endpoint}")
    ResponseEntity<ServiceResponseDto> employeeProcessing (@RequestBody @Valid EmployeeCreateRequestDto requestDto,
                                                           @RequestHeader HttpHeaders headers){

        var nonCorrect = FieldChecker.hasNullsInFields(requestDto);
        var responseDto = new ServiceResponseDto();
        if(nonCorrect) {
            responseDto.setStatus(false);
            responseDto.setError(ErrorDto
                    .builder()
                    .name("non correct fields")
                    .status("status1")
                    .build());
        } else {
            var employeeModel = requestMapper.mapDtoToModelEmployee(requestDto);
            Try.of1(() -> {employeeService.deleteEmployee(employeeModel);})
                    .onSuccess((e)->{responseDto.setStatus(true);})
                    .onFailure((e)->{responseDto.setError(new ErrorDto("status10", e.getMessage()));
                                     responseDto.setStatus(false);});
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

