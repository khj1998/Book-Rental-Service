개인적으로 수정 및 추가한 부분
1. 로컬 윈도우 도커 데스트 탑에 각 서비스 (bestbook, book, user, rental) & 인프라 (kafka ui, kafka, zookeeper, mongodb)를 배포하기 위한 쉘 스크립트 및 docker-compoer 파일 작성
2. 각 마이크로서비스를 운영하는데 필요한 로그 작성
  => 서비스 레이어 트랜잭션 시작을 알리는 로그, kafka pub/sub 시작 및 트랜잭션 처리 이력을 남기는 로그 등 작성.
3. book-rental-service를 루트 디렉토리 멀티 모듈 프로젝트 구조로 수정.
4. maven -> gradle dependency 관리 툴 변경
