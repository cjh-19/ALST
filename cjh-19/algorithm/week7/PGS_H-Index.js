function solution(citations) {
    var answer = 0;
    citations.sort((a,b) => b - a);
    
    for(let i=0; i<citations.length; i++){
        let Hnum = citations[i]; // 현재 논문 인용 횟수
        let top = i+1; // Hnum이상 인용한 논문 수
        if(top <= Hnum){ // 논문수 <= 인용 횟수
            answer = top; // 논문 수로 H-Index 갱신
        }
        else {
            break;
        }
    }
    
    return answer;
}