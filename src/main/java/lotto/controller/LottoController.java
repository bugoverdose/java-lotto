package lotto.controller;

import static lotto.util.LottoCountUtils.getValidAutosCount;
import static lotto.util.LottoCountUtils.getValidTotalCount;
import static lotto.util.LottoNumberUtils.registerBonusNumber;
import static lotto.util.LottoNumberUtils.registerWinningNumbers;

import lotto.dto.LottoCountsDto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoReferee;
import lotto.domain.Lottos;
import java.util.List;

public class LottoController {

    public LottoCountsDto initCountsDto(int totalLottoPrice, int manualsCount) {
        int totalCount = getValidTotalCount(totalLottoPrice);
        int autosCount = getValidAutosCount(manualsCount, totalCount);

        return new LottoCountsDto(manualsCount, autosCount);
    }

    public Lottos initLottos(List<String> manualsRaw, LottoCountsDto countsDto) {
        return Lottos.of(manualsRaw, countsDto);
    }

    public LottoReferee initLottoReferee(String winningNumbersInput, int bonusBallValue) {
        List<LottoNumber> winningNumbers = registerWinningNumbers(winningNumbersInput);
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers, bonusBallValue);

        return new LottoReferee(winningNumbers, bonusNumber);
    }
}
