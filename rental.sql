--������ ���̺�(������ID, �̸�, ���� ��)
--ȸ�� ���̺�(ȸ��ID, �̸�, ����ó, �������� ��)
--�ü� ���̺�(�ü�ID, ��ġ, �ü����� ��)
--���� ���̺�(����ID, ȸ��ID, �ü�ID, �����Ͻ�, �뿩�Ⱓ)

/* 
TABLE MEMBER
������ȣ, ���̵�, �н����� �̸�, ������(BOOLEAN), ����ó, �����ð�

TABLE �ü� FACILITY
�ü���ȣ, �ü��̸�, �ü���ġ, ������

TABLE ���� Reservation
�����ȣ, ȸ�����̵�, �ü����̵�, ������, �뿩�Ⱓ

TABLE �ı� REVIEW


�̿� ���� ó������ NULL ������ ���� �ִٰ�
�̿� �Ϸ��ϸ� Ʈ���ŷ� �Ҹ��� ����
*/

SELECT * FROM R_FAC;
DESC R_USER;


----------------------------------------------------------------------
ALTER TABLE R_USER ADD CONSTRAINT R_USER_UNIQUE UNIQUE(USERID);

ALTER TABLE R_RES
ADD CONSTRAINT USER_ID_FK
FOREIGN KEY (USERID) REFERENCES R_USER(USERID);
----------------------------------------------------------------------

ALTER TABLE R_RES ADD CONSTRAINT R_FAC_UNIQUE UNIQUE(FACNAME);

ALTER TABLE R_FAC
ADD CONSTRAINT FAC_FK
FOREIGN KEY (FACNO) REFERENCES R_RES(FACNAME);

----------------------------------------------------------------------
ALTER TABLE R_REVIEW ADD CONSTRAINT R_REVIEW_UNIQUE UNIQUE(FACNAME);

ALTER TABLE R_FAC
ADD CONSTRAINT FAC_REVIEW_FK
FOREIGN KEY (FACNAME) REFERENCES R_REVIEW(FACNAME);
-------------
ALTER TABLE R_REVIEW 
ADD CONSTRAINT REVIEW_USER_FK FOREIGN KEY (USERID) REFERENCES R_USER(USERID);
-------------------


SELECT * FROM r_user;