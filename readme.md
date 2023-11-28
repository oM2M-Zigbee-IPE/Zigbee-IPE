### 김상원

# HttpProxy

HttpProxy.connect(DeviceName, id, state, method);

해당 함수를 통해 http request에 대한 response를 얻는다.

connect함수가 static이기 때문에 클래스 명으로 직접 접근이 가능하고 값이 보존된다.

1. DeviceName, id, state를 통해 적절하게 url을 생성하고 method 설정을 하여 response를 받는다.

2. json 형식의 response body를 JsonNode를 통해 Json형태로 반환한다.


# ipeConfig

ipeConfig는 ipe의 설정값들을 static 형식으로 값을 갖고 있는 클래스이다.

# RequestMapping

향후에 객체지향적으로 코드를 수정하게 된다면 IDevice라는 Interface를 상속 받는 객체들을 선택하기 위해 작성한 코드이다.

다른 branch로 옮길 생각이었으나 실수로 옮기지 못하였다...

