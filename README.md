# 애자일 소프트웨어 개발 선언



우리는 소프트웨어를 개발하고, 또 다른 사람의 개발을
도와주면서 소프트웨어 개발의 더 나은 방법들을 찾아가고있다. 
이 작업을 통해 우리는 다음을 가치 있게 여기게 되었다.

공정과 도구보다 개인과 상호작용을
포괄적인 문서보다 작동하는 소프트웨어를
계약 협상보다 고객과의 협력을
계획을 따르기보다 변화에 대응하기를 가치 있게 여긴다. 
이 말은, 왼쪽에 있는 것들도 가치가 있지만,
우리는 오른쪽에 있는 것들에 더 높은 가치를 둔다는 것이다.


![image](https://user-images.githubusercontent.com/90680271/153197345-906722ab-4a85-4f73-bed4-65eac2e2c292.png)

스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계되어 있다

XML 설정 사용

최근에는 스프링 부트를 많이 사용하면서 XML기반의 설정은 잘 사용하지 않는다. 하지만 많은 레거시 프로젝트가 XML로 이루어져있고

XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있어서 사용하는 방법은 알아야한다.

![image](https://user-images.githubusercontent.com/90680271/153197697-00c7b2cc-7f24-4c31-b3dd-0565c0d26b5d.png)


AnnotationConfigApplicationContext 는 AnnotatedBeanDefinitionReader 를 사용해서 AppConfig.class 를 읽고 BeanDefinition 을 생성한다.

GenericXmlApplicationContext 는 XmlBeanDefinitionReader 를 사용해서 appConfig.xml 설정 보를 읽고 BeanDefinition 을 생성한다.

새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 만들어서 BeanDefinition 을 생성하면 된다.





@Configuration과 바이트코드 조작의 마법
스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 그런데

스프링이 자바 코드까지 어떻게 하기는 어렵다. 저 자바 코드를 보면 분명 3번 호출되어야 하는 것이 맞다.

그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.

모든 비밀은 @Configuration 을 적용한 AppConfig 에 있다.


![image](https://user-images.githubusercontent.com/90680271/153201285-13ca80c0-0cbd-40f3-9784-3c952781e5e8.png)



@Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다 따라서 스프링 설정정보는 항상 @Configuration을 사용하자.



 @ComponentScan
 
 ![image](https://user-images.githubusercontent.com/90680271/153364553-6f9dbd9f-b260-4265-b35a-3005b91e3f83.png)
 
 
 @ComponentScan 은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 

  이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
  
  빈 이름 기본 전략: MemberServiceImpl 클래스 memberServiceImpl
  
  빈 이름 직접 지정: 만약 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("memberService2") 이런식으로 이름을 부여하면 된다.
  
  
   @Autowired 의존관계 자동 주입


![image](https://user-images.githubusercontent.com/90680271/153364767-33e84e8b-d5e3-4a08-a4a7-ce5a303c1a20.png)



![image](https://user-images.githubusercontent.com/90680271/153364947-4cf6b229-2485-489c-94b4-95cc03565c5d.png)


스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다. (그리고 이 설정안에 바로 @ComponentScan 이 들어있다!)



컴포넌트 스캔 기본 대상

컴포넌트 스캔은 @Component 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.

@Component : 컴포넌트 스캔에서 사용

@Controlller : 스프링 MVC 컨트롤러에서 사용

@Service : 스프링 비즈니스 로직에서 사용

@Repository : 스프링 데이터 접근 계층에서 사용

@Configuration : 스프링 설정 정보에서 사용




수동 빈 등록 vs 자동 빈 등록

만약 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌되면 어떻게 될까?


--> 수동 빈 등록이 우선권을 가진다.





의존관계 주입은 크게 4가지 방법이 있다.

생성자 주입

수정자 주입(setter 주입)

필드 주입

일반 메서드 주입


무엇을 사용하는게 좋을까?

결론적으로
생성자 주입을 선택해라!
과거에는 수정자 주입과 필드 주입을 많이 사용했지만, 최근에는 스프링을 포함한 DI 프레임워크 대부분이
생성자 주입을 권장한다. 그 이유는 다음과 같다.


1.대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
2.수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다. 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
3.생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다



조회 대상 빈이 2개 이상일 때 해결 방법

@Autowired 필드 명 매칭

@Qualifier @Qualifier끼리 매칭 빈 이름 매칭

@Primary 사용


스프링 빈의 이벤트 라이프사이클
스프링 컨테이너 생성 -> 스프링 빈 생성 ->의존관계 주입 -> 초기화 콜백 ->사용 ->소멸전 콜백-> 스프링 종료
초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
소멸전 콜백: 빈이 소멸되기 직전에 호출



@PostConstruct, @PreDestroy 애노테이션 특징
최신 스프링에서 가장 권장하는 방법이다.
애노테이션 하나만 붙이면 되므로 매우 편리하다.
패키지를 잘 보면 javax.annotation.PostConstruct 이다. 스프링에 종속적인 기술이 아니라 JSR-250
라는 자바 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
컴포넌트 스캔과 잘 어울린다.
유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용하자.


프로토타입 스코프

싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환한다. 반면에
프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서
반환한다


![image](https://user-images.githubusercontent.com/90680271/153812666-35f82c18-0796-490c-97a6-502d54a6e7c7.png)


1. 싱글톤 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환한다.
3. 이후에 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스프링 빈을 반환한다.



![image](https://user-images.githubusercontent.com/90680271/153812730-97adb5e4-93b0-4270-a6e2-a4eec8ef87ae.png)

1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성하고, 필요한 의존관계를 주입한다.


![image](https://user-images.githubusercontent.com/90680271/153812812-21d05495-ff61-4b87-838f-e1ae1bf948cf.png)


3. 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환한다.
4. 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환한다.

여기서 핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다는
것이다. 클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않는다. 
프로토타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에 있다. 그래서 @PreDestroy 같은 종료
메서드가 호출되지 않는다.



프로토타입 빈의 특징 

스프링 컨테이너에 요청할 때 마다 새로 생성된다.

스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.

종료 메서드가 호출되지 않는다.  그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도클라이언트가 직접 해야한다.



프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점

스프링 컨테이너에 프토토타입 스코프의 빈을 요청하면 항상 새로운 객체 인스턴스를 생성해서 반환한다. 
하지만 싱글톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야 한다. 그림과 코드로 설명하겠다.
먼저 스프링 컨테이너에 프로토타입 빈을 직접 요청하는 예제를 보자.


![image](https://user-images.githubusercontent.com/90680271/153813388-5cde95ba-0dc0-4063-9ab1-097cec1ea6a4.png)


1. 클라이언트A는 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(x01)한다. 해당 빈의 count 필드 값은 0이다.
3. 클라이언트는 조회한 프로토타입 빈에 addCount() 를 호출하면서 count 필드를 +1 한다.
결과적으로 프로토타입 빈(x01)의 count는 1이 된다.

![image](https://user-images.githubusercontent.com/90680271/153813415-a7188759-eda2-43a3-af42-17724883035e.png)

1. 클라이언트B는 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(x02)한다. 해당 빈의 count 필드 값은 0이다.
3. 클라이언트는 조회한 프로토타입 빈에 addCount() 를 호출하면서 count 필드를 +1 한다.
결과적으로 프로토타입 빈(x02)의 count는 1이 된다


![image](https://user-images.githubusercontent.com/90680271/153814108-aee5d7c4-502b-4e1b-8dbf-cb6e7c9530bb.png)



![image](https://user-images.githubusercontent.com/90680271/153814387-a8ffc2df-8fc8-4edf-8ddd-e8d91e3a04b5.png)


![image](https://user-images.githubusercontent.com/90680271/153814406-3d721b66-5f66-40c1-b0bd-4ee7c0d96c58.png)




clientBean 은 싱글톤이므로, 보통 스프링 컨테이너 생성 시점에 함께 생성되고, 의존관계 주입도 발생한다.
1. clientBean 은 의존관계 자동 주입을 사용한다. 주입 시점에 스프링 컨테이너에 프로토타입 빈을 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 생성해서 clientBean 에 반환한다. 프로토타입 빈의 count 필드 값은 0이다.
이제 clientBean 은 프로토타입 빈을 내부 필드에 보관한다. (정확히는 참조값을 보관한다.)

클라이언트 A는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은 clientBean 이 반환된다.

3. 클라이언트 A는 clientBean.logic() 을 호출한다.

4. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. 
count값이 1이 된다.

클라이언트 B는 clientBean 을 스프링 컨테이너에 요청해서 받는다.싱글톤이므로 항상 같은
clientBean 이 반환된다.
여기서 중요한 점이 있는데, clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난
빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지, 사용 할 때마다
새로 생성되는 것이 아니다!

5. 클라이언트 B는 clientBean.logic() 을 호출한다.

6. clientBean 은 prototypeBean의 addCount() 를 호출해서 프로토타입 빈의 count를 증가한다. 
원래 count 값이 1이었으므로 2가 된다.


웹 스코프

웹 스코프의 특징
웹 스코프는 웹 환경에서만 동작한다.
웹 스코프는 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드가 호출된다.


웹 스코프 종류

request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고, 관리된다.

session: HTTP Session과 동일한 생명주기를 가지는 스코프

application: 서블릿 컨텍스트( ServletContext )와 동일한 생명주기를 가지는 스코프

websocket: 웹 소켓과 동일한 생명주기를 가지는 스코


![image](https://user-images.githubusercontent.com/90680271/153815840-3deba58f-598d-4b45-8201-4e20a02cecac.png)



