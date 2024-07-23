// Heap이란?
// 최댓값, 최솟값을 빠르게 찾기 위해 고안된 자료형
// 우선 순위 큐를 위해 만들어짐

// MinHeap 구현
class MinHeap {
    // 힙을 저장할 배열 초기화
    constructor() {
        this.heap = [];
    }
    // 새로운 클래스 객체이므로 length와 배열 크기 구하는 메소드를 얻는 함수 지정해야 함
    size() {
        return this.heap.length;
    }
    peek() {
        return this.heap[0];
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
        // 힙에 하나의 요소만 있는 경우 위치 변경을 할 필요가 없으므로
        if (this.heap.length === 1) return this.heap.pop();
        
        const minValue = this.heap[0];
        // 마지막 노드 해당 위치에서 삭제 하고,
        // 마지막 노드를 부모노드로 지정
        this.heap[0] = this.heap.pop();
        let index = 0;
        
        // 오름차순 정렬
        // 루트 노드부터 시작해서, 부모 노드와 왼쪽 오른쪽 자식 노드 간의 비교
        // 왼쪽 자식이 힙 길이보다 작을 때까지 (힙 배열 끝까지 탐색할때까지)
        // (왼쪽 자식 인덱스 = 부모 인덱스 * 2 + 1)
        while (index * 2 + 1 < this.heap.length) {
            // 오른쪽 자식이 있다면(오른쪽 자식 인덱스 < 힙 길이)
            //(오른쪽 자식 인덱스 < 힙 길이 && 오른쪽 자식 값 < 왼쪽 자식 값)
            // ? 오른쪽 자식 : 왼쪽 자식
            // 오른쪽 왼쪽 중 더 작은 값 인덱스를 minChildIndex로 설정
            let minChildIndex =
                (index * 2 + 2 < this.heap.length && 
                 this.heap[index * 2 + 2] < this.heap[index * 2 + 1])
                ? index * 2 + 2 : index * 2 + 1;
            
            // 부모 노드가 minChildIndex 보다 작으면
            // minheap 만족이므로 break
            if(this.heap[index] < this.heap[minChildIndex])
                break;
            
            // 부모 노드와 minChildIndex 교환
            const temp = this.heap[index];
            this.heap[index] = this.heap[minChildIndex];
            this.heap[minChildIndex] = temp;
            
            // index 재설정
            index = minChildIndex;
        }
        // 정렬이 완료되면 삭제했던 이전 최솟값 반환
        return minValue;
    }
}

function solution(scoville, K) {
    const minHeap = new MinHeap();
    // scoville 배열을 최소힙 배열로 변경
    for (const i of scoville){
        minHeap.push(i);
    }
    
    let mixCount = 0;
    
    // 힙에 음식이 2개있고
    // 스코빌 지수가 가장 작은 값이 K 미만이면 계속 반복
    while(minHeap.size() >= 2 && minHeap.peek() < K){
        // 스코빌 지수가 가장 낮은 두 음식 섞고 다시 추가
        let mix = minHeap.pop() + minHeap.pop() * 2;
        minHeap.push(mix);
        mixCount++;
    }
    
    // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
    if (minHeap.peek() < K)
        mixCount = -1;
    
    return mixCount;
}