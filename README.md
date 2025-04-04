# 📌 TodoAppPlusLv6

## 📖 프로젝트 소개
: 회원 관리 & 일정 관리 기능이 있는 Spring Boot 기반 앱입니다
Session 인증, 로그인 예외 처리, 비밀번호 암호화 등의 기능이 있습니다!

## 📄 API문서
<a href ="https://lace-zenith-49f.notion.site/1cac1a66561280428efced02d40b9a5f?v=1cac1a66561280738f9a000c615c1a01"> API문서 보러가기 </a>

<img width="800" alt="image" src="https://github.com/user-attachments/assets/0917b08a-809d-43d6-a447-8c18670b9409" />


## ERD
<img width="507" alt="image" src="https://github.com/user-attachments/assets/0716efd3-1ede-4666-9dbb-b0bd945f2b4a" />


## 🚀 주요 기능
### **✅ 일정 (Todo) 관리**
1. 일정 생성 
2. 전체 일정 조회
3. 특정 회원의 일정 조회
4. 일정 수정 (id로)
5. 일정 삭제 (id로)

### **✅ 회원 (Member) 관리**
1. 회원 가입 (POST /members/signup)
2. 모든 회원 조회 (GET /members)
3. 특정 회원 조회 (GET /members/{id})
4. 비밀번호 변경 (PUT /members)
5. 회원 삭제 (DELETE /members)

### **✅ 로그인 및 인증**
1. 이메일 & 비밀번호 로그인
2. Session & Fliter를 통한 인증

### **✅ 예외 처리**
일정 제목 10글자 제한, 유저명 2글자 이상 등
GlobalExceptionHandler를 활용한 예외 처리

### **✅ 비밀번호 암호화**
PasswordEncoder로 회원 비밀번호를 암호화해 DB에 저장함



### **🛠 기술 스택**
- Spring Boot (JPA, Security, Validation)
- MySQL (JPA 활용)



### 📂 폴더 구조
```
📦 todoAppPlus
├── common        
├── config        # 비밀번호 암호화
├── controller    # 클라이언트 요청 처리
├── dto           # 데이터 전송 객체 - Request/Response
│   ├── memberDto # 회원 관련 DTO
│   ├── todoDto   # 일정 관련 DTO
├── entity        # 회원, 일정의 엔티티
├── exception     # 예외 처리 관련 클래스 모음
├── filter        # 회원 인증 필터
├── repository    # DB접근
├── service       # 비즈니스 로직
└── TodoAppPlusApplication  # 메인 애플리케이션
```


