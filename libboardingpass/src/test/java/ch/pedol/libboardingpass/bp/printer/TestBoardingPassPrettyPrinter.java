package ch.pedol.libboardingpass.bp.printer;

import org.junit.Test;

import ch.pedol.libboardingpass.bp.decode.BoardingPassDecoder;
import ch.pedol.libboardingpass.bp.raw.RawBoardingPass;

public class TestBoardingPassPrettyPrinter {
	private final static String RAW_DATA = "M1PEDOL/DARIOMR       E       DUSZRHLX 1027 261Y030A0088 355>2180MO4261BLH              262205248031561  LX A3 125483514       Z*30600000005  A3S";
	private final static String RAW_DATA2 = "M1PEDOL/DARIOMR       E2ULDZW DUSZRHLH 3138 267M030A0023 355>2180KO4266BLH              262205247927787  LH A3 125483514        *30600000005     ";
	@Test
	public void test() {
		BoardingPassDecoder decoder = new BoardingPassDecoder(RAW_DATA);
		RawBoardingPass bp = decoder.decode();
		new BoardingPassPrettyPrinter().print(bp);
		
		decoder = new BoardingPassDecoder(RAW_DATA2);
		bp = decoder.decode();
		new BoardingPassPrettyPrinter().print(bp);
	}
}
