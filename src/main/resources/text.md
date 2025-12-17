- 로그인 화면으로 이동

        - 프로그램의 시작점은 main.
        main.jsx에서 첫 렌더링이 일어남
        - queryClient 전역 상태를 세팅 -> 초기화 -> 브라우저라우터 세팅 -> 앱 실행
        - App이 실행되려면 AuthRoute가 필요 (렌더링은 작은 것들부터 큰 것들 순서로 됨 -> 부품이 완성되야 전체가 완성되기 때문)
            - AuthRoute 실행 : navigate 선언, location선언 후 현재 접속 경로 가져옴 -> useMeQuery가 실행됨
            - useMeQuery가 실행될 때 백엔드에 요청이 날라감 (AccessToken이 있는지 확인 요청)
                - 처음 AccessToken의 값은 null값 (로그인한 적 없으니까)
                - requestMe라는 요청이 날라감. -> 요청을 딱 하는 순간에 인터셉터를 세팅해둠
                - AccessToken을 꺼내서 Bearer 붙여서 헤더에 세팅
                - 그 후에 요청이 한 번 날아감
                - 그러면 최초에 me라고 하는 요청이 날아갈 때 Bearer null이라는 요청이 날아감.
                    - Bearer null이라는 요청이 날아가면 백엔드가 동작함
                    - 하는 동안에 useMeQuery가 로딩상태에 빠진다.
                    - useEffect 동작은 아직 안함(대기상태) -> AuthRoute가 마운트될 때 동작
                    - 리턴 일어남 -> home이 먼저 화면에 렌더링됨
                -리턴되면 부품이 완성된거라 마운트해야됨 -> 리턴되고나서 useMount
                - isLoading이 true인데 ! 걸리면서 false가 되니까 동작 안함
                - 그리고 나서 리턴되어지고 마운트 되었기 때문에 다시 App으로 감
            - 이제 MainLayout이 동작할 차례
                - children으로 authRoute가 들어감
                - 코드 실행 결과를 리턴 , 마운트 -> App이라는 게 만들어짐
                - BrowserRouter가 완성되니까 화면에 딱 나타남
                - 그 동안에 백엔드에서 요청을 처리하고 있었다
                - 요청이 가면 filter를 탄다 
                - filter를 타고 들어왔는데 authentication이 null이 아니기 때문애 통과
                - Bearer null 에서 Bearer 제거
                - 그 후 accesstoken은 null값을 받는다 -> 토큰이 잘못되었나? False, 근데 not 붙어서 true가 되니까 값 넘겨줌
                - authentication을 거치지않고 다음 코드로 넘어가면 인증 실패
                - 그래서 authenticaiton 실패하면 AuthenticatioEntryPoint 에 처리 - Not Found를 제외하면 401에러를 띄운다
                - 401로 응답을 해주면 이때 useQuery가 반응을 한다
                - 상태가 변하면 상태를 쓴 위치(AuthRoute)에서 라우팅이 다시 일어난다.
                - 그러고나서 useEffect가 다시 동작을 함 (상태가 변했기 때문에) - 401에러
                -  useEffect가 돌 때 데이터 검사를 함.
                - 경로가 바뀌면 useEffect가 다시 돈다
                - 경로 : auth_login
                - 렌더링은 다시 되지만 캐시 데이터는 유지됨
                - 로그인 자체는 없고 계속 홈으로 강제 이동


    2. 로그인이 됐을 때
        - 로그인 페이지에서 onClick이 일어남. -> 주소 바뀜
        - 로그인 되면 SecurityConfig에서 oauth2Login이 실행됨
        - yml 파일을 확인함
        - 클라이언트 이름에 NAVER이 있는지 확인
        - 그 후 다시 네이버로 요청날림
        - 네이버로부터 authorization 코드를 하나 받아옴
        - oauth2 라이브러리가 코드를 받는 즉시 토큰 요청을 한다.
            - 이 토큰이 쓸 수 있는건지 확인
            - 유효하다면 userRequest를 받아옴 -> 토큰을 가지고 oauth2User객체를 받아옴
            - 이 때 네이버에 백엔드가 다시 요청 날림 -> 유저 정보 받아옴
            - loadUser에 oAuth2User 만들어냄
            - 네이버의 정보를 꺼내쓸 수 있다.
            - 결국 oAut2User이 리턴됨
            - 사이트마다 키 값이 다 다르기때문에 세팅 필요
            - 1. 권한, attributes, 2. name - attribute 키 받아옴

            - 리턴 정확하게해서 로그인 성공되었으면
            Oauth2SuccessHandler 이동

            만약 회원가입이 된 적이 있는 oauth2_user이라고 하면
            null이면 db에 생성
            그 때 AUthentication 그대로 넘겨줌 -> principalUser에서 user객체 꺼내고
            닉네임은 랜덤하게 세팅
            그 상태로 db에다가 user정보 insert
            그걸 리턴
            --> foundUser가 null값에서 방금 리턴된 값으로 대체

            -> accessToken을 이 때 받음

            accessToken을 받아서 완전히 백엔드에 갔다가 날라감
            --> 완전 새로 다 띄우기 때문에
            --> main부터 다시 날라감

            pathName이 바로 토큰값이 됨. -> 저거에 맞게끔 oauth2가 렌더링 됨
            -> oauth2 컴포넌트가 렌더링될거고 oauth2.jsx 동작

            