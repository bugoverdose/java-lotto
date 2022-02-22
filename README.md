# 로또 미션 저장소

## 구현할 기능 목록

- [x] 로또를 구매할 금액을 입력받는다.
    - [x] 1000원당 로또 한 장을 구매한다.
    - [ ] 1000원 미만의 단위가 입력되는 경우 예외가 발생한다.
    - [x] 숫자를 입력하지 않은 경우 예외가 발생한다.

- [x] 구매한 로또 갯수만큼 로또 객체를 생성한다.
    - [x] 로또는 1~45 사이의 숫자 6개로 구성된다.
    - [x] 6개의 숫자는 서로 독립적이며, 임의로 선택된다.
    - [x] 6개의 숫자는 오름차순으로 정렬되어된다.
    - [ ] 각 로또의 숫자 정보는 서로 독립적으로 생성된다.
    - [x] 생성된 로또 정보를 출력한다.

- [ ] 당첨 번호와 보너스 번호를 입력받는다.
    - [ ] 1~45 범위를 벗어난 숫자를 입력한 경우 예외가 발생한다.
    - [ ] 중복된 숫자를 입력한 경우 예외가 발생한다.
    - [ ] 당첨 번호는 (", ") 으로 구분 받는다.
    - [ ] 당첨 번호의 개수가 6개가 아닐 경우 예외가 발생한다.
    - [ ] 숫자를 입력하지 않은 경우 예외가 발생한다.

- [ ] 당첨 통계를 출력한다.
    - [ ] 당첨 케이스별 결과를 계산하여 출력한다.
    - [ ] 수익률(당첨금액/구매금액)을 계산하여 출력한다(소수점 3번째 자리에서 반올림).
