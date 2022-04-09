package lotto.constant;

public class ExceptionMessages {

    public static final String INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE = "숫자를 입력해야 합니다.";
    public static final String NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE = "음수를 입력할 수 없습니다.";
    public static final String INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE = "로또 가격은 1000원 단위로 입력해야 합니다.";
    public static final String INVALID_MANUAL_LOTTOS_COUNT_EXCEPTION_MESSAGE = "수동 로또의 개수는 전체 로또의 개수를 넘어설 수 없습니다.";
    public static final String INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE = "로또 번호는 6개의 숫자를 ', '로 구분하여 입력해야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "1과 45 사이의 숫자를 입력해야 합니다.";
    public static final String DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE = "당첨 번호는 서로 달라야 합니다.";
    public static final String NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 볼의 값은 당첨 번호와 달라야 합니다.";

    private ExceptionMessages() {
    }
}
