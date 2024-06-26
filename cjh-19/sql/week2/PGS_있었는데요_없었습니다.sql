SELECT B.ANIMAL_ID, B.NAME
FROM ANIMAL_INS AS A
LEFT JOIN ANIMAL_OUTS AS B
ON A.ANIMAL_ID=B.ANIMAL_ID
WHERE A.DATETIME>B.DATETIME
ORDER BY A.DATETIME ASC;

# 날짜 크기 비교
# 과거 < 현재 < 미래
# A.DATETIME>B.DATETIME => 보호 시작일 > 입양일