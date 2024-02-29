# HH99LV3

## 유스케이스
![image](https://github.com/lsc713/HH99LV3/assets/77494780/1d0f0860-f4e7-4bc9-b6f8-22d3c3a08138)

## ERD
![image](https://github.com/lsc713/HH99LV3/assets/77494780/37e17878-0c4c-4826-b423-87e396560ef6)

## API 명세
회원가입 - api/v1/admin/signup 
로그인 - api/v1/admin
강사 등록 - post api/v1/admin
강의 등록 - post api/v1/lecture
선택한 강사 조회 - get api/v1/admin/{tutorId}
선택한 강의 조회 - get api/v1/lecture/{lectureId}
카테고리별 강의 목록 조회 - get api/v1/lecture/{category}
선택한 강사가 촬영한 강의 목록 조회 기능 - get api/v1/tutor/{tutorId}
강사 정보 수정 - put api/v1/admin/{adminId}
강의 정보 수정 - put api/v1/lecture/{lectureId}
강사 정보 삭제 - delete api/v1/admin/{adminId}
강의 정보 삭제 - delete api/v1/lecture/{lectureId}
+ 시간이 된다면 swagger
+ 더 있으면 security







