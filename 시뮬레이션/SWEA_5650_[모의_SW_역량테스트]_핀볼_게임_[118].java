import java.util.*;
import java.io.*;

public class Solution {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int N;
	static int[][] board;
	static int ans;
	static List<List<int[]>> wormhole;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			ans = 0;
			wormhole = new ArrayList<>();
			for (int i = 0; i < 12; i++) {
				wormhole.add(new ArrayList<>());
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] >= 6 && board[i][j] <= 10) {
						wormhole.get(board[i][j]).add(new int[] { i, j });
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int dir = 0; dir < 4; dir++) {
						if (board[i][j] == 0) {
							ans = Math.max(ans, go(i, j, dir));
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	static int go(int sr, int sc, int dir) {
		int nr = sr + dr[dir];
		int nc = sc + dc[dir];
		int point = 0;
		// 0 = 오른쪽 1 = 아래 2 = 왼쪽 3 = 위
		while (true) {
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 다음 위치가 보드 안쪽
				int next = board[nr][nc];
				if (next == -1 || (nr == sr && nc == sc)) {
					return point;
				}
				switch (next) {
				case 0:
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					break;
				case 1:
					if (dir == 1) {
						dir = 0;
					} else if (dir == 2) {
						dir = 3;
					} else {
						return point * 2 + 1;
					}
					point++;
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					break;
				case 2:
					if (dir == 2) {
						dir = 1;
					} else if (dir == 3) {
						dir = 0;
					} else {
						return point * 2 + 1;
					}
					point++;
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					break;
				case 3:
					if (dir == 0) {
						dir = 1;
					} else if (dir == 3) {
						dir = 2;
					} else {
						return point * 2 + 1;
					}
					point++;
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					break;
				case 4:
					if (dir == 0) {
						dir = 3;
					} else if (dir == 1) {
						dir = 2;
					} else {
						return point * 2 + 1;
					}
					point++;
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					break;
				case 5:
					return point * 2 + 1;
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
					int wr = wormhole.get(next).get(0)[0];
					int wc = wormhole.get(next).get(0)[1];
					int wr2 = wormhole.get(next).get(1)[0];
					int wc2 = wormhole.get(next).get(1)[1];
					if (wr == nr && wc == nc) {
						nr = wr2 + dr[dir];
						nc = wc2 + dc[dir];
					} else {
						nr = wr + dr[dir];
						nc = wc + dc[dir];
					}
					break;
				}
			} else {// 다음 위치가 보드 바깥쪽 -> 튕김
				return point * 2 + 1;
			}
		}
	}
}