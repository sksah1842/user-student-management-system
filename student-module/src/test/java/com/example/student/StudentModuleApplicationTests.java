package com.example.student;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("docker")
class StudentModuleApplicationTests {

	@Test
	void contextLoads() {
	}

}
