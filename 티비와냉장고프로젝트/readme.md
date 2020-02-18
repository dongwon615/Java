# TV와 Refrigerator 관리하는 프로그램

### 20200218
이전까지 코드는
1. Product 클래스를  TV와  Refrigerator 클래스가 상속을 받음
2. IProductMgr 인터페이스를 통해서 Product 클래스의 기능을 정함
3. ProductMgrImpl 클래스로 실제 기능들 구현을 함

오늘 한 일
1. ProductMgrImpl 클래스 내에 open 과 close 메서드를 통해 파일 입출력으로 이전 정보 기억하는 기능 구현
2. CodeNotFound와 Duplicate Exception 기능을 추가하여 예외상황 throw 구현
