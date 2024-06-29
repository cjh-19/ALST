// Heap이란?
// 최댓값, 최솟값을 빠르게 찾기 위해 고안된 자료형
// 우선 순위 큐를 위해 만들어짐

// MinHeap 구현
class MinHeap {
    // 힙을 저장할 배열 초기화
    constructor() {
        this.heap = [];
    }
    
    // 힙 배열에 새로운 값 추가
    // 마지막 노드에 push -> 부모 노드와 비교하며 교환
    push(value) {
        // 힙의 맨 마지막 노드에 push
        this.heap.push(value);
        let index = this.heap.length - 1;
        
        // 노드 개수가 2개 이상이고
        // 새로 추가된 값이 부모 노드보다 작을 경우
        // 부모 노드 위치 = (자식노드 위치index - 1) / 2
        // 위치를 교환하여 최소 힙 구조 유지
        
        // 오름차순 정렬
        while(
            index > 0 &&
            this.heap[index] < this.heap[Math.floor((index - 1) / 2)]
        ) {
            // 새로운 노드와 부모노드 교환
            const temp = this.heap[index];
            this.heap[index] = this.heap[Math.floor((index - 1) / 2)];
            this.heap[Math.floor((index - 1) / 2)] = temp;
            
            // index를 그 부모노드로 다시 최신화하고 while문 반복
            index = Math.floor((index - 1) / 2);
        }
    }
    
    // 힙에서 최소값 제거 및 반환
    pop() {
        // 힙이 비어있는 경우
        if (this.heap.length === 0) return null;
        // 힙에 하나의 요소만 있는 경우
        if (this.heap.length === 1) return this.heap.pop();
        
        const minValue = this.heap[0];
        // 마지막 노드 해당 위치에서 삭제 하고,
        // 마지막 노드를 부모노드로 지정
        this.heap[0] = this.heap.pop();
        let index = 0;
        
        // 오름차순 정렬
        // 루트 노드부터 시작해서, 부모 노드와 왼쪽 오른쪽 자식 노드 간의 비교
        // 왼쪽 자식의 힙 길이보다 작을 때까지 
        while (index * 2 + 1 < this.heap.length) {
            // 오른쪽 자식 < 힙 길이 &&
            // 오른쪽 자식 < 왼쪽 자식 ? 오른쪽 자식 : 왼쪽 자식
            let minChildIndex =
                (in)
        }
    }
}

function solution(scoville, K) {
    let shakeCnt = 0;
    
    // scoville 배열에 7 미만 값이 없어질때까지 반복
    while(scoville.find(i => i < K) !== undefined){
        // scoville 오름차순 정렬
        scoville.sort((a,b) => a-b);
        
        if(scoville.length === 1 && scoville[0] < K){
            return -1;
        }
        
        // 가장 낮은 스코빌과 그다음 스코빌 합
        let new_scv = scoville[0] + scoville[1] * 2;
        scoville.splice(0,2);
        scoville.push(new_scv);
        shakeCnt++;
    }
    
    return shakeCnt;
}