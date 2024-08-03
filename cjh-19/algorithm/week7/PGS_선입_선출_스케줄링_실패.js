function solution(n, cores) {
    let count = 0; // 처리에 들어간 작업 수
    let Cnum = 0; // 마지막 처리 코어 번호
    let time = 0; // 작업 시간

    // 모든 작업을 처리할 때까지 반복
    while(n != count){
        cores.forEach((c, idx) => {
            if(time % c === 0){
                count++;
                Cnum = idx + 1;
            }
        });
        time++;
    }

    return Cnum;
}