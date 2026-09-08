import java.util.Arrays;

class Solution {
    private int answer;
    private int[] weak;
    private int[] dist;
    private boolean[] used;
    private int weakLen;

    public int solution(int n, int[] weak, int[] dist) {
        answer = dist.length + 1;

        weakLen = weak.length;
        this.dist = dist;
        used = new boolean[dist.length];

        // 원형을 직선처럼 사용하기 위해 weak 배열 2배 확장
        this.weak = Arrays.copyOf(weak, weakLen * 2);

        for (int i = weakLen; i < weakLen * 2; i++) {
            this.weak[i] = this.weak[i - weakLen] + n;
        }

        // 각 취약 지점을 시작점으로 시도
        for (int start = 0; start < weakLen; start++) {
            recur(start, 0, new int[dist.length]);
        }

        if (answer == dist.length + 1)
            return -1;

        return answer;
    }

    // 친구 투입 순서를 순열로 생성
    private void recur(int start, int depth, int[] order) {

        if (depth == dist.length) {
            check(start, order);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            order[depth] = dist[i];

            recur(start, depth + 1, order);

            used[i] = false;
        }
    }

    // 해당 시작점 + 친구 순서로 몇 명이 필요한지 검사
    private void check(int start, int[] order) {

        int friend = 0;

        // 첫 번째 친구가 시작점부터 갈 수 있는 최대 위치
        int cover = weak[start] + order[friend];

        // start부터 취약 지점 개수만큼 확인
        for (int i = start; i < start + weakLen; i++) {

            // 현재 친구가 이 취약 지점을 못 덮으면
            if (weak[i] > cover) {
                friend++;

                // 친구를 전부 사용했는데도 남았다면 실패
                if (friend == dist.length)
                    return;

                // 새 친구는 현재 취약 지점부터 출발
                cover = weak[i] + order[friend];
            }
        }

        answer = Math.min(answer, friend + 1);
    }
}