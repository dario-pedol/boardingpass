package ch.pedol.libboardingpass.bp.encode;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ch.pedol.libboardingpass.qr.QRCodeWriter;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class TestBoardingPassEncoder {

	@Test
	public void test() {
		String qrCodeData = "M1PEDOL/DARIOMR       E       DUSZRHLX 1027 261Y030A0088 355>2180MO4261BLH              262205248031561  LX A3 125483514       Z*30600000005  A3S";
		String filePath = "src/test/resources/generated/bp.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		try {
			new QRCodeWriter().createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
		} catch (WriterException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
