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
SELECT 
RPAD(ROUND(sal * 0.3333, 2), 7, '0') BONUS
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




SELECT * FROM EMP;