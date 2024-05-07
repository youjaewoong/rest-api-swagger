## Spring Boot를 rest api 개발

1. 사용자 관리 API
* 전체 사용자 목록 조회: GET HTTP Method, http://localhost:8088/users
* 개별 사용자 조회: GET HTTP Method, http://localhost:8088/users/{id}
* 사용자 삭제: DELETE HTTP Method, http://localhost:8088/users/{id}
* 사용자 정보 수정: PUT HTTP Method, http://localhost:8088/users/{id}

2. 게시물 관리 API
* 전체 게시물 목록 조회: GET HTTP Method, http://localhost:8088/users/{id}/posts
* 게시물 삭제: DELETE HTTP Method, http://localhost:8088/users/{id}/posts/{post_id}


3. redis session
* pom.xml 에 redis 관련 의존성이 추가되어 있다.
* session 관련 이기 떄문에 redis 로컬에 설치되어 있어야한다. 그렇지 않을 경우 주석 처리

[redis 명령어]
* hgetall spring:session:sessions:f0c0f1a2-6283-4d72-9b1e-d306d5a3b012


### swagger
http://localhost:8088/swagger-ui.html
