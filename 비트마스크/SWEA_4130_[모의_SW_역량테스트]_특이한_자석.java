import java.util.*;
import java.io.*;

public class Solution {
	static int[] mag;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			mag = new int[4];
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					int val = Integer.parseInt(st.nextToken());

					if (val == 1) {
						mag[i] |= (1 << j);
					}
				}
			}
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				used = new boolean[4];
				if (dir == 1) {
					used[idx] = true;
					rotateRight(idx);
				}else {
					used[idx] = true;
					rotateLeft(idx);
				}
				
			}
			int ans = 0;
			for (int i = 0; i < 4; i++) {
				if ((mag[i] & 1) == 1) {
					ans += (1 << i);
				}
			}
			System.out.println("#" + tc + " " + ans	);
		}
	}

	static void rotateRight(int idx) {
		int right = (mag[idx] >> 2) & 1;
		int left = (mag[idx] >> 6) & 1;

		if (idx != 0 && !used[idx - 1] && ((mag[idx - 1] >> 2) & 1) != left) {
			used[idx - 1] = true;
			rotateLeft(idx - 1);
		}
		if (idx != 3 && !used[idx + 1] && ((mag[idx + 1] >> 6) & 1) != right) {
			used[idx + 1] = true;
			rotateLeft(idx + 1);
		}
		mag[idx] = ((mag[idx] << 1) & 0xFF) | ((mag[idx] >> 7) & 1);
	}

	static void rotateLeft(int idx) {
		int right = (mag[idx] >> 2) & 1;
		int left = (mag[idx] >> 6) & 1;

		if (idx != 0 && !used[idx - 1] && ((mag[idx - 1] >> 2) & 1) != left) {
			used[idx - 1] = true;
			rotateRight(idx - 1);
		}
		if (idx != 3 && !used[idx + 1] && ((mag[idx + 1] >> 6) & 1) != right) {
			used[idx + 1] = true;
			rotateRight(idx + 1);
		}
		mag[idx] = (mag[idx] >> 1) | ((mag[idx] & 1) << 7);
	}
}
