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

