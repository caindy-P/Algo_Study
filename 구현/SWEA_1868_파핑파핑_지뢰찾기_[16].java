import java.util.*;
import java.io.*;

public class Solution {
	static int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dc = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int N;
	static char[][] board;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new char[N][N];
			visit = new boolean[N][N];
			int ans = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '*') {
						for (int k = 0; k < 8; k++) {
							int nr = i + dr[k];
							int nc = j + dc[k];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
								continue;
							} else {
								if (board[nr][nc] == '.') {
									board[nr][nc] = '#';
								}
							}
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.' && !visit[i][j]) {
						visit[i][j] = true;
						search(i, j);
						ans++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '#' && !visit[i][j]) {
						ans++;
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void search(int r, int c) {
		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || visit[nr][nc]) {
				continue;
			}
			if (board[nr][nc] == '#') {
				visit[nr][nc] = true;
			} else if (board[nr][nc] == '.') {
				visit[nr][nc] = true;
				search(nr, nc);
			}
		}
	}
}
