function solution(k, dungeons) {
    var answer = -1;
    // 순열을 통해서 모든 경우의 수로 탐색
    dungeons.sort((a,b) => a - b);
    // output은 dungeons의 모든 경우의 수(순열)
    let output = [];
    permutation([], dungeons, output);
    
    output.forEach((v) => {
        let tmp = k; // 매 경우의 수마다 피로도 갱신
        let count = 0; // 탐험 던전 수
        
        // v는 경우의 수중 한가지 ex. [[80,20],[50,40],[30,10]]
        for(let i=0; i<v.length; i++){
            if(tmp >= v[i][0]){
                tmp -= v[i][1];
                count++;
            }
        }
        answer = Math.max(answer, count);
    });
    
    return answer;
}

// 순열을 구하는 함수
// 현재 담은 배열: start, 남은 배열: rest, 최종 모든 경우의 수 리스트: output
function permutation(start, rest, output){
    // 모든 숫자로 배열을 만들었다면 output에 저장
    if (rest.length === 0){
        return output.push(start);
    }
    
    // rest 배열이 남아있다면
    rest.forEach((v, idx) => {
        // rest의 idx번째 값을 제외하고
        // 0번째부터 idx까지 배열과 idx+1부터 끝까지 배열을 합친 rest로 갱신
        // start는 전체 start와 rest의 idx번째 값인 v를 합한 값으로 다시 넣음
        let newrest = [...rest.slice(0,idx), ...rest.slice(idx+1)];
        permutation([...start, v], newrest, output);
    });
}