### Zigbee IPE

## 1. oM2M 플러그인으로 동작
---

<br>



## 2. 구성 요소
---


## 3. 사용 법
---


1. Repository를 Clone 받아서 oM2M root 디렉토리 바로 아래 집어넣는다.

2. root 디렉토리에서 pom.xml에 <module>org.eclipse.om2m.ipe.zigbee</module>를 등록한다.

3. oM2M의 기본 설정은 IN-CSE에 MN-CSE를 연결 시켜서 사용하는 방식을 알려주지만 zigbee는 보통 가정에서 사용합니다. 
   따라서, IN-CSE에 바로 등록할 수 있게 org.eclipse.om2m.site.in-cse 디렉토리에 있는 om2m.product에 플러그인을 추가한다.
   <plugin id="org.eclipse.om2m.ipe.zigbee"/> 를 plugins에 추가해주면 된다.

4. 이후 oM2M 공식 홈페이지에 나와있는 빌드 방법 그대로 mvn clean install -DskipTests=true 로 빌드를 진행한다. 

5. 빌드가 성공적으로 진행 된 후, in-cse를 가동시킨후 osgi 프레임 워크에서 org.eclipse.om2m.ipe.zigbee_1.1.0.20231204-1351 를 찾아서 시작시킨다.

6. http://127.0.0.1:8080/webpage 에 접속하여 zigbee ipe가 AE로 등록된걸 확인 할 수 있다.
