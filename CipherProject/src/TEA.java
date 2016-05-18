public class TEA {
	static long[] key;

	public TEA() {
		key = new long[4];
		key[0] = 0;
		key[1] = 0;
		key[2] = 0;
		key[3] = 0;
	}

	public void encrypt(long[] v, long[] k) {
		long left = v[0];
		long right = v[1];
		long sum = 0;
		long delta = 0x9E3779B9;
		for (int round = 0; round < 32; round++) {
			sum += delta;
			left += (right << 4) + k[0] ^ right + sum ^ (right >> 5) + k[1];
			right += (left << 4) + k[2] ^ left + sum ^ (right >> 5) + k[3];
			left = left & 0xffffffffL;
			right = right &0xffffffffL;

			System.out.printf("%x\n", left);
			System.out.printf("%x\n\n", right);
		}
		v[0] = left;
		v[1] = right;
		System.out.printf("%8x\n", v[0]);
		System.out.printf("%8x", v[1]);
	}
	
	public static void main(String[] args) {
		TEA tea = new TEA();
		long[] input = { 0x00000000, 0x00000000 };
		tea.encrypt(input, key);
	}
}
