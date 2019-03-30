
  CREATE TABLE "TESTUSER"."CM_USER_MST" 
   (	"V_USER_ID" VARCHAR2(20) NOT NULL ENABLE, 
	"V_PASSWORD" VARCHAR2(500), 
	"V_USER_NAME" CHAR(100), 
	"V_ACCOUNTNON_EXPIRED" CHAR(1),
	"V_ACCOUNTNON_LOCKED" CHAR(1),
	"V_CREDENTIALS_NON_EXPIRED" CHAR(1),
	"V_ENABLED" CHAR(1),
	"V_REG_USERID" VARCHAR2(20), 
	"D_REG_DTM" DATE, 
	"V_UPDATE_USERID" VARCHAR2(20), 
	"D_UPDATE_DTM" DATE, 
	 CONSTRAINT "PK_CM_USER_MST" PRIMARY KEY ("V_USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "TESTUSER_DATA_01"  ENABLE
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "TESTUSER_DATA_01" ;
  
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_USER_ID" IS '사용자ID';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_PASSWORD" IS '비밀번호';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_USER_NAME" IS '사용자이름';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_ACCOUNTNON_EXPIRED" IS '계정만료여부';
   
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_ACCOUNTNON_LOCKED" IS '계정잠김여부';
   
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_CREDENTIALS_NON_EXPIRED" IS '비밀번호만료여부';
   
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_ENABLED" IS '계정활성화여부';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_REG_USERID" IS '작성자';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."D_REG_DTM" IS '작성일시';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."V_UPDATE_USERID" IS '수정자';
 
   COMMENT ON COLUMN "TESTUSER"."CM_USER_MST"."D_UPDATE_DTM" IS '수정일시';
 
   COMMENT ON TABLE "TESTUSER"."CM_USER_MST"  IS '사용자마스터';
 