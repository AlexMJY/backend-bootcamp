use xe;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO salgrade ( grade, losal, hisal) VALUES ( 1, 700, 1200);
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO salgrade ( grade, losal, hisal) VALUES ( 2, 1201, 1400);
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO salgrade ( grade, losal, hisal) VALUES ( 3, 1401, 2000);
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO salgrade ( grade, losal, hisal) VALUES ( 4, 2001, 3000);
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO salgrade ( grade, losal, hisal) VALUES ( 5, 3001, 9999);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO dept(dname, loc)  VALUES ( 'ACCOUNTING', 'NEW YORK');
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO dept(dname, loc)  VALUES ( 'RESEARCH',   'DALLAS');
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO dept(dname, loc)  VALUES ( 'SALES',      'CHICAGO');
-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO dept(dname, loc)  VALUES ( 'OPERATIONS', 'BOSTON');

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
VALUES ('SMITH',  'CLERK',     14,  STR_TO_DATE('1980/12/30', '%Y/%m/%d'),  800, NULL, 2, 1 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'WARD',   'SALESMAN',  7,  STR_TO_DATE('1981/02/22', '%Y/%m/%d'), 1250,  500, 3, 2 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'ALLEN',  'SALESMAN',  7,   STR_TO_DATE('1981/02/20', '%Y/%m/%d'), 1600,  300, 3, 3 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'JONES',  'MANAGER',   10,  STR_TO_DATE('1981/04/02', '%Y/%m/%d'),  2975, NULL, 2, 4 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'MARTIN', 'SALESMAN',  7, STR_TO_DATE('1981/09/28', '%Y/%m/%d'), 1250, 1400, 3, 2);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ('BLAKE',  'MANAGER',   10, STR_TO_DATE('1981/05/01', '%Y/%m/%d'),  2850, NULL, 3, 4);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values   ('CLARK',  'MANAGER',   10,  STR_TO_DATE('1981/06/09', '%Y/%m/%d'),  2450, NULL, 1, 4);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ('SCOTT',  'ANALYST',   5,  STR_TO_DATE('1982/12/09', '%Y/%m/%d'), 3000, NULL, 2, 4);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ('KING',   'PRESIDENT', NULL, STR_TO_DATE('1981/11/17', '%Y/%m/%d'), 5000, NULL, 1, 5);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ('TURNER', 'SALESMAN',  7,  STR_TO_DATE('1981/09/08', '%Y/%m/%d'),  1500,    0, 3, 3 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'ADAMS',  'CLERK',     9,  STR_TO_DATE('1983/01/12', '%Y/%m/%d'), 1100, NULL, 2, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'JAMES',  'CLERK',     7, STR_TO_DATE('1981/12/03', '%Y/%m/%d'),   950, NULL, 3, 1 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ( 'FORD',   'ANALYST',   5, STR_TO_DATE('1981/12/03', '%Y/%m/%d'),  3000, NULL, 2, 4 );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno, no)
values  ('MILLER', 'CLERK',     9,   STR_TO_DATE('1982/01/23', '%Y/%m/%d'), 1300, NULL, 4, 1 );
commit;