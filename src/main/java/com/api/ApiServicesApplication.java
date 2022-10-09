package  com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author Ishwar Bathe
*/

@SpringBootApplication
@ComponentScan(basePackages = "com.api")
public class ApiServicesApplication{

	
    public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(ApiServicesApplication.class, args);
    }
    
   

}
