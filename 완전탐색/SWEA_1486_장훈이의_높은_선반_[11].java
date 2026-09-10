import java.util.*;
import java.io.*;

public class Solution {
	static int ans;
	static int N;
	static int B;
	static int[] height;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			recur(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void recur(int now, int sum) {
		if (sum - B > ans)
			return;
		if (now == N) {
			if (sum >= B) {
				ans = Math.min(sum - B, ans);
			}
			return;
		}
		recur(now + 1, sum + height[now]);
		recur(now + 1, sum);
	}
}