## API 명세서
![image](https://github.com/user-attachments/assets/6b4741ae-ca71-49ac-972e-ce67d8db15d2)

## ERD 
![img.png](img.png)
## SQL
CREATE TABLE `member` (
`id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',
`email` VARCHAR(50) NOT NULL UNIQUE COMMENT '이메일 (로그인 ID)',
`password` VARCHAR(255) NOT NULL COMMENT '비밀번호',
`username` VARCHAR(50) NOT NULL COMMENT '사용자 이름',
`createdAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
`modifiedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE `scheduler` (
`id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 고유 ID',
`title` VARCHAR(50) NOT NULL COMMENT '일정 제목',
`contents` TEXT NOT NULL COMMENT '일정 내용',
`memberId` BIGINT NOT NULL COMMENT '일정 생성자 ID',
`createdAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
`modifiedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
CONSTRAINT `FK_SCHEDULER_MEMBER` FOREIGN KEY (`memberId`) REFERENCES `member`(`id`) ON DELETE CASCADE
);

CREATE TABLE `comment` (
`id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 고유 ID',
`content` VARCHAR(255) NOT NULL COMMENT '댓글 내용',
`memberId` BIGINT NOT NULL COMMENT '댓글 작성자 ID',
`schedulerId` BIGINT NOT NULL COMMENT '댓글이 달린 일정 ID',
`createdAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
`modifiedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
CONSTRAINT `FK_COMMENT_MEMBER` FOREIGN KEY (`memberId`) REFERENCES `member`(`id`) ON DELETE CASCADE,
CONSTRAINT `FK_COMMENT_SCHEDULER` FOREIGN KEY (`schedulerId`) REFERENCES `scheduler`(`id`) ON DELETE CASCADE
);
---

## 프로젝트 설명 (일정 API)
진행 날짜 : 2025.03.31 ~ 04.02

프로젝트 설명 (일정 API)

 - 이 프로젝트는 사용자별 일정 관리를 위한 RESTful API입니다. 사용자는 회원가입 후 로그인하여 자신의 일정을 생성하고, 수정하고, 삭제할 수 있으며, 댓글을 달 수 있습니다.
---
### 주요기능
- 회원 관리

    - 회원가입, 로그인, 로그아웃 기능 제공

    - 세션을 활용한 인증 처리

- 일정 관리

    - 일정 생성: 사용자가 일정 제목과 내용을 입력하여 일정 추가

    - 일정 조회: 전체 일정 및 특정 일정 조회

    - 일정 수정: 일정 작성자가 제목/내용을 수정 가능

    - 일정 삭제: 일정 작성자만 해당 일정 삭제 가능

- 댓글 기능

    - 일정에 대한 댓글 작성 및 조회, 삭제

    - 일정 작성자만 댓글을 작성 가능

- 예외 처리 및 보안

    - 로그인하지 않은 사용자는 일정 관리 기능 접근 불가 (401 Unauthorized 응답)

    - 잘못된 요청 시 예외 처리 (IllegalArgumentException, UserNotFoundException 등)

    - @Transactional을 활용한 데이터 일관성 유지
---

### 기술 스택
- Java 17, Spring Boot3
- Spring Data JPA, MySQL
- Lombok, Jakarta Validation
- GitHub, Postman
- IntelliJ IDEA

