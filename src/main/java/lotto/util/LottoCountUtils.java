package lotto.util;

import static lotto.validator.NumberValidators.validateManualLottosCount;
import static lotto.validator.NumberValidators.validateTotalLottoPriceUnit;

import lotto.domain.Lotto;

public class LottoCountUtils {

    public static int getValidTotalCount(int totalLottoPrice) {
        validateTotalLottoPriceUnit(totalLottoPrice);
        return totalLottoPrice / Lotto.PRICE;
    }

    public static int getValidAutosCount(int manualsCount, int totalCount) {
        validateManualLottosCount(manualsCount, totalCount);
        return totalCount - manualsCount;
    }
}
