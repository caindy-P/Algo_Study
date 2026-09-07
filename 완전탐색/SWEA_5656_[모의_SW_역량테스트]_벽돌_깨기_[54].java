import java.util.*;
import java.io.*;

public class Solution_5656_박도윤 {
	static int ans;
	static int[] dw = { 1, 0, -1, 0 };
	static int[] dh = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			int[][] block = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < W; j++) {
					block[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MAX_VALUE;

			searchBlock(N, W, H, block, 0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	static void searchBlock(int N, int W, int H, int[][] block, int now) {

		boolean empty = true;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (block[i][j] > 0) {
					empty = false;
					break;
				}
			}
			if (!empty)
				break;
		}
		if (empty) {
			ans = 0;
			return;
		}

		// N개의 구슬을 모두 사용
		if (now == N) {
			int count = 0;

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (block[i][j] > 0) {
						count++;
					}
				}
			}

			ans = Math.min(count, ans);
			return;
		}

		for (int i = 0; i < W; i++) {

			// 맨 아래도 0이면 해당 열에는 벽돌이 없음
			if (block[H - 1][i] == 0)
				continue;

			boolean[][] checked = checkBlock(W, H, block, i);
			int[][] nextBlock = breakBlock(block, checked);
			searchBlock(N, W, H, nextBlock, now + 1);
		}
	}

	// idx열에 구슬을 떨어뜨렸을 때 깨지는 모든 벽돌 표시
	static boolean[][] checkBlock(int W, int H, int[][] block, int idx) {

		boolean[][] checked = new boolean[H][W];
		Deque<int[]> q = new ArrayDeque<>();

		// 위에서부터 처음 만나는 벽돌 찾기
		for (int i = 0; i < H; i++) {
			if (block[i][idx] > 0) {
				q.offer(new int[] { i, idx });
				checked[i][idx] = true;
				break;
			}
		}

		while (!q.isEmpty()) {
			int[] point = q.poll();
			int hei = point[0];
			int wid = point[1];
			int power = block[hei][wid];

			for (int i = 0; i < 4; i++) {
				for (int dist = 1; dist < power; dist++) {
					int nw = wid + dw[i] * dist;
					int nh = hei + dh[i] * dist;

					// 범위를 벗어나면 해당 방향 탐색 종료
					if (nw < 0 || nw >= W || nh < 0 || nh >= H)
						break;

					// 빈 공간이면 지나감
					if (block[nh][nw] == 0)
						continue;

					// 이미 폭발 처리한 벽돌이면 다시 큐에 넣지 않음
					if (checked[nh][nw])
						continue;

					checked[nh][nw] = true;
					q.offer(new int[] { nh, nw });
				}
			}
		}

		return checked;
	}

	// checked 벽돌 제거 후 중력 적용
	static int[][] breakBlock(int[][] block, boolean[][] checked) {

		int H = block.length;
		int W = block[0].length;

		int[][] ret = new int[H][W];

		for (int w = 0; w < W; w++) {
			int write = H - 1;

			for (int h = H - 1; h >= 0; h--) {
				if (block[h][w] == 0 || checked[h][w])
					continue;

				ret[write][w] = block[h][w];
				write--;
			}
		}
		return ret;
	}
}