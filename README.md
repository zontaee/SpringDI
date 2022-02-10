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
