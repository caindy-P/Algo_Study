import java.util.*;

public class Solution_프로세서_연결하기_박도윤 {

	static int maxCore;
	static int minWire;

	// 상, 하, 좌, 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			List<int[]> core = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
					// 가장자리에 있지 않은 코어만 저장
					if (arr[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						core.add(new int[] { i, j });
					}
				}
			}
			maxCore = 0;
			minWire = Integer.MAX_VALUE;
			recur(N, 0, arr, core, 0, 0);
			System.out.println("#" + test_case + " " + minWire);
		}
	}
	static void recur(int N, int start, int[][] arr, List<int[]> core, int connected, int wire) {
		// 모든 코어를 확인했다면 결과 갱신
		if (start == core.size()) {
			if (connected > maxCore) {
				maxCore = connected;
				minWire = wire;
			} else if (connected == maxCore) {
				minWire = Math.min(minWire, wire);
			}
			return;
		}
		int coreRow = core.get(start)[0];
		int coreCol = core.get(start)[1];
		// 현재 코어를 4방향으로 연결
		for (int dir = 0; dir < 4; dir++) {
			int nr = coreRow + dr[dir];
			int nc = coreCol + dc[dir];
			boolean possible = true;
			int length = 0;
			// 해당 방향으로 끝까지 갈 수 있는지 확인
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				// 다른 코어나 전선이 있으면 연결 불가능
				if (arr[nr][nc] != 0) {
					possible = false;
					break;
				}
				nr += dr[dir];
				nc += dc[dir];
			}
			if (!possible) {
				continue;
			}
			// 전선 설치
			nr = coreRow + dr[dir];
			nc = coreCol + dc[dir];
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				arr[nr][nc] = 2;
				length++;
				nr += dr[dir];
				nc += dc[dir];
			}
			// 다음 코어
			recur(N, start + 1, arr, core, connected + 1, wire + length);
			// 전선 제거
			nr = coreRow + dr[dir];
			nc = coreCol + dc[dir];
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				arr[nr][nc] = 0;
				nr += dr[dir];
				nc += dc[dir];
			}
		}
		// 현재 코어를 연결하지 않는 경우
		recur(N, start + 1, arr, core, connected, wire);
	}
}