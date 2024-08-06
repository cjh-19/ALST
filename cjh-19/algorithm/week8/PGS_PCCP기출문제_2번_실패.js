function solution(land) {
    let n = land.length; // 세로
    let m = land[0].length; // 가로

    let dx = [-1, 1, 0, 0]; // 상하
    let dy = [0, 0, -1, 1]; // 좌우

    // 방문 체크
    let visited = Array.from(Array(n), () => Array(m).fill(false));

    // 석유 덩어리의 크기를 반환하는 dfs
    const dfs = (x, y) => {
        visited[x][y] = true;
        let size = 1; // 시작 위치의 석유 크기 포함

        for(let k=0; k<4; k++){
            let nx = x + dx[k];
            let ny = y + dy[k];
            
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && land[nx][ny] === 1){
                size += dfs(nx, ny);
            }
        }

        return size;
    }

    // 가로열로 반복문을 돌며 세로 반복문 돌기

    // 최대 석유량
    let max = 0;
    // 가로부터 순차적으로 찌르기
    for(let i=0; i<m; i++){
        let sum = 0;
        visited = Array.from(Array(n), () => Array(m).fill(false));
        // 세로의 각 위치에서 뻗어나갈 수 있는 석유양 계산
        for(let j=0; j<n; j++){
            if(land[j][i] === 1 && !visited[j][i]){
                let size = dfs(j, i);
                sum += size;
            }
        }
        max = Math.max(max, sum);
    }

    return max;
}