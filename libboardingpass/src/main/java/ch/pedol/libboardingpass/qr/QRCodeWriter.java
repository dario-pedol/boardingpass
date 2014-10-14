package ch.pedol.libboardingpass.qr;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * @author Dario Pedol (dario@pedol.ch)
 *
 */
public class QRCodeWriter {
	/**
	 * 
	 * @param qrCodeData	The string to encode
	 * @param filePath		Where to store the file
	 * @param charset
	 * @param hintMap
	 * @param qrCodeheight
	 * @param qrCodewidth
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public void createQRCode(String qrCodeData, String filePath,
			String charset, Map<EncodeHintType, ?> hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {

		String data = new String(qrCodeData.getBytes(charset), charset);
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		String extension = filePath.substring(filePath.lastIndexOf('.') + 1);
		MatrixToImageWriter.writeToFile(matrix, extension, new File(filePath));
	}
}