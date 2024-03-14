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




SELECT * FROM EMP;