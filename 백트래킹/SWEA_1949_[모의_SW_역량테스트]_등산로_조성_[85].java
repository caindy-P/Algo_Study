import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int K;
	static int[][] board;
	static boolean[][] visit;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int highest = 0;
			board = new int[N][N];
			visit = new boolean[N][N];
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, board[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == highest) {
						visit[i][j] = true;
						dfs(i, j, true, 1);
						visit[i][j] = false;
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}

	}

	static void dfs(int nowr, int nowc, boolean canDig, int count) {
		for (int dir = 0; dir < 4; dir++) {
			int nr = nowr + dr[dir];
			int nc = nowc + dc[dir];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
				if (board[nowr][nowc] > board[nr][nc]) {
					visit[nr][nc] = true;
					dfs(nr, nc, canDig, count + 1);
					visit[nr][nc] = false;
				} else if (canDig && board[nowr][nowc] > board[nr][nc] - K) {
					int temp = board[nr][nc];
					board[nr][nc] = board[nowr][nowc] - 1;
					visit[nr][nc] = true;
					dfs(nr, nc, false, count + 1);
					board[nr][nc] = temp;
					visit[nr][nc] = false;
				}
			}
		}
		ans = Math.max(count, ans);
	}
}
