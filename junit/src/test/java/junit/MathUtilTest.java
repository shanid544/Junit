package junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


//@TestInstance(TestInstance.Lifecycle.PER_METHOD) 
//--> default behaviour. 
//test instance created for each method

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Mathutil is rinning")
class MathUtilTest {
	
	MathUtil m;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll                       //if we want to run a method befor all the funtions. 
	                                 //junit cant create a instance for that beforall 
	                                  //thst why it should static
	static void beforAll() {
		System.out.println("before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		m = new MathUtil();
		//this will print like logs into the console
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}

	@AfterEach
	void cleanup() {
		System.out.println("cleaned");
	}
	
	@Nested
	@DisplayName("tstin add method")
	@Tag("math")
	class testAdd{
		@Test
		@DisplayName(value = "testind add method for positive intigres")
		void testaddpositive() {
			int expected = 5;
			int actual = m.add(1, 2);
			assertEquals(expected,actual,"here we can add any messages that helps devolopers");
			
		}
		
		@Test
		@DisplayName(value = "testind add method for negative intigeres")
		void testnegative() {
			int expected = -3;
			int actual = m.add(-1, -2);
			assertEquals(expected,actual,() -> "we expecyed this :"+ expected +" but we got "+actual);
			
		}
	}
	
	@Test
	@DisplayName(value = "testind add method")
	@Tag("math")
	void test() {
		int expected = 3;
		int actual = m.add(1, 2);
		assertEquals(expected,actual,"here we can add any messages that helps devolopers");
		}

	
	@Test
	@Tag("area")
	//@RepeatedTest(3)
	//void testArea(RepetitionInfo repetitionInfo) {
	void testArea() {
		//int i=repetitionInfo.getCurrentRepetition();
		//int n= repetitionInfo.getTotalRepetitions();
		int expected = 16;
		int actual = m.area(4);
		assertEquals(expected,actual,"here we can add any messages that helps devolopers");
		
	}
	@Test
	@Tag("math")
	//@EnabledOnOs(OS.LINUX)
	void testDivide() {
		Boolean serverUp = false;
		assumeTrue(serverUp);
		assertThrows(NullPointerException.class, () -> m.divide(1,0), "divide by zero should throw an error");
	}
	
	@Test
	@DisplayName(value = "failing test  test skipping...")
	@Disabled
	void testDisabled() {
		fail("this test should be disabled");
	}
	
	@Test
	void testMultiply() {
		//assertEquals(4,m.multiply(2, 2), "divide by zero should throw an error");
		assertAll(
				()-> assertEquals(4,m.multiply(2, 2)),
				()-> assertEquals(0,m.multiply(0, 2)),
				()-> assertEquals(-2,m.multiply(-1, 2))
				);
	}
}
