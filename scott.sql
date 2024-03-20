-- ���̺��� ���� ��ȸ 
-- SELECT [ALL|DISTINCT] ��ȸ�� �÷��� | * 
-- FROM  ���̺� �̸�
-- [WHERE �˻�����
-- GROUP BY �׷�ȭ�� �÷���
-- HAVING �˻�����
-- ORDER BY ���ı��� ASC|DESC ]

-- ��� �÷� ��ȸ
SELECT * FROM emp;

-- ���� �÷� ��ȸ
SELECT ename FROM emp;

-- ���� �÷� ��ȸ
SELECT ename , job, sal FROM emp;

SELECT deptno FROM emp;

SELECT ALL deptno FROM emp;
SELECT DISTINCT deptno FROM emp;  -- �ߺ� ����

SELECT  deptno, job FROM emp;
SELECT DISTINCT deptno, job FROM emp;

SELECT ename, job, sal * 12 FROM emp;

-- �÷��� ��Ī
SELECT ename, job,  sal ����,  sal * 12 AS "�� ��"  FROM emp;

-- �÷� ����
SELECT ename, job, 
            ename || job, 
            ename || ' : ' || job  AS "�̸�:����"
FROM emp;

SELECT ename, sal, ename || sal FROM emp;


-- ���̺� ���� Ȯ��
DESC emp;

-- ���̺� ��ȸ
SELECT * FROM tab;


SELECT DISTINCT deptno, job FROM emp ORDER BY deptno ASC; -- ��������
SELECT DISTINCT deptno, job FROM emp ORDER BY deptno DESC; -- ��������

SELECT DISTINCT deptno, job FROM emp ORDER BY deptno, job ASC;  -- ���� �� ��(depno�� �켱��)
SELECT deptno, job FROM emp ORDER BY deptno, job ASC;

SELECT deptno, job, ename, sal FROM emp ORDER BY job, sal ASC;
SELECT deptno, job, ename, sal FROM emp ORDER BY 2;

-- �˻� ���� ����
-- ���ڿ� ��¥�� ��� �ݵ�� '  ' ���
-- �÷��� ���� ��ҹ��� ����

-- deptno�� 10�� ���ڵ� ��ȸ
SELECT * FROM emp WHERE deptno = 10;
SELECT * FROM emp WHERE job = 'MANAGER';
SELECT * FROM emp WHERE job = 'manager';  -- ����, �����Ͱ��� ��ҹ��� ����

-- 81�⵵ ���� �Ի��� ���ڵ常 ��ȸ
SELECT * FROM emp WHERE hiredate < '81/01/01';
SELECT * FROM emp WHERE hiredate < '81-01-01';
SELECT * FROM emp WHERE hiredate < '1981/01/01';
SELECT * FROM emp WHERE hiredate < '810101';
SELECT * FROM emp WHERE hiredate < '19810101';


SELECT * FROM emp WHERE job = 'MANAGER' AND deptno = 10;
SELECT * FROM emp WHERE job = 'MANAGER' OR deptno = 10;

-- 80�⵵ �Ǵ� 82�⵵�� �Ի��� ������� ���ڵ带
-- ���� �Ի��� ����� ������ ��ȸ
SELECT * FROM emp 
               WHERE (hiredate >= '80/01/01' AND hiredate <= '80/12/31')
               OR (hiredate >= '82/01/01' AND hiredate <= '82/12/31')
               ORDER BY hiredate;
               
-- ������ 30000���� ���ڵ��� ename, job, ����, hiredate�� ���� ū ���� ������� ��ȸ
SELECT ename, job, sal * 12 AS ����, hiredate 
FROM emp 
WHERE 40000 > (sal * 12) 
AND (sal * 12) >= 30000 
ORDER BY sal DESC;


-- job�� ANALYST, PRESIDENT, SALESMAN�� ���ڵ带 ���ĺ������� ��ȸ
SELECT * FROM emp 
WHERE job = 'ANALYST' OR job = 'PRESIDENT' OR job = 'SALESMAN'
ORDER BY job ASC;


-- deptno�� 20�� ���ڵ� ��ȸ
SELECT * FROM emp WHERE deptno = 20;
-- deptno�� 20�� �ƴ� ���ڵ� ��ȸ
SELECT * FROM emp WHERE deptno != 20;
SELECT * FROM emp WHERE deptno <> 20;
SELECT * FROM emp WHERE deptno ^= 20;
SELECT * FROM emp WHERE NOT deptno = 20;

SELECT * FROM emp WHERE sal < 2000 ORDER BY sal ASC;

SELECT ename, job, sal, 
            +sal, -sal,  -- ���� ��� ��ȣ ǥ��
            sal+1, sal-1  -- ����, ����, ��� ������
FROM emp WHERE sal < 2000 ORDER BY 3;

-- ename�� A, B, C�� �����ϴ� ���ڵ� ��ȸ
SELECT * FROM emp WHERE ename < 'D';


-- �÷��� BETWEEN ������ AND ū��
-- �̻�/���� ��밡��, �̸�/�ʰ��� ������� ����
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


-- IN (�񱳰� ��� - ����, ��¥, ���� ��� ����)
-- job�� ANALYST, PRESIDENT, SALESMAN�� ���ڵ带 ���ĺ������� ��ȸ
SELECT * FROM emp
WHERE job 
        IN ('ANALYST', 'PRESIDENT', 'SALESMAN')
ORDER BY job;

--job�� SAMESMAN�� �ƴ� ���ڵ� ��ȸ
SELECT * FROM emp
WHERE job 
        NOT IN ('SALESMAN')
ORDER BY job;


-- �÷��� LIKE %  : 0�� �̻��� ���ڿ��� ��ġ
--                 _   : Ư�� ��ġ�� �� ���� ���ڿ� ��ġ�ϴ��� ���� Ȯ��

-- 81�⵵ ���� �Ի��� ���ڵ� ��ȸ use LIKE
SELECT * FROM emp WHERE hiredate LIKE '81%';

SELECT * FROM emp WHERE ename LIKE '_O%';

-- ename�� M���� �����ϰ� R�� ������ ���ڵ� ��ȸ
SELECT * FROM emp WHERE ename LIKE 'M%' AND ename LIKE '%R';

-- ename�� 4���� ���ڵ� �߿� �� ��°�� A�� �ִ� 
SELECT * FROM emp WHERE ename LIKE '_A__';

-- ename�� 4���� ���ڵ� �߿� �� ��°�� A�� ����
SELECT * FROM emp WHERE ename NOT LIKE '_A__' AND ename LIKE '____';


-- job�� R_R�� ���� ���ڵ� ��ȸ
SELECT * FROM emp WHERE job LIKE '%R\_R%' ESCAPE '\';

SELECT * FROM emp WHERE (job LIKE '%MAN%' or job LIKE '%ID%') AND sal > 2000 ;


-- IS NULL, IS NOT NULL
-- comm�� ���� ���ڵ� ��ȸ
SELECT * FROM emp WHERE comm = NULL;  -- �۵� ����
SELECT * FROM emp WHERE comm IS NULL;  -- �۵�
-- comm�� null�� �ƴ� ������� sal�� 10%�� ���ʽ��� ��ȸ
SELECT ename, job, sal, sal * 0.1 as bonus, comm FROM emp WHERE comm IS NOT NULL;


---------------------------------------------------------------------------------------------------------
-- ���� ������ UNION, UNION ALL, MINUS, INTERSECT
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 10;
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 20;
-- ��� ��Ƽ� ���
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno, job, ename FROM emp WHERE job = 'CLERK' AND deptno = 20;

SELECT deptno, job FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno, job FROM emp WHERE job = 'CLERK' AND deptno = 20;

SELECT deptno FROM emp WHERE job = 'CLERK' AND deptno = 10
UNION
SELECT deptno FROM emp WHERE job = 'CLERK' AND deptno = 20;


-- deptno�� 10, 30�� deptno, dname, ename�� �ߺ� ���� ��
-- deptno�� �����Ͽ� ��ȸ
SELECT deptno, '' DNAME, ename FROM emp WHERE deptno IN(10, 30)
UNION
SELECT deptno, dname, '' FROM dept WHERE deptno IN(10, 30)
ORDER BY 1;


-- ����� ������ ã�� MINUS
-- ����� �ҼӵǾ� ���� ���� deptno ��ȸ
SELECT deptno FROM dept 
MINUS
SELECT deptno FROM emp;

-- ���� ������ ã�� MINUS
-- ����� �ҼӵǾ� �ִ� deptno ��ȸ
SELECT deptno FROM dept 
INTERSECT
SELECT deptno FROM emp;


----------------------------------------------------------------------------------------------
-- DML ; Data Manipulation Language ������ ���۾�
-- INSERT, DELETE, UPDATE
-- COMMIT, ROLLBACK ���

-- CTAS ; Create Table As Select
-- ���� ���̺��� �����͸� �̿��Ͽ� ���̺� ����
-- ��, ���������� ������� �ʴ´�

-- 1. ���� ���̺��� �����Ϳ� ������ ��� ����
CREATE TABLE emp2 AS SELECT * FROM emp;

-- 2. ���� ���̺��� ������ ����
CREATE TABLE emp3 AS SELECT * FROM emp WHERE 1=2;

-- 3. ���� ���̺��� Ư�� �÷��鸸 ����
CREATE TABLE t_emp AS SELECT empno, ename, deptno, sal, hiredate FROM emp WHERE 1=2;


-- ���̺� ���ڵ� �߰��ϱ�
-- INSERT INTO ���̺��̸� [(�÷���1, �÷���2, ...)]
-- VALUES (�÷�1�� ��, �÷�2�� ��, ...);

-- t_emp ���̺��� ��� �÷��� ������ �Է� 1
INSERT INTO t_emp (empno, ename, deptno, sal, hiredate)
VALUES (1111, 'KIM', 40, 1000, '2024/03/07'); 

-- t_emp ���̺��� ��� �÷��� ������ �Է� 2
INSERT INTO t_emp 
VALUES (2222, 'Lee', NULL, 2000, SYSDATE); 

-- t_emp ���̺��� ��� �÷��� ������ �Է� 3
INSERT INTO t_emp(empno, ename, hiredate) 
VALUES (3333, 'Han', ''); 

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (4444, 'SAN', '2024-03-10');

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (5555, 'Ohh', '28-02-2024');  -- X

INSERT INTO t_emp(empno, ename, hiredate)
VALUES (5555, 'Ohh', TO_DATE('28-02-2024', 'DD-MM-YYYY'));




-- ���� ������ �Է�
INSERT INTO t_emp(empno, sal)
VALUES (6666, 123456789);  -- larger then specified precision allowed for this column

INSERT INTO t_emp(empno, sal)
VALUES (6666, 1234567);  -- O

INSERT INTO t_emp(empno, sal)
VALUES (6666, 12345);  -- O

INSERT INTO t_emp(empno, sal)
VALUES (6666, 0.12345); -- ��° �ڸ����� ���� (0.12)

INSERT INTO t_emp(empno, sal)
VALUES (6666, 0.12543); -- ��° �ڸ����� ����� �ݿø����� (0.13)

COMMIT;  -- ���� ���� �ݿ��ϱ� (���� �ѹ� �Ұ���)

-- ���̺� ������ �����ϱ� -------------------------------------
-- UPDATE ���̺��̸�
-- SET �÷���1 = ������ ��1, �÷��� 2 = ������ ��2, ...
-- WHERE ��������

-- deptno�� ��� 99�����κ���
UPDATE t_emp SET deptno = 99;

-- deptno�� null�� �����͸� ��� 99������ ����
UPDATE t_emp SET deptno = 99 WHERE deptno IS NULL;

-- deptno�� 99���̰�, sal�� ���� �������� sal�� 100����
UPDATE t_emp SET sal = 100, deptno = 10 WHERE deptno = 99 AND sal IS NULL;



-- ���̺� ������ �����ϱ�
-- DELETE FROM ���̺��̸�
-- WHERE ��������

-- t_emp ���̺��� ��� ������ �����ϱ�
DELETE FROM t_emp;
ROLLBACK;

-- sal�� 1 �̸��� ���ڵ� �����ϱ�
DELETE FROM t_emp WHERE sal < 1;



SELECT * FROM t_emp;


---------------------------------------------------------


SELECT * FROM t_survey WHERE endDate > SYSDATE;

SELECT * FROM t_survey WHERE endDate > SYSDATE AND startDate <= SYSDATE;

-----------------------------------------------------------------
-- ������ �Լ�
-- ����


SELECT * FROM emp;

SELECT ename, INITCAP(ename), LOWER(ename), UPPER('abc') FROM emp;

-- ename�� �� �� ��ҹ��� ���о���  smith�� ���ڵ带 �˻�
SELECT * FROM emp WHERE LOWER(ename) = 'smith';

SELECT * FROM t_emp;
SELECT * FROM t_emp WHERE LOWER(ename) = 'goo' OR UPPER(ename) = 'GOO';
SELECT * FROM t_emp WHERE LOWER(ename) = LOWER('GOO');

SELECT 3 * 4, 'dual table', SYSDATE, SYSTIMESTAMP FROM dual;

SELECT LENGTH('eng'), LENGTH('lionel messi'),LENGTH(12345), LENGTH('�౸��') FROM dual;
-- BYTE�� ���� �ѱ��� �� �뷮�� ũ��
SELECT LENGTHB('eng'), LENGTHB('lionel messi'),LENGTHB(12345), LENGTHB('�౸��') FROM dual;

SELECT * FROM emp WHERE LENGTH(ename) <= 4;


-- �÷� ����
SELECT  ename, job, 
                ename || job,
                CONCAT(ename, job),
                ename || ' : ' || job AS "�̸� : ����",
                CONCAT(ename, CONCAT(' : ', job)) AS "�̸� : ����"
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

-- 4���� �Ի��� ������� ���ڵ� ��ȸ
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(hiredate, INSTR(hiredate, '/') + 2, 1) = '4';

-- �̹� �޿� �Ի��� ������� ���ڵ� ��ȸ
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(hiredate, 4, 2) = SUBSTR(SYSDATE, 4, 2);


SELECT * FROM t_survey;

-- https://www.homepage.com/user/info.html
-- http://127.0.0.1:8090/index.jsp
-- ������ ���� ����
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


-- ���� ���ֱ�
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

-- ���� �����
SELECT 
empno,
LPAD(empno, 7, '*')
FROM emp;

SELECT 
empno,
RPAD(empno, 10, '-')
FROM emp;


-- ����
SELECT 
sal, sal * 0.33333,
ROUND(sal * 0.33333) "NONE",
ROUND(sal * 0.33333, 0) "0",
ROUND(sal * 0.33333, 1) "1",
ROUND(sal * 0.33333, 2) "2",
ROUND(sal * 0.33333, -1) "-1",
ROUND(sal * 0.33333, -2) "-2"
FROM emp;


-- sal�� 0.3333�� ���ʽ��� ����Ͽ�
-- �Ҽ��� ��° �ڸ����� �ݿø��Ͽ� ���, �� ���ʽ��� 1000���� 10000�̸��� ���� ������� �ϰ�
-- ������ �ڸ��� 0���� ǥ��
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

-- empno�� Ȧ���� ������ TEAM_RED, ¦���� ������ TEAM_BLUE�� �����Ͽ�
-- TEAM, EMPNO, ENAME�� ��ȸ. 
--  TEAM�� �������� �������� ����, ���� TEAM�������� �̸��� �������� �������� ����
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

-- ������� 3�� �� ������ �����Ͽ� TEAM`1 ~ TEAM5���� ǥ��
SELECT ROWNUM, ename, CONCAT('TEAM',CEIL(ROWNUM / 3))
FROM emp;



------
-- ��¥


SELECT
hiredate,
hiredate + 100 �Ի�100����,
SYSDATE - hiredate �ټ��ϼ�,
SYSDATE - 1 ����,
SYSDATE ����,
SYSDATE + 1 ����
FROM emp;

-- ��� ����� ename, hiredate, �ټӳ�� ��ȸ
-- �ټӳ���� ������ ������ ����
SELECT
ename, hiredate, FLOOR((SYSDATE - hiredate) / 365) || '��' AS �ټӳ��
FROM emp ORDER BY 2;

SELECT
ename, hiredate, FLOOR((SYSDATE - hiredate) / 365 * 12) || '����' AS �ټӿ���,
TRUNC( MONTHS_BETWEEN(sysdate, hiredate), 2)
FROM emp ORDER BY 2;

SELECT ename, hiredate, ADD_MONTHS(hiredate, 6)
FROM emp;

SELECT 
NEXT_DAY(SYSDATE, '�ݿ���'),
LAST_DAY(SYSDATE)
FROM dual;

SELECT SYSTIMESTAMP,
ROUND(SYSTIMESTAMP),
TRUNC(SYSTIMESTAMP)
FROM dual;




-----------------------------
-- ������ �Լ�
-- ����ȯ
-- NUMBER   TO_CHAR   ->   CHAR   TO_DATE   ->   DATE
--               <-   TO_NUMBER            <- TO_CHAR

SELECT 
1 + '1',  -- ������ ����ȯ
1 + TO_NUMBER('1')  -- ����� ����ȯ
FROM dual;


-- ���ڸ� ���ڷ�
SELECT 
ename, sal * 123.45,
TO_CHAR(sal * 123.45, '999,999.99') "1",
TO_CHAR(sal * 123.45, 'L999,999.99') "2",
TO_CHAR(sal * 123.45, '$999,999.99') "3",
TO_CHAR(sal * 123.45, '$000,000.00') "4"
FROM emp;


-- ���ڸ� ��¥��
SELECT 
TO_DATE ('24-03-01', 'YY-MM-DD'),
TO_DATE ('2024-03-01', 'YYYY-MM-DD')
FROM dual;


-- ��¥�� ���ڷ�
SELECT 
SYSDATE, 
TO_CHAR(SYSDATE, 'YY.MM.DD'),
TO_CHAR(SYSDATE, 'YY"��" MM"��" DD"��"')
FROM dual;



----------------------
-- ������ �Լ�
-- NULL �� �� - NVL, NVL2, NULLIF
SELECT 
ename, comm,
NVL(comm, 100), -- comm�� null�̸� 100���� ����
NVL2(comm, 200, 100) -- comm�� null�̸� 100����, null�� �ƴϸ� 200���� ����
FROM emp;


SELECT 
NULLIF(10, 10),  -- ���� ������ null �� ���
NULLIF(10, 20),  -- ���� �ٸ��� ù��° �� ���
NULLIF(40, 20)
FROM dual;


---------------------
-- ���� ��
-- DECODE - equal �� : ��Ȯ�� ��ġ�ϴ��� ���� ��
-- CASE - range �� : �������� ��

-- deptno�� 10�̸� Team1, 20�̸� Team2, 30�̸� Team3, �������� Free�� ����
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

-- hiredate��
-- 80 ~ 81�⵵ ���̴� Senior,
-- 82 ~ 84�⵵ ���̴� Junior,
-- 85 ~ 89�⵵ ���̴� Newbie,
-- �׿ܴ� Free
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
ename, comm, '���系��'
FROM emp
ORDER BY 2;
-- comm�� 0�̸� ���� ����, 1�̻��̸� ��������, null�̸� �ش���� ����

SELECT
ename, comm,
    CASE  
        WHEN comm = 0 THEN '�������'
        WHEN comm IS NULL THEN '�ش���׾���'
        WHEN comm > 0 THEN '��������'
    END AS ���系��
FROM emp
ORDER BY 2;


--------------------------

-- ������Group �Լ�


-- �հ�� ����
SELECT  SUM(sal), SUM(ALL sal), SUM(DISTINCT sal) FROM emp
UNION ALL
SELECT  COUNT(sal), COUNT(ALL sal), COUNT(DISTINCT sal) FROM emp;

SELECT  COUNT(comm), COUNT(ALL comm), COUNT(DISTINCT comm),
COUNT(*)
FROM emp;

-- t_member�� id�÷��� ���� 'aaaa'�� ���� ���� ��ȸ
SELECT COUNT(*) FROM t_member WHERE id = 'aaaa';

SELECT 
COUNT(id),
CASE  
        WHEN COUNT(id) > 0 THEN '���̵� �����մϴ�.'
        WHEN COUNT(id) < 0 THEN  '���̵� �������� �ʽ��ϴ�.'
END AS ID��ȸ
FROM t_member 
WHERE id = 'aaaa';


-- emp���̺��� MAN�� ���Ե� ����
SELECT COUNT(job) FROM emp WHERE job LIKE '%MAN%';
-- comm�� ���� ������� �� ��ȸ
SELECT COUNT(*) FROM emp WHERE comm IS NULL or comm = 0;


--  ���
SELECT 
AVG(comm) -- null���� ����
FROM EMP;

-- ��ü �ο� ���
SELECT
ROUND(SUM(comm) / COUNT(*), 2) AS ��ü�ο����1,
ROUND(AVG(NVL(comm, 0)), 2) AS ��ü�ο����2
FROM emp;


-- �ִ밪, �ּҰ�, ǥ������, �л�
SELECT 
MAX(sal), MIN(sal), -- MAX, MIN �� �� ���������� �ؼ� ��������
MAX(hiredate), MIN(hiredate),
MAX(ename), MIN(ename),
STDDEV(sal) AS ǥ������,
VARIANCE(sal) AS �л�

FROM emp;

-- sal�� ���� ���� �޴� ����� ename, sal ��ȸ
SELECT MAX(sal) FROM emp;
SELECT
ename, sal
FROM emp
-- WHERE sal = MAX(sal); -- ERROR ; group function is not allowed here 
WHERE sal = 5000;

-- �μ��� sal ���
SELECT deptno, AVG(sal) -- �׷� �Լ��� ������� ��� (ex) AVG)
FROM emp                         -- �׷� �Լ��� �ƴ� �÷��� 
GROUP BY deptno             -- �ݵ�� GROUP BY ���� ���
ORDER BY 1;                    -- SELECT�� ������ ���� �÷��� ��� ����

-- �� �μ��� job�� ���� �� ��� sal
SELECT deptno, job, COUNT(*) AS job_count, AVG(sal) AS avg_salary
FROM emp
GROUP BY deptno, job
ORDER BY 1;

-- �� �μ��� sal�� ����� 2000 �̻��� ���ڵ��� 
-- deptno, �μ��� �ο��� �� ��� sal ��ȸ

SELECT deptno, COUNT(*), AVG(sal)
FROM emp
GROUP BY deptno
HAVING AVG(sal) >= 2000  -- �׷�ȭ ���� ��� �˻������� WHERE�� �ƴ� HAVING�� ���
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
-- �м��Լ�
-- ROLLUP - �־��� �����͵��� �Ұ� ���

-- deptno, job, sal�� �հ�, job�� ����
-- deptno�� ������������ ��ȸ
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

-- CUBE - �Ұ�� ��ü�� �Ѱ� ���
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


-- �� �μ��� �Ŵ��� ���� �����ϴ� �ο� ���� ��ȸ�Ͽ�
-- deptno, mgr, mgr�� ���� �ο� ���� ǥ��
-- ��, �Ŵ����� ���� ���� ����
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


-- �μ���, JOB�� ���� 1
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

-- �μ���, JOB�� ���� 2
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
                                

-- ������ �ǹ� ���̺��� ���� ���·� ��ȸ
SELECT *
FROM T_PIVOT
UNPIVOT (JOB_COUNT
                    FOR JOB IN(CLEARK, SALESMAN, PRESIDENT, MANAGER, ANALYST));
                    


-- LAG : ���� �� �� ��������
-- LEAD : ���� �� ��������
SELECT 
ENAME, 
LAG(SAL, 1, 0) OVER(ORDER BY SAL) LAG,
SAL,
LEAD(SAL, 1, 0) OVER(ORDER BY SAL) LEAD
FROM EMP
ORDER BY 2;

-- ���� �Ǹŷ�, ���� �Ǹŷ�, ���ϰ� ���� �� ��ȸ
SELECT 
HIREDATE "������", 
LAG(SAL, 1, 0) OVER(ORDER BY HIREDATE) "���� �Ǹŷ�",
SAL "���� �Ǹŷ�",
SAL - (LAG(SAL, 1, 0) OVER(ORDER BY HIREDATE)) AS "���� ���" 
FROM EMP;


-- ���� ���
-- RANK, RANK OVER, DENSE_RANK, ROW_NUMBER

-- ��ü ENAME �߿��� JAMES�� ���� ��ȸ
SELECT RANK('JAMES') WITHIN GROUP (ORDER BY ENAME) "RANK"
FROM EMP;


-- JOB�� CLEARK�� ���ڵ� �߿��� JAMES�� ���� ��ȸ
SELECT 
RANK('JAMES') WITHIN GROUP (ORDER BY ENAME) "RANK"
FROM EMP
WHERE JOB = 'CLERK';

-- ��ü ������� ENAME, SAL ���� ��ȸ
SELECT ENAME, SAL, 
RANK() OVER (ORDER BY SAL) "����",
RANK() OVER (ORDER BY SAL ASC) "ASC",
RANK() OVER (ORDER BY SAL DESC) "DESC",
DENSE_RANK() OVER (ORDER BY SAL DESC) "DENSE", -- ���� ���� ���� ó��
ROW_NUMBER() OVER (ORDER BY SAL DESC) "���� �ߺ� ����"
FROM EMP;

-- DEPTNO�� 30�� �μ��� SAL ������ ��ȸ�Ͽ�
-- DEPTNO, ENAME, JOB, SAL ������ ǥ��
-- ���� ���� �����Ͽ� ó��
SELECT DEPTNO, ENAME, JOB, SAL,
DENSE_RANK() OVER(ORDER BY SAL DESC) "SAL RANK"
FROM EMP
WHERE DEPTNO = 30;


-- ���� �հ� ���ϱ�
-- SUM
SELECT 
DEPTNO, HIREDATE "������",
SAL "���� �Ǹŷ�",
SUM(SAL) OVER(PARTITION BY DEPTNO 
                             ORDER BY HIREDATE, SAL) "���� �Ǹŷ�"
FROM EMP
ORDER BY 1;

-------------------------------------------------
-- JOIN
-- ���� ���̺� ����� �ִ� �����͵��� �����ؼ� ������
-- ���� ������ : ���� ����
-- ����            : ���� ����


-- ����Ŭ ����
-- SELECT  ���̺��̸�.�÷��� 
-- FROM     ���̺�1�̸�, ���̺�2�̸� 
-- WHERE   ���̺�1�̸�.�÷��� = ���̺�2�̸�.�÷���  -- << ��������
-- AND        -- << �˻� ����


-- ANSI ����
-- SELECT  ���̺��̸�.�÷��� 
-- FROM     ���̺�1�̸� [INDER | OUTER] JOIN  ���̺�2�̸� 
-- ON          ���̺�1�̸�.�÷��� = ���̺�2�̸�.�÷���  -- << ��������
-- WHERE   -- << �˻� ����


-- INNER JOIN -  Cartesian Product / CROSS JOIN / īƼ�� ��
-- - ���� ������ �������� ���
-- - �ش� ���ο� �����ϴ� ��� ��� ���� ���
-- - ���� ��� : �÷��� �� = ���̺�1 + ���̺�2
--                      ���� �� = ���̺� * ���̺�2

SELECT * FROM EMP, DEPT;  -- ORACLE JOIN
SELECT * FROM EMP CROSS JOIN DEPT; -- ANSI JOIN

--  ERROR column ambiguously defined
-- DEPTNO�� �ߺ��ż� ����
SELECT DEPTNO, DNAME FROM EMP, DEPT;  -- ORACLE JOIN
SELECT * FROM EMP CROSS JOIN DEPT; -- ANSI JOIN

SELECT EMP.DEPTNO, DNAME FROM EMP, DEPT;
SELECT E.DEPTNO, DNAME FROM EMP E CROSS JOIN DEPT D;

-- INNER JOIN - EQUI JOIN ���� ����
-- ���� ���̺��� �����͸� ������ ��
-- ���� ������ �˻��Ͽ�
-- ���� ���� ���� �����͸� ���� ���̺��� ���� ��

-- deptno, dname, ename�� ��ȸ
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

-- ����� �μ��̸�, ����̸�, ����  (hr)
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



-- INNDER JOIN - NON EQUIT JOIN �񵿵� ���� (scott)
-- - '='�� �ƴ� ������ ���
-- - ��Ȯ�� ��ġ���� �ʴ� ������ ����

-- ��� sal�� grade�� ��ȸ
SELECT ename, sal, grade, losal, hisal -- ORACLE
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal;
-- ���� �Ʒ��� ���� (��ġ�� �� ������ ���� ��Ī�� ���� �ʾƵ� �ȴ�.)
SELECT e.ename, e.sal, s.grade, s.losal, s.hisal
FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal;

SELECT ename, sal, grade, losal, hisal -- ANSI
FROM emp JOIN salgrade
ON sal BETWEEN losal AND hisal;

-- �� grade�� ��� ���� ��ȸ�Ͽ� grade, �Ұ�� ǥ��
SELECT grade || 'ȣ��' AS ȣ��, LPAD(COUNT(grade), 2, 0) AS �Ұ�
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal
GROUP BY grade
ORDER BY 1;

SELECT grade || 'ȣ��' , LPAD(COUNT(grade), 2, 0)
FROM emp JOIN salgrade
ON sal BETWEEN losal AND hisal
GROUP BY grade
ORDER BY 1;

-- �� grade�� ��� ���� ��ȸ�Ͽ� grade, �Ұ�� ǥ��
-- ��, �Ұ谡 3�� �̻��� �����͸� ������� �Ͽ� ó��
SELECT grade || 'ȣ��' AS ȣ��, LPAD(COUNT(grade), 2, 0) AS �Ұ�
FROM emp, salgrade
WHERE sal BETWEEN losal AND hisal
HAVING COUNT(grade) >= 3  -- GROUP�Լ��� ���� ������ WHERE ��� HAVING ���
GROUP BY grade
ORDER BY 1;


-- INNSER JOIN - SELF JOIN
--  ���ϴ� �����Ͱ� �� ���̺� ��� ����ִ� ���
--  ���� ���̺��� ��ġ �� ���� ���̺��� ��ó�� ����Ͽ� ����


-- ��� ����� ename, ename, mgr, �Ŵ����� �̸��� ��ȸ�Ͽ� ǥ��
SELECT e.empno, e.ename, e.mgr, m.ename AS �Ŵ����̸�
FROM emp e, emp m
WHERE e.mgr = m.empno
ORDER BY 2;



-- OUTER JOIN
-- - INNER JOIN ������ �������� �ʴ� ��쿡�� ��� ���� ���
-- - �� �� ���̺��� �����Ͱ� �ְ� �ٸ� ���̺��� ���� ���
--   �����Ͱ� �ִ� ���� ������ �켱�Ͽ� ���
-- - OUTER Ű���� ���� �� INNER ���� ����
--   LEFT [OUTER] JOIN  ���� �������� ���
--   RIGHT [OUTER] JOIN ������ �������� ���
-- - ����Ŭ�� ��� �ڷᰡ ������ �ʿ� (+) ǥ��
--   FULL [OUTER] JOIN ���� ��� (ANSI�� ����)
--   ORACLE�� �������� ����, ��� UNION ���
--   Ǯ��ĵ���� ���ɿ� �ǿ���



SELECT e.empno, e.ename, e.mgr, m.ename AS �Ŵ����̸� -- ANSI
FROM emp e JOIN emp m
ON e.mgr = m.empno
ORDER BY 2;

SELECT e.empno, e.ename, e.mgr, m.ename AS �Ŵ����̸� -- ANSI
FROM emp e LEFT OUTER JOIN emp m
ON e.mgr = m.empno
ORDER BY 2;

SELECT e.empno, e.ename, e.mgr, m.ename AS �Ŵ����̸� -- ORACLE
FROM emp e, emp m
WHERE e.mgr = m.empno (+)  -- ������ �ʿ� (+)
ORDER BY 2;


-- ��� ����� deptno, dname, ename�� ��ȸ
-- ��, ����� ���� �μ��� ����
SELECT d.deptno, d.dname, e.ename
FROM dept d, emp e
WHERE d.deptno = e.deptno (+)
ORDER BY 1;

SELECT d.deptno, d.dname, e.ename
FROM emp e RIGHT OUTER JOIN dept d
ON d.deptno = e.deptno
ORDER BY 1;

-- ��� ����� deptno, dname, empno, ename ��ȸ
-- ��, ������ ���� ��� �� ����� ���� �μ��� ����
SELECT d.deptno, d.dname, empno, ename
FROM emp e FULL OUTER JOIN dept d
ON d.deptno = e.deptno
ORDER BY 1;

-- �� �μ����� sal ���� ����Ͽ� �������� ����
-- deptno, dname, �޿��հ�(�� �ڸ����� , ǥ��)
SELECT  -- LPAD(NVL(e.deptno, 0), 2, 0) DEPTNO, 
        TO_CHAR(NVL(e.deptno, 0), '00') DEPTNO, 
        NVL(d.dname, 'NONE') DNAME, 
        NVL(TO_CHAR(SUM(e.sal), '999,999'), 0) �޿��հ�
FROM emp e, dept d
WHERE e.deptno = d.deptno (+)
GROUP BY e.deptno, d.dname
ORDER BY 1 DESC;

SELECT LPAD(NVL(e.deptno, 0), 2, 0) DEPTNO, d.dname, NVL(TO_CHAR(SUM(e.sal), '999,999'), 0) �޿��հ�
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno (+)
GROUP BY e.deptno, d.dname
ORDER BY 1 DESC;


-------------------------------------------------------------
--  DDL ; Data Definition Language
-- - CREATE, ALTER, TRUNCATE, DROP
-- - ���� �� �ڵ� COMMIT �Ǿ� ROLLBACK �Ұ���
-- ��� ��Ģ
-- - ���̺�� �÷����� �ݵ�� ���ڷ� ����
-- - ������, ����, _, &, # �� ����
-- - �ִ� 30BYTE���� ���
-- - ����Ŭ ������ ��� �Ұ�

-- ���̺� ����
-- CREATE TABLE [��Ű��.]���̺�� (
--      �÷���1 ������Ÿ�� [DEFAULT ����] [�÷� ���� ���� ����],
--      ...
--  [���̺� ���� ���� ����]
-- );

-- �Խ��� ���̺�
-- �⺻ ���� �����Ͽ� ���̺� ����
CREATE TABLE t_board (
    no  NUMBER(10) DEFAULT 0,
    title VARCHAR2(100),
    content VARCHAR2(4000),
    writer VARCHAR2(50) DEFAULT 'guest',
    regdate DATE    DEFAULT SYSDATE
);


-- t_board ���̺� title�� test, content�� test content�� ���ڵ� �߰�
INSERT INTO t_board(title, content) VALUES ('test', 'test_content');

SELECT * FROM t_board;


-- ALTER
-- - ���� ��ü ����
-- - ���̺��� ���� ����
--      �÷� �߰�, ����
--      �÷���, ������ Ÿ��, ���� ����

-- �÷� �߰�
ALTER TABLE t_emp
ADD work VARCHAR2(20);

-- �÷��� ����
ALTER TABLE t_emp
RENAME COLUMN work TO job;

-- ���̺� �̸� ����
RENAME t_board TO board;

-- �÷� ���� ����
ALTER TABLE t_emp
MODIFY job VARCHAR2(10);

-- �÷� Ÿ�� ����
ALTER TABLE t_emp
MODIFY job NUMBER;

-- �÷� ����
ALTER TABLE t_emp
DROP COLUMN job;

DESC t_emp;

-- ���̺� ����
DROP TABLE board;  -- ���������� ������ (���� ����)

DROP TABLE board PURGE;  -- ���������� �Ⱥ����� ���� ���� (���� �Ұ�)

-- ������ ���̺� ����
FLASHBACK TABLE board TO BEFORE DROP;


-- emp ���̺��� �����Ͽ� t_temp ���̺� ����
-- (�����Ϳ� ���� ���)
CREATE TABLE t_temp AS SELECT * FROM emp;

DELETE FROM t_temp;

ROLLBACK;       -- �ǵ����� x -> ��� ���Ϸ� ����

TRUNCATE TABLE t_temp; -- ������, �ε��� ����

SELECT * FROM t_temp;


RENAME t_temp TO t_readonly;

-- �б� ���� ���̺�� ����
ALTER TABLE t_readonly READ ONLY;

-- �б�, ���� ���� ���̺�� ����
ALTER TABLE t_readonly READ WRITE;

INSERT INTO t_readonly (empno, ename, sal) VALUES(1, 'Lee', 100);



-- ���� �÷� ���̺�
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

-- ���������� �̿��Ͽ� ���� �� �Է�
INSERT INTO t_emp
    SELECT  empno, ename, deptno, sal, hiredate 
    FROM    emp
    WHERE   deptno = 10;

INSERT ALL
    INTO t_emp(deptno) VALUES(50)
    INTO t_pivot(deptno) VALUES(50)
    SELECT * FROM dual;
    
-- emp ���̺��� ������ �߿���
-- comm�� null�� �ƴ� �������� ename, job, sal, comm�� bonus���̺��
-- comm�� null�� �������� ename, deptno, sal�� t_emp���̺� �Է�
INSERT ALL
    WHEN comm IS NOT NULL THEN
        INTO bonus VALUES(ename, job, sal, comm)
    WHEN comm IS NULL AND deptno = 20 THEN
        INTO t_emp (ename, deptno, sal) 
        VALUES (ename, deptno, sal)
    SELECT  ename, deptno, job, sal, comm
    FROM    emp;


-- ���� ������ �̿��Ͽ� ���� �÷��� �� ���� ����
UPDATE t_emp
SET     empno = 1112,
        (deptno, sal) = (SELECT deptno, sal FROM t_emp WHERE ename='Kim')
WHERE ename = 'FORD';

UPDATE t_emp
SET sal = sal * 1.1
WHERE deptno = (SELECT deptno FROM t_emp WHERE ename = 'Lee');

-- �μ��� ��ġ�� DALLAS�� ����� �޿��� �� �� �λ� ������Ʈ
-- sal�� ���� ��� 1000���� �����Ͽ� ó��
UPDATE t_emp
SET sal = NVL(sal, 1000) * 2
WHERE deptno = (SELECT deptno FROM dept WHERE loc = 'DALLAS');

-- dept = 20
-- sal = between 3
-- ename�� ������ ���ڵ� �����
DELETE t_emp 
WHERE ename IN (SELECT ename 
                FROM t_emp, salgrade 
                WHERE sal BETWEEN losal AND hisal 
                AND grade = 3 
                AND deptno = 20);



---------------------------------------------------
-- MERGE
-- ���̺� ����

-- salgrade ���̺��� ������ �����Ͽ� t_merge ���̺� ����
CREATE TABLE t_merge AS SELECT * FROM salgrade WHERE 1=2;

-- salgrade ���̺��� grade�� 3������ �����͵鸸 �����Ͽ� salgrade2 ���̺� ����
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
-- CONSTRAINTS ���� ����
-- PRIMARY KEY : Null x, �ߺ� �� x >> NOT NULL + QUNIQUE
-- FOREIGN KEY : �ٸ� ���̺��� PRIMARY KEY �Ǵ� UNIQUE�� ������ Į���� ����
--               PARENT TABLE - ���� ���� ���̺� / CHILD TABLE - ������ �����޴� ���̺�
-- UNIQUE : �ߺ� �� X
-- CHECK : ���� ������ ������ ���� ������ ������ �����Ͽ� ������ ���� ���
-- NOT NULL : NULL �Է� X. �÷� �����θ� ���� ����




SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM USER_CONS_COLUMNS;


-- ���̺� ���� �� ���� ���� ���� - ���� ���� �̸� ����
CREATE TABLE member (
    userid      VARCHAR2(20)    PRIMARY KEY, 
    empno       NUMBER          REFERENCES emp(empno),
    usernm      VARCHAR2(20)    NOT NULL,
    userpw      VARCHAR2(20)    NOT NULL,
    email       VARCHAR2(50)    UNIQUE,
    gender      CHAR(1),        CHECK(gender IN ('M', 'F')),
    regdate     DATE            DEFAULT SYSDATE
);
    
-- �θ� ���̺� �׽�Ʈ ������ �߰�
INSERT INTO emp(empno, ename) VALUES (1000, 'Woo');
INSERT INTO emp(empno, ename) VALUES (2000, 'Goo');
INSERT INTO emp(empno, ename) VALUES (3000, 'Hoo');

-- member ���̺� userid�� abc�� ���ڵ� �߰�
INSERT INTO member(userid) VALUES ('abc');  -- X (NOT NULL �� �ο찪 �ȳ־���)
INSERT INTO member(userid, usernm, userpw) VALUES ('abc', 'Woo', '1111'); -- O
INSERT INTO member(userid, usernm, userpw) VALUES ('abc', 'Woo', '1111'); -- O (UNIQUE �������� ����)
INSERT INTO member(userid, usernm, userpw, empno) VALUES ('aaa', 'Goo', '1111', 2222); -- X (emp�� 2222�� ����)
INSERT INTO member(userid, usernm, userpw, empno) VALUES ('aaa', 'Goo', '1111', 2000); -- O

DELETE FROM emp WHERE empno = 2000; -- X (CHILDE ���ڵ尡 �־ ���� �Ұ���)
DELETE FROM member WHERE userid = 'aaa';
DELETE FROM emp WHERE empno = 2000; -- O

INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'Male'); -- X
INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'X'); -- X
INSERT INTO member(userid, usernm, userpw, gender) VALUES('aaa', 'Goo', '1111', 'F'); -- O

INSERT INTO member(userid, usernm, userpw, email) VALUES('bbb', 'Goo', '1111', 'g@g.com'); -- O
INSERT INTO member(userid, usernm, userpw, email) VALUES('ccc', 'Goo', '1111', 'g@g.com'); -- O

DROP TABLE member;


-- ���̺� ���� �� ���� ���� ���� - ���� ���� �̸� ���
CREATE TABLE member (
    userid      VARCHAR2(20)    CONSTRAINT member_pk  PRIMARY KEY, 
    empno       NUMBER          CONSTRAINT member_fk  REFERENCES emp(empno)   ON DELETE CASCADE, -- �θ� ���ڵ�� �Բ� ����
    usernm      VARCHAR2(20)    ,
    userpw      VARCHAR2(20)    CONSTRAINT member_pw_chk    CHECK(LENGTH(userpw) >= 4), -- ���� 4�� �̻� ����
    email       VARCHAR2(50)    CONSTRAINT  member_em_nn NOT NULL 
                                CONSTRAINT  member_em_uq UNIQUE,
    gender      CHAR(1),        CHECK(gender IN ('M', 'F')),
    regdate     DATE            DEFAULT SYSDATE
);

INSERT INTO member(userid, email, empno) VALUES('aaa', 'aaa@aaa.com', 3000);
INSERT INTO member(userid, email, empno) VALUES('bbb', 'aaa@aaa.com', '123');
INSERT INTO member(userid, email, userpw) VALUES('bbb', 'bbb@bbb.com', '1234');

DELETE FROM emp WHERE empno = 3000; -- O (ON DELETE CASCADE�� �߱� ����)


-- ���̺� ���� �� ���� ���� ����
CREATE TABLE member (
    userid      VARCHAR2(20), 
    empno       NUMBER,
    usernm      VARCHAR2(20) NOT NULL,
    userpw      VARCHAR2(20),
    email       VARCHAR2(50),
    gender      CHAR(1),
    regdate     DATE    DEFAULT SYSDATE,
    
    CONSTRAINT member_pk PRIMARY KEY(userid),
    CONSTRAINT member_fk FOREIGN KEY(empno) REFERENCES emp(empno) ON DELETE SET NULL, -- �θ� ���ڵ� �輼 �� NULL�� ����
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

-- ���� ���̺� ���� ���� �߰� - PK
ALTER TABLE t_qna ADD CONSTRAINT t_qna_pk PRIMARY KEY(qno);  -- X (�ߺ� ������ ����)
UPDATE t_qna SET qno = 2 WHERE qid = 'bbb'; -- �ߺ� ����

ALTER TABLE t_qna ADD CONSTRAINT t_qna_pk PRIMARY KEY(qno);  -- O 

ALTER TABLE t_qna MODIFY qno CONSTRAINT t_qna_pk PRIMARY KEY;

-- ���� ���̺� ���� ���� �߰� - FK
ALTER TABLE t_qna
ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);  -- X (parent keys not found, member�� id�� �ȸ���)

-- t_qna���̺��� qid�� ��� aaa�� ����
UPDATE t_qna SET qid = 'aaaa';

ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);

ALTER TABLE t_qna MODIFY qid CONSTRAINT t_qna_fk REFERENCES t_member(id) ON DELETE SET NULL;

-- question �÷��� NULL�� ������� �ʵ��� ���� ���� �߰�
ALTER TABLE t_qna
ADD CONSTRAINT t_qna_nn NOT NULL(question); -- X (NOT NULL�� �߰��� �ƴ϶� �����ϴ� ��)

ALTER TABLE t_qna MODIFY(question CONSTRAINT t_qna_nn NOT NULL); -- O


-- question �÷��� �ߺ��� ������� �ʵ��� ���� ���� �߰�
ALTER TABLE t_qna ADD UNIQUE(question);

ALTER TABLE t_qna MODIFY question UNIQUE;

ALTER TABLE t_qna MODIFY qdate Date DEFAULT SYSDATE;

ALTER TABLE t_qna MODIFY (hitno NUMBER DEFAULT 0);


-- ���� ���� ����
ALTER TABLE t_qna DROP CONSTRAINT t_qna_pk;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_fk;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_nn;
ALTER TABLE t_qna DROP CONSTRAINT t_qna_un;


-- ���� ���� �̸� ����
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
DROP TABLE t_member;  -- X (�ڽ� ���̺��� ���� ��)
ALTER TABLE t_qna DROP CONSTRAINT T_QNA_FK;

-- t_qna ���̺� qno�� t_member ���̺��� id�� �����ϵ��� 
-- �ܷ�Ű t_qna_fk �߰�
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);

DROP TABLE t_member;

DROP TABLE t_member CASCADE CONSTRAINTS; -- �θ� ���̺� ���� ��, �ڽ� ���̺��� FK ����

-- t_member ���̺��� ������ �����͸� �����Ͽ� t_member2 ���̺�� ����
CREATE TABLE t_member2 AS SELECT * FROM t_member;


-- t_member2 ���̺��� �̸��� t_member�� ����
-- t_member2 ���̺��� id �÷��� �⺻Ű�� ����
-- t_qna ���̺��� qno �÷��� t_member ���̺��� id�� �����ϵ��� ����
-- t_qna ���̺��� �����ϰ� �ִ� id ������ t_member ���̺� ���ڵ� �ϳ� �߰�
-- t_member ���̺��� ������ �����͸� �����Ͽ� t_member3 ���̺�� ����
ALTER TABLE t_member2 RENAME TO t_member;
ALTER TABLE t_member MODIFY(id PRIMARY KEY);
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);
INSERT INTO t_member(id, pw, name, email) VALUES ('aaaa', '1111', 'userA', 'a@a.com');
CREATE TABLE t_member3 AS SELECT * FROM t_member;
SELECT * FROM t_member;

-- FK�� �����Ǵ� �÷� ����
ALTER TABLE t_member DROP COLUMN id;  -- X (����)
ALTER TABLE t_member DROP COLUMN id CASCADE CONSTRAINTS; -- O

-- t_member ���̺� id �÷� �߰� �� pk ����
-- t_qna ���̺��� qid �÷��� t_member ���̺��� id�� �����ϵ��� ����
ALTER TABLE t_member ADD id VARCHAR2(20);ww
ALTER TABLE t_member ADD PRIMARY KEY(id);
ALTER TABLE t_qna ADD CONSTRAINT t_qna_fk FOREIGN KEY(qid) REFERENCES t_member(id);


------------------------------------------------------------------------
-- ���� ���� Ȱ��ȭ �� ��Ȱ��ȭ
INSERT INTO t_qna(qno, question, qid, qdate, hitno)
VALUES (3, 'question????????', 'bbb', SYSDATE, 0); -- X (�������� ����Ǿ� �ִ� t_member �θ� ���̺� ���� ���̵��)

ALTER TABLE t_qna DISABLE NOVALIDATE CONSTRAINT T_QNA_FK;  -- FK ��Ȱ��ȭ

ALTER TABLE t_qna ENABLE NOVALIDATE CONSTRAINT T_QNA_FK;  -- FK Ȱ��ȭ
    -- ���� �߰��Ǵ� �����͵���� ���� ���� �˻�
    
INSERT INTO t_qna(qno, question, qid, qdate, hitno)
VALUES (4, 'question????????', 'bbb', SYSDATE, 0); -- X

UPDATE t_qna SET qid = 'aaaa' WHERE qno = 3;

ALTER TABLE t_qna ENABLE VALIDATE CONSTRAINT T_QNA_FK;
    -- ��� ������ ���� ���� �˻�


ALTER TABLE t_qna DISABLE VALIDATE CONSTRAINT T_QNA_FK;  -- read only / DML �Ұ���

INSERT INTO t_qna(qno, question, qid, qdate, hitno) VALUES (3, 'question????????', 'bbb', SYSDATE, 0); -- X


SELECT * FROM USER_CONS_COLUMNS WHERE table_name = 'T_QNA';
SELECT * FROM t_qna;
SELECT * FROM t_member;

-------------------------------------------------------------
ALTER TABLE t_survey_attend ADD FOREIGN KEY(id) REFERENCES t_member(id);
-------------------------------------------------------------






--------------------------------------------------------------
-- �ε���
--  - � �����Ͱ� ��� �ִ��� �˷��ֱ� ���� �÷��� ���� �����ϴ� ����Ŭ ��ü
--  - ���ϴ� �����͸� ���� ã�� ���� ���
--  - �⺻ Ű�� ���� Ű�� ������ ���Ἲ Ȯ�� �� ���� ��ȸ�� �������� ����Ŭ ���ο��� �ڵ����� �ε��� ����
--  - ����Ŭ �ε����� ���������� B* Ʈ�� �������� ����
--  - �⺻������ ������ ������� ���


-- �ε��� ���� ����
--  - ��ü ���̺� ��ĵ -> ���� -> Block ���


-- �ε��� ����
--  - �ε����� ���� �߰����� ���� �ʿ�
--  - �ε��� ������ �ð� �ҿ�
--  - �������� ����(DML)�� ���� �Ͼ�� ��쿡�� ������ ���� ����


-- �ε��� ��� O
--  - ���̺� ���� ���� ���� ��
--  - WHERE ���� �ش� �÷��� ���� ���� ��
--  - �˻� ����� ��ü �������� 2% ~ 4% ������ ��
--  - JOIN�� ���� ���Ǵ� �÷��̳� NULL ���� �÷��� ���� ��


-- �ε��� ��� X
--  - ���̺��� ���� ���� ���� ��
--  - WHERE ���� �ش� �÷��� ���� ������ ���� ��
--  - �˻� ����� ��ü �������� 10% ~ 15% ������ ��
--  - DML�� ���� ���� ��


-- ROWID
--  - ����Ŭ�� ��� �����Ͱ� ���� ������ �ּ�
--  - �����͸� ã�ư� �� �ʿ�
--  - ROWID������ ��Ƽ� ���� �ִ� ���� �ε���
SELECT ROWID, ROWNUM, ename FROM t_emp;


-- INDEX ���� REBUILD
--  - DML�� ���� ��� �ε����� ������ �ֱ������� �Ͼ ����ȭ(fragmentation) �߻�
--   -> ������ �������� �ε��� �� �ڸ��� ��� �Ǿ� ���� ���Ϸ� �̾����Ƿ� �ε����� �ٽ� ��������� ��




SELECT * FROM USER_INDEXES;     --������� ��� �ε��� ��ȸ
SELECT * FROM USER_IND_COLUMNS; --�ε����� ������ �÷� ��ȸ


-- UNIQUE INDEX
--  - �⺻ Ű�� ���� Űó�� ������ ���� ���� �÷��� ���ؼ� ����
--  - �ε��� ���� Ű ���� �ߺ� ������ X
--  - �ӵ��� ����
CREATE UNIQUE INDEX t_emp_idx
ON                  t_emp(empno);

DROP INDEX t_emp_idx;



-- NON UNIQUE INDEX
--  - �ߺ� �����͸� ���� �÷��� ����
CREATE INDEX t_emp_idx
ON           t_emp(empno);  -- �⺻�� ��������

DROP INDEX t_emp_idx_asc;
SELECT * FROM t_emp;

CREATE INDEX t_emp_idx_desc
ON           t_emp(empno DESC);  -- �������� �ε���

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

-- ����Ŭ ��Ʈ : 
--  - �ε����� ���������� �а� �ϰų� �ؿ������� �а� ����� ����
--  - ������ ���� �ʰ� ���� ȿ���� ���ų� �ִ�/�ּҰ��� ���� �� �ִ�.
-- SELECT /*INDEX(���̺��|���̺�Ī �ε����̸�*/ * FROM ~~~
-- SELECT /*INDEX_DESC(���̺��|���̺�Ī �ε����̸�*/ * FROM ~~~
SELECT /*+INDEX_DESC(t_survey t_survey_pk)*/ * FROM t_survey WHERE sno <= 3;


ALTER INDEX t_survey_pk_desc REBUILD;


----------------------------------------------------------------------------------
-- VIEW
--  - �������� ���̺� �ٰ��� ������ ���� ���̺�
--  - �������� �����ʹ� ������ 
--  - ���� ���̺��� ����ϴ� �Ͱ� �����ϰ� ��� ����
--  - �⺻ ���̺��� �Ļ��� ��ü��,
--  - �⺻ ���̺� ���� �ϳ��� ������

--  - SELECT���� �Ź� �Է����� �ʾƵ� ��
--  - ���� ������ ��ȣ �� �Ϻ� ������ ���� ���� ����

--  - DML ��� ����
--  -  �⺻ ���̺��� �����
--  - �ֿ� �뵵�� ��ȸ

SELECT * FROM USER_SYS_PRIVS;

---------------------------- sys-----------------------------
GRANT CREATE VIEW TO SCOTT;  -- ���� �ο�
-------------------------------------------------------------

-- �� ����
CREATE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99);

SELECT * FROM USER_VIEWS; -- �����ϰ� �ִ� �� ��ȸ
SELECT * FROM V_EMP;  -- ������ �� ��ȸ

UPDATE V_EMP SET SAL = 9000 WHERE ENAME = 'Goo';   -- �信�� DML ���� (���� ���̺� ����)
ROLLBACK;



-- �� ����� X (���� �̸� ����)
CREATE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 40);

-- ���� �̸� �� �����
CREATE OR REPLACE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 40);

-- �� ���� ����
-- WITH CHECK OPTION�� ����ϸ� �信 ���� INSERT �Ǵ� UPDATE ������ 
-- �� ���� ������ �����ϴ� ��� ����Ŭ �����ͺ��̽����� ������ �߻���Ų��. 
-- �̸� ���� ���� ������ ���Ἲ�� ������ �� �ִ�.
CREATE OR REPLACE VIEW V_EMP
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99)
WITH CHECK OPTION; 

ROLLBACK;

UPDATE V_EMP SET DEPTNO = 90 WHERE ENAME ='Goo'; -- X
UPDATE T_EMP SET DEPTNO = 90 WHERE ENAME ='Goo'; -- O


-- �б� ���� �� (SELECT�� ������ READ ONLY VIEW)
CREATE OR REPLACE VIEW V_EMP(�μ���ȣ, ����̸�, �޿�)
AS (SELECT DEPTNO, ENAME, SAL
FROM T_EMP
WHERE DEPTNO = 99)
WITH READ ONLY;


SELECT * FROM V_EMP;
SELECT * FROM T_EMP;


-- �μ����� ��� �޿���, �޿� �Ѿ�
-- DEPTNO, DNAME, ��� �޿�, �޿� �Ѿ����� ǥ��
-- ��, DNAME ���� �����͵� �����ϰ� 
-- DNAME�� - �� ǥ��

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


-- FINANCE �μ� �Ҽ� ������� �μ��̸�, ����̸�, �޿�, ����, �μ���ġ ��ȸ
SELECT D.DEPARTMENT_NAME, E.first_name, E.SALARY, J.JOB_TITLE, L.CITY
FROM DEPARTMENTS D, EMPLOYEES E, JOBS J, LOCATIONS L
WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID
    AND E.JOB_ID = J.JOB_ID 
    AND D.LOCATION_ID = L.LOCATION_ID 
    AND D.DEPARTMENT_NAME = 'Finance';
    

CREATE OR REPLACE VIEW V_EMP_FINCANCE(�μ��̸�, ����̸�, �޿�, ����, �μ���ġ)
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
--  - CREATE VIEW�� ������ �䰡 �ƴ϶� ���� ������ �ӽ÷� ������ ��
--  - FROM ���� ���������� ���


-- DEPTNO�� 99�� ���ڵ� ��ȸ (V_EMP �� ���)
SELECT * FROM ( SELECT DEPTNO, ENAME, SAL
                FROM T_EMP
                WHERE DEPTNO = 99);            
                
SELECT * FROM T_EMP;

-- JOB�� SALESMAN�� ������� JOB, ENAME, DNAME�� �ζ��� �並 �̿��Ͽ� ��ȸ
SELECT E.JOB, E.ENAME, D.DNAME 
FROM (SELECT * FROM EMP WHERE JOB = 'SALESMAN') E , DEPT D
WHERE E.DEPTNO = D.DEPTNO;




-- �� ����
DROP VIEW V_EMP;
DROP VIEW V_DEPT_SAL;



SELECT ROWNUM, ENAME, HIREDATE FROM EMP ORDER BY ENAME;


SELECT ROWNUM NO , ENAME, HIREDATE 
FROM ( SELECT * FROM EMP ORDER BY ENAME);    

-- �ζ��κ並 �̿��Ͽ� SAL���� ������ ���� 5�� ��ȸ�Ͽ�
-- RANKING, ENAME, SAL ǥ��. ��, SAL�� ���� �����ʹ� ����
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
-- ������
SELECT ROWNUM NO, ENAME, SAL 
FROM (SELECT  * FROM EMP WHERE SAL IS NOT NULL ORDER BY SAL DESC)
WHERE CEIL(ROWNUM / 3) > 1;

 

SELECT NO, ENAME, HIREDATE
FROM (SELECT ROWNUM AS NO, E.* -- ENAME, HIREDATE 
      FROM (SELECT * FROM EMP ORDER BY HIREDATE ASC) E)
WHERE NO BETWEEN (5 * 3) - 2 AND 5 * 3;



-----------------------------------------------------------------
--  SUB QUERY
--  - �ϳ��� ���� �ȿ� ���ԵǾ� �ִ� ������
--  - ���� ���� ���� ���Ե� ������
--  - WHERE ���� ���ǹ��� ���


-- ���� �� ���� ����
--  - ���� ���� ����� �ϳ��� �ุ�� ��ȯ
--  - =, >, >=, <=, <, != TKDYD

-- JAMES�� ���� �μ����� ���ϴ� ������� DEPTNO, ENAME
SELECT DEPTNO, ENAME FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'JAMES');

-- ��� SAL �̻��� �޴� ������� �� ��ȸ
SELECT COUNT(*) FROM EMP
WHERE SAL >= (SELECT AVG(SAL) FROM EMP);

-- ��� SAL �̻��� �޴� ������� DNAME, ENAME, JOB, SAL ��ȸ
SELECT D.DNAME, E.ENAME, E.JOB, E.SAL
FROM EMP E, DEPT D
WHERE E.SAL >= (SELECT AVG(SAL) FROM EMP) AND E.DEPTNO = D.DEPTNO;


-- ���� ���� SAL�� �޴� JOB�� �ش� JOB�� ��� SAL ��ȸ
SELECT JOB, AVG(SAL)
FROM EMP
WHERE JOB = (SELECT JOB FROM EMP WHERE SAL = (SELECT MIN(SAL) FROM EMP))
GROUP BY JOB;

-- ���� ���� ��� sal�� �޴� job�� �ش� job�� ��� sal ��ȸ
SELECT JOB, AVG(SAL)
FROM EMP
WHERE JOB = (SELECT JOB FROM EMP WHERE SAL = (SELECT MIN(SAL) FROM EMP))
GROUP BY JOB;

SELECT JOB, AVG(SAL)
FROM EMP
GROUP BY JOB
HAVING  AVG(SAL) = (SELECT MIN(AVG(SAL)) 
                    FROM EMP GROUP BY JOB);
                    
                    
                    
-- ���� �� ���� ����
--  - ���� ���� ����� �ϳ� �̻��� ���� �� ���
--  - WHERE ������ ���� ���� �÷��� ���� ��
--  - IN, ANY, ALL, EXISTS ���


-- DEPTNO�� 10 �Ǵ� 30�� ���ڵ� ��ȸ
SELECT * FROM EMP WHERE DEPTNO IN(10, 30);  -- SUB QUERY �Ⱦ�

SELECT * FROM EMP WHERE DEPTNO = ANY (10, 30);  -- SUB QUERY ��

-- SAL�� 3000 �̻��� ������ ���� �μ����� �ٹ��ϴ� ����� ��ȸ - �������� O
SELECT * FROM EMP WHERE DEPTNO = ANY (
SELECT DISTINCT DEPTNO FROM EMP WHERE SAL >= ANY(3000))
ORDER BY DEPTNO;

SELECT * FROM EMP WHERE SAL > ANY(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER�� �ִ� �޿����� ���� �޴� �����
SELECT * FROM EMP WHERE SAL < ANY(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER�� �ִ� �޿����� ���� �޴� �����
SELECT * FROM EMP WHERE SAL > ALL(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;

-- MANAGER�� �ּ� �޿����� ���� �޴� �����
SELECT * FROM EMP WHERE SAL < ALL(SELECT SAL FROM EMP WHERE JOB = 'MANAGER') ORDER BY SAL;


SELECT * FROM DEPT WHERE LOC IN (SELECT LOC FROM DEPT WHERE LOC = 'CHICAGO');

SELECT * FROM DEPT WHERE EXISTS (SELECT LOC FROM DEPT WHERE LOC = 'CHICAGO');
SELECT * FROM DEPT WHERE EXISTS (SELECT LOC FROM DEPT WHERE LOC = 'SEOUL');


-- ���� �÷� �������� MULTI COLUMN SUB QUERY
--  - ���� �������� ��ȯ�Ǵ� ����� �� �̻��� �÷�


-- �� �μ����� �ּ� SAL�� �޴� ������� DEPTNO, ENAME, SAL ��ȸ
SELECT DEPTNO, ENAME, SAL
FROM EMP
WHERE (DEPTNO, SAL) IN (SELECT DEPTNO, MIN(SAL) 
                        FROM EMP 
                        GROUP BY DEPTNO)
ORDER BY 1;           


-- ��ȣ ���� ���� ����
--  - ���ο��� �ѱ� �����͸� ���� ������ ���� �� �����ִ� ���
--  - ���� ������ ���� ���� ���̿� ����
--  - ���� ���� >> ���� ���� >> ���� ����
--  - ������ �ſ� ���� ����


-- �Ҽ� �μ��� ��� SAL���� ���� SAL�� �޴� ��� ��ȸ
SELECT * FROM EMP MAIN
WHERE SAL > ( SELECT AVG(SAL)
              FROM EMP SUB
              WHERE MAIN.DEPTNO = SUB.DEPTNO
              GROUP BY DEPTNO);
              
         
              
-- ��Į�� ���� ����
--  - SELECT ���� ���Ǵ� ������
--  - 1�ุ ��ȯ
--  - �Լ�ó�� ���


-- DEPTNO, DNAME, ENAME, JOB, SAL, GRADE ��ȸ
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



-- WITH�� ����� ���� ����
--  - FROM���� ���Ǵ� ���� ����
--  - Ư�� ���̺��� ��ü �����Ͱ� �ƴ� �Ϻ� �����͸� ������ �� ��Ī���� ���


-- DEPTNO�� 10�� �������� DEPTNO, DNAME, ENAME ��ȸ - INLINE VIEW
SELECT E.DEPTNO, DNAME, ENAME
FROM (SELECT * FROM EMP WHERE DEPTNO = 10) E, DEPT D
WHERE E.DEPTNO = D.DEPTNO;


-- DEPTNO�� 10�� �������� DEPTNO, DNAME, ENAME ��ȸ - WITH
WITH E AS (SELECT * FROM EMP WHERE DEPTNO = 10),
     D AS (SELECT * FROM DEPT) 
SELECT E.DEPTNO, DNAME, ENAME
FROM E, D
WHERE E.DEPTNO = D.DEPTNO;     


------------------------------------------------------------------------------
-- ������ SEQUENCE
--  - ���������� �ڵ� �����Ǵ� ����
--  - DML�� ROLLBACK�� �� ���� ROLLBACK �ȵ�
--  - �⺻: 1���� �����Ͽ� 1�� ����

-- ������ ���
--  - �������� ���� �� : �������̸�.CURRVAL
--  - �������� ���� �� : �������̸�.NEXTVAL


-- �⺻ ������ ���� 
CREATE SEQUENCE T_QNA_SEQ;

SELECT * FROM USER_SEQUENCES;  -- ������ ��ȸ

SELECT T_QNA_SEQ.CURRVAL FROM DUAL;  -- x
SELECT T_QNA_SEQ.NEXTVAL FROM DUAL;  -- ������ ����
SELECT T_QNA_SEQ.CURRVAL FROM DUAL;  -- O

-- ������ ����
--  - �ִ�/�ּ�, ������, ����Ŭ, ĳ�� ��� ���� ���� ����
--  - ���� �� ���� �Ұ���
ALTER SEQUENCE T_QNA_SEQ NOCACHE;  -- ĳ�� ��� X

-- ������ ����
DROP SEQUENCE T_QNA_SEQ;

-- ������ ����
-- �̸� T_QNA_SEQ, ������ 1����, ������ 1�� Ŀ����,
-- �ִ밪�� 999999999999999999, �ּҰ��� 0,
-- �ݺ� X, ĳ����� X
CREATE SEQUENCE T_QNA_SEQ
START WITH 1
INCREMENT BY 1
MAXVALUE 99999999999999
MINVALUE 0
NOCYCLE
NOCACHE;

-- �������� ����Ͽ� T_QNA ���̺� ���ڵ� �߰�
-- ������ SEQUENCE??, �ۼ��ڴ� FK �������ǿ� ������� �ʵ��� ����
INSERT INTO T_QNA (QNO, QUESTION, QID)
VALUES (T_QNA_SEQ.NEXTVAL, 'SEQUENCE??', 'aaaa'); -- ���� ���� �ʾƵ� NEXTVAL�� �����


-- T_SURVEY_SEQ ������ ���� (ĳ�� ��� x)
CREATE SEQUENCE T_SURVEY_SEQ NOCACHE;

-- T_SURVEY_ATTEND_SEQ ������ ���� (ĳ�� ��� x)
CREATE SEQUENCE T_SURVEY_ATTEND_SEQ NOCACHE;





-------------------------------- SYS ------------------------------------------
GRANT CREATE SYNONYM TO SCOTT;  -- ���� �ο�
GRANT CREATE PUBLIC SYNONYM TO SCOTT;


-------------------------------------------------------------------------------

-- SYNONYM ���Ǿ�
--  - ���̺� �̸� ��� ����ϴ� ����
CREATE SYNONYM T_SA FOR T_SURVEY_ATTEND;

SELECT * FROM T_SA;

-- T_SURVEY_ATTEND ���̺� ������ �߰�
-- �������� ��ȣ�� �������� ���
-- ���̺�, �̸��� ���� ���
-- �������� ������ ������ ���� 
INSERT INTO T_SA (ANO, SNO, ID, NUM)
VALUES (T_SURVEY_ATTEND_SEQ.NEXTVAL, 1001, 'aaaa', 1);


-- ���Ǿ� ����
DROP SYNONYM T_SA;


---------------------------------------------------------------------------
-- ������ ����
--  - ���� ���谡 ���̵��� ����ϴ� ����
--  - ���� �ǻ� �÷� LEVEL PSEUDO COLUMN
--      - ROWNUM�̳� ROWID���� ���� ���̺� ����Ǿ� ���� ������
--        �����ϴ� ��ó�� ��� ����
--      - ������ ������ ǥ���� �� ���� ǥ��
--      - LEVEL 1 : ��Ʈ ���
--        LEVEL 2 : ��Ʈ ����� �ڽ� ���
--        LEVEL 3 : ��Ʈ ����� �ڽ� ����� �ڽ� ���
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

SELECT MNO, PNO, LEVEL,
        DECODE(LEVEL, 1, MEMO, LPAD(' ', (LEVEL * 2) - 2) || 'RE:' || MEMO) AS INDENTED_MEMO, 
       ID, REGDATE 
FROM T_MEMO
START WITH PNO = 0
CONNECT BY PRIOR MNO = PNO
ORDER SIBLINGS BY PNO ASC;






SELECT * FROM EMP;
SELECT * FROM T_EMP;
SELECT * FROM DEPT;
SELECT * FROM T_SA;