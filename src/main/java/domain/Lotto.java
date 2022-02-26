package domain;

import static validator.NumberValidators.validateLottoNumbersSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBERS_SIZE = 6;
    private static final List<LottoNumber> allLottoNumbers = IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
            .boxed()
            .map(LottoNumber::of)
            .collect(Collectors.toList());

    private final List<LottoNumber> chosenNumbers;

    public Lotto() {
        List<LottoNumber> lottoNums = generateRandomLottoNumbers();
        this.chosenNumbers = getSortedLottoNumbers(lottoNums);
    }

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbersSize(lottoNumbers);
        this.chosenNumbers = getSortedLottoNumbers(lottoNumbers);
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(allLottoNumbers);
        return new ArrayList<>(allLottoNumbers.subList(0, LOTTO_NUMBERS_SIZE));
    }

    private List<LottoNumber> getSortedLottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        return Collections.unmodifiableList(lottoNumbers);
    }

    public List<LottoNumber> getChosenNumbers() {
        return chosenNumbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + chosenNumbers + '}';
    }
}
