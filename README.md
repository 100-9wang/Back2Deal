<div align="center">

<img src="https://img.shields.io/badge/Spring%20Boot-3.1.5-green?logo=springboot" />
<img src="https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql" />
<img src="https://img.shields.io/badge/Java-17-orange?logo=java" />

# 🖥️ Back2Deal  
**중고컴퓨터 판매 쇼핑몰 프로젝트**  
React + Spring Boot + MySQL 기반의 웹 서비스

</div>

---

## ✨ 주요 기능

- 👥 회원가입 / 로그인 (일반 사용자 / 판매자 / 관리자)
- 🛍 상품 조회 (검색, 필터링), 상세 페이지
- 🛒 장바구니 담기 / 수정 / 삭제
- 💳 주문 및 결제, 포인트 적립
- 📝 게시판 기능 (공지사항, Q&A, 리뷰, 자유게시판)
- 🧑‍💻 관리자 전용 사용자/상품 관리, 통계 대시보드

---

## 📁 기술 스택

| 영역 | 기술 |
|------|------|
| Frontend | React, Axios, CSS |
| Backend | Spring Boot (Java 17), Spring Data JPA |
| Database | MySQL 8.0 |
| Build & Deploy | Maven, Render.com |
| Tools | Eclipse, Git, Lombok |

---

## 📂 프로젝트 구조

```
src/
└── main/
    └── java/
        └── com.myspring.back2deal/
            ├── controller/
            ├── service/
            ├── service/impl/
            ├── repository/
            ├── dto/
            └── entity/
```

---

## ⚙️ 실행 방법

### 1️⃣ Git 클론 & 빌드
```bash
git clone https://github.com/your-id/back2deal.git
cd back2deal
./mvnw clean install
```

### 2️⃣ DB 연결 정보 설정
`src/main/resources/application.properties` 파일 수정:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/back2deal
spring.datasource.username=root # MySQL 아이디를 입력해주세요.
spring.datasource.password=your_password # MySQL 비밀번호를 입력해주세요.
```

### 3️⃣ 실행

```bash
./mvnw spring-boot:run
```

📌 **권장 환경**:
- Java 17 이상
- MySQL 8.0 이상
- Maven 3.8 이상

접속 주소: [http://localhost:8080](http://localhost:8080)

---

## 📸 화면 미리보기 (예정)

> 추후: React 프론트와 연동된 화면 GIF 삽입 예정

---

## 🚨 트러블 슈팅 (예정)

개발 중 발생한 주요 이슈와 해결법을 기록할 예정입니다.

---

## 🚀 향후 추가 예정 기능

- 실시간 채팅 기능
- 소셜 로그인 (구글, 네이버, 카카오)
- 성능 최적화 및 테스트 코드 작성

---

## 🗓️ 개발 일정 및 목표

- [x] 프로젝트 초기 구성 및 DB 설계
- [x] 요구사항 명세 및 ERD 작성
- [ ] API 명세 및 화면 설계
- [ ] React 프론트 개발 연동
- [ ] 배포 (JAR 기반 Render or EC2)

---
