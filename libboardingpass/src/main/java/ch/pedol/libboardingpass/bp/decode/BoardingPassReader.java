package ch.pedol.libboardingpass.bp.decode;

public class BoardingPassReader {
	private String encodedData;
	private int index;
	private int blockLength;

	public BoardingPassReader(String encodedData) {
		this.encodedData = encodedData;
	}

	public void setBlockLength(int length) {
		this.blockLength = index + length - 1;
	}

	public String getEncodedData() {
		return encodedData;
	}

	public int getDataLength() {
		return encodedData.length();
	}

	public String next() {
		return next(1);
	}

	public void resetBlock() {
		blockLength = 0;
	}

	public String next(int length) {
		if (index > blockLength && blockLength > 0)
			return null;
		String res = encodedData.substring(index, index + length);
		index += length;
		return res.trim();
	}

	public int nextHex() {
		return Integer.valueOf(next(2), 16).intValue();
	}

}
