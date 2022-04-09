package lotto.util;

import static lotto.domain.Lotto.LOTTO_NUMBERS_SIZE;
import static lotto.domain.LottoNumber.allLottoNumbers;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoUtils {

    public static List<Lotto> getValidManuals(List<String> manualsRaw) {
        if (manualsRaw.isEmpty()) {
            return List.of();
        }

        return manualsRaw.stream()
                .map(LottoNumberUtils::getValidLottoNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public static List<Lotto> generateAutos(int autosCount, LottoStrategy strategy) {
        return Stream.generate(() -> new Lotto(strategy.generateAutoNumbers()))
                .limit(autosCount)
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> generateAutoNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(allLottoNumbers);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_NUMBERS_SIZE);
    }
}
