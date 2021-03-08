package com.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MockitoVerify {
	@Test
	void testVerify() {
		List<String> mockList = mock(List.class);
		mockList.add("Pankaj");
		mockList.size();
		verify(mockList).add("Pankaj");
		
	}

}
