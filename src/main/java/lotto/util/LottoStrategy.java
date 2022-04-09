package lotto.util;

import lotto.domain.LottoNumber;
import java.util.List;

public interface LottoStrategy {

    List<LottoNumber> generateAutoNumbers();
}
