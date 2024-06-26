function solution(answers) {
    // 5문제 찍는 방식이 담긴 배열
    let people = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ];
    // 점수를 담을 배열
    let value = [];
    // 3명 각자 채점
    for(let i=0; i<people.length; i++){
        let count = 0; // 맞은 개수
        for(let j=0; j<answers.length; j++){
            if(answers[j] === people[i][j % people[i].length])
                count++;
        }
        value.push(count); // 맞은 개수 추가
    }
    
    // 가장 많이 맞춘 학생 탐색
    let Max = Math.max(...value);
    let higher = [];
    for(let i=0; i<value.length; i++){
        if(value[i] === Max)
            higher.push(i+1);
    }
    
    // 오름차순 정렬
    higher.sort((a,b) => a - b);
    
    return higher;
}