import static view.InputView.requestBonusNumber;
import static view.InputView.requestTotalLottoPrice;
import static view.InputView.requestWinningNumbers;
import static view.OutputView.printLottoResults;
import static view.OutputView.printPurchaseInfo;

import controller.LottoController;
import domain.LottoGame;
import domain.LottoReferee;
import domain.Lottos;

public class Application {

    private static final LottoController controller = new LottoController();

    public static void main(String[] args) {
        Lottos lottos = controller.initCustomerLottos(requestTotalLottoPrice());
        printPurchaseInfo(lottos.getLottos());

        LottoReferee referee = controller.initLottoReferee(requestWinningNumbers(), requestBonusNumber());

        LottoGame lottoGame = new LottoGame(lottos, referee);
        printLottoResults(lottoGame.getResultStatistics());
        printLottoResults(lottoGame.calculateProfitRatio());
    }
}
