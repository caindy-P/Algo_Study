import java.util.Scanner;

class Solution
{
	static int count;
	static int[][] board;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			count = 0;
			board = new int[n][n];
			play(board, n, 0);
			System.out.println("#"+test_case+" "+count);
		}
	}

	static void play(int[][] board, int n, int row) {
		for (int col = 0; col < n; col++) {
			if (board[row][col] == 0) {
				if(row == n-1) {
					count += 1;
				}
				else {
					queen(board, n, row, col, 1);
					play(board, n, row + 1);
					queen(board, n, row, col, -1);
				}
			}	
		}
	}

	static void queen(int[][] board, int n, int row, int col, int num) {
		for (int i = 0; i < n; i++) {
			board[row][i] += num;
			board[i][col] += num;
		}
		board[row][col] -= num;
		int[] dr = { 1, 1, -1, -1 };
		int[] dc = { -1, 1, -1, 1 };
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 4; j++) {
				int nr = row + dr[j] * i;
				int nc = col + dc[j] * i;
				if (0 <= nr && nr < n && 0 <= nc && nc < n) {
					board[nr][nc] += num;
				}
			}
		}
	}
}