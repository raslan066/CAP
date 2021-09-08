package com.comcast.stringinator;

import com.comcast.stringinator.controller.StringinatorController;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.service.StringinatorService;
import com.comcast.stringinator.service.StringinatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest
class StringinatorApplicationTests {

	@Autowired
	private StringinatorServiceImpl signatureService;

	@Autowired
	private StringinatorController stringinatorController;
	@Autowired
	private StringinatorServiceImpl stringinatorService;

	@BeforeMethod
	private void setUp(){
		signatureService = new StringinatorServiceImpl();
	}

	@Test
	void StringinatorResultTest() {
		// This is an example of white box integration test
		StringinatorResult result = signatureService.stringinate(new StringinatorInput("abcba"));
		System.out.println("RESULT=-====> "+ result.getInput());
		Assert.assertEquals("abcba", result.getInput());
		Assert.assertEquals(Integer.valueOf(5), result.getLength());
		Assert.assertTrue(result.isPalindrome());
		Assert.assertEquals(2, result.getMostOccurredChars().getCount());
	}

	@Test
	void getMostOccurredCharsTest(){
		// Mockito can be implemented to mock external methods for unit testing
		Assert.assertEquals(signatureService.getMostOccurredChars("abcbe").getCount(),2);
	}



}
