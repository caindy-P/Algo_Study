import java.util.*;

public class Solution_가장큰수 {
	public String solution(int[] numbers) {
		String[] arr = Arrays.stream(numbers).mapToObj(i -> String.valueOf(i)).toArray(size -> new String[size]);
		Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
		String answer = "";
		for (int i = 0; i < arr.length; i++) {
			answer += arr[i];
		}
		if(answer.charAt(0)=='0') return "0";
		return answer;
	}
}
