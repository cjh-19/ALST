-- 연도별 대장균 크기의 편차 구하기
WITH MAX_SIZE_COLONY AS (
    SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR , MAX(SIZE_OF_COLONY) AS MAX_COLONY
    FROM ECOLI_DATA
    GROUP BY YEAR
)

SELECT YEAR(E.DIFFERENTIATION_DATE) AS YEAR, (M.MAX_COLONY - SIZE_OF_COLONY) AS YEAR_DEV, ID  
FROM ECOLI_DATA AS E
JOIN MAX_SIZE_COLONY AS M
ON YEAR(E.DIFFERENTIATION_DATE) = M.YEAR
ORDER BY YEAR, YEAR_DEV