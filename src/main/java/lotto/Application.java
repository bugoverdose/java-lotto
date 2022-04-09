package lotto;

import static lotto.view.InputView.requestBonusNumber;
import static lotto.view.InputView.requestManualsCount;
import static lotto.view.InputView.requestManualsNumbers;
import static lotto.view.InputView.requestTotalPrice;
import static lotto.view.InputView.requestWinningNumbers;
import static lotto.view.OutputView.printLottoResults;
import static lotto.view.OutputView.printPrizePriceRatio;
import static lotto.view.OutputView.printPurchaseInfo;

import lotto.controller.LottoController;
import lotto.dto.LottoCountsDto;
import lotto.domain.LottoGame;
import lotto.domain.LottoReferee;
import lotto.domain.Lottos;
import java.util.List;

public class Application {

    private static final LottoController controller = new LottoController();

    public static void main(String[] args) {
        LottoCountsDto countsDto = controller.initCountsDto(requestTotalPrice(), requestManualsCount());
        List<String> manualsNumbers = requestManualsNumbers(countsDto.getManuals());

        Lottos lottos = controller.initLottos(manualsNumbers, countsDto);
        printPurchaseInfo(lottos);

        LottoReferee referee = controller.initLottoReferee(requestWinningNumbers(), requestBonusNumber());
        LottoGame lottoGame = new LottoGame(lottos, referee);
        printLottoResults(lottoGame.getResultStatistics());
        printPrizePriceRatio(lottoGame.calculatePrizePriceRatio());
    }
}
