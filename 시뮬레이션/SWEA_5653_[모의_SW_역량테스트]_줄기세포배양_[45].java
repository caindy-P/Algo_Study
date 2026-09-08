import java.util.*;
import java.io.*;

class Cell {
	int life;
	int time;
	boolean activate;

	Cell(int life, int time) {
		this.life = life;
		this.time = time;
		this.activate = false;
	}
}

public class Solution {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Cell[][] c = new Cell[N + K * 2 + 2][M + K * 2 + 2];
			int[][] next = new int[N + K * 2 + 2][M + K * 2 + 2];
			int ans = 0;

			for (int i = 0; i < N + K * 2 + 2; i++) {
				for (int j = 0; j < M + K * 2 + 2; j++) {
					c[i][j] = new Cell(0, 0);
				}
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					c[i + N + K + 1][j + M + K * 1 + 1].life = Integer.parseInt(st.nextToken());
				}
			}

			for (int k = 0; k < K; k++) {
				for (int i = 0; i < N+ K * 2 + 2; i++) {
					for (int j = 0; j < M + K * 2 + 2; j++) {
						if (c[i][j].life != 0) {
							if (c[i][j].time == c[i][j].life) {// 활성화
								for (int dir = 0; dir < 4; dir++) {
									int nr = i + dr[dir];
									int nc = j + dc[dir];
									if (c[nr][nc].life == 0) {
										next[nr][nc] = Math.max(next[nr][nc], c[i][j].life);
									}
								}
							}
							c[i][j].time++;
						}
					}
				}
				for (int i = 0; i < N + K * 2 + 2; i++) {
					for (int j = 0; j < M + K * 2 + 2; j++) {
						if(next[i][j] != 0) {
							c[i][j].life = next[i][j];
							next[i][j] = 0;
						}
					}
				}
			}

			for (int i = 0; i < N + K * 2 + 2; i++) {
				for (int j = 0; j < M + K * 2 + 2; j++) {
					if (c[i][j].life != 0 && c[i][j].time < c[i][j].life * 2) {
						ans++;
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}
}
