-- 테이블의 내용 조회 
-- SELECT [ALL|DISTINCT] 조회할 컬럼명 | * 
-- FROM  테이블 이름
-- [WHERE 검색조건
-- GROUP BY 그룹화할 컬럼명
-- HAVING 검색조건
-- ORDER BY 정렬기준 ASC|DESC ]

-- 모든 컬럼 조회
SELECT * FROM emp;

-- 단일 컬럼 조회
SELECT ename FROM emp;

-- 복수 컬럼 조회
SELECT ename , job, sal FROM emp;

SELECT deptno FROM emp;

SELECT ALL deptno FROM emp;
SELECT DISTINCT deptno FROM emp;  -- 중복 제거

SELECT  deptno, job FROM emp;
SELECT DISTINCT deptno, job FROM emp;

SELECT ename, job, sal * 12 FROM emp;

-- 컬럼명 별칭
SELECT ename, job,  sal 월급,  sal * 12 AS "연 봉"  FROM emp;

-- 컬럼 결합
SELECT ename, job, 
            ename || job, 
            ename || ' : ' || job  AS "이름:직업"
FROM emp;

SELECT ename, sal, ename || sal FROM emp;


-- 테이블 구조 확인
DESC emp;

-- 테이블 조회
SELECT * FROM tab;


SELECT DISTINCT deptno, job FROM emp ORDER BY deptno ASC; -- 오름차순
SELECT DISTINCT deptno, job FROM emp ORDER BY deptno DESC; -- 내림차순

SELECT DISTINCT deptno, job FROM emp ORDER BY deptno, job ASC;  -- 조건 두 개(depno가 우선시)
SELECT deptno, job FROM emp ORDER BY deptno, job ASC;

SELECT deptno, job, ename, sal FROM emp ORDER BY job, sal ASC;
SELECT deptno, job, ename, sal FROM emp ORDER BY 2;

-- 검색 조건 지정
-- 문자와 날짜의 경우 반드시 '  ' 사용
-- 컬럼의 값은 대소문자 구별

-- deptno가 10인 레코드 조회
SELECT * FROM emp WHERE deptno = 10;
SELECT * FROM emp WHERE job = 'MANAGER';
SELECT * FROM emp WHERE job = 'manager';  -- 오류, 데이터값은 대소문자 구별

-- 81년도 이전 입사자 레코드만 조회
SELECT * FROM emp WHERE hiredate < '81/01/01';
SELECT * FROM emp WHERE hiredate < '81-01-01';
SELECT * FROM emp WHERE hiredate < '1981/01/01';
SELECT * FROM emp WHERE hiredate < '810101';
SELECT * FROM emp WHERE hiredate < '19810101';


SELECT * FROM emp WHERE job = 'MANAGER' AND deptno = 10;
SELECT * FROM emp WHERE job = 'MANAGER' OR deptno = 10;

-- 80년도 또는 82년도에 입사한 사람들의 레코드를
-- 먼저 입사한 사람들 순서로 조회
SELECT * FROM emp 
               WHERE (hiredate >= '80/01/01' AND hiredate <= '80/12/31')
               OR (hiredate >= '82/01/01' AND hiredate <= '82/12/31')
               ORDER BY hiredate;
               
-- 연봉이 30000대인 레코드의 ename, job, 연봉, hiredate를 가장 큰 연봉 순서대로 조회
SELECT ename, job, sal * 12 AS 연봉, hiredate 
FROM emp 
WHERE 40000 > (sal * 12) 
AND (sal * 12) >= 30000 
ORDER BY sal DESC;


-- job이 ANALYST, PRESIDENT, SALESMAN인 레코드를 알파벳순으로 조회
SELECT * FROM emp 
WHERE job = 'ANALYST' OR job = 'PRESIDENT' OR job = 'SALESMAN'
ORDER BY job ASC;


-- deptno가 20인 레코드 조회
SELECT * FROM emp WHERE deptno = 20;
-- deptno가 20이 아닌 레코드 조회
SELECT * FROM emp WHERE deptno != 20;
SELECT * FROM emp WHERE deptno <> 20;
SELECT * FROM emp WHERE deptno ^= 20;
SELECT * FROM emp WHERE NOT deptno = 20;

SELECT * FROM emp WHERE sal < 2000 ORDER BY sal ASC;

SELECT ename, job, sal, 
            +sal, -sal,  -- 음수 양수 부호 표시
            sal+1, sal-1  -- 덧셈, 뺄셈, 산술 연산자
FROM emp WHERE sal < 2000 ORDER BY 3;

-- ename이 A, B, C로 시작하는 레코드 조회
SELECT * FROM emp WHERE ename < 'D';


-- 컬럼명 BETWEEN 작은값 AND 큰값
-- 이상/이하 사용가능, 미만/초과는 사용하지 않음
SELECT * FROM emp 
WHERE  hiredate BETWEEN '80/01/01' AND '80/12/31'
OR hiredate BETWEEN '82/01/01' AND '82/12/31'
ORDER BY hiredate ASC;  

SELECT * FROM emp 
WHERE sal * 12 BETWEEN 30000 AND 39999
ORDER BY 3;  

SELECT * FROM emp 
WHERE sal * 12 NOT BETWEEN 30000 AND 39999
ORDER BY 3;  


-- IN (비교값 목록 - 숫자, 날짜, 문자 모두 가능)
-- job이 ANALYST, PRESIDENT, SALESMAN인 레코드를 알파벳순으로 조회
SELECT * FROM emp
WHERE job 
        IN ('ANALYST', 'PRESIDENT', 'SALESMAN')
ORDER BY job;

--job이 SAMESMAN이 아닌 레코드 조회
SELECT * FROM emp
WHERE job 
        NOT IN ('SALESMAN')
ORDER BY job;


-- 컬럼명 LIKE %  : 0개 이상의 문자열과 일치
--                 _   : 특정 위치의 한 개의 문자와 일치하는지 여부 확인

-- 81년도 이전 입사자 레코드 조회 use LIKE
SELECT * FROM emp WHERE hiredate LIKE '81%';

SELECT * FROM emp WHERE ename LIKE '_O%';

-- ename이 M으로 시작하고 R로 끝나는 레코드 조회
SELECT * FROM emp WHERE ename LIKE 'M%' AND ename LIKE '%R';

-- ename이 4자인 레코드 중에 두 번째에 A가 있는 
SELECT * FROM emp WHERE ename LIKE '_A__';

-- ename이 4자인 레코드 중에 두 번째에 A가 없는
SELECT * FROM emp WHERE ename NOT LIKE '_A__' AND ename LIKE '____';


-- job에 R_R이 들어가는 레코드 조회
SELECT * FROM emp WHERE job LIKE '%R\_R%' ESCAPE '\';

SELECT * FROM emp WHERE (job LIKE '%MAN%' or job LIKE '%ID%') AND sal > 2000 ;


-- IS NULL, IS NOT NULL
-- comm이 없는 레코드 조회
SELECT * FROM emp WHERE comm = NULL;  -- 작동 안함
SELECT * FROM emp WHERE comm IS NULL;  -- 작동
-- comm이 null이 아닌 사람들의 sal에 10%를 보너스로 조회
SELECT ename, job, sal, sal * 0.1 as bonus, comm FROM emp WHERE comm IS NOT NULL;


---------------------------------------------------------------------------------------------------------
-- 집합 연산자 UNION, UNION ALL, MINUS, INTERSECT
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 10;
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 20;
-- 결과 모아서 출력
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 20;

SELECT deptno, job FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno, job FROM emp WHERE job = 'CLERK' AND deptno = 20;

SELECT deptno FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno FROM emp WHERE job = 'CLERK' AND deptno = 20;


-- deptno가 10, 30인 deptno, dname, ename을 중복 제거 및
-- deptno로 정렬하여 조회
SELECT deptno, '' DNAME, ename FROM emp WHERE deptno IN(10, 30)
UNION
SELECT deptno, dname, '' FROM dept WHERE deptno IN(10, 30)
ORDER BY 1;


-- 비공통 데이터 찾기 MINUS
-- 사원이 소속되어 있지 않은 deptno 조회
SELECT deptno FROM dept 
MINUS
SELECT deptno FROM emp;

-- 공통 데이터 찾기 MINUS
-- 사원이 소속되어 있는 deptno 조회
SELECT deptno FROM dept 
INTERSECT
SELECT deptno FROM emp;


----------------------------------------------------------------------------------------------
-- DML ; Data Manipulation Language 데이터 조작어
-- INSERT, DELETE, UPDATE
-- COMMIT, ROLLBACK 대상

-- CTAS ; Create Table As Select
-- 기존 테이블의 데이터를 이용하여 테이블 생성
-- 단, 제약조건은 복사되지 않는다

-- 1. 기존 테이블의 데이터와 구조를 모두 복사
CREATE TABLE emp2 AS SELECT * FROM emp;

-- 2. 기존 테이블의 구조만 복사
CREATE TABLE emp3 AS SELECT * FROM emp WHERE 1=2;

-- 3. 기존 테이블의 특정 컬럼들만 복사
CREATE TABLE t_emp AS SELECT empno, ename, deptno, sal, hiredate FROM emp WHERE 1=2;


-- 테이블에 레코드 추가하기
-- INSERT INTO 테이블이름 [(컬럼명1, 컬럼명2, ...)]
-- VALUES (컬럼1의 값, 컬럼2의 값, ...);

-- t_emp 테이블의 모든 컬럼에 데이터 입력 1
INSERT INTO t_emp (empno, ename, deptno, sal, hiredate)
VALUES (1111, 'KIM', 40, 1000, '2024/03/07'); 

-- t_emp 테이블의 모든 컬럼에 데이터 입력 2
INSERT INTO t_emp 
VALUES (2222, 'Lee', NULL, 2000, SYSDATE); 

-- t_emp 테이블의 모든 컬럼에 데이터 입력 3
INSERT INTO t_emp(empno, ename, hiredate) 
VALUES (3333, 'Han', ''); 

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (4444, 'SAN', '2024-03-10');

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (5555, 'Ohh', '28-02-2024');  -- X

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (5555, 'Ohh', TO_DATE('28-02-2024', 'DD-MM-YYYY'));




-- 숫자 데이터 입력
INSERT INTO t_emp(empno, sal)
VALUES (6666, 123456789);  -- larger then specified precision allowed for this column

INSERT INTO t_emp(empno, sal)
VALUES (6666, 1234567);  -- O

INSERT INTO t_emp(empno, sal)
VALUES (6666, 12345);  -- O

INSERT INTO t_emp(empno, sal)
VALUES (6666, 0.12345); -- 둘째 자리에서 끊김 (0.12)

INSERT INTO t_emp(empno, sal)
VALUES (6666, 0.12543); -- 둘째 자리에서 끊기고 반올림까지 (0.13)

COMMIT;  -- 변경 사항 반영하기 (이후 롤백 불가능)

-- 테이블에 데이터 변경하기 -------------------------------------
-- UPDATE 테이블이름
-- SET 컬럼명1 = 변경할 값1, 컬럼명 2 = 변경할 값2, ...
-- WHERE 변경조건

-- deptno를 모두 99번으로변경
UPDATE t_emp SET deptno = 99;

-- deptno가 null인 데이터를 모두 99번으로 변경
UPDATE t_emp SET deptno = 99 WHERE deptno IS NULL;

-- deptno가 99번이고, sal이 없는 데이터의 sal을 100으로
UPDATE t_emp SET sal = 100, deptno = 10 WHERE deptno = 99 AND sal IS NULL;



-- 테이블 데이터 삭제하기
-- DELETE FROM 테이블이름
-- WHERE 삭제조건

-- t_emp 테이블의 모든 데이터 삭제하기
DELETE FROM t_emp;
ROLLBACK;

-- sal이 1 미만인 레코드 삭제하기
DELETE FROM t_emp WHERE sal < 1;



SELECT * FROM t_emp;


---------------------------------------------------------


SELECT * FROM t_survey WHERE endDate > SYSDATE;

SELECT * FROM t_survey WHERE endDate > SYSDATE AND startDate <= SYSDATE;

-----------------------------------------------------------------
-- 단일행 함수
-- 문자


SELECT * FROM emp;

SELECT ename, INITCAP(ename), LOWER(ename), UPPER('abc') FROM emp;

-- ename의 값 중 대소문자 구분없이  smith인 레코드를 검색
SELECT * FROM emp WHERE LOWER(ename) = 'smith';

SELECT * FROM t_emp;
SELECT * FROM t_emp WHERE LOWER(ename) = 'goo' OR UPPER(ename) = 'GOO';
SELECT * FROM t_emp WHERE LOWER(ename) = LOWER('GOO');

SELECT 3 * 4, 'dual table', SYSDATE, SYSTIMESTAMP FROM dual;

SELECT LENGTH('eng'), LENGTH('lionel messi'),LENGTH(12345), LENGTH('축구장') FROM dual;
-- BYTE로 보면 한글은 더 용량이 크다
SELECT LENGTHB('eng'), LENGTHB('lionel messi'),LENGTHB(12345), LENGTHB('축구장') FROM dual;

SELECT * FROM emp WHERE LENGTH(ename) <= 4;


-- 컬럼 결합
SELECT  ename, job, 
                ename || job,
                CONCAT(ename, job),
                ename || ' : ' || job AS "이름 : 직업",
                CONCAT(ename, CONCAT(' : ', job)) AS "이름 : 직업"
FROM emp;


SELECT DISTINCT job, 
SUBSTR(job, 1, 5), SUBSTR(job, 6), SUBSTR(job, -3, 3), SUBSTR(job, -6)
FROM emp  WHERE job = 'SALESMAN';

SELECT '19' || SUBSTR(hiredate, 1, 2) FROM emp WHERE hiredate IS NOT NULL;

SELECT 'HELLO WORLD > L' ,
INSTR('HELLO WORLD', 'L')  "1",
INSTR('HELLO WORLD', 'L', 1) "2",
INSTR('HELLO WORLD', 'L', 1, 1) "3",
INSTR('HELLO WORLD', 'L', 1, 2) "4",
INSTR('HELLO WORLD', 'L', 2, 3) "5",
INSTR('HELLO WORLD', 'L', 3, 3) "6"
FROM dual;

SELECT 'HELLO WORLD > L' ,
INSTR('HELLO WORLD', 'L')  "1",
INSTR('HELLO WORLD', 'L', -1) "2",
INSTR('HELLO WORLD', 'L', -1, 1) "3",
INSTR('HELLO WORLD', 'L', -1, 2) "4",
INSTR('HELLO WORLD', 'L', -2, 3) "5",
INSTR('HELLO WORLD', 'L', -3, 3) "6"
FROM dual;

SELECT * FROM emp WHERE SUBSTR(hiredate, 5, 1) = 4;
SELECT * FROM emp WHERE SUBSTR(hiredate, 5, 1) = 3;

-- 4월에 입사한 사람들의 레코드 조회
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(hiredate, INSTR(hiredate, '/') + 2, 1) = '4';

-- 이번 달에 입사한 사람들의 레코드 조회
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(hiredate, 4, 2) = SUBSTR(SYSDATE, 4, 2);


SELECT * FROM t_survey;

-- https://www.homepage.com/user/info.html
-- http://127.0.0.1:8090/index.jsp
-- 도메인 정보 추출
SELECT SUBSTR(title,INSTR(title, '/', 1, 2) + 1, (INSTR(title, '/', 1, 3) - INSTR(title, '/', 1, 2)) - 1)  
FROM t_survey WHERE sno > 6;



SELECT ename, job, REPLACE(job,'PRESIDENT','CEO') "RP"
FROM emp  WHERE job = 'PRESIDENT';


SELECT ename, REPLACE(ename, SUBSTR(ename, 2, 3), 
FROM emp;


SELECT ename, REPLACE(ename, SUBSTR(ename, -3, LENGTH(ename)-2), 
FROM emp;

SELECT ename, REPLACE(ename, SUBSTR(ename, -3), 
FROM emp;

SELECT ename, SUBSTR(ename, 1, LENGTH(ename) - 3) || 
FROM emp;


-- 공백 없애기
SELECT 
LENGTH('    ABC   '),
LENGTH(TRIM('    ABC   ')),
LENGTH(LTRIM('    ABC   ')),
LENGTH(RTRIM('    ABC   '))
FROM dual;

SELECT 
hiredate,
TRIM(LEADING 8 FROM hiredate),
TRIM(TRAILING 8 FROM hiredate),
TRIM(BOTH '8' FROM hiredate)
FROM emp;

-- 공백 만들기
SELECT 
empno,
LPAD(empno, 7, '*')
FROM emp;

SELECT 
empno,
RPAD(empno, 10, '-')
FROM emp;


-- 숫자
SELECT 
sal, sal * 0.33333,
ROUND(sal * 0.33333) "NONE",
ROUND(sal * 0.33333, 0) "0",
ROUND(sal * 0.33333, 1) "1",
ROUND(sal * 0.33333, 2) "2",
ROUND(sal * 0.33333, -1) "-1",
ROUND(sal * 0.33333, -2) "-2"
FROM emp;


-- sal의 0.3333을 보너스로 계산하여
-- 소수점 둘째 자리까지 반올림하여 출력, 단 보너스가 1000에서 10000미만의 값을 대상으로 하고
-- 부족한 자리는 0으로 표시
SELECT RPAD(ROUND(sal * 0.3333, 2), 7, '0') AS BONUS
FROM emp
WHERE ROUND((sal * 0.3333), 2) BETWEEN 1000 AND 9999.99;


SELECT 
sal, sal * 0.33333,
TRUNC(sal * 0.33333) "NONE",
TRUNC(sal * 0.33333, 0) "0",
TRUNC(sal * 0.33333, 1) "1",
TRUNC(sal * 0.33333, 2) "2",
TRUNC(sal * 0.33333, -1) "-1",
TRUNC(sal * 0.33333, -2) "-2"
FROM emp;

SELECT 
sal / 30,
CEIL(sal / 30),
FLOOR(sal / 30),
MOD(sal, 30),
MOD(10, 2)
FROM emp;

-- empno가 홀수로 끝나면 TEAM_RED, 짝수로 끝나면 TEAM_BLUE로 지정하여
-- TEAM, EMPNO, ENAME을 조회. 
--  TEAM을 기준으로 오름차순 정렬, 같은 TEAM내에서는 이름을 기준으로 오름차순 정렬
SELECT ename "TEAM",  empno, ename FROM emp WHERE MOD(empno, 2) = 0
ORDER BY ASC;

SELECT 
    CASE 
        WHEN MOD(empno, 2) = 0 THEN 'TEAM_BLUE'  ELSE 'TEAM_RED' 
    END AS TEAM, 
    empno, ename 
FROM 
    emp 
ORDER BY 1 ASC, 3 ASC;



SELECT 'TEAM_RED' AS TEAM, empno, ename
FROM emp 
WHERE MOD(empno, 2) != 0

UNION ALL

SELECT 'TEAM_BLUE' AS TEAM, empno, ename
FROM emp
WHERE MOD(empno, 2) = 0

ORDER BY 1 ASC, 3 ASC;

SELECT ROWNUM, ename, 'TEAM'
FROM emp;

-- 사원들을 3명씩 한 팀으로 구성하여 TEAM`1 ~ TEAM5까지 표시
SELECT ROWNUM, ename, CONCAT('TEAM',CEIL(ROWNUM / 3))
FROM emp;



------
-- 날짜


SELECT
hiredate,
hiredate + 100 입사100일차,
SYSDATE - hiredate 근속일수,
SYSDATE - 1 어제,
SYSDATE 오늘,
SYSDATE + 1 내일
FROM emp;

-- 모든 사원의 ename, hiredate, 근속년수 조회
-- 근속년수가 오래된 순서로 정렬
SELECT
ename, hiredate, FLOOR((SYSDATE - hiredate) / 365) || '년' AS 근속년수
FROM emp ORDER BY 2;

SELECT
ename, hiredate, FLOOR((SYSDATE - hiredate) / 365 * 12) || '개월' AS 근속월수,
TRUNC( MONTHS_BETWEEN(sysdate, hiredate), 2)
FROM emp ORDER BY 2;

SELECT ename, hiredate, ADD_MONTHS(hiredate, 6)
FROM emp;

SELECT 
NEXT_DAY(SYSDATE, '금요일'),
LAST_DAY(SYSDATE)
FROM dual;

SELECT SYSTIMESTAMP,
ROUND(SYSTIMESTAMP),
TRUNC(SYSTIMESTAMP)
FROM dual;




-----------------------------
-- 단일행 함수
-- 형변환
-- NUMBER   TO_CHAR   ->   CHAR   TO_DATE   ->   DATE
--               <-   TO_NUMBER            <- TO_CHAR

SELECT 
1 + '1',  -- 묵시적 형변환
1 + TO_NUMBER('1')  -- 명시적 형변환
FROM dual;


-- 숫자를 문자로
SELECT 
ename, sal * 123.45,
TO_CHAR(sal * 123.45, '999,999.99') "1",
TO_CHAR(sal * 123.45, 'L999,999.99') "2",
TO_CHAR(sal * 123.45, '$999,999.99') "3",
TO_CHAR(sal * 123.45, '$000,000.00') "4"
FROM emp;


-- 문자를 날짜로
SELECT 
TO_DATE ('24-03-01', 'YY-MM-DD'),
TO_DATE ('2024-03-01', 'YYYY-MM-DD')
FROM dual;


-- 날짜를 문자로
SELECT 
SYSDATE, 
TO_CHAR(SYSDATE, 'YY.MM.DD'),
TO_CHAR(SYSDATE, 'YY"년" MM"월" DD"일"')
FROM dual;



----------------------
-- 단일행 함수
-- NULL 값 비교 - NVL, NVL2, NULLIF
SELECT 
ename, comm,
NVL(comm, 100), -- comm이 null이면 100으로 지정
NVL2(comm, 200, 100) -- comm이 null이면 100으로, null이 아니면 200으로 지정
FROM emp;


SELECT 
NULLIF(10, 10),  -- 둘이 같으면 null 값 출력
NULLIF(10, 20),  -- 둘이 다르면 첫번째 값 출력
NULLIF(40, 20)
FROM dual;


---------------------
-- 조건 비교
-- DECODE - equal 비교 : 정확히 일치하는지 여부 비교
-- CASE - range 비교 : 범위값을 비교

-- deptno가 10이면 Team1, 20이면 Team2, 30이면 Team3, 나머지는 Free로 지정
SELECT 
deptno, ename, 
DECODE(deptno, '10', 'Team1',
                             '20', 'Team2',
                             '30', 'Team3',
                                     'Free') AS TEAM
FROM emp
ORDER BY 1;


SELECT 
deptno, ename, 
CASE deptno WHEN 10 THEN 'Team10'
                       WHEN 20 THEN 'Team20'
                       WHEN 30 THEN 'Team30'
                                        ELSE 'Freee'
                     END AS TEAM
FROM emp
ORDER BY 1;

-- hiredate가
-- 80 ~ 81년도 사이는 Senior,
-- 82 ~ 84년도 사이는 Junior,
-- 85 ~ 89년도 사이는 Newbie,
-- 그외는 Free
SELECT 
deptno, ename, hiredate,
    CASE 
        WHEN hiredate BETWEEN '80/01/01' AND '81/12/31' THEN 'Senior'  
        WHEN hiredate BETWEEN '82/01/01' AND '84/12/31' THEN 'Junior'  
        WHEN hiredate BETWEEN '85/01/01' AND '89/12/31' THEN 'Newbie'  
        ELSE 'Free' 
    END AS TEAM
FROM  emp 
ORDER BY 3;



SELECT 
ename, comm, '수당내역'
FROM emp
ORDER BY 2;
-- comm이 0이면 수당 없음, 1이상이면 수당지급, null이면 해당사항 없음

SELECT
ename, comm,
    CASE  
        WHEN comm = 0 THEN '수당없음'
        WHEN comm IS NULL THEN '해당사항없음'
        WHEN comm > 0 THEN '수당지급'
    END AS 수당내역
FROM emp
ORDER BY 2;


--------------------------

-- 복수행Group 함수


-- 합계와 개수
SELECT  SUM(sal), SUM(ALL sal), SUM(DISTINCT sal) FROM emp
UNION ALL
SELECT  COUNT(sal), COUNT(ALL sal), COUNT(DISTINCT sal) FROM emp;

SELECT  COUNT(comm), COUNT(ALL comm), COUNT(DISTINCT comm),
COUNT(*)
FROM emp;

-- t_member의 id컬럼의 값이 'aaaa'인 행의 개수 조회
SELECT COUNT(*) FROM t_member WHERE id = 'aaaa';

SELECT 
COUNT(id),
CASE  
        WHEN COUNT(id) > 0 THEN '아이디가 존재합니다.'
        WHEN COUNT(id) < 0 THEN  '아이디가 존재하지 않습니다.'
END AS ID조회
FROM t_member 
WHERE id = 'aaaa';


-- emp테이블에서 MAN이 포함된 개수
SELECT COUNT(job) FROM emp WHERE job LIKE '%MAN%';
-- comm이 없는 사람들의 수 조회
SELECT COUNT(*) FROM emp WHERE comm IS NULL or comm = 0;


--  평균
SELECT 
AVG(comm) -- null값은 제외
FROM EMP;

-- 전체 인원 평균
SELECT
ROUND(SUM(comm) / COUNT(*), 2) AS 전체인원평균1,
ROUND(AVG(NVL(comm, 0)), 2) AS 전체인원평균2
FROM emp;


-- 최대값, 최소값, 표준편차, 분산
SELECT 
MAX(sal), MIN(sal), -- MAX, MIN 둘 다 내부정렬을 해서 성능저하
MAX(hiredate), MIN(hiredate),
MAX(ename), MIN(ename),
STDDEV(sal) AS 표준편차,
VARIANCE(sal) AS 분산

FROM emp;

-- sal을 가장 많이 받는 사람의 ename, sal 조회
SELECT MAX(sal) FROM emp;
SELECT
ename, sal
FROM emp
-- WHERE sal = MAX(sal); -- ERROR ; group function is not allowed here 
WHERE sal = 5000;

-- 부서별 sal 평균
SELECT deptno, AVG(sal) -- 그룹 함수를 사용했을 경우 (ex) AVG)
FROM emp                         -- 그룹 함수가 아닌 컬럼은 
GROUP BY deptno             -- 반드시 GROUP BY 절에 명시
ORDER BY 1;                    -- SELECT에 사용되지 않은 컬럼도 명시 가능

-- 각 부서별 job의 개수 및 평균 sal
SELECT deptno, job, COUNT(*) AS job_count, AVG(sal) AS avg_salary
FROM emp
GROUP BY deptno, job
ORDER BY 1;

-- 각 부서별 sal의 평균이 2000 이상인 레코드의 
-- deptno, 부서의 인원수 및 평균 sal 조회

SELECT deptno, COUNT(*), AVG(sal)
FROM emp
GROUP BY deptno
HAVING AVG(sal) >= 2000  -- 그룹화 했을 경우 검색조건을 WHERE이 아닌 HAVING을 사용
ORDER BY 1;

SELECT deptno, job,  COUNT(*)
FROM emp
GROUP BY deptno, job
HAVING COUNT(*) >= 3;

SELECT deptno, AVG(sal)
FROM emp
WHERE job = 'MANAGER'
GROUP BY deptno
ORDER BY 1;


-----------------------------
-- 분석함수
-- ROLLUP - 주어진 데이터들의 소계 계산

-- deptno, job, sal의 합계, job의 개수
-- deptno의 오름차순으로 조회
SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY deptno, job
ORDER BY 1;


SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY ROLLUP(deptno, job)
ORDER BY 1;

SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY deptno, ROLLUP(job)
ORDER BY 1;

SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY job, ROLLUP(deptno)
ORDER BY 1;

-- CUBE - 소계와 전체의 총계 계산
SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY CUBE(deptno, job)
ORDER BY 1;

SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY deptno, CUBE(job)
ORDER BY 1;

SELECT 
deptno, job, SUM(sal), COUNT(*)
FROM emp
GROUP BY job, CUBE(deptno)
ORDER BY 1;


-- 각 부서의 매니저 별로 관리하는 인원 수를 조회하여
-- deptno, mgr, mgr의 관리 인원 수를 표시
-- 단, 매니저가 없는 경우는 제외
SELECT deptno, mgr, COUNT(mgr)
FROM emp
WHERE mgr IS NOT NULL
GROUP BY mgr, deptno
ORDER BY 1;


SELECT deptno, mgr, COUNT(mgr)
FROM emp
WHERE mgr IS NOT NULL
GROUP BY GROUPING SETS(mgr, deptno)
ORDER BY 1;


SELECT deptno, LISTAGG(ename, ' | ')
WITHIN GROUP (ORDER BY sal DESC)
FROM emp
GROUP BY deptno;

SELECT deptno, 
    COUNT(DECODE(job, 'CLERK', 1)) AS CLARK,
    COUNT(DECODE(job, 'SALESMAN', 1)) AS SALESMAN,
    COUNT(DECODE(job, 'PRESIDENT', 1)) AS PRESIDENT,
    COUNT(DECODE(job, 'MANAGER', 1)) AS MANAGER,
    COUNT(DECODE(job, 'ANALYST', 1)) AS ANALYST
FROM emp
GROUP BY deptno
ORDER BY 1;


-- 부서별, JOB의 개수 1
SELECT 
DEPTNO,
COUNT(DECODE(JOB, 'CLERK', 100)) AS CLARK,
COUNT(DECODE(JOB, 'SALESMAN', 100)) AS SALESMAN,
COUNT(DECODE(JOB, 'PRESIDENT', 100)) AS PRESIDENT,
COUNT(DECODE(JOB, 'MANAGER', 100)) AS MANAGER,
COUNT(DECODE(JOB, 'ANALYST', 100)) AS ANALYST
FROM EMP
GROUP BY DEPTNO
ORDER BY 1;

-- 부서별, JOB의 개수 2
CREATE TABLE T_PIVOT
AS
SELECT *
FROM (SELECT DEPTNO, JOB FROM EMP)
PIVOT (COUNT(JOB)
        FOR  JOB  IN('CLERK' AS CLEARK, 
                                'SALESMAN' AS SALESMAN,
                                'PRESIDENT' AS PRESIDENT, 
                                'MANAGER' AS MANAGER, 
                                'ANALYST' AS ANALYST))
ORDER BY 1;
                                

-- 생성된 피벗 테이블을 원래 형태로 조회
SELECT *
FROM T_PIVOT
UNPIVOT (JOB_COUNT
                    FOR JOB IN(CLEARK, SALESMAN, PRESIDENT, MANAGER, ANALYST));
                    


-- LAG : 이전 행 값 가져오기
-- LEAD : 이후 값 가져오기
SELECT 
ENAME, 
LAG(SAL, 1, 0) OVER(ORDER BY SAL) LAG,
SAL,
LEAD(SAL, 1, 0) OVER(ORDER BY SAL) LEAD
FROM EMP
ORDER BY 2;

-- 전일 판매량, 당일 판매량, 전일과 당일 비교 조회
SELECT 
HIREDATE "매출일", 
LAG(SAL, 1, 0) OVER(ORDER BY HIREDATE) "전일 판매량",
SAL "당일 판매량",
SAL - (LAG(SAL, 1, 0) OVER(ORDER BY HIREDATE)) AS "전일 대비" 
FROM EMP;


-- 순위 출력
-- RANK, RANK OVER, DENSE_RANK, ROW_NUMBER

-- 전체 ENAME 중에서 JAMES의 순위 조회
SELECT RANK('JAMES') WITHIN GROUP (ORDER BY ENAME) "RANK"
FROM EMP;


-- JOB이 CLEARK인 레코드 중에서 JAMES의 순위 조회
SELECT 
RANK('JAMES') WITHIN GROUP (ORDER BY ENAME) "RANK"
FROM EMP
WHERE JOB = 'CLERK';

-- 전체 사원들의 ENAME, SAL 순위 조회
SELECT ENAME, SAL, 
RANK() OVER (ORDER BY SAL) "순위",
RANK() OVER (ORDER BY SAL ASC) "ASC",
RANK() OVER (ORDER BY SAL DESC) "DESC",
DENSE_RANK() OVER (ORDER BY SAL DESC) "DENSE", -- 동일 순위 인정 처리
ROW_NUMBER() OVER (ORDER BY SAL DESC) "순위 중복 제거"
FROM EMP;

-- DEPTNO가 30인 부서의 SAL 순위를 조회하여
-- DEPTNO, ENAME, JOB, SAL 순위를 표시
-- 동일 순위 인정하여 처리
SELECT DEPTNO, ENAME, JOB, SAL,
DENSE_RANK() OVER(ORDER BY SAL DESC) "SAL RANK"
FROM EMP
WHERE DEPTNO = 30;


-- 누적 합계 구하기
-- SUM
SELECT 
DEPTNO, HIREDATE "매출일",
SAL "당일 판매량",
SUM(SAL) OVER(PARTITION BY DEPTNO 
                             ORDER BY HIREDATE, SAL) "누적 판매량"
FROM EMP
ORDER BY 1;

-------------------------------------------------
-- JOIN
-- 여러 테이블에 흩어져 있는 데이터들을 조합해서 가져옴
-- 집합 연산자 : 세로 연결
-- 조인            : 가로 연결


-- 오라클 조인
-- SELECT  테이블이름.컬럼명 
-- FROM     테이블1이름, 테이블2이름 
-- WHERE   테이블1이름.컬럼명 = 테이블2이름.컬럼명  -- << 조인조건
-- AND        -- << 검색 조건


-- ANSI 조인
-- SELECT  테이블이름.컬럼명 
-- FROM     테이블1이름 [INDER | OUTER] JOIN  테이블2이름 
-- ON          테이블1이름.컬럼명 = 테이블2이름.컬럼명  -- << 조인조건
-- WHERE   -- << 검색 조건


-- INNER JOIN -  Cartesian Product / CROSS JOIN / 카티션 곱
-- - 조인 조건을 누락했을 경우
-- - 해당 조인에 참여하는 모든 대상 행을 출력
-- - 조인 결과 : 컬럼의 수 = 테이블1 + 테이블2
--                      행의 수 = 테이블 * 테이블2

SELECT * FROM EMP, DEPT;  -- ORACLE JOIN
SELECT * FROM EMP CROSS JOIN DEPT; -- ANSI JOIN

--  ERROR column ambiguously defined
-- DEPTNO가 중복돼서 에러
SELECT DEPTNO, DNAME FROM EMP, DEPT;  -- ORACLE JOIN
SELECT * FROM EMP CROSS JOIN DEPT; -- ANSI JOIN

SELECT EMP.DEPTNO, DNAME FROM EMP, DEPT;
SELECT E.DEPTNO, DNAME FROM EMP E CROSS JOIN DEPT D;

-- INNER JOIN - EQUI JOIN 동등 조인
-- 선행 테이블에서 데이터를 가져온 후
-- 조인 조건을 검사하여
-- 같은 값을 가진 데이터를 후행 테이블에서 꺼내 옴

-- deptno, dname, ename을 조회
SELECT dept.deptno, dname, ename
FROM emp, dept
WHERE dept.deptno = emp.deptno
AND dept.deptno = 10;

SELECT d.deptno, dname, ename
FROM emp e JOIN dept d
ON e.deptno = d.deptno
WHERE e.deptno = 10;

SELECT * FROM EMP;



----------------------------------------------------------------------
----------HR----------------------------------------------------------

-- 사원의 부서이름, 사원이름, 직함  (hr)
SELECT d.department_id,   -- Oracle  
       department_name,
       first_name,
       job_title
FROM departments d, employees e, jobs j
WHERE d.department_id = e.department_id 
AND e.job_id = j.job_id;


SELECT d.department_id,  -- ANSI
       department_name,
       first_name,
       job_title
FROM departments d JOIN employees e
ON d.department_id = e.department_id 
JOIN jobs j
ON j.job_id = e.job_id;



-- INNDER JOIN - NON EQUIT JOIN 비동등 조인 (scott)
-- - '='이 아닌 조건을 사용
-- - 정확히 일치하지 않는 값으로 조인

-- 모든 sal의 grade를 조회
SELECT ename, sal, grade, losal, hisal -- ORACLE
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal;
-- 위와 아래는 같다 (겹치는 게 없으니 굳이 별칭을 쓰지 않아도 된다.)
SELECT e.ename, e.sal, s.grade, s.losal, s.hisal
FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal;

SELECT ename, sal, grade, losal, hisal -- ANSI
FROM emp JOIN salgrade
ON sal BETWEEN losal AND hisal;

-- 각 grade의 사원 수를 조회하여 grade, 소계로 표시
SELECT grade || '호봉' AS 호봉, LPAD(COUNT(grade), 2, 0) AS 소계
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal
GROUP BY grade
ORDER BY 1;

SELECT grade || '호봉' , LPAD(COUNT(grade), 2, 0)
FROM emp JOIN salgrade
ON sal BETWEEN losal AND hisal
GROUP BY grade
ORDER BY 1;

-- 각 grade의 사원 수를 조회하여 grade, 소계로 표시
-- 단, 소계가 3명 이상인 데이터만 대상으로 하여 처리
SELECT grade || '호봉' AS 호봉, LPAD(COUNT(grade), 2, 0) AS 소계
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal
HAVING COUNT(grade) >= 3  -- GROUP함수로 정한 변수는 WHERE 대신 HAVING 사용
GROUP BY grade
ORDER BY 1;


-- INNSER JOIN - SELF JOIN
--  원하는 데이터가 한 테이블에 모두 들어있는 경우
--  같은 테이블을 마치 두 개의 테이블인 것처럼 사용하여 조인


-- 모든 사원의 ename, ename, mgr, 매니저의 이름을 조회하여 표시
SELECT e.empno, e.ename, e.mgr, m.ename AS 매니저이름
FROM emp e, emp m
WHERE e.mgr = m.empno
ORDER BY 2;



-- OUTER JOIN
-- - INNER JOIN 조건을 만족하지 않는 경우에도 모든 행을 출력
-- - 한 쪽 테이블에는 데이터가 있고 다른 테이블에는 없는 경우
--   데이터가 있는 쪽의 내용을 우선하여 출력
-- - OUTER 키워드 생략 시 INNER 조인 수행
--   LEFT [OUTER] JOIN  왼쪽 기준으로 출력
--   RIGHT [OUTER] JOIN 오른쪽 기준으로 출력
-- - 오라클의 경우 자료가 부족한 쪽에 (+) 표시
--   FULL [OUTER] JOIN 양쪽 모두 (ANSI가 지원)
--   ORACLE은 지원하지 않음, 대신 UNION 사용
--   풀스캔으로 성능에 악영향



SELECT e.empno, e.ename, e.mgr, m.ename AS 매니저이름 -- ANSI
FROM emp e JOIN emp m
ON e.mgr = m.empno
ORDER BY 2;

SELECT e.empno, e.ename, e.mgr, m.ename AS 매니저이름 -- ANSI
FROM emp e LEFT OUTER JOIN emp m
ON e.mgr = m.empno
ORDER BY 2;

SELECT e.empno, e.ename, e.mgr, m.ename AS 매니저이름 -- ORACLE
FROM emp e, emp m
WHERE e.mgr = m.empno (+)  -- 부족한 쪽에 (+)
ORDER BY 2;


-- 모든 사원의 deptno, dname, ename을 조회
-- 단, 사원이 없는 부서도 포함
SELECT d.deptno, d.dname, e.ename
FROM dept d, emp e
WHERE d.deptno = e.deptno (+)
ORDER BY 1;

SELECT d.deptno, d.dname, e.ename
FROM emp e RIGHT OUTER JOIN dept d
ON d.deptno = e.deptno
ORDER BY 1;

-- 모든 사원의 deptno, dname, empno, ename 조회
-- 단, 관리자 없는 사원 및 사원이 없는 부서도 포함
SELECT d.deptno, d.dname, empno, ename
FROM emp e FULL OUTER JOIN dept d
ON d.deptno = e.deptno
ORDER BY 1;

-- 각 부서별로 sal 합을 계산하여 내림차순 정렬
-- deptno, dname, 급여합계(세 자리마다 , 표시)
SELECT  -- LPAD(NVL(e.deptno, 0), 2, 0) DEPTNO, 
        TO_CHAR(NVL(e.deptno, 0), '00') DEPTNO, 
        NVL(d.dname, 'NONE') DNAME, 
        NVL(TO_CHAR(SUM(e.sal), '999,999'), 0) 급여합계
FROM emp e, dept d
WHERE e.deptno = d.deptno (+)
GROUP BY e.deptno, d.dname
ORDER BY 1 DESC;

SELECT LPAD(NVL(e.deptno, 0), 2, 0) DEPTNO, d.dname, NVL(TO_CHAR(SUM(e.sal), '999,999'), 0) 급여합계
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno (+)
GROUP BY e.deptno, d.dname
ORDER BY 1 DESC;


-------------------------------------------------------------
--  DDL ; Data Definition Language
-- - CREATE, ALTER, TRUNCATE, DROP
-- - 실행 시 자동 COMMIT 되어 ROLLBACK 불가능
-- 명명 규칙
-- - 테이블과 컬럼명은 반드시 문자로 시작
-- - 영문자, 숫자, _, &, # 만 가능
-- - 최대 30BYTE까지 허용
-- - 오라클 예약어는 사용 불가

-- 테이블 생성
-- CREATE TABLE [스키마.]테이블명 (
--      컬럼명1 데이터타입 [DEFAULT 형식] [컬럼 레벨 제약 조건],
--      ...
--  [테이블 레벨 제약 조건]
-- );

-- 게시판 테이블
-- 기본 값을 지정하여 테이블 생성
CREATE TABLE t_board (
    no  NUMBER(10) DEFAULT 0,
    title VARCHAR2(100),
    content VARCHAR2(4000),
    writer VARCHAR2(50) DEFAULT 'guest',
    regdate DATE    DEFAULT SYSDATE
);


-- t_board 테이블에 title은 test, content는 test content로 레코드 추가
INSERT INTO t_board(title, content) VALUES ('test', 'test_content');

SELECT * FROM t_board;


-- ALTER
-- - 기존 객체 변경
-- - 테이블의 구조 변경
--      컬럼 추가, 삭제
--      컬럼명, 데이터 타입, 길이 변경

-- 컬럼 추가
ALTER TABLE t_emp
ADD work VARCHAR2(20);

-- 컬럼명 변경
ALTER TABLE t_emp
RENAME COLUMN work TO job;

-- 테이블 이름 변경
RENAME t_board TO board;

-- 컬럼 길이 변경
ALTER TABLE t_emp
MODIFY job VARCHAR2(10);

-- 컬럼 타입 변경
ALTER TABLE t_emp
MODIFY job NUMBER;

-- 컬럼 삭제
ALTER TABLE t_emp
DROP COLUMN job;

DESC t_emp;

-- 테이블 삭제
DROP TABLE board;  -- 휴지통으로 보내기 (복원 가능)

DROP TABLE board PURGE;  -- 휴지통으로 안보내고 완전 삭제 (복원 불가)

-- 삭제된 테이블 복원
FLASHBACK TABLE board TO BEFORE DROP;


-- emp 테이블을 복사하여 t_temp 테이블 생성
-- (데이터와 구조 모두)
CREATE TABLE t_temp AS SELECT * FROM emp;

DELETE FROM t_temp;

ROLLBACK;       -- 되돌리기 x -> 백업 파일로 복구

TRUNCATE TABLE t_temp; -- 데이터, 인덱스 삭제

SELECT * FROM t_temp;


RENAME t_temp TO t_readonly;

-- 읽기 전용 테이블로 변경
ALTER TABLE t_readonly READ ONLY;

-- 읽기, 쓰기 가능 테이블로 변경
ALTER TABLE t_readonly READ WRITE;

INSERT INTO t_readonly (empno, ename, sal) VALUES(1, 'Lee', 100);



-- 가상 컬럼 테이블
CREATE TABLE t_virtual (
    item1 NUMBER,
    item2 NUMBER,
    total NUMBER GENERATED ALWAYS AS (item1 + item2)
);

INSERT INTO t_virtual (item1, item2) VALUES(100, 200);
SELECT * FROM t_virtual;

UPDATE t_virtual SET item2 = 2;



-- DATA DICTIONARY
SELECT * FROM DICTIONARY;
SELECT * FROM DICT;

SELECT * FROM ALL_TABLES;

DESC USER_TABLES;
SELECT * FROM USER_TABLES;
SELECT * TABLE_NAME FROM USER_TABLES;

SELECT * FROM USER_OBJECTS;

----------------------SYS--------------------------------
DESC DBA_TABLES;
SELECT * FROM DBA_TABLES;
SELECT OWNER, TABLE_NAME FROM DBA_TABLES;
SELECT OWNER, TABLE_NAME, PCT_FREE, CLUSTER_NAME FROM DBA_TABLES;

SELECT * FROM DBA_USERS
WHERE USERNAME = 'SCOTT';

SELECT * FROM V$VERSION;
---------------------------------------------------------
-------------SCOTT---------------------------------------

SELECT * FROM t_emp;
SELECT * FROM emp;

-- 서브쿼리를 이용하여 여러 행 입력
INSERT INTO t_emp
    SELECT  empno, ename, deptno, sal, hiredate 
    FROM    emp
    WHERE   deptno = 10;

INSERT ALL
    INTO t_emp(deptno) VALUES(50)
    INTO t_pivot(deptno) VALUES(50)
    SELECT * FROM dual;
    
-- emp 테이블의 데이터 중에서
-- comm이 null이 아닌 데이터의 ename, job, sal, comm은 bonus테이블로
-- comm이 null인 데이터의 ename, deptno, sal은 t_emp테이블에 입력
INSERT ALL
    WHEN comm IS NOT NULL THEN
        INTO bonus VALUES(ename, job, sal, comm)
    WHEN comm IS NULL AND deptno = 20 THEN
        INTO t_emp (ename, deptno, sal) 
        VALUES (ename, deptno, sal)
    SELECT  ename, deptno, job, sal, comm
    FROM    emp;


-- 서브 쿼리를 이용하여 여러 컬럼을 한 번에 수정
UPDATE t_emp
SET     empno = 1112,
        (deptno, sal) = (SELECT deptno, sal FROM t_emp WHERE ename='Kim')
WHERE ename = 'FORD';

UPDATE t_emp
SET sal = sal * 1.1
WHERE deptno = (SELECT deptno FROM t_emp WHERE ename = 'Lee');

-- 부서의 위치가 DALLAS인 사원의 급여를 두 배 인상 업데이트
-- sal이 없는 경우 1000으로 지정하여 처리
UPDATE t_emp
SET sal = NVL(sal, 1000) * 2
WHERE deptno = (SELECT deptno FROM dept WHERE loc = 'DALLAS');

-- dept = 20
-- sal = between 3
-- ename을 꺼내서 레코드 지우기
DELETE t_emp 
WHERE ename IN (SELECT ename 
                FROM t_emp, salgrade 
                WHERE sal BETWEEN losal AND hisal 
                AND grade = 3 
                AND deptno = 20);



---------------------------------------------------
-- MERGE
-- 테이블 병합

-- salgrade 테이블의 구조를 복사하여 t_merge 테이블 생성
CREATE TABLE t_merge AS SELECT * FROM salgrade WHERE 1=2;

-- salgrade 테이블의 grade가 3이하인 데이터들만 복사하여 salgrade2 테이블 생성
CREATE TABLE salgrade2 AS SELECT * FROM salgrade WHERE grade <= 3;

MERGE INTO t_merge tm 
      USING  salgrade sg
ON (tm.grade = sg.grade)
WHEN MATCHED THEN
    UPDATE SET  tm.losal = sg.losal,
                tm.hisal = sg.hisal
WHEN NOT MATCHED THEN
    INSERT VALUES(sg.grade, sg.losal, sg.hisal);
    
    
MERGE INTO t_merge tm 
      USING  salgrade2 sg
ON (tm.grade = sg.grade)
WHEN MATCHED THEN
    UPDATE SET  tm.losal = sg.losal,
                tm.hisal = sg.hisal
WHEN NOT MATCHED THEN
    INSERT VALUES(sg.grade, sg.losal, sg.hisal);
    
SELECT * FROM t_merge;
SELECT * FROM emp;
SELECT * FROM t_emp;
SELECT * FROM t_pivot;
SELECT * FROM bonus;

----------------------------------------------------------
-- CONSTRAINTS 제약 조건
-- PRIMARY KEY : Null x, 중복 값 x >> NOT NULL + QUNIQUE
-- FOREIGN KEY : 다른 테이블의 PRIMARY KEY 또는 UNIQUE로 설정된 칼럼을 참조
--               PARENT TABLE - 정보 제공 테이블 / CHILD TABLE - 정보를 제공받는 테이블
-- UNIQUE : 중복 값 X
-- CHECK : 저장 가능한 데이터 값의 범위나 조건을 지정하여 설정된 값만 허용
-- NOT NULL : NULL 입력 X. 컬럼 레벨로만 정의 가능




SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM USER_CONS_COLUMNS;


-- 테이블 생성 시 제약 조건 지정 - 제약 조건 이름 생략
CREATE TABLE member (
    userid      VARCHAR2(20)    PRIMARY KEY, 
    empno       NUMBER          REFERENCES emp(empno),
    usernm      VARCHAR2(20)    NOT NULL,
    userpw      VARCHAR2(20)    NOT NULL,
    email       VARCHAR2(50)    UNIQUE,
    gender      CHAR(1),        CHECK(gender IN ('M', 'F')),
    regdate     DATE            DEFAULT SYSDATE
);
    
-- 부모 테이블에 테스트 데이터 추가
INSERT INTO emp(empno, ename) VALUES (1000, 'Woo');
INSERT INTO emp(empno, ename) VALUES (2000, 'Goo');
INSERT INTO emp(empno, ename) VALUES (3000, 'Hoo');

-- member 테이블에 userid가 abc인 레코드 추가
INSERT INTO member(userid) VALUES ('abc');  -- X (NOT NULL 인 로우값 안넣어줌)
INSERT INTO member(userid, usernm, userpw) VALUES ('abc', 'Woo', '1111'); -- O
INSERT INTO member(userid, usernm, userpw) VALUES ('abc', 'Woo', '1111'); -- O (UNIQUE 제약조건 위배)
INSERT INTO member(userid, usernm, userpw, empno) VALUES ('aaa', 'Goo', '1111', 2222); -- X (emp에 2222가 없음)
INSERT INTO member(userid, usernm, userpw, empno) VALUES ('aaa', 'Goo', '1111', 2000); -- O

DELETE FROM emp WHERE empno = 2000; -- X (CHILDE 레코드가 있어서 삭제 불가능)
DELETE FROM member WHERE userid = 'aaa';
DELETE FROM emp WHERE empno = 2000; -- O

INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'Male'); -- X
INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'X'); -- X
INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'F'); -- O

INSERT INTO member(userid, usernm, userpw, email) VALUES('bbb', 'Goo', '1111', 'g@g.com'); -- O
INSERT INTO member(userid, usernm, userpw, email) VALUES('ccc', 'Goo', '1111', 'g@g.com'); -- O

DROP TABLE member;


-- 테이블 생성 시 제약 조건 지정 - 제약 조건 이름 명시
CREATE TABLE member (
    userid      VARCHAR2(20)    CONSTRAINT member_pk  PRIMARY KEY, 
    empno       NUMBER          CONSTRAINT member_fk  REFERENCES emp(empno)   ON DELETE CASCADE, -- 부모 레코드와 함께 삭제
    usernm      VARCHAR2(20)    ,
    userpw      VARCHAR2(20)    CONSTRAINT member_pw_chk    CHECK(LENGTH(userpw) >= 4), -- 길이 4자 이상 제한
    email       VARCHAR2(50)    CONSTRAINT  member_em_nn NOT NULL 
                                CONSTRAINT  member_em_uq UNIQUE,
    gender      CHAR(1),        CHECK(gender IN ('M', 'F')),
    regdate     DATE            DEFAULT SYSDATE
);

INSERT INTO member(userid, email, empno) VALUES('aaa', 'aaa@aaa.com', 3000);
INSERT INTO member(userid, email, empno) VALUES('bbb', 'aaa@aaa.com', '123');
INSERT INTO member(userid, email, userpw) VALUES('bbb', 'bbb@bbb.com', '1234');

DELETE FROM emp WHERE empno = 3000; -- O (ON DELETE CASCADE를 했기 때문)


-- 테이블 생성 시 제약 조건 지정
CREATE TABLE member (
    userid      VARCHAR2(20), 
    empno       NUMBER,
    usernm      VARCHAR2(20) NOT NULL,
    userpw      VARCHAR2(20),
    email       VARCHAR2(50),
    gender      CHAR(1),
    regdate     DATE    DEFAULT SYSDATE,
    
    CONSTRAINT member_pk PRIMARY KEY(userid),
    CONSTRAINT member_fk FOREIGN KEY(empno) REFERENCES emp(empno) ON DELETE SET NULL, -- 부모 레코드 삭세 시 NULL로 설정
    CONSTRAINT member_chk CHECK(LENGTH(userpw) >= 4), 
    UNIQUE(email)
);

INSERT INTO member(userid, usernm, empno) VALUES('aaa', 'userA', 1000);
DELETE FROM emp WHERE empno = 1000;

DROP TABLE member;
SELECT * FROM emp;
SELECT * FROM member;


CREATE TABLE t_qna (
    qno         NUMBER,
    question    VARCHAR2(1000),
    qid         VARCHAR2(20),
    answer      VARCHAR2(1000),
    qdate       DATE,
    adate       DATE,
    mdate       DATE,
    hitno       NUMBER
);

INSERT INTO t_qna(qno, question, qid, qdate, hitno) VALUES(1, 'question?', 'abc', SYSDATE, 0);
INSERT INTO t_qna(qno, question, qid, qdate, hitno) VALUES(1, 'question???', 'bbb', SYSDATE, 0);

-- 기존 테이블에 제약 조건 추가 - PK
ALTER TABLE t_qna ADD CONSTRAINT t_qna_pk PRIMARY KEY(qno);  -- X (중복 데이터 있음)
UPDATE t_qna SET qno = 2 WHERE qid = 'bbb'; -- 중복 제거

ALTER TABLE t_qna ADD CONSTRAINT t_qna_pk PRIMARY KEY(qno);  -- O 

ALTER TABLE t_qna MODIFY qno CONSTRAINT t_qna_pk PRIMARY KEY;

-- 기존 테이블에 제약 조건 추가 - FK
ALTER TABLE t_qna
ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);  -- X (parent keys not found, member의 id랑 안맞음)

-- t_qna테이블의 qid를 모두 aaa로 변경
UPDATE t_qna SET qid = 'aaaa';

ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);

ALTER TABLE t_qna MODIFY qid CONSTRAINT t_qna_fk REFERENCES t_member(id) ON DELETE SET NULL;

-- question 컬럼에 NULL을 허용하지 않도록 제약 조건 추가
ALTER TABLE t_qna
ADD CONSTRAINT t_qna_nn NOT NULL(question); -- X (NOT NULL은 추가가 아니라 수정하는 것)

ALTER TABLE t_qna MODIFY(question CONSTRAINT t_qna_nn NOT NULL); -- O


-- question 컬럼에 중복을 허용하지 않도록 제약 조건 추가
ALTER TABLE t_qna ADD UNIQUE(question);

ALTER TABLE t_qna MODIFY question UNIQUE;

ALTER TABLE t_qna MODIFY qdate Date DEFAULT SYSDATE;

ALTER TABLE t_qna MODIFY (hitno NUMBER DEFAULT 0);


-- 제약 조건 삭제
ALTER TABLE t_qna DROP CONSTRAINT t_qna_pk;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_fk;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_nn;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_un;


-- 제약 조건 이름 변경
ALTER TABLE t_qna RENAME CONSTRAINT SYS_C007046 TO t_qna_uq;
    

--------------t_member------------------------------------------------

  CREATE TABLE "SCOTT"."T_MEMBER" 
   (	"ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PW" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"PHOTO" VARCHAR2(100 BYTE), 
	"GENDER" VARCHAR2(5 BYTE), 
	"JOINDATE" DATE, 
	 CONSTRAINT "T_MEMBER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
---------------------------------------------------------------
DROP TABLE t_member;  -- X (자식 테이블에서 참조 중)
ALTER TABLE t_qna DROP CONSTRAINT T_QNA_FK;

-- t_qna 테이블에 qno를 t_member 테이블의 id를 참조하도록 
-- 외래키 t_qna_fk 추가
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);

DROP TABLE t_member;

DROP TABLE t_member CASCADE CONSTRAINTS; -- 부모 테이블 삭제 시, 자식 테이블의 FK 제거

-- t_member 테이블의 구조와 데이터를 복사하여 t_member2 테이블로 생성
CREATE TABLE t_member2 AS SELECT * FROM t_member;


-- t_member2 테이블의 이름을 t_member로 변경
-- t_member2 테이블의 id 컬럼을 기본키로 지정
-- t_qna 테이블의 qno 컬럼을 t_member 테이블의 id를 참조하도록 설정
-- t_qna 테이블에서 참조하고 있는 id 값으로 t_member 레이블에 레코드 하나 추가
-- t_member 테이블의 구조와 데이터를 복사하여 t_member3 테이블로 생성
ALTER TABLE t_member2 RENAME TO t_member;
ALTER TABLE t_member MODIFY(id PRIMARY KEY);
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);
INSERT INTO t_member(id, pw, name, email) VALUES ('aaaa', '1111', 'userA', 'a@a.com');
CREATE TABLE t_member3 AS SELECT * FROM t_member;
SELECT * FROM t_member;

-- FK로 참조되는 컬럼 삭제
ALTER TABLE t_member DROP COLUMN id;  -- X (종속)
ALTER TABLE t_member DROP COLUMN id CASCADE CONSTRAINTS; -- O

-- t_member 테이블에 id 컬럼 추가 및 pk 지정
-- t_qna 테이블의 qid 컬럼을 t_member 테이블의 id를 참조하도록 설정
ALTER TABLE t_member ADD id VARCHAR2(20);ww
ALTER TABLE t_member ADD PRIMARY KEY(id);
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);


------------------------------------------------------------------------
-- 제약 조건 활성화 및 비활성화
INSERT INTO t_qna(qno, question, qid, qdate, hitno)
VALUES (3, 'question????????', 'bbb', SYSDATE, 0); -- X (종속으로 연결되어 있는 t_member 부모 테이블에 없는 아이디라서)

ALTER TABLE t_qna DISABLE NOVALIDATE CONSTRAINT T_QNA_FK;  -- FK 비활성화

ALTER TABLE t_qna ENABLE NOVALIDATE CONSTRAINT T_QNA_FK;  -- FK 활성화
    -- 새로 추가되는 데이터들부터 제약 조건 검사
    
INSERT INTO t_qna(qno, question, qid, qdate, hitno)
VALUES (4, 'question????????', 'bbb', SYSDATE, 0); -- X

UPDATE t_qna SET qid = 'aaaa' WHERE qno = 3;

ALTER TABLE t_qna ENABLE VALIDATE CONSTRAINT T_QNA_FK;
    -- 모든 데이터 제약 조건 검사


ALTER TABLE t_qna DISABLE VALIDATE CONSTRAINT T_QNA_FK;  -- read only / DML 불가능

INSERT INTO t_qna(qno, question, qid, qdate, hitno) VALUES (3, 'question????????', 'bbb', SYSDATE, 0); -- X


SELECT * FROM USER_CONS_COLUMNS WHERE table_name = 'T_QNA';
SELECT * FROM t_qna;
SELECT * FROM t_member;

-------------------------------------------------------------
ALTER TABLE t_survey_attend ADD FOREIGN KEY(id) REFERENCES t_member(id);
-------------------------------------------------------------






--------------------------------------------------------------
-- 인덱스
--  - 어떤 데이터가 어디에 있는지 알려주기 위해 컬럼에 대해 생성하는 오라클 객체
--  - 원하는 데이터를 빨리 찾기 위해 사용
--  - 기본 키나 유일 키는 데이터 무결성 확인 및 빠른 조회를 목적으로 오라클 내부에서 자동으로 인덱스 생성
--  - 오라클 인덱스는 내부적으로 B* 트리 형식으로 구성
--  - 기본적으로 들어오는 순서대로 기록


-- 인덱스 생성 과정
--  - 전체 테이블 스캔 -> 정렬 -> Block 기록


-- 인덱스 장점
--  - 인덱스를 위한 추가적인 공간 필요
--  - 인덱스 생성에 시간 소요
--  - 데이터의 변경(DML)이 자주 일어나는 경우에는 오히려 성능 저하


-- 인덱스 사용 O
--  - 테이블에 행의 수가 많을 때
--  - WHERE 절에 해당 컬럼이 많이 사용될 때
--  - 검색 결과가 전체 데이터의 2% ~ 4% 정도일 때
--  - JOIN에 자주 사용되는 컬럼이나 NULL 포함 컬럼이 많을 때


-- 인덱스 사용 X
--  - 테이블의 행의 수가 적을 때
--  - WHERE 절에 해당 컬럼이 자주 사용되지 않을 때
--  - 검색 결과가 전체 데이터의 10% ~ 15% 정도일 때
--  - DML이 많이 사용될 때


-- ROWID
--  - 오라클의 모든 데이터가 갖는 고유의 주소
--  - 데이터를 찾아갈 때 필요
--  - ROWID정보를 모아서 갖고 있는 것인 인덱스
SELECT ROWID, ROWNUM, ename FROM t_emp;


-- INDEX 수정 REBUILD
--  - DML이 잦은 경우 인덱스의 갱신이 주기적으로 일어나 단편화(fragmentation) 발생
--   -> 삭제된 데이터의 인덱스 값 자리게 비게 되어 성능 저하로 이어지므로 인덱스를 다시 생성해줘야 함




SELECT * FROM USER_INDEXES;     --사용자의 모든 인덱스 조회
SELECT * FROM USER_IND_COLUMNS; --인덱스가 지정된 컬럼 조회


-- UNIQUE INDEX
--  - 기본 키나 유일 키처럼 유일한 값을 갖는 컬럼에 대해서 생성
--  - 인덱스 내의 키 값에 중복 데이터 X
--  - 속도가 빠름
CREATE UNIQUE INDEX t_emp_idx
ON                  t_emp(empno);

DROP INDEX t_emp_idx;



-- NON UNIQUE INDEX
--  - 중복 데이터를 갖는 컬럼에 생성
CREATE INDEX t_emp_idx
ON           t_emp(empno);  -- 기본은 오름차순

DROP INDEX t_emp_idx_asc;
SELECT * FROM t_emp;

CREATE INDEX t_emp_idx_desc
ON           t_emp(empno DESC);  -- 내림차순 인덱스

CREATE UNIQUE INDEX t_emp_idx_asc
ON           t_emp(ename);  

SELECT * FROM t_emp WHERE sal >= 2000;
SELECT * FROM t_emp WHERE ename = 'Lee';
SELECT * FROM t_emp WHERE empno <= 5000;

SELECT * FROM t_survey;

CREATE UNIQUE INDEX t_survey_pk_desc
ON           t_survey(sno DESC); 

SELECT * FROM t_survey ORDER BY sno DESC;
SELECT * FROM t_survey WHERE sno <= 3;
SELECT * FROM t_survey WHERE sno > 3;

-- 오라클 힌트 : 
--  - 인덱스를 위에서부터 읽게 하거나 밑에서부터 읽게 방법을 지정
--  - 정렬을 하지 않고 정렬 효과를 내거나 최대/최소값을 구할 수 있다.
-- SELECT /*INDEX(테이블명|테이블별칭 인덱스이름*/ * FROM ~~~
-- SELECT /*INDEX_DESC(테이블명|테이블별칭 인덱스이름*/ * FROM ~~~
SELECT /*+INDEX_DESC(t_survey t_survey_pk)*/ * FROM t_survey WHERE sno <= 3;


ALTER INDEX t_survey_pk_desc REBUILD;


----------------------------------------------------------------------------------
-- VIEW
--  - 물리적인 테이블에 근거한 논리적인 가상 테이블
--  - 실질적인 데이터는 없지만 
--  - 실제 테이블을 사용하는 것과 동일하게 사용 가능
--  - 기본 테이블에서 파생된 객체로,
--  - 기본 테이블에 대한 하나의 쿼리문

--  - SELECT문을 매번 입력하지 않아도 됨
--  - 원본 데이터 보호 및 일부 데이터 한정 노출 가능

--  - DML 사용 가능
--  -  기본 테이블이 변경됨
--  - 주요 용도는 조회

SELECT * FROM USER_SYS_PRIVS;

---------------------------- sys-----------------------------
GRANT CREATE VIEW TO SCOTT;  -- 권한 부여
-------------------------------------------------------------

-- 뷰 생성
CREATE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99);

SELECT * FROM USER_VIEWS; -- 소유하고 있는 뷰 조회
SELECT * FROM V_EMP;  -- 생성된 뷰 조회

UPDATE V_EMP SET SAL = 9000 WHERE ENAME = 'Goo';   -- 뷰에서 DML 실행 (원본 테이블도 영향)
ROLLBACK;



-- 뷰 재생성 X (동일 이름 존재)
CREATE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 40);

-- 동일 이름 뷰 재생성
CREATE OR REPLACE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 40);

-- 뷰 생성 조건
-- WITH CHECK OPTION을 사용하면 뷰에 대한 INSERT 또는 UPDATE 문에서 
-- 뷰 정의 조건을 위반하는 경우 오라클 데이터베이스에서 오류를 발생시킨다. 
-- 이를 통해 뷰의 데이터 무결성을 보장할 수 있다.
CREATE OR REPLACE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99)
WITH CHECK OPTION; 

ROLLBACK;

UPDATE V_EMP SET DEPTNO = 90 WHERE ENAME ='Goo'; -- X
UPDATE T_EMP SET DEPTNO = 90 WHERE ENAME ='Goo'; -- O


-- 읽기 전용 뷰 (SELECT만 가능한 READ ONLY VIEW)
CREATE OR REPLACE VIEW V_EMP(부서번호, 사원이름, 급여)
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99)
WITH READ ONLY;


SELECT * FROM V_EMP;
SELECT * FROM T_EMP;


-- 부서별로 평균 급여와, 급여 총액
-- DEPTNO, DNAME, 평균 급여, 급여 총액으로 표시
-- 단, DNAME 없는 데이터도 포함하고 
-- DNAME은 - 로 표시

SELECT E.DEPTNO, NVL(D.DNAME, '-'), TRUNC (AVG(E.SAL)) AS AVG, SUM(E.SAL) AS SUM    -- ORACLE
FROM T_EMP E, DEPT D
WHERE E.DEPTNO = D.DEPTNO (+)
GROUP BY E.DEPTNO, D.DNAME
ORDER BY 1;

SELECT E.DEPTNO, NVL(D.DNAME, '-'), TRUNC (AVG(E.SAL)) AS AVG, SUM(E.SAL) AS SUM    -- ANSI
FROM T_EMP E LEFT OUTER JOIN DEPT D
ON E.DEPTNO = D.DEPTNO (+)
GROUP BY E.DEPTNO, D.DNAME
ORDER BY 1;

CREATE OR REPLACE VIEW V_DEPT_SAL(DEPTNO, DNAME, AVG_SAL, SUM_SAL)
AS (SELECT E.DEPTNO, NVL(D.DNAME, '-'), TRUNC (AVG(E.SAL)), SUM(E.SAL)
     FROM T_EMP E, DEPT D
     WHERE E.DEPTNO = D.DEPTNO (+)
     GROUP BY E.DEPTNO, D.DNAME)
ORDER BY 1;


SELECT e.ename, d.dname, s.grade, e.sal
FROM emp e, dept d, salgrade s
WHERE e.deptno = d.deptno
  AND e.sal BETWEEN s.losal AND s.hisal
ORDER BY e.ename;

-----------------------------------HR---------------------------------------


-- FINANCE 부서 소속 사원들의 부서이름, 사원이름, 급여, 직함, 부서위치 조회
SELECT D.DEPARTMENT_NAME, E.first_name, E.SALARY, J.JOB_TITLE, L.CITY
FROM DEPARTMENTS D, EMPLOYEES E, JOBS J, LOCATIONS L
WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID
    AND E.JOB_ID = J.JOB_ID 
    AND D.LOCATION_ID = L.LOCATION_ID 
    AND D.DEPARTMENT_NAME = 'Finance';
    

CREATE OR REPLACE VIEW V_EMP_FINCANCE(부서이름, 사원이름, 급여, 직함, 부서위치)
AS(
    SELECT D.DEPARTMENT_NAME, E.first_name, E.SALARY, J.JOB_TITLE, L.CITY
    FROM DEPARTMENTS D, EMPLOYEES E, JOBS J, LOCATIONS L
    WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID
    AND E.JOB_ID = J.JOB_ID 
    AND D.LOCATION_ID = L.LOCATION_ID 
    AND D.DEPARTMENT_NAME = 'Finance'
);
SELECT * FROM v_emp_fincance;


---------------------------SCOTT---------------------------


---------- INLINE VIEW
--  - CREATE VIEW로 생성된 뷰가 아니라 문장 내에서 임시로 생성된 뷰
--  - FROM 절에 서브쿼리로 기술


-- DEPTNO가 99인 레코드 조회 (V_EMP 뷰 사용)
SELECT * FROM ( SELECT DEPTNO, ENAME, SAL
                FROM T_EMP
                WHERE DEPTNO = 99);            
                
SELECT * FROM T_EMP;

-- JOB이 SALESMAN인 사원들의 JOB, ENAME, DNAME을 인라인 뷰를 이용하여 조회
SELECT E.JOB, E.ENAME, D.DNAME 
FROM (SELECT * FROM EMP WHERE JOB = 'SALESMAN') E , DEPT D
WHERE E.DEPTNO = D.DEPTNO;




-- 뷰 삭제
DROP VIEW V_EMP;
DROP VIEW V_DEPT_SAL;



SELECT ROWNUM, ENAME, HIREDATE FROM EMP ORDER BY ENAME;


SELECT ROWNUM NO , ENAME, HIREDATE 
FROM ( SELECT * FROM EMP ORDER BY ENAME);    

-- 인라인뷰를 이용하여 SAL많은 순서로 상위 5명 조회하여
-- RANKING, ENAME, SAL 표시. 단, SAL이 없는 데이터는 제외
SELECT RANKING, ENAME, SAL
FROM (
    SELECT RANK() OVER (ORDER BY SAL DESC) AS RANKING, ENAME, SAL
    FROM EMP
    WHERE SAL IS NOT NULL
)
WHERE RANKING <= 5;

SELECT ROWNUM NO, ENAME, SAL 
FROM (SELECT  * FROM EMP WHERE SAL IS NOT NULL ORDER BY SAL DESC);

SELECT NO, ENAME, HIREDATE
FROM (SELECT ROWNUM AS NO, E.* -- ENAME, HIREDATE 
      FROM (SELECT * FROM EMP ORDER BY HIREDATE ASC) E)
WHERE NO > (SELECT COUNT(*) - 5 FROM EMP);



CREATE OR REPLACE VIEW V_EMP_HIREDATE(NO, ENAME, HIREDATE)
AS (SELECT NO, ENAME, HIREDATE
    FROM (SELECT ROWNUM NO, ENAME, HIREDATE
          FROM EMP
          WHERE HIREDATE IS NOT NULL)
    WHERE NO <= 5)
ORDER BY HIREDATE ASC;

SELECT * FROM V_EMP_HIREDATE;
-----------------------------------------------------------------------
-- 페이지
SELECT ROWNUM NO, ENAME, SAL 
FROM (SELECT  * FROM EMP WHERE SAL IS NOT NULL ORDER BY SAL DESC)
WHERE CEIL(ROWNUM / 3) > 1;

 

SELECT NO, ENAME, HIREDATE
FROM (SELECT ROWNUM AS NO, E.* -- ENAME, HIREDATE 
      FROM (SELECT * FROM EMP ORDER BY HIREDATE ASC) E)
WHERE NO BETWEEN (5 * 3) - 2 AND 5 * 3;



-----------------------------------------------------------------
--  SUB QUERY
--  - 하나의 쿼리 안에 포함되어 있는 쿼리문
--  - 메인 쿼리 내에 포함된 쿼리문
--  - WHERE 절의 조건문에 사용


-- 단일 행 서브 쿼리
--  - 쿼리 실행 결과가 하나의 행만을 반환
--  - =, >, >=, <=, <, != TKDYD

-- JAMES와 같은 부서에서 일하는 사람들의 DEPTNO, ENAME
SELECT DEPTNO, ENAME FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'JAMES');

-- 평균 SAL 이상을 받는 사람들의 수 조회
SELECT COUNT(*) FROM EMP
WHERE SAL >= (SELECT AVG(SAL) FROM EMP);

-- 평균 SAL 이상을 받는 사람들의 DNAME, ENAME, JOB, SAL 조회
SELECT D.DNAME, E.ENAME, E.JOB, E.SAL
FROM EMP E, DEPT D
WHERE E.SAL >= (SELECT AVG(SAL) FROM EMP) AND E.DEPTNO = D.DEPTNO;


-- 가장 적은 SAL을 받는 JOB과 해당 JOB의 평균 SAL 조회
SELECT JOB, AVG(SAL)
FROM EMP
WHERE JOB = (SELECT JOB FROM EMP WHERE SAL = (SELECT MIN(SAL) FROM EMP))
GROUP BY JOB;

-- 가장 적은 평균 sal를 받는 job과 해당 job의 평균 sal 조회
SELECT JOB, AVG(SAL)
FROM EMP
WHERE JOB = (SELECT JOB FROM EMP WHERE SAL = (SELECT MIN(SAL) FROM EMP))
GROUP BY JOB;

SELECT JOB, AVG(SAL)
FROM EMP
GROUP BY JOB
HAVING  AVG(SAL) = (SELECT MIN(AVG(SAL)) 
                    FROM EMP GROUP BY JOB);
                    
                    
                    
-- 다중 행 서브 쿼리
--  - 쿼리 실행 결과가 하나 이상의 행일 때 사용
--  - WHERE 절에서 여러 개의 컬럼을 동시 비교
--  - IN, ANY, ALL, EXISTS 사용


-- DEPTNO가 10 또는 30인 레코드 조회
SELECT * FROM EMP WHERE DEPTNO IN(10, 30);  -- SUB QUERY 안씀

SELECT * FROM EMP WHERE DEPTNO = ANY (10, 30);  -- SUB QUERY 씀

-- SAL이 3000 이상인 사람들과 같은 부서에서 근무하는 사람들 조회 - 서브쿼리 O
SELECT * FROM EMP WHERE DEPTNO = ANY (
SELECT DISTINCT DEPTNO FROM EMP WHERE SAL >= ANY(3000))
ORDER BY DEPTNO;

SELECT * FROM EMP WHERE SAL > ANY(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER의 최대 급여보다 적게 받는 사람들
SELECT * FROM EMP WHERE SAL < ANY(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER의 최대 급여보다 많이 받는 사람들
SELECT * FROM EMP WHERE SAL > ALL(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER의 최소 급여보다 적게 받는 사람들
SELECT * FROM EMP WHERE SAL < ALL(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;


SELECT * FROM DEPT WHERE LOC IN (SELECT LOC FROM DEPT WHERE LOC = 'CHICAGO');

SELECT * FROM DEPT WHERE EXISTS (SELECT LOC FROM DEPT WHERE LOC = 'CHICAGO');
SELECT * FROM DEPT WHERE EXISTS (SELECT LOC FROM DEPT WHERE LOC = 'SEOUL');


-- 다중 컬럼 서브쿼리 MULTI COLUMN SUB QUERY
--  - 서브 쿼리에서 반환되는 결과가 둘 이상의 컬럼


-- 각 부서별로 최소 SAL을 받는 사람들의 DEPTNO, ENAME, SAL 조회
SELECT DEPTNO, ENAME, SAL
FROM EMP
WHERE (DEPTNO, SAL) IN (SELECT DEPTNO, MIN(SAL) 
                        FROM EMP 
                        GROUP BY DEPTNO)
ORDER BY 1;           


-- 상호 연관 서브 쿼리
--  - 메인에서 넘긴 데이터를 서브 쿼리가 저장 후 돌려주는 방식
--  - 메인 쿼리와 서브 쿼리 사이에 조인
--  - 메인 쿼리 >> 서브 쿼리 >> 메인 쿼리
--  - 성능이 매우 나쁜 유형


-- 소속 부서의 평균 SAL보다 많은 SAL을 받는 사원 조회
SELECT * FROM EMP MAIN
WHERE SAL > ( SELECT AVG(SAL)
              FROM EMP SUB
              WHERE MAIN.DEPTNO = SUB.DEPTNO
              GROUP BY DEPTNO);
              
         
              
-- 스칼라 서브 쿼리
--  - SELECT 절에 사용되는 쿼리문
--  - 1행만 반환
--  - 함수처럼 사용


-- DEPTNO, DNAME, ENAME, JOB, SAL, GRADE 조회
SELECT D.DEPTNO, D.DNAME, E.ENAME, E.JOB, E.SAL, S.GRADE
FROM DEPT D, EMP E, SALGRADE S
WHERE D.DEPTNO = E.DEPTNO 
AND   E.SAL BETWEEN S.LOSAL AND S.HISAL;


SELECT DEPTNO,
        -- DNAME
       (SELECT DNAME FROM DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO) AS DNAME, 
       ENAME, JOB, SAL, 
       --GRADE
       (SELECT GRADE FROM SALGRADE WHERE SAL BETWEEN LOSAL AND HISAL) AS GRADE
FROM EMP
ORDER BY 1, SAL;



-- WITH를 사용한 서브 쿼리
--  - FROM절에 사용되는 서브 쿼리
--  - 특정 테이블의 전체 데이터가 아닌 일부 데이터를 추출한 후 별칭으로 사용


-- DEPTNO가 10인 데이터의 DEPTNO, DNAME, ENAME 조회 - INLINE VIEW
SELECT E.DEPTNO, DNAME, ENAME
FROM (SELECT * FROM EMP WHERE DEPTNO = 10) E, DEPT D
WHERE E.DEPTNO = D.DEPTNO;


-- DEPTNO가 10인 데이터의 DEPTNO, DNAME, ENAME 조회 - WITH
WITH E AS (SELECT * FROM EMP WHERE DEPTNO = 10),
     D AS (SELECT * FROM DEPT) 
SELECT E.DEPTNO, DNAME, ENAME
FROM E, D
WHERE E.DEPTNO = D.DEPTNO;     


------------------------------------------------------------------------------
-- 시퀀스 SEQUENCE
--  - 연속적으로 자동 생성되는 숫자
--  - DML을 ROLLBACK할 때 같이 ROLLBACK 안됨
--  - 기본: 1부터 시작하여 1씩 증가

-- 시퀀스 사용
--  - 시퀀스의 현재 값 : 시퀀스이름.CURRVAL
--  - 시퀀스의 다음 값 : 시퀀스이름.NEXTVAL


-- 기본 시퀀스 생성 
CREATE SEQUENCE T_QNA_SEQ;

SELECT * FROM USER_SEQUENCES;  -- 시퀀스 조회

SELECT T_QNA_SEQ.CURRVAL FROM DUAL;  -- x
SELECT T_QNA_SEQ.NEXTVAL FROM DUAL;  -- 시퀀스 증가
SELECT T_QNA_SEQ.CURRVAL FROM DUAL;  -- O

-- 시퀀스 수정
--  - 최대/최소, 증감값, 사이클, 캐시 사용 여부 변경 가능
--  - 현재 값 변경 불가능
ALTER SEQUENCE T_QNA_SEQ NOCACHE;  -- 캐시 사용 X

-- 시퀀스 삭제
DROP SEQUENCE T_QNA_SEQ;

-- 시퀀스 생성
-- 이름 T_QNA_SEQ, 시작은 1부터, 증가는 1씩 커지게,
-- 최대값은 999999999999999999, 최소값은 0,
-- 반복 X, 캐쉬사용 X
CREATE SEQUENCE T_QNA_SEQ
START WITH 1
INCREMENT BY 1
MAXVALUE 99999999999999
MINVALUE 0
NOCYCLE
NOCACHE;

-- 시퀀스를 사용하여 T_QNA 테이블에 레코드 추가
-- 제목은 SEQUENCE??, 작성자는 FK 제약조건에 위배되지 않도록 지정
INSERT INTO T_QNA (QNO, QUESTION, QID)
VALUES (T_QNA_SEQ.NEXTVAL, 'SEQUENCE??', 'aaaa'); -- 만약 들어가지 않아도 NEXTVAL이 실행됨


-- T_SURVEY_SEQ 시퀀스 생성 (캐시 사용 x)
CREATE SEQUENCE T_SURVEY_SEQ NOCACHE;

-- T_SURVEY_ATTEND_SEQ 시퀀스 생성 (캐시 사용 x)
CREATE SEQUENCE T_SURVEY_ATTEND_SEQ NOCACHE;





-------------------------------- SYS ------------------------------------------
GRANT CREATE SYNONYM TO SCOTT;  -- 권한 부여
GRANT CREATE PUBLIC SYNONYM TO SCOTT;


-------------------------------------------------------------------------------

-- SYNONYM 동의어
--  - 테이블 이름 대신 사용하는 별명
CREATE SYNONYM T_SA FOR T_SURVEY_ATTEND;

SELECT * FROM T_SA;

-- T_SURVEY_ATTEND 테이블에 데이터 추가
-- 설문응답 번호는 시퀀스를 사용
-- 테이블, 이름은 별명 사용
-- 나머지는 임의의 값으로 지정 
INSERT INTO T_SA (ANO, SNO, ID, NUM)
VALUES (T_SURVEY_ATTEND_SEQ.NEXTVAL, 1001, 'aaaa', 1);


-- 동의어 삭제
DROP SYNONYM T_SA;


---------------------------------------------------------------------------
-- 계층형 쿼리
--  - 계층 관계가 보이도록 출력하는 쿼리
--  - 레벨 의사 컬럼 LEVEL PSEUDO COLUMN
--      - ROWNUM이나 ROWID같이 실제 테이블에 저장되어 있지 않지만
--        실제하는 것처럼 사용 가능
--      - 계층형 정보를 표시할 때 레벨 표시
--      - LEVEL 1 : 루트 노드
--        LEVEL 2 : 루트 노드의 자식 노드
--        LEVEL 3 : 루트 노드의 자식 노드의 자식 노드
SELECT LEVEL
FROM dual
CONNECT BY LEVEL <= 10;

CREATE SEQUENCE T_MEMO_SEQ  NOCACHE;

DROP SEQUENCE T_MEMO_SEQ;

ALTER TABLE t_memo ADD CONSTRAINT t_memo_fk FOREIGN KEY(ID) REFERENCES t_member(id);

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 0, 'admin', 'FIRST MEMO');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 1, 'aaaa', 'THE NEXT ONE');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 1, 'bbbb', 'memo memo');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 0, 'aaaa', 'i am a memo');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 0, 'admin', 'U r a memo');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 4, 'admin', 'i am not a memo');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 4, 'bbbb', 'bee');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 2, 'bbbb', 'football is fun');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 6, 'bbbb', 'football is fun');

INSERT INTO t_memo(mno, pno, id, memo)
VALUES(t_memo_seq.NEXTVAL, 8, 'bbbb', 'last memo');

COMMIT;

SELECT MNO, PNO, LEVEL, ID, REGDATE ,
        DECODE(LEVEL, 1, MEMO, LPAD(' ', (LEVEL * 2) - 2) || 'RE:' || MEMO) AS INDENTED_MEMO
FROM T_MEMO
START WITH PNO = 0
CONNECT BY PRIOR MNO = PNO
ORDER SIBLINGS BY PNO ASC;


------------------------------------------------------------------------
-- 계정 관리
--
-- 권한 PREVILEGES
--  - 접속 사용자에 따라 접근 가능한 데이터 영역과 권한이 구분됨

--  - 시스템 권한 SYSTEM PREVILEGES
--      - 사용자 생성, 정보 수정, 삭제, DB접근 권한
--      - 시스템 리소스와 객체 생성 및 관리 권한

--  - 객체 권한 OBJECT PREVILEGES
--      - 특정 사용자가 생성한 객체 관련 권한
--      - 테이블, 뷰, 인덱스, 시퀀스, 동의어 등의 접근

-- 권한 부여 GRANT, 권한 회수 REVOKE
-- GRANT CREATE TABLE, CREATE SESSION TO SCOTT;
-- GRANT CREATE VIEW TO SCOTT WITH ADMIN OPTION;
-- REVOKE CREATE TABLE FROM SCOTTL;


-- sys 계정 접속 후 user1 계정 생성
CREATE USER user1 IDENTIFIED BY 1111;

-- 계정 조회
SELECT * FROM ALL_USERS;
SELECT * FROM ALL_USERS WHERE USERNAME='USER1';

-- user1에게 DB접속 권한 부여
GRANT CREATE SESSION TO user1;

-- user1에게 테이블 생성 권한 부여
GRANT CREATE TABLE TO user1;

-- user1의 테이블 스페이스를 USERS로 변경
ALTER USER user1 DEFAULT TABLESPACE USERS;

-- 테이블 스페이스 공간을 크기 제한 없이 할당
ALTER USER user1 QUOTA UNLIMITED ON USERS;

-- user1의 비밀번호 변경
ALTER USER user1 IDENTIFIED BY 1234;

-- user1 계정 삭제
DROP USER user1;
DROP USER user1 CASCADE;  -- 생성한 객체들도 함께 삭제



-- USERS 테이블 스페이스에 user2 계정 생성
-- DB 접속 권한과 RESOURCE를 부여
CREATE USER user2 IDENTIFIED BY 1111
DEFAULT TABLESPACE USERS;

GRANT CREATE SESSION, RESOURCE TO user2;


-- user2의 CREATE SESSION 권한 회수
REVOKE CREATE SESSION FROM user2;

-- 다시 권한 주고 접속
GRANT CREATE SESSION, RESOURCE TO user2;

-- user2에게 SCOTT의 t_emp 테이블 SELECT, DELETE 권한 부여
GRANT SELECT ON t_emp TO user2; -- X
GRANT SELECT ON scott.t_emp TO user2; -- O
GRANT DELETE ON scott.t_emp TO user2; -- O

-- user2에게 SCOTT의 t_emp 테이블 SELECT 권한 회수
REVOKE SELECT ON scott.t_emp FROM user2;




----------------------------------------------------------
-- ROLE
--  - 여러 종류의 권한을 묶어 놓은 그룹
--      - 사전 정의 롤 : CONNECT, RESOURCE, DBA
--      - 사용자 정의 롤
--  - 다수의 사용자에게 공통적으로 필요한 권한들을
--    하나의 그룹으로 묶어서 롤에 대한 권한을 부여

-- 사용자가 가진 롤 조회
SELECT * FROM DBA_ROLE_PRIVS;

SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'SCOTT'
UNION ALL
SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'USER2';

-- 롤의 권한 조회
SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE = 'CONNECT'
UNION ALL
SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE = 'RESOURCE';


----------------------------------------------------------------

-- DEV 계정 비밀번호 1111로 지정하여 생성
-- USERS를 기본 테이블스페이스로 생성
-- USERS 테이블 스페이스를 기본으로 사용할 수 있게 하여 생성
-- DB접속 및 RESOURCE 롤을 가지도록 처리
-- T_TEST 테이블 생성 및 테스트 레코드 하나 추가

CREATE USER DEV IDENTIFIED BY 1111 DEFAULT TABLESPACE USERS;
GRANT CREATE SESSION, RESOURCE TO DEV;
--ALTER USER DEV DEFAULT TABLESPACE USERS;

---------------------------------------SYS END-------------------------------------


-----------------------------------------------------------------------------------
-- PL / SQL
-- Oracle's Procedural Language extenstion to SQL
--  - 오라클 자체에 내장되어 있는 절차적 프로그래밍 언어
--  - DB 내의 데이터 조작을 위해서 제공
--  - SQL 문장에서 변수 선언, 비교, 반복 처리 가능


-- 변수 선언 구문
-- identifier [CONSTANT] datatype(datasize) [NOT NULL] [:= | DEFAULT exp];

SET SERVEROUTPUT ON;

BEGIN 
    DBMS_OUTPUT.PUT_LINE('Hello World!~');
END;
/

-- 변수 및 상수 선언과 사용
DECLARE
    v_ename     VARCHAR2(20);   -- 변수 선언
    v_dpetno    NUMBER := 10;   -- 변수 선언 및 초기화(값 대입)
    v_company   CONSTANT VARCHAR2(20) := 'A co.,';  -- 상수
    v_hiredate  DATE DEFAULT SYSDATE;   -- 기본값 사용
    v_empno     NUMBER NOT NULL := &empno;  -- 필수항목 설정 / &는 유저한테 입력받기 기능
    v_sal       emp.sal%TYPE := 2000;   -- emp테이블의 sal컬럼 타입으로
BEGIN
--    v_company := 'B co.,';  -- 상수에 값 변경 불가능. 에러
    DBMS_OUTPUT.PUT_LINE('급여 : ' || v_sal);
    DBMS_OUTPUT.PUT_LINE('사원 번호 : ' || v_empno);
    DBMS_OUTPUT.PUT_LINE('입사 일자 : ' || TO_CHAR(v_hiredate, 'YYYY.MM.DD'));
    DBMS_OUTPUT.PUT_LINE('입사 일자 : ' || v_hiredate);
    DBMS_OUTPUT.PUT_LINE('회사 이름 : ' || v_company);
    DBMS_OUTPUT.PUT_LINE('부서 번호 : ' || v_dpetno);
    
    DBMS_OUTPUT.PUT_LINE('사원 이름 : ' || v_ename);
    v_ename := 'Han';  -- 변수에 값 저장
    DBMS_OUTPUT.PUT_LINE('사원 이름 : ' || v_ename);
END;
/


-- 파일에 저장된 프로시저 실행
@ c:\dev\exercise.sql


-- SELECT 결과를 저장
DECLARE
    v_ename emp.ename%TYPE;
    v_sal   emp.sal%TYPE;
BEGIN
    SELECT ENAME, SAL 
    INTO v_ename, v_sal
    FROM EMP 
    WHERE EMPNO = 9998;
    
    DBMS_OUTPUT.PUT_LINE('사원 이름 : ' || v_ename);
    DBMS_OUTPUT.PUT_LINE('급여 : ' || v_sal);
END;
/

-- DML 실행    
BEGIN
    INSERT INTO t_emp 
    VALUES (8000,'Pal', 88, 800, SYSDATE);
END;
/

-- DDL 실행
BEGIN
    CREATE TABLE t_test (
    no  NUMBER,
    timeinfo DATE
);
END;
/

DECLARE
    stmt   VARCHAR2(1000);
BEGIN
    stmt := 'CREATE TABLE t_test(no NUMBER, timeinfo DATE)';
    EXECUTE IMMEDIATE stmt; -- O
    DBMS_OUTPUT.PUT_LINE('CREATE TABLE OK');
END;
/


-- RECORD 타입 변수
DECLARE
    TYPE emp_record IS RECORD ( 
        empno   t_emp.empno%TYPE,
        ename   t_emp.ename%TYPE,
        sal     t_emp.sal%TYPE 
    );
    v_emp_rec emp_record;       --emp_record 타입의 변수 선언
BEGIN
    v_emp_rec.empno := 8001;    --emp_record 타입의 변수에 값 저장
    v_emp_rec.ename := 'Paa';
    v_emp_rec.sal := 880;
    
    DBMS_OUTPUT.PUT_LINE('empno : ' || v_emp_rec.empno);
    DBMS_OUTPUT.PUT_LINE('ename : ' || v_emp_rec.ename);
    DBMS_OUTPUT.PUT_LINE('sal   : ' || v_emp_rec.sal);
END;
/

DECLARE
    TYPE emp_record IS RECORD ( 
        empno   t_emp.empno%TYPE,
        ename   t_emp.ename%TYPE,
        sal     t_emp.sal%TYPE 
    );
    v_emp_rec emp_record;       --emp_record 타입의 변수 선언
BEGIN
    v_emp_rec.empno := 8001;    --emp_record 타입의 변수에 값 저장
    v_emp_rec.ename := 'Paa';
    v_emp_rec.sal := 880;
    
    INSERT INTO t_emp(empno, ename, sal) 
    VALUES(v_emp_rec.empno, v_emp_rec.ename, v_emp_rec.sal);
    
    SELECT empno, ename, sal INTO  v_emp_rec
    FROM   t_emp
    WHERE  empno = 8001;
    
    DBMS_OUTPUT.PUT_LINE('empno : ' || v_emp_rec.empno);
    DBMS_OUTPUT.PUT_LINE('ename : ' || v_emp_rec.ename);
    DBMS_OUTPUT.PUT_LINE('sal   : ' || v_emp_rec.sal);
END;
/


DECLARE
    TYPE emp_record IS RECORD ( 
        empno    t_emp.empno%TYPE,
        ename    t_emp.ename%TYPE,
        deptno   t_emp.deptno%TYPE,
        sal      t_emp.sal%TYPE, 
        hiredate t_emp.hiredate%TYPE 
    );
    v_emp_rec emp_record;       --emp_record 타입의 변수 선언
BEGIN
    v_emp_rec.empno := 8003;    --emp_record 타입의 변수에 값 저장
    v_emp_rec.ename := 'Pam';
    v_emp_rec.deptno := 88;
    v_emp_rec.sal := 882;
    v_emp_rec.hiredate := SYSDATE;
    
    --v_emp_rec 변수에 저장된 값을 테이블에 추가
    INSERT INTO t_emp VALUES v_emp_rec; 
                        
    --DB 쿼리 결과를 v_emp_rec 변수로 받기
    SELECT *     INTO  v_emp_rec
    FROM   t_emp WHERE  empno = 8003;

    v_emp_rec.deptno := 99;  --부서 번호 변경
    UPDATE t_emp SET ROW = v_emp_rec WHERE empno = 8003;
    
    DBMS_OUTPUT.PUT_LINE('empno : ' || v_emp_rec.empno);
    DBMS_OUTPUT.PUT_LINE('ename : ' || v_emp_rec.ename);
    DBMS_OUTPUT.PUT_LINE('sal   : ' || v_emp_rec.sal);
END;
/

-- TABLE OF 타입 변수
-- 연관 배열 형태
DECLARE
    TYPE rainbow_arr 
    IS   TABLE OF VARCHAR2(20)  --저장할 값
         INDEX BY PLS_INTEGER;  --인덱스
    v_rainbow rainbow_arr;      --rainbow_arr 타입의 배열 변수 선언
BEGIN
    v_rainbow(1) := 'RED';
    v_rainbow(2) := 'ORANGE'; 
    v_rainbow(3) := 'YELLOW';
    v_rainbow(4) := 'GREEN';
    
    DBMS_OUTPUT.PUT_LINE('v_rainbow.COUNT : ' || v_rainbow.COUNT );
    DBMS_OUTPUT.PUT_LINE('v_rainbow.FIRST : ' || v_rainbow.FIRST );
    DBMS_OUTPUT.PUT_LINE('v_rainbow.LAST : ' || v_rainbow.LAST );
    DBMS_OUTPUT.PUT_LINE('v_rainbow.PRIOR(4) : ' || v_rainbow.PRIOR(4) );
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE(v_rainbow(1));
    DBMS_OUTPUT.PUT_LINE(v_rainbow(2));
    DBMS_OUTPUT.PUT_LINE(v_rainbow(3));
    DBMS_OUTPUT.PUT_LINE(v_rainbow(4));
END;
/


-- BIND 변수
-- - 호스트 변수라고도 함
-- - VARIABLE 키워드 사용
-- - SQL문과 PL/SQL 블록에서 사용됨
-- - PL/SQL 블록이 실행된 후에도 액세스 가능

VARIABLE v_bind NUMBER;
BEGIN
    SELECT COUNT(*)     INTO :v_bind
    FROM   t_emp
    WHERE  deptno = 99;
END;
/

PRINT v_bind


--------------------------------------------
-- PL/SQL 제어문
-- - 조건문 : IF, CASE
-- - 반복문 : BASIC LOOP, WHILE, FOR

-- IF ~ THEN
DECLARE
    v_deptno    t_emp.deptno%TYPE;  --t_emp 테이블의 deptno 타입의 변수 v_deptno 선언
    v_dept_zone VARCHAR2(20);       --문자열 20자를 저장할 변수 v_dept_zone 선언
BEGIN
    SELECT deptno   INTO v_deptno   --t_emp 테이블의 empno가 2222인 레코드의 deptno를
    FROM   t_emp                    --위에서 선언한 변수에 담기
    WHERE  empno = 2222;
    
    --v_deptno가 
    --50을 초과하면 v_dept_zone을 국외로 저장
    --90 이상이면 발령대기 저장
    --그 외는 국내로 저장
    IF v_deptno > 50 AND v_deptno < 90 THEN
        v_dept_zone := '국외';
    ELSIF v_deptno >= 90 THEN
        v_dept_zone := '발령대기';
    ELSE
        v_dept_zone := '국내';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('부서 번호 : ' || v_deptno );
    DBMS_OUTPUT.PUT_LINE('근무 지역 : ' || v_dept_zone );
END;
/


-- CASE -  정확히 일치하는 값
DECLARE
    v_deptno    t_emp.deptno%TYPE;   
    v_dept_zone VARCHAR2(20);      
BEGIN
    SELECT deptno   INTO v_deptno   
    FROM   t_emp                    
    WHERE  empno = 2222;
    
    --v_deptno가 77면 v_dept_zone을 '근무 중'
    --           88이면    "        '교대 중'
    --그렇지 않으면 '발령대기'
    CASE v_deptno
        WHEN 77 THEN v_dept_zone := '근무 중';
        WHEN 88 THEN v_dept_zone := '교대 중';
        ELSE         v_dept_zone := '발령대기';
    END CASE;
        
    DBMS_OUTPUT.PUT_LINE('부서 번호 : ' || v_deptno );
    DBMS_OUTPUT.PUT_LINE('근무 지역 : ' || v_dept_zone );
END;
/


-- 반복문 LOOP
DECLARE
    i   NUMBER := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE(i);
        i := i + 1;
        EXIT WHEN i > 5;    --반복문 중단 조건
    END LOOP;
END;
/

DECLARE
    i   NUMBER := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE(i);
        i := i + 1;
        
        IF i > 5 THEN   --반복문 중단 조건
            EXIT;
        END IF;
    END LOOP;
END;
/

--반복문 WHILE
DECLARE
    i   NUMBER := 6;
BEGIN
    WHILE i <= 10   LOOP  --반복 조건
        DBMS_OUTPUT.PUT_LINE(i);
        i := i + 1;
    END LOOP;
END;
/

--반복문 FOR
BEGIN
    FOR i IN 11..25 LOOP
 /*     IF MOD(i, 3) != 0 THEN
            CONTINUE;  --이하 수행 x
        END IF;   */
        CONTINUE WHEN MOD(i, 3) != 0;
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
END;
/

--반복문 FOR - REVERSE
BEGIN
    FOR i IN REVERSE 16..20 LOOP
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
END;
/


SET SERVEROUTPUT ON;


-- RECORD 및 TABLE 변수 이용하여 SELECT 결과 받기
DECLARE
    TYPE dept_record IS RECORD ( 
        deptno    dept.deptno%TYPE,
        dname     dept.dname%TYPE
    );
    TYPE dept_arr
    IS  TABLE OF dept_record
        INDEX BY PLS_INTEGER;
        
    v_dept_list dept_arr;
    i   PLS_INTEGER := 0; -- 인덱스 변수
BEGIN
    -- dept 테이블에서 SELECT한 결과를 v_dept_list에 저장
    FOR j IN ( SELECT deptno, dname FROM dept ) LOOP
        i := i + 1;
        v_dept_list(i).deptno := j.deptno;
        v_dept_list(i).dname  := j.dname;
    END LOOP;    
    
    -- v_dept_list에 저장된 값을 화면에 표시
    FOR k IN 1 .. i LOOP
        DBMS_OUTPUT.PUT_LINE(v_dept_list(k).deptno || ' : ' || v_dept_list(k).dname);
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------------');
    
    -- SELCT 결과를 바로 출력
    FOR j IN ( SELECT deptno, dname FROM dept ) LOOP
        DBMS_OUTPUT.PUT_LINE(j.deptno || ' : ' || j.dname);
    END LOOP;  
END;
/


-- %ROWTYPE 변수 이용하여 SELECT 결과 받기
DECLARE
    TYPE dept_arr
    IS  TABLE OF dept%ROWTYPE  -- 저장할 값
        INDEX BY PLS_INTEGER;  -- 인덱스
        
    v_dept_list dept_arr;
    i   PLS_INTEGER := 0; -- 인덱스 변수
BEGIN
    FOR j IN ( SELECT deptno, dname FROM dept ) LOOP
        i := i + 1;
        v_dept_list(i).deptno := j.deptno;
        v_dept_list(i).dname  := j.dname;
    END LOOP;  
    
    FOR k IN 1 .. i LOOP
        DBMS_OUTPUT.PUT_LINE(v_dept_list(k).deptno || ' : ' || v_dept_list(k).dname);
    END LOOP;
END;
/


--------------------------------------------------------------------------------
-- CURSOR
--  - SELECT 또는 DML 같은 SQL문을 실행했을 때
--      해당 SQL문의 처리 정보를 저장한 메모리 공간
--  - SQL문의 실행 결과값 사용 가능

-- 묵시적 커서 IMPLICIT CURSOR
--  - SQL 사용시 자동 선언
--  - 단일 행 결과에 사용
--  - OPEN, FETCH, CLOSE 불필요
--  - SQL%ROWCOUNT
--    SQL%FOUND
--    SQL%NOTFOUND
--    SQL%ISOPEN

-- 명시적 커서 EXPLICIT CURSOR
--  - 사용자가 선언하여 생성한 후 사용
--  - 다중 행 결과에 사용 가능
--  - OPEN, FETCH, CLOSE 필요
--  - 커서이름%ROWCOUNT
--    커서이름%FOUND
--    커서이름%NOTFOUND
--    커서이름%ISOPEN


-- 명시적 커서 사용 단계
-- CURSOR 선언 > 오픈 > 추출 > 종료


-- 묵시적 커서 이용 - 현재 커서 정보 확인
BEGIN
    IF (SQL%ISOPEN) THEN
        DBMS_OUTPUT.PUT_LINE('CURSOR OPEN O');
    ELSE
        DBMS_OUTPUT.PUT_LINE('CURSOR OPEN x');
    END IF;
    
    -- DML 실행
    UPDATE t_emp SET deptno = 80 WHERE empno = 8000;
    
    IF (SQL%FOUND) THEN
        DBMS_OUTPUT.PUT_LINE('변경된 행 O');
        DBMS_OUTPUT.PUT_LINE('변경된 행의 수 : ' || SQL%ROWCOUNT );
    END IF;
    
    IF (SQL%NOTFOUND) THEN
        DBMS_OUTPUT.PUT_LINE('변경된 행 X');
    END IF;
END;
/


-- 명시적 커서 - 단일 행 반환
DECLARE
    v_dept_row  dept%ROWTYPE;
    
    CURSOR  crs     -- 명시적 커서 선언
    IS      SELECT * FROM dept WHERE deptno = 30;
BEGIN
    OPEN crs;   -- 커서 오픈
    FETCH crs INTO v_dept_row;  -- 데이터 추출하여 선언한 변수에 저장
        DBMS_OUTPUT.PUT_LINE('부서 번호 : ' || v_dept_row.deptno);
        DBMS_OUTPUT.PUT_LINE('부서 이름 : ' || v_dept_row.dname);
        DBMS_OUTPUT.PUT_LINE('부서 위치 : ' || v_dept_row.loc);
    CLOSE crs;  -- 커서 종료
END;
/

-- 명시적 커서 - 복수 행 반환
-- FOR를 사용할 경우 OPEN, FETCH, CLOSE 불필요
DECLARE
    CURSOR  crs     -- 명시적 커서 선언
    IS      SELECT * FROM dept;
BEGIN
--    OPEN crs;   -- 커서 오픈
--    FETCH crs INTO v_dept_row;  -- 데이터 추출하여 선언한 변수에 저장
    DBMS_OUTPUT.PUT_LINE('부서 번호 | 부서 이름 | 부서 위치');
    FOR i IN crs LOOP    
        DBMS_OUTPUT.PUT_LINE(i.deptno || ' | ' || 
                             i.dname  || ' | ' || 
                             i.loc
        );
    
    END LOOP;
--    CLOSE crs;  -- 커서 종료
END;
/


-- 명시적 커서 - 매개변수 전달
DECLARE
    CURSOR  crs ( p_deptno dept.deptno%TYPE ) -- 매개변수(parameter) 명시
    IS      SELECT * FROM dept WHERE deptno = p_deptno;
BEGIN
--    OPEN crs;   -- 커서 오픈
--    FETCH crs INTO v_dept_row;  -- 데이터 추출하여 선언한 변수에 저장

    DBMS_OUTPUT.PUT_LINE('부서 번호 | 부서 이름 | 부서 위치');
    FOR i IN crs (&input_deptno) LOOP    -- 매개변수 입력받기
        DBMS_OUTPUT.PUT_LINE(i.deptno || ' | ' || 
                             i.dname  || ' | ' || 
                             i.loc
        );
    
    END LOOP;
--    CLOSE crs;  -- 커서 종료
END;
/




-----------------------------------------------------
-- 예외

-- 예외명: ZERO_DIVIDE
-- 예외번호: ORA-01476
-- 설명: 0으로 나누려 했을 때 발생하는 예외

DECLARE
    i NUMBER := 5;
BEGIN
    i := i / 0;  -- 예외 발생
    DBMS_OUTPUT.PUT_LINE('exception ???');
EXCEPTION
    WHEN ZERO_DIVIDE THEN
        DBMS_OUTPUT.PUT_LINE('0으로 나누기 예외 발생!');
END;
/


-- 사전 정의 예외 처리
DECLARE
    v_num   NUMBER;
BEGIN
    SELECT dname INTO v_num  -- 예외 1
    FROM dept 
    WHERE deptno = 20;
EXCEPTION 
    WHEN VALUE_ERROR THEN   
        DBMS_OUTPUT.PUT_LINE('예외 발생 1 : 수치 또는 값 오류 발생');
END;
/


------------------------------------------------------------------
-- 저장 서브 프로그램
--  - 이름을 지정하여 저장해두는 PL/SQL 블럭
--  - STORED PRECEDURE : 특정 처리 작업 수행, SQL문에서 사용 불가능
--  - STORED FUNCTION : 특정 연산의 결과 반한, SQL문에서 사용 가능
--  - PACKAGE : 저장된 서브프로그램의 그룹화에 사용
--  - TRIGGER : 특정 상황 발생 시에 자동으로 수행되는 기능 구현에 사용

-- PRECEDURE
CREATE OR REPLACE PROCEDURE DEL_ALL
IS -- v_rows NUMBER := 0;
BEGIN
    DELETE FROM t_emp;
    
    IF (SQL%FOUND) THEN
        DBMS_OUTPUT.PUT_LINE('삭제된 행의 수 : ' || SQL%ROWCOUNT);
    ELSE
        DBMS_OUTPUT.PUT_LINE('삭제된 행이 없습니다.');
    END IF;
END;
/

SELECT * FROM t_emp;

EXECUTE DEL_ALL;  -- 저장된 프로시저 실행

ROLLBACK;

BEGIN
    DEL_ALL;
END;
/

-- 프로시저 내용 확인
SELECT * FROM USER_SOURCE WHERE NAME = 'DEL_ALL';


-- 파라미터를 받는 프로시저
CREATE OR REPLACE PROCEDURE PARAM_IN (
    param1  IN NUMBER,
    param2  NUMBER := 2,
    param3  NUMBER,
    param4  NUMBER DEFAULT 4

)
IS 
BEGIN
    DBMS_OUTPUT.PUT_LINE(param1);
    DBMS_OUTPUT.PUT_LINE(param2);
    DBMS_OUTPUT.PUT_LINE(param3);
    DBMS_OUTPUT.PUT_LINE(param4);
END;
/

EXECUTE PARAM_IN(param1 => 15, param3 => 17);
EXECUTE PARAM_IN(9, 11, 13);
EXECUTE PARAM_IN(1, 3, 5, 7);



-- 테이블 이름을 입력 받아서
-- 해당 테이블에 레코드가 있는 경우에는
--  > 모든 데이터 삭제 후 삭제된 행의 수를 출력
-- 그렇지 않은 경우에는 
--  > '삭제된 행이 없습니다' 출력하는 DEL_ALL 프로시저 작성
CREATE OR REPLACE PROCEDURE DEL_ALL (
    TABLE_NAME IN VARCHAR2
)
IS
    query VARCHAR2(50) := 'DELETE FROM ' || TABLE_NAME;

BEGIN
    DBMS_OUTPUT.PUT_LINE('테이블 이름 : ' || TABLE_NAME);
    EXECUTE IMMEDIATE query;
    
    IF (SQL%FOUND) THEN
        DBMS_OUTPUT.PUT_LINE('삭제된 행의 수 : ' || SQL%ROWCOUNT);
    ELSE
        DBMS_OUTPUT.PUT_LINE('삭제된 행이 없습니다.');
    END IF;
END;
/


-- 결과를 반환하는 프로시져
-- t_survey_attend 테이블의 시퀀스를 1증가 시킨 값을 반환
CREATE OR REPLACE PROCEDURE GET_SEQ (
    v_seq_no OUT NUMBER
)
IS
BEGIN
    SELECT t_qna_seq.NEXTVAL INTO v_seq_no FROM dual;
END;
/


EXECUTE DEL_ALL('t_emp');

ROLLBACK;

-- GET_SEQ 프로시저에서 반환하는 시퀀스를 이용하여
-- t_servey_attend 테이블에 설문을 추가하고
-- t_servey 테이블에 해당 설문의 응답 카운트를 1 증가
-- DO_SERVEY 프로시저 작성
CREATE OR REPLACE PROCEDURE DO_SURVEY IS
v_no NUMBER;

BEGIN
    GET_SEQ(v_no); -- GET_SEQ 프로시저의 반환값 넘겨받기
    DBMS_OUTPUT.PUT_LINE('t_survey_attend 테이블의 시퀀스 : ' || v_no);
    
    -- insert query
    INSERT INTO t_servey_attend (ano, sno, id, num, attenddate)
    VALUES (v_no, 100, , SYSDATE);
    
    
    -- update query
END;
/

EXECUTE DO_SURVEY;


-- 파라미터를 넘겨받고 반환하는 프로시저
CREATE OR REPLACE PROCEDURE PARAM_IN_OUT (
        v_no IN OUT NUMBER
) IS
BEGIN
    DBMS_OUTPUT.PUT_LINE(' NO IN : ' || v_no);
    v_no := v_no * 2;  -- 넘겨받은 값을 2배하여 저장
END;
/

-- 익명의 PL/SQL 블럭으로 파라미터 5를 지정하여 반환되는 값을 화면에 표시
DECLARE 
    v_no NUMBER := 5;
BEGIN
    PARAM_IN_OUT(v_no);
    DBMS_OUTPUT.PUT_LINE('NO OUT : ' || v_no);
END;
/


------------------------------------------------------------
-- 프로시저 오류 정보 조회
CREATE OR REPLACE PROCEDURE SOME_ERR
IS
BEGIN
    DELETE FROM some_table;
END;
/

SHOW ERROERS;
SHOW ERR PRECEDURE SOME_ERR;
SELECT * FROM USER_ERRORS WHERE NAME = 'SOME_ERR';

-- 프로시저 삭제
DROP PROCEDURE SOME_ERR;


SELECT ename, sal FROM emp WHERE sal >= 2000; -- 5% 보너스
SELECT ename, sal FROM emp WHERE sal < 2000;  -- 10% 보너스


--DECLARE
--    TYPE emp_arr
--    IS  TABLE OF emp%ROWTYPE  -- 저장할 값
--        INDEX BY PLS_INTEGER;  -- 인덱스
--        
--    v_empt_list emp_arr;
--    i   PLS_INTEGER := 0; -- 인덱스 변수
--BEGIN
--    
--    FOR j IN ( SELECT ename, sal FROM dept ) LOOP
--        i := i + 1;
--        v_dept_list(i).deptno := j.deptno;
--        v_dept_list(i).dname  := j.dname;
--    END LOOP;  
--    
--    FOR k IN 1 .. i LOOP
--        DBMS_OUTPUT.PUT_LINE(v_dept_list(k).deptno || ' : ' || v_dept_list(k).dname);
--    END LOOP;
--END;
--/

--CREATE OR REPLACE PROCEDURE emp_bonus 
--IS  -- emp_row EMP%ROWTYPE,
--    bonus_10 NUMBER,
--    bonus_5 NUMBER
--BEGIN
CREATE OR REPLACE PROCEDURE emp_bonus2 (
    p_bonus_5 OUT NUMBER,
    p_bonus_10 OUT NUMBER,
    p_bonus_sum OUT NUMBER
) AS
    CURSOR c_emp IS SELECT sal FROM emp;
    v_total_bonus_5 NUMBER := 0;
    v_total_bonus_10 NUMBER := 0;
BEGIN
    FOR emp IN c_emp LOOP
        
        IF emp.sal > 2000 THEN
            v_total_bonus_5 := v_total_bonus_5 + (emp.sal * 0.05);
        ELSE
            v_total_bonus_10 := v_total_bonus_10 + (emp.sal * 0.1);
        END IF;
    END LOOP;
    
    p_bonus_5 := v_total_bonus_5;
    p_bonus_10 := v_total_bonus_10;
    p_bonus_sum := p_bonus_5 + p_bonus_10;
END;
/


DECLARE
    v_bonus_5 NUMBER;
    v_bonus_10 NUMBER;
    v_bonus_sum NUMBER;
BEGIN
    emp_bonus2(v_bonus_5, v_bonus_10, v_bonus_sum);
    DBMS_OUTPUT.PUT_LINE('Total amount with 5% bonus: ' || v_bonus_5);
    DBMS_OUTPUT.PUT_LINE('Total amount with 10% bonus: ' || v_bonus_10);
    DBMS_OUTPUT.PUT_LINE('Total amount bonus: ' || v_bonus_sum);
END;
/


CREATE OR REPLACE PROCEDURE emp_bonus (
    p_bonus_sum OUT NUMBER
) IS 
    CURSOR c_emp IS 
    SELECT sal FROM emp;
    
    v_total_bonus NUMBER := 0;
BEGIN
    FOR emp IN c_emp LOOP
        
        IF emp.sal > 2000 THEN
            v_total_bonus := v_total_bonus + (emp.sal * 0.05);
        ELSE
            v_total_bonus := v_total_bonus + (emp.sal * 0.1);
        END IF;
    END LOOP;
    
    p_bonus_sum := v_total_bonus;
END;
/

DECLARE
    v_bonus_sum NUMBER;
BEGIN
    emp_bonus(v_bonus_sum);
    DBMS_OUTPUT.PUT_LINE('Total amount bonus: ' || v_bonus_sum);
END;
/

----------------------------------------------------------------------
-- FUNCTION 함수

-- 전화번호 포맷 변경 후 반환 함수
CREATE OR REPLACE FUNCTION
    PHONE_FMT(NO IN VARCHAR2)  -- 전달받을 매개변수 타입 명시
RETURN VARCHAR2  -- 반환타입 명시
IS
    v_no    VARCHAR2(13);
BEGIN
    -- 매개변수로 전달받은 NO(ex: 11112222)를
    -- 010-1111-2222의 형태로 v_no에 저장
    v_no := '010-' || SUBSTR(NO, 1, 4) || '-' || SUBSTR(NO, 5);
    DBMS_OUTPUT.PUT_LINE('input: ' || NO);
    DBMS_OUTPUT.PUT_LINE('output: ' || v_no);
    RETURN v_no;
END;
/

DECLARE
    v_no VARCHAR2(20);
BEGIN
    v_no := PHONE_FMT('11112222');
END;
/

SELECT PHONE_FMT('11112222') FROM dual;


-- 함수 삭제
DROP FUNCTION PHONE_FMT;

------------------------------------------------------------
-- TRIGGER 트리거
--  - 관련된 특정 사건(evnet)이 발생될 때마다 묵시적(자동)으로 실행되는 PL/SQL블럭
--  - 트리거 동작 대상
--      - DML : INSERT, DELETE, UPDATE
--      - DDL : CREATE, ALTER, DROP
--      - DB : SERVERERROR, LOGIN, LOGOFF, STARTUP, SHUTDOWN
--  - 트리거 구분
--      - DML 트리거
--      - DDL 트리거
--      - INSTEAD OF 트리거 : 뷰에 사용되는 DML 동작시
--      - SYSTEM 트리거 : DB나 스키마 이벤트 동작 시
--      - 단순 트리거 : 트리거 동작 문장이 실행되기 전, 후 / 행에 영향을 미치기 전, 후
--      - 복합 트리거 : 단순 트리거의 여러 시점에 동작


-- t_emp 테이블에 주말에는 DML 수행 불가
CREATE OR REPLACE TRIGGER TRG_DML_WEEKEND
BEFORE 
    INSERT OR DELETE OR UPDATE ON t_emp
BEGIN
    IF TO_CHAR(SYSDATE, 'DY') IN ('토', '일') THEN -- 오늘이 주말인지 확인
        IF INSERTING THEN
            RAISE_APPLICATION_ERROR(-20001, '주말 - 사원정보 추가 불가');
        ELSIF UPDATING THEN
            RAISE_APPLICATION_ERROR(-20001, '주말 - 사원정보 변경 불가');
        ELSIF DELETING THEN
            RAISE_APPLICATION_ERROR(-20001, '주말 - 사원정보 삭제 불가');
        ELSE
            RAISE_APPLICATION_ERROR(-20001, '주말 - 사원정보 테이블 사용 불가');
        END IF;
    END IF;
END;
/


-- t_emp 테이블의 DML 수행 기록 저장 테이블
CREATE TABLE t_dml_log (
    tablenm     VARCHAR(20),    -- 테이블 이름
    type        VARCHAR(20),    -- DML 종류 
    empno       NUMBER,         -- 대상 사원 번호
    usernm      VARCHAR(30),    -- DML 수행 계정
    datetime    DATE            -- 수행 일시
);

-- t_emp 테이블의 DML 수행 기록 트리거
CREATE OR REPLACE TRIGGER TRG_DML_LOG
AFTER 
    INSERT OR DELETE OR UPDATE ON t_emp
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO t_dml_log
        VALUES('t_emp', 'INSERT', :new.empno, 
                SYS_CONTEXT('USERENV', 'SESSION_USER'),
                SYSDATE);
    ELSIF UPDATING THEN
        INSERT INTO t_dml_log
        VALUES('t_emp', 'UPDATE', :old.empno, 
                SYS_CONTEXT('USERENV', 'SESSION_USER'),
                SYSDATE);
    ELSIF DELETING THEN
        INSERT INTO t_dml_log
        VALUES('t_emp', 'DELTE', :old.empno, 
                SYS_CONTEXT('USERENV', 'SESSION_USER'),
                SYSDATE);
    END IF;
END;
/

INSERT INTO t_emp (empno, ename, deptno, sal, hiredate)
VALUES (1000, 'MOON', 40, 1000, '2024/03/07'); 


UPDATE t_emp SET deptno = 99 WHERE empno = 2222;



SELECT * FROM USER_TRIGGERS;        -- 트리거 조회
ALTER TRIGGER TRG_DML_LOG DISABLE;  -- 특정 트리거 비활성화
ALTER TRIGGER TRG_DML_LOG ENABLE;   -- 특정 트리거 활성화
DROP TRIGGER TRG_DML_LOG;           -- 트리거 삭제



-----------------------------------------------------------------
-- 백업 및 복원
--  - expdp, impdp
--
-- 백업 디렉토리 지정 c:\dev\dbBackup
 
---- sys --------------------------------------------------------
CREATE OR REPLACE DIRECTORY dbBackup
AS 'c:\dev\dbBackup';

GRANT READ, WRITE ON DIRECTORY dbBackup TO scott;

-- CMD에서
-- expdp scott/tiger@localhost:1521/XE DIRECTORY=dbBackup DUMPFILE=scott_20240325.dmp
---- scott ------------------------------------------------------
DROP TABLE t_member3;

SELECT * FROM t_member3;

-- impdp scott/tiger@localhost:1521/XE DIRECTORY=dbBackup DUMPFILE=scott_20240325.dmp

DELETE FROM t_emp WHERE empno = 1111;
COMMIT;
SELECT * FROM t_emp WHERE empno = 1111;
-- impdp scott/tiger@localhost:1521/XE DIRECTORY=dbBackup 
-- DUMPFILE=scott_20240325.dmp TABLES=t_emp TABLE_EXISTS_ACTION=APPEND




SELECT * FROM EMP;
SELECT * FROM T_EMP;
SELECT * FROM DEPT;
SELECT * FROM T_SA;



create user rental identified by 1111;

grant connect, resource, dba to rental;

GRANT CREATE VIEW TO rental;
GRANT CREATE SYNONYM TO rental;
GRANT CREATE PUBLIC SYNONYM TO rental;

COMMIT;