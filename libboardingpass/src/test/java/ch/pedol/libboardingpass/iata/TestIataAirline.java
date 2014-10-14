package ch.pedol.libboardingpass.iata;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestIataAirline {

	@Test
	public void test() {
		assertEquals("Swiss International Air Lines", IataAirline.getByCode("LX"));
	}

}
