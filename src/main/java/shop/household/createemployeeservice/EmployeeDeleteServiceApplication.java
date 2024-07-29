package shop.household.createemployeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("shop.household")
//@ConfigurationPropertiesScan
public class EmployeeDeleteServiceApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(EmployeeDeleteServiceApplication.class, args);
		}

}

