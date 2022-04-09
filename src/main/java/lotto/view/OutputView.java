package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String BLANK_SPACE = " ";
    private static final String OUTPUT_VIEW_SEPARATOR = "---------" + LINE_SEPARATOR;
    private static final String BOUGHT_MANUALS_COUNT_TEXT_FORMAT = LINE_SEPARATOR + "수동으로 %d장," + BLANK_SPACE;
    private static final String BOUGHT_RANDOM_COUNT_TEXT_FORMAT = "자동으로 %d개를 구매했습니다." + LINE_SEPARATOR;
    private static final String CHOSEN_LOTTO_NUMBERS_DELIMITER = ", ";
    private static final String CHOSEN_LOTTO_NUMBERS_TEXT_FORMAT = "[%s]" + LINE_SEPARATOR;
    private static final String LOTTO_RESULTS_ANNOUNCEMENT_TEXT = "당첨 통계" + LINE_SEPARATOR;
    private static final String MATCH_COUNT_TEXT_FORMAT = "%d개 일치";
    private static final String BONUS_BALL_MATCH_TEXT = ", 보너스 볼 일치";
    private static final String PRIZE_TEXT_FORMAT = "(%d원) -" + BLANK_SPACE;
    private static final String LOTTO_RESULTS_COUNT_TEXT_FORMAT = "%d개";
    private static final String PRIZE_PRICE_RATIO_TEXT_FORMAT = "총 수익률은 %.2f입니다.";

    private OutputView() {
    }

    public static void printPurchaseInfo(Lottos lottos) {
        StringBuilder builder = new StringBuilder();
        builder.append(format(BOUGHT_MANUALS_COUNT_TEXT_FORMAT, lottos.getManuals()))
                .append(format(BOUGHT_RANDOM_COUNT_TEXT_FORMAT, lottos.getAutos()));

        lottos.getLottos()
                .stream()
                .map(OutputView::formatChosenLottoNumbers)
                .forEach(builder::append);

        print(builder.toString());
    }

    private static String formatChosenLottoNumbers(Lotto lotto) {
        String lottoNumFormat = lotto.getChosenNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(CHOSEN_LOTTO_NUMBERS_DELIMITER));

        return format(CHOSEN_LOTTO_NUMBERS_TEXT_FORMAT, lottoNumFormat);
    }

    public static void printLottoResults(Map<LottoResult, Integer> resultStatistics) {
        String lottoResults = LINE_SEPARATOR
                + LOTTO_RESULTS_ANNOUNCEMENT_TEXT
                + OUTPUT_VIEW_SEPARATOR
                + getJoinedLottoResults(resultStatistics);

        print(lottoResults);
    }

    private static String getJoinedLottoResults(Map<LottoResult, Integer> resultStatistics) {
        return resultStatistics.keySet()
                .stream()
                .map(lottoResult -> formatLottoResult(lottoResult, resultStatistics.get(lottoResult)))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    private static String formatLottoResult(LottoResult lottoResult, int count) {
        return format(MATCH_COUNT_TEXT_FORMAT, lottoResult.getMatchCount())
                + bonusBallInfo(lottoResult)
                + format(PRIZE_TEXT_FORMAT, lottoResult.getPrize())
                + format(LOTTO_RESULTS_COUNT_TEXT_FORMAT, count);
    }

    private static String bonusBallInfo(LottoResult lottoResult) {
        if (lottoResult.getHasBonus()) {
            return BONUS_BALL_MATCH_TEXT;
        }
        return BLANK_SPACE;
    }

    public static void printPrizePriceRatio(float value) {
        print(format(PRIZE_PRICE_RATIO_TEXT_FORMAT, value));
    }

    private static String format(String stringFormat, Object value) {
        return String.format(stringFormat, value);
    }

    public static void print(String value) {
        System.out.println(value);
    }
}
