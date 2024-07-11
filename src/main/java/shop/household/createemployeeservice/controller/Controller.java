package shop.household.createemployeeservice.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.household.api.employee.ServiceResponseDto;
import shop.household.api.employee.EmployeeCreateRequestDto;

import shop.household.createemployeeservice.util.ControllerUtils;



@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Component
public class Controller {

    private final ControllerUtils controllerUtils;

    @PostMapping("${endpoint}")
    ResponseEntity<ServiceResponseDto> employeeDeleteByObject(@RequestHeader HttpHeaders headers, @Validated @RequestBody EmployeeCreateRequestDto requestDto,
                                                          BindingResult result) {

        var responseDto = controllerUtils.prepareResponseDeleteByObject(requestDto, result);
        return new ResponseEntity<>(responseDto, result.hasErrors() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @DeleteMapping("${endpoint}/{id}")
    ResponseEntity<ServiceResponseDto> employeeDeleteById(@RequestHeader HttpHeaders headers,  @PathVariable("id") String id) {

        var responseDto = controllerUtils.prepareResponseDeleteById(id);
        return new ResponseEntity<>(responseDto,  HttpStatus.OK);
    }

}

