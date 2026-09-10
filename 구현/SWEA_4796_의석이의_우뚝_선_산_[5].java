/*
 * BufferedReader로 풀었더니 입력에 문제가 있어서 런타임 에러가 났다.
 * Scanner로 변경하니 해결되었다.
 */

import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int ans;
	static int[] mountain;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			N = sc.nextInt();
			mountain = new int[N];
			// StringTokenizer st = new StringTokenizer(br.readLine());
			ans = 0;
			int l = 0;
			int r = 0;
			for (int i = 0; i < N; i++) {
				mountain[i] = sc.nextInt();
			}
			for (int i = 0; i < N - 1; i++) {
				if (mountain[i] < mountain[i + 1]) {
					if (r != 0) {
						ans += l * r;
						l = 0;
						r = 0;
					}
					l++;
				} else {
					if (l != 0) {
						r++;
					}
				}
			}
			ans += l * r;
			System.out.println("#" + tc + " " + ans);
		}
	}
}
