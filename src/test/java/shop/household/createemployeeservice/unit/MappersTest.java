//package shop.household.createemployeeservice.unit;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import shop.household.api.employee.EmployeeCreateRequestDto;
//import shop.household.createemployeeservice.IT;
//import shop.household.createemployeeservice.entity.Employee;
//import shop.household.createemployeeservice.mapper.ResponseMapper;
//import shop.household.createemployeeservice.mapper.ResponseMapperImpl;
//
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static shop.household.createemployeeservice.unit.EmployeeServiceTest.EMPLOYEE_ID;
//
//@IT
////@RequiredArgsConstructor
//public class MappersTest {
//
//
//    private final ResponseMapper responseMapper = new ResponseMapperImpl();
//
////    public MappersTest(ResponseMapper responseMapper) {
////        this.responseMapper = responseMapper;
////    }
//
//
////   @Test
////    void requestMapperTest(){
////
////
////   }
//
//
//
//    @Test
//    void responseMapperTest(){
//        Employee employee =Employee.
//                builder()
//                .id(EMPLOYEE_ID)
//                .name("Andrew")
//                .email("yy@yandex.ru")
//                .telephone( "+7-555-555-55-55")
//                .build();
//
//        assertNull(responseMapper.mapEmployee(employee).getLastname());
//        Employee emp = Employee.
//                builder()
//                .id(EMPLOYEE_ID)
//                .name("Andrew")
//                .email("yy@yandex.ru")
//                .telephone( "+7-555-555-55-55")
//                .build();
//
//    }
//
//    @Test
//    void validatorTest(){
//        var exampleDto = new ExampleDto("Andrew", null);
//        check(exampleDto);
//        try{
//            var lastName = exampleDto.getLastName();}
//        catch(Exception exception){
//            System.out.println("exception!!!");
//        }
//
//    }
//
//    @Data
//    @RequiredArgsConstructor
//    class ExampleDto{
//        private final String name;
//        private final String lastName;
//    }
//
//    void check(@Validated ExampleDto exampleDto) {
//
//    }
//
//}
