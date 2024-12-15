# 유튜브 API연동을 통한 유튜브 기반 음악 플레이리스트

## 프로젝트 소개
이 프로젝트는 대학교 강의 중, Spring을 사용하는 "고급 웹프로그래밍"이라는 강의에서 진행한 프로젝트로,
1인으로 간단한 CRUD기능을 수행하는 Spring프로젝트를 사용하였다.

사용 언어는 HTML, CSS, JavaScript, Spring, MySQL로,
Spring은 Spring Boot로 진행하였다.

구글 클라우드에서 유튜브 API프로젝트와 토큰을 생성하고,
거기서 받은 토큰을 바탕으로 스프링부트의 application.properties파일에 넣어 활용하였다.

예시 코드
```properties
youtube.api.key=내 유튜브 API 토큰
```

간단한 회원가입 & 로그인 로직과,
songList페이지에는 Youtube에서 API 토큰을 생성한 것을 바탕으로 검색창에 원하는 것을 검색을 하면
실제 YouTube에서 나오는 값이랑 똑같이 검색결과가 나온다.
음악듣기 버튼은 도저히 고쳐지지가 않아 일단 보류한 상태다.
Youtube에서 보기 버튼을 클릭하면, 해당 유튜브 동영상 링크로 가지도록 코딩했다.
플레이리스트에 추가 버튼을 클릭하면, 현재 내가 생성한 플레이리스트 목록들이 나오고,
넣고 싶은 플레이리스트를 클릭하게 되면 내가 원하는 노래가 원하는 플레이리스트로 들어가지도록 하였다.

playlistDetail페이지에서는
내가 만들고싶은 플레이리스트의 제목을 입력하고,
Add Playlist를 클릭하면 플레이리스트가 생성 된다.
View를 클릭하면 내가 플레이리스트에 담은 노래들이 모달창으로 나오게 되고,
Delete를 클릭하면 플레이리스트가 삭제되게 된다.
그리고 내가 원하는 노래를 클릭해 들을 수 있고,
그리고 삭제 버튼을 눌러 플레이리스트에서 노래를 삭제할 수 있다.

## 개발 기간
2024년 12월 4일 ~ 2024년 12월 11일

## DB 설계
테이블은 총 3개로,
users테이블과,
playlists테이블,
그리고 youtube_playlist_items테이블이 있다.

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 사용자 ID
    username VARCHAR(255) NOT NULL UNIQUE,    -- 사용자 이름 (아이디)
    password VARCHAR(255) NOT NULL,           -- 비밀번호
    email VARCHAR(255) NOT NULL UNIQUE,       -- 이메일
    phone_number VARCHAR(20),                -- 전화번호
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 가입일
);

CREATE TABLE playlists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 플레이리스트 ID
    name VARCHAR(255) NOT NULL,              -- 플레이리스트 이름
    user_id BIGINT NOT NULL,                 -- 사용자 ID (외래키, NOT NULL 추가)
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,  -- 사용자와의 관계
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 생성일
);

CREATE TABLE youtube_playlist_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,        -- 항목 ID
    playlist_id BIGINT,                          -- 플레이리스트 ID (외래키)
    title VARCHAR(255) NOT NULL,                  -- 곡 제목
    description TEXT,                             -- 곡 설명
    youtube_video_id VARCHAR(255) NOT NULL,       -- 유튜브 영상 ID
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE,  -- 플레이리스트와의 관계
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 추가일
);
```
이렇게 쿼리문이 구성되어있고,
users 테이블은 사용자 테이블이고,
playlists 테이블은 내 플레이리스트를 저장하는 테이블이다.
users에서 id를 외래키로 가져와 사용한다.
youtube_playlist_items 테이블은 youtube에서 검색한 노래에 관한 테이블이고,
playlist id 를 외래키로 가져와 사용하였다.

## 주요 기능
+ 사용자가 회원가입을 할 수 있도록 함.
+ 사용자가 로그인을 할 수 있도록 함.
+ 사용자가 로그인 인증이 되지 않으면, songList페이지와 playListDetails페이지에 들어갈 수 없도록 구현.
+ songList페이지에서는 YouTube를 기반으로 검색할 수 있는 기능과, 내가 원하는 노래를 플레이리스트에 추가할 수 있는 기능 구현
+ playlistDetails페이지에서는 내가 원하는 이름의 플레이리스트를 만들고, 플레이리스트를 삭제할 수 있는 기능 구현.
+ 플레이리스트 페이지에서, 내 플레이리스트를 클릭하면, 내가 추가한 노래들이 보이고, 음악으로 재생할 수 있는 기능과, 노래를 삭제할 수 있는 기능 구현.

## 프로젝트를 하며 느낀점
이번에는 혼자서 프로젝트를 진행해보았다.
저번 DroneSpace프로젝트는 PHP를 이용하여 백엔드를 구현했지만,
이번에는 처음으로 Java & Spring Boot를 이용하여 백엔드 부분을 구현하였다.

- 처음에 쓰던 Maven Spring이 아닌 Gradle Spring Boot를 사용한 이유 ?
  -> 처음에는 Maven Spring, 그리고 외부 톰캣을 다운받아서 (10.1.26버전) 사용하다가,
  Gradle Spring Boot를 사용했는데,
  Maven Spring은 너무너무너무 불편한 것들이 많았다.

  1) 프로젝트를 진행하다보면, xml파일에 설정할 것들이 너무 많음
web.xml, applicationContext.xml, hibernate.cfg.xml, log4j.xml파일 등,
xml파일들을 설정할 것들이 너무나도 많았다.
근데 그 xml파일들이 정상적으로 작동이 되냐 ? 그것도 아니였던 것 같다.
물론 내가 코드를 잘못 짜서 그런것이겠지만, 서버를 키거나, 어떠한 기능을 테스트했을 때,
콘솔 로그에는 거의 xml파일의 문제였다. (그 중에서도 applicationContext.xml파일의 Bean 문제가 가장 컸다. 이 문제를 해결하지 못해서 Spring boot를 사용했다.)
Spring Boot에서는 거의 오류 없이 해결이 됐었을 뿐더러,
xml파일을 따로 설정할 필요가 없었기 때문에 프로젝트 진행이 매우매우 수월했다.

  2) tomcat 서버를 따로 프로젝트와 연결해주어야함.
Spring boot에는 내장 톰캣서버가 있기 때문에, 굳이 연결할 필요가 없어 너무 편했다.
그리고 따로 톰캣을 설치하지 않아도 돼서 너무나도 편했다.

  3) pom.xml은 의존성 추가 과정이 복잡함.
Gradle에서의 build.gradle은 의존성을 추가할 때, 한줄이면 충분하기 때문에 의존성 주입하는 과정도 편했다.
그리고, Spring boot에서는 stater 종속성이 제공돼서 굳이 버전을 하나하나 맞출 필요가 없다는 부분도 너무 편했다.

<br>
1. Entity, Controller와 Service, Repository의 역할을 제대로 이해하자.
뿐만 아니라, DTO, DAO, exception은 어느 상황에서 활용해야하는지 이해하자.

이번 프로젝트를 진행하기 전에, Spring이라는 프레임워크에 대한 지식이 상당히 부족한 상태에서 프로젝트를 시작했다.

그래서 그런지 각 클래스의 역할이 뭔지 이해도 제대로 하지 못한 채 Chat GPT의 도움을 자주 받곤 했다.

각 클래스의 역할을 제대로 이해하고, 각 로직은 어떻게 구성을 해야하는지 깊은 생각이 필요하고,

많은 학습이 필요할 것 같다는 것을 느꼈다.
+ 간단한 회원가입 컨트롤러
``` java
// 회원가입 페이지를 불러오는 메서드
@GetMapping("/register")
    public String register() {
        return "register";
    }
// 새 사용자를 등록하는 메서드
@PostMapping("/signup")
    public ModelAndView registerUser(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ModelAndView("redirect:/login");
    }
```

<br>
2. AI의 의존도를 줄이자.

이번 프로젝트를 진행하며 느낀것중 가장 큰 것은,

내가 AI, 즉 Chat GPT에 너무 많은 의존을 한다는 것이었다.

꾸준한 학습으로 AI가 주가 아닌, AI가 나를 보조해 주는 느낌까지 할 수 있도록 학습해야할 것 같다는 생각이 가장 크게 들었다.

다음부터 진행하는 프로젝트는 AI의 의존도를 최대한 낮추고 프로젝트를 진행해볼 계획이다.



3. Test부분을 건너띄지 말자.

Test코드를 작성하는것이 필요하다고 느낀 부분은,

미리 내가 만든 코드들을 실행하기 전,

하나하나의 기능을 담당하는 로직들의 기능을 미리 실험해보고,

에러가 발생하면 그 에러를 미리 고칠 수 있는게 너무 매력적이라고 생각이 들었다.

그리고 내가 만들었던 코드들이 사용자들이 원하는 요구사항을 충족할 수 있는 코드인지도

미리 실험을 해 볼 수 있다는 점이 나에게는 생각보다 크게 다가왔던 것 같다.

## 마무리

요즘 프론트엔드 개발자와 백엔드 개발자의 진로 중, 아주 많은 고민을 하는 중이다.

결국 나중에는 둘 다 해야 한다는 주변 현직자들의 이야기가 있었지만,

그래도 처음에 시작하는 것은 본인이 했을 때, 가장 재밌었던 것을 해야 오래할 수 있고,

재미있게 할 수 있다는 얘기를 많이 들었다.


점점 진로에 대해 확고해져 가고 있긴 하지만,

그래도 아직은 많은 고민이 필요할 것 같다.


이번 프로젝트를 끝내고, 다음에 계획하고 있는 프로젝트가 있는데,

그 프로젝트는 Chat GPT의 의존도를 최대한 낮추고 진행을 하고싶다.

프로젝트를 진행하기 전, 충분한 학습을 통해 프로젝트를 큰 막힘 없이 진행하고싶다는 생각이 이번 프로젝트를 진행하며 가장 크게 들었다.
