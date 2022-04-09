package lotto.validator;

import static lotto.common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static lotto.constant.ExceptionMessages.DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.INVALID_MANUAL_LOTTOS_COUNT_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static lotto.constant.ExceptionMessages.NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static lotto.validator.NumberValidators.validateAndParseNumber;
import static lotto.validator.NumberValidators.validateLottoNumberRange;
import static lotto.validator.NumberValidators.validateManualLottosCount;
import static lotto.validator.NumberValidators.validateNoDuplicateInList;
import static lotto.validator.NumberValidators.validateNoDuplicates;
import static lotto.validator.NumberValidators.validateTotalLottoPriceUnit;
import static lotto.validator.NumberValidators.validateLottoNumbersSize;

import lotto.domain.Lotto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberValidatorsTest {

    @Test
    void validateAndParseNumber_returnsIntOnPass() {
        int parsedValue = validateAndParseNumber("10");
        assertThat(parsedValue).isEqualTo(10);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(strings = {"10 ", " 10", "  10  "})
    void validateAndParseNumber_passesOnNumberWithBlank(String value) {
        int parsedValue = validateAndParseNumber(value);
        assertThat(parsedValue).isEqualTo(10);
    }

    @Test
    void validateAndParseNumber_throwIllegalArgumentExceptionOnFail() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateAndParseNumber("!"))
                .withMessageMatching(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @Test
    void validateTotalLottoPriceUnit_passesOnPositiveNumberInput() {
        assertThatNoException()
                .isThrownBy(() -> validateTotalLottoPriceUnit(Lotto.PRICE * 10));
    }

    @Test
    void validateTotalLottoPriceUnit_passesOnZeroInput() {
        assertThatNoException()
                .isThrownBy(() -> validateTotalLottoPriceUnit(0));
    }

    @Test
    void validateTotalLottoPriceUnit_failOnNegativeNumberInput() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateTotalLottoPriceUnit(Lotto.PRICE * -10))
                .withMessageMatching(NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @Test
    void validateTotalLottoPriceUnit_failIfChangesExist() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateTotalLottoPriceUnit(Lotto.PRICE * 10 + 1))
                .withMessageMatching(INVALID_TOTAL_LOTTO_PRICE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateManualLottosCount_passesOnPositiveNumberOverTotalInput() {
        assertThatNoException()
                .isThrownBy(() -> validateManualLottosCount(10, 20));
    }

    @Test
    void validateManualLottosCount_passesInZeroInputs() {
        assertThatNoException()
                .isThrownBy(() -> validateManualLottosCount(0, 20));
        assertThatNoException()
                .isThrownBy(() -> validateManualLottosCount(0, 0));
    }

    @Test
    void validateManualLottosCount_failOnNegativeNumberInput() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateManualLottosCount(-10, 20))
                .withMessageMatching(NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateManualLottosCount(-30, -20))
                .withMessageMatching(NEGATIVE_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @Test
    void validateManualLottosCount_failIfManualsIsOverTotal() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateManualLottosCount(20, 10))
                .withMessageMatching(INVALID_MANUAL_LOTTOS_COUNT_EXCEPTION_MESSAGE);
    }


    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {1, 20, 45})
    void validateLottoNumberRange_passIfInRange(int value) {
        assertThatNoException()
                .isThrownBy(() -> validateLottoNumberRange(value));
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(ints = {0, 46})
    void validateLottoNumberRange_throwIllegalArgumentExceptionOnFail(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateLottoNumberRange(value))
                .withMessageMatching(INVALID_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateLottoNumbersSize_passOnValidListSize() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateLottoNumbersSize(nums));
    }

    @Test
    void validateLottoNumbersSize_throwsIllegalExceptionOnInvalidListSize() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateLottoNumbersSize(nums))
                .withMessageMatching(INVALID_LOTTO_NUMBERS_SIZE_EXCEPTION_MESSAGE);
    }

    @Test
    void validateNoDuplicates_passOnNoDuplicates() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateNoDuplicates(nums));
    }

    @Test
    void validateNoDuplicates_throwIllegalArgumentExceptionOnFail() {
        List<Integer> nums = Arrays.asList(1, 1, 2, 3, 4, 5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateNoDuplicates(nums))
                .withMessageMatching(DUPLICATE_WINNING_NUMBER_EXCEPTION_MESSAGE);
    }

    @Test
    void validateNoDuplicateInList_passOnNoDuplicates() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> validateNoDuplicateInList(7, nums));
    }

    @Test
    void validateNoDuplicateInList_throwIllegalArgumentExceptionOnFail() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateNoDuplicateInList(6, nums))
                .withMessageMatching(NOT_UNIQUE_BONUS_NUMBER_EXCEPTION_MESSAGE);
    }
}
