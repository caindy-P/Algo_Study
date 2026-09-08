import java.util.*;
import java.io.*;

public class Solution {
	static int D, W, K;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] film = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = 100;
			recur(0, 0, film);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void recur(int now, int count, int[][] film) {
		if (count >= ans) {
			return;
		}
		if (isPass(film)) {
			ans = Math.min(ans, count);
			return;
		}
		if (now == D) {
			return;
		}
		// 그대로
		recur(now + 1, count, film);
		// A로 채워서
		int[] temp = film[now].clone();
		for (int i = 0; i < W; i++) {
			film[now][i] = 0;
		}
		recur(now + 1, count + 1, film);
		film[now] = temp.clone();
		// B로 채워서
		temp = film[now].clone();
		for (int i = 0; i < W; i++) {
			film[now][i] = 1;
		}
		recur(now + 1, count + 1, film);
		film[now] = temp.clone();
	}

	static boolean isPass(int[][] film) {
		for (int i = 0; i < film[0].length; i++) {
			int now = 0;
			int count = 0;
			for (int j = 0; j < film.length; j++) {
				if (film[j][i] == now) {
					count++;
				} else {
					now = film[j][i];
					count = 1;
				}
				if (count == K) {
					break;
				}
			}
			if (count != K) {
				return false;
			}
		}
		return true;
	}
}