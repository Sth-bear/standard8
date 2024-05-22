*Hilt 의존성을 추가했습니다. 해당 Hilt버전은 2.51.1로 다음을 참고했습니다.*
- https://github.com/google/dagger

## 아래의 내용으로 이해하며 진행했습니다. 

    class Car{ 
        private val engine = Engine() 
    } 

    class Car(private val engine: Engine) {}

 >둘의 차이는 engine을 Car에서 스스로 만들어서 사용하느냐, 생성자로 받아오느냐.
 >
 >직접 만들어서 사용 할 경우,  car라는 껍대기는 유지한 상태로 속성 Engine만 바꿔야 하는 경우 Car자체를 수정해야함.
 >
 >그러나, 생성자로 받아오는 경우 주입되는 Engine이라는 부분만 교체해서 들어오면 정상작동이 가능함  ->  단일책임 및 재사용성의 증가. 

이러한 의존성을 주입하는 방법: 1. 생성자 / 2. 필드주입

+ 필드 주입의 경우 

    + 모든 안드로이드 프레임 워크에서 생성자 삽입이 불가능한 점(Activity,Fragment), 
    + 생명주기에 따른 의존성 관리(fragment 화면 전환시), -> 변화에 대응이 가능함.
    + 외부로 부터 받기에 쉽게 교체가 가능한 결합도의 감소 등의 장점(해당 외부주입만 교체)을 지님.

+ 그렇다면 생성자는 왜 사용하는가?
  
    + 생성자로 인해 의존성이 주입되는 경우 인스턴스의 수명에 고정되어 바뀌지 않음. 즉, 고정된 값을 가져야 하는 경우에 적합. 


## 어떻게 주입했는가? 

1. standard8Application.kt 파일을 통해  @HiltAndroidApp을 통해 힐트를 초기화 및 intent에 name으로 설정. 
2. MainActivity에 @AndroidEntryPoint 지정. 이를 통해 main과 하위 fragment에 의존성 주입을 허가.
3. 실제 기능을 하는 Retorfit 의 모듈화 선언 

   - @Module -> 의존성 제공자를 가진 모듈임을 선언
   - @InstallIn -> 어떤 영역으로 작동할것인지
   - @Provides -> 의존성 제공자 선언 

4. 주입대상자에게 주입(*실제 기능을 하는 구현체)
   - @Inject -> 주입 / constructor(대상) -> 어떤 대상을 주입받을 것인지 명시 


## 문제점

  ![스크린샷 2024-05-20 145430](https://github.com/Sth-bear/standard8/assets/72172581/9c558c71-7cc3-4d9b-805f-1671c8dd5306)

***위의 오류가 발생. 확인결과 제공자가 없어서 발생한 문제***
|대상자|::|요구|
|---|---|---|
|KakaoRemoteDataSourceImpl|::|KakaoService|
|2.KakaoRepositoryImpl|::|KakaoRemoteDataSource|
|3.ViewModel|::|KakaoRepository |

- 1번의 경우 Retrofit 에서 제공하고 있음.
- 그러나 2,3번은 따로 제공자가 없어 발생하는 문제라고 판단.
- 이에 di 패키지를 만들어 각각의 제공자를 만들기 위해 Module파일을 선언하여 해결함. 

                

        
        
    
