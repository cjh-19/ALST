-- 고양이와 개는 몇 마리 있을까
SELECT ANIMAL_TYPE, COUNT(*) AS count
FROM ANIMAL_INS
WHERE ANIMAL_TYPE = "Cat" OR ANIMAL_TYPE = "Dog"
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE