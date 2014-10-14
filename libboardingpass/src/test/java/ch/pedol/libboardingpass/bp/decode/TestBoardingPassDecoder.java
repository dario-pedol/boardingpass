package ch.pedol.libboardingpass.bp.decode;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

import ch.pedol.libboardingpass.bp.decode.BoardingPassDecoder;
import ch.pedol.libboardingpass.bp.printer.BoardingPassPrettyPrinter;
import ch.pedol.libboardingpass.bp.raw.RawBoardingPass;
import ch.pedol.libboardingpass.qr.QRCodeReader;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
/**
 * 
 * @author Dario Pedol (dario@pedol.ch)
 *
 */
public class TestBoardingPassDecoder {
	/**
	 * 
	 */
	private final static String CHARSET = "UTF-8"; // or "ISO-8859-1"
	@Test
	public void testDecode() {
		for (int i = 1; i < 9; i++) {
			String data;
			try {
				data = QRCodeReader.readQRCode("src/test/resources/bp" + i + ".png", CHARSET, Collections.<DecodeHintType, Object> emptyMap());
				System.out.println("RAW: " + data);
				BoardingPassDecoder decoder = new BoardingPassDecoder(data);
				RawBoardingPass bpdata = decoder.decode();
				new BoardingPassPrettyPrinter().print(bpdata);
			} catch (NotFoundException | IOException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
		String data;
		try {
			data = QRCodeReader.readQRCode("src/test/resources/2dcode.jpg", CHARSET, Collections.<DecodeHintType, Object> emptyMap());
			System.out.println("RAW: " + data);
			BoardingPassDecoder decoder = new BoardingPassDecoder(data);
			RawBoardingPass bpdata = decoder.decode();
			System.out.println(bpdata);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}