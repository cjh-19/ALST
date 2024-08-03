function solution(brown, yellow) {
    var answer = [];
    // 가로 >= 세로
    let total_size = brown + yellow; // 총 개수 = 넓이 = 가로 * 세로
    let h=0, w=0;
    // 갈색 세로 길이 구하기
    for(h=3; h<=total_size/2; h++){
        if(total_size % h !== 0) continue; // 가로 길이가 정수 아니면 패스
        
        w = total_size / h; // 갈색 가로 길이
        
        // 가로 >= 세로 && 노란 격자 개수가 맞을 경우
        if(w >= h && (w-2) * (h-2) === yellow){
            answer.push(w);
            answer.push(h);
            break;
        }
    }
    
    return answer;
}