package ch.pedol.libboardingpass.iata;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestIataAirport {

	@Test
	public void test() {
		assertEquals("Zurich, Switzerland", IataAirport.getByCode("ZRH"));
		assertEquals("Munich, Germany", IataAirport.getByCode("MUC"));
		assertEquals("", IataAirport.getByCode("XXX"));
	}

}
