## 요약 
### 반복자 패턴 (Iterator)
> 컬렉션의 내부 구현 방법을 외부 노출하지 않으면서, 집합체(=컬렉션) 내의 모든 항목에 접근하는 방법을 제공하는 디자인 패턴

<img src="https://github.com/ljw1126/user-content/blob/master/head-first-design-pattern/iterator/iterator-pattern-uml.png?raw=true">

- **반복자 패턴**은 주로 컬렉션 프레임워크에서 제공되는 자료구조와 같이 **일관된 접근 방식을 제공할 필요성이 있을 때 사용**된다
- 이는 **컬렉션의 내부 구조를 숨기고 외부에서 일관된 방식으로 요소에 접근할 수 있도록 해준다**

**장점**
- 집합체에 속한 객체에 손쉽게 접근할 수 있다
  - 클라이언트는 집합체가 어떻게 구성되어 있는지 알 필요없이, *순회방법만 알고 사용하면 된다*
  - *SRP 원칙 준수*한다
- 일관된 인터페이스 사용해 여러 형태의 집합 구조를 순회할 수 있다
  - 내부 구조가 변경되더라도, 클라이언트에게 영향을 끼치지 않을 수 있다

**단점**
- 클래스가 늘어나고 복잡도가 증가한다
  - 새로운 Iterator 구현 클래스를 생성해서 사용하는 경우

<br/>

[반복자 패턴을 사용하지 않는 경우]
````java
public static void main(String[] args) {
   Board baord = new Board();
   board.addPost("디자인 패턴 게임");
   board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
   board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계씬 분들입니다");

   List<Post> posts = board.getPosts();
   for(int i = 0; i < posts.size(); i++) {
      Post post = posts.get(i); // 내부 집합체에 직접 접근하여 의존성이 강하고, 변경에 취약하다
      System.out.println(post.getTitle());   
   }
}
````

<br/>

[반복자 패턴을 사용하는 경우]
````java
public static void main(String[] args) {
   Board baord = new Board();
   board.addPost("디자인 패턴 게임");
   board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
   board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계씬 분들입니다");

   Iterator<Post> postIterator = board.createIterator(); // Iterator<Post> 반환하는 임의 메소드 
   while(postIterator.hasNext()) { // 접근 방법을 숨기고, 일관된 방식으로 조회 가능
      System.out.println(postIterator.next().getTitle());
   } 
}
````