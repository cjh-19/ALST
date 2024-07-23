function solution(numbers) {
    // 소수 판단 조건 -> 에라토스텐네스의 체

    let allNum = getPer(numbers); // 만들 수 있는 모든 숫자 조합
    let maxNum = Math.max(...allNum); // 가장 큰 수
    
    let prime = primeArr(maxNum); // 에라토스테네스 체 배열 생성
    
    // allNum 값이 prime 배열에 있는 숫자만 필터링된 배열 길이
    let primeCnt = allNum.filter(p => prime.includes(p) && p !== 0).length;
    
    return primeCnt;
}

// 에라토스테네스 체 배열 생성
const primeArr = (maxN) => {
    let prime = [];
    
    for(let i=0; i <= maxN; i++){
        prime.push(i);
    }
    prime[1] = 0; // 1은 소수가 아님
    for(let i=2; i <= maxN; i++){
        // 이미 지워진 값이라면
        if(prime[i] === 0) continue;
        
        // i의 배수 삭제
        for(let j=i*2; j<=maxN; j+=i){
            prime[j] = 0;
        }
    }
    return prime;
}

// dfs를 이용하여 문자열을 입력받아 순열 구하기
const getPer = (str) => {
    const perArr = []; // 순열을 담을 배열
    let visited = new Array(str.length).fill(0); // 방문 여부를 나타내는 배열
    
    // dfs 함수를 통해 순열 생성
    const dfs = (curstr) => {
        perArr.push(Number(curstr)); // 입력받은 문자열을 숫자로 변환하여 추가
        
        for(let i=0; i<str.length; i++){
            // 현재 숫자를 방문하지 않은 경우
            if(visited[i] === 0){
                visited[i] = 1; // 방문 표시 -> 사용한 숫자를 이후 dfs에서 사용하지 않기 위해서
                dfs(curstr + str[i]); // 입력 문자열과 방문한 문자열을 붙여 생성
                visited[i] = 0; // 재귀가 종료되면 방문 표시 해제
            }
        }
    }
    
    // dfs 시작
    dfs('');
    perArr.splice(0,1); // 첫 문자열은 빈 문자열이므로 제거
    
    // 중복 숫자 제거 후 Set 객체 이므로 배열로 반환
    return Array.from(new Set(perArr));
}