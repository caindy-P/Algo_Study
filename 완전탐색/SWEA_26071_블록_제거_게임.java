import java.util.*;

public class Solution_블록제거게임_박도윤 {
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				arr.add(sc.nextInt());
			}
			answer = 0;
			recur(arr, 0);
			System.out.println("#" + test_case + " " + answer);
		}
	}

	static void recur(ArrayList<Integer> arr, int score) {
		// 블록이 하나만 남았을 때
		if (arr.size() == 1) {
			score += arr.get(0);
			answer = Math.max(answer, score);
			return;
		}
		// 현재 남아 있는 모든 블록을 각각 제거해보기
		for (int i = 0; i < arr.size(); i++) {
			int left = 1;
			int right = 1;
			// 왼쪽 블록이 존재하면
			if (i > 0) {
				left = arr.get(i - 1);
			}
			// 오른쪽 블록이 존재하면
			if (i < arr.size() - 1) {
				right = arr.get(i + 1);
			}
			// 이번에 얻는 점수
			int nowScore = left * right;
			// 현재 블록 제거
			int removed = arr.remove(i);
			// 다음 블록 제거
			recur(arr, score + nowScore);
			// 원상복구
			arr.add(i, removed);
		}
	}
}
