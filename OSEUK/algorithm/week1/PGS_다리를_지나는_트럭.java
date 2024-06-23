package programmers.kit.stackqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingBridge {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int currentWeight = 0;
        int index = 0;

        // 다리에 올라가 있는 공간을 0으로 초기화
        for (int i = 0; i < bridge_length; i++){
            bridge.add(0);
        }

        // while문 한 번 돌 때 1초 경과
        while (!bridge.isEmpty()){
            time++;
            currentWeight -= bridge.poll(); // 트럭이 빠져나가는 값을 현재 무게에서 뺌 (처음엔 0)

            if (index < truck_weights.length){
                // 앞에 대기하고 있는 트럭의 무게를 더해도 weight보다 작을 때
                if (currentWeight + truck_weights[index] <= weight){
                    bridge.add(truck_weights[index]);
                    currentWeight += truck_weights[index];
                    index++;
                }
                else{
                    bridge.add(0);  /* 작아도 0을 추가하면서 bridge queue의 길이를 유지 */
                }
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bridge_length = Integer.parseInt(br.readLine());
        int weight = Integer.parseInt(br.readLine());
        String[] tw = br.readLine().split(" ");
        int[] truck_weights = new int[tw.length];
        int i = 0;
        for (String s : tw) {
            truck_weights[i++] = Integer.parseInt(s);
        }

        System.out.println(solution(bridge_length, weight, truck_weights));
    }
}
