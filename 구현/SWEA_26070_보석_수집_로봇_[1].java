import java.util.*;

public class Solution_26070_박도윤 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = 0;
			int[][] board = new int[N][N];
			List<int[]> jewel = new ArrayList<>(M);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = sc.nextInt();
					if (board[i][j] != 0) {
						M++;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0) {
						jewel.add(new int[] { board[i][j], i, j });
					}
				}
			}
			jewel.sort(new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});
			int row = 0;
			int col = 0;
			int dir = 0; // 0 = 오른쪽, 1 = 아래, 2 = 왼쪽, 3 = 위
			int ans = 0;
			for (int i = 0; i < jewel.size(); i++) {
				int jrow = jewel.get(i)[1];
				int jcol = jewel.get(i)[2];
				int target;

				if (jrow > row && jcol > col) {
				    target = 0;       // 우하
				} else if (jrow > row && jcol < col) {
				    target = 1;       // 좌하
				} else if (jrow < row && jcol < col) {
				    target = 2;       // 좌상
				} else {
				    target = 3;       // 우상
				}

				int diff = (target - dir + 4) % 4;
				int turn = Math.min(diff + 1, 3);

				ans += turn;
				dir = (dir + turn) % 4;

				row = jrow;
				col = jcol;
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}
