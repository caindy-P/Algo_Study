import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];

			int ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				int now = map[i][0];
				int seq = 1;
				boolean[] used = new boolean[N];
				boolean isAble = true;
				for (int j = 1; j < N; j++) {
					int next = map[i][j];

					if (Math.abs(now - next) > 1) {
						isAble = false;
						break;
					}

					if (now + 1 == next) {
						if (seq < X) {
							isAble = false;
							break;
						}

						for (int k = j - X; k < j; k++) {
							if (used[k]) {
								isAble = false;
								break;
							}
						}

						if (!isAble)
							break;

						for (int k = j - X; k < j; k++) {
							used[k] = true;
						}
					}

					if (now == next) {
						seq++;
					} else {
						seq = 1;
					}

					now = next;
				}
				now = map[i][N - 1];
				seq = 1;
				for (int j = N - 2; j >= 0; j--) {
					int next = map[i][j];
					// 1이상 차이 -> 건설 불가
					if (Math.abs(now - next) > 1) {
						isAble = false;
						break;
					}
					// 다음보다 1 작음
					if (now + 1 == next) {
						if (seq < X) {
							isAble = false;
							break;
						}

						for (int k = j + 1; k <= j + X; k++) {
							if (used[k]) {
								isAble = false;
								break;
							}
						}

						if (!isAble)
							break;

						for (int k = j + 1; k <= j + X; k++) {
							used[k] = true;
						}
					}
					if (now == next) {
						seq += 1;
					} else {
						seq = 1;
					}
					now = next;
				}
				if (isAble) {
					ans += 1;
				}
			}
			for (int i = 0; i < N; i++) {
				int now = map[0][i];
				int seq = 1;
				boolean[] used = new boolean[N];
				boolean isAble = true;
				for (int j = 1; j < N; j++) {
					int next = map[j][i];

					if (Math.abs(now - next) > 1) {
						isAble = false;
						break;
					}

					if (now + 1 == next) {
						if (seq < X) {
							isAble = false;
							break;
						}

						for (int k = j - X; k < j; k++) {
							if (used[k]) {
								isAble = false;
								break;
							}
						}

						if (!isAble)
							break;

						for (int k = j - X; k < j; k++) {
							used[k] = true;
						}
					}

					if (now == next) {
						seq++;
					} else {
						seq = 1;
					}

					now = next;
				}
				now = map[N - 1][i];
				seq = 1;
				for (int j = N - 2; j >= 0; j--) {
					int next = map[j][i];
					// 1이상 차이 -> 건설 불가
					if (Math.abs(now - next) > 1) {
						isAble = false;
						break;
					}
					// 다음보다 1 작음
					if (now + 1 == next) {
						if (seq < X) {
							isAble = false;
							break;
						}

						for (int k = j + 1; k <= j + X; k++) {
							if (used[k]) {
								isAble = false;
								break;
							}
						}

						if (!isAble)
							break;

						for (int k = j + 1; k <= j + X; k++) {
							used[k] = true;
						}
					}
					if (now == next) {
						seq += 1;
					} else {
						seq = 1;
					}
					now = next;
				}
				if (isAble) {
					ans += 1;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
