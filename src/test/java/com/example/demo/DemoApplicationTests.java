package com.example.demo;

import com.example.demo.service.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {
	private Calculator calculator;

	@BeforeEach
	void setup(){
		calculator=new Calculator();
	}
	@Test
	void testAdd(){
		assertEquals(9,calculator.add(3,6));
	}
	@Test
	void testMultiply(){
		assertEquals(15,calculator.multiply(3,5),"Right");
	}

	@Test
	void testDivide(){
		assertThrows(ArithmeticException.class,()->calculator.divide(5,0),"Chia cho 0 kia");
	}

}
