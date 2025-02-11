package lotto.domain;

import static lotto.common.TestUtils.createCountsDto;
import static lotto.common.TestUtils.createNewLotto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private static final int MANUAL_LOTTOS_COUNT = 3;
    private static final int AUTOS_COUNT = 5;
    private static final List<String> manualsRaw = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        manualsRaw.add("1, 2, 3, 4, 5, 6");
        manualsRaw.add("1, 2, 3, 4, 5, 16");
        manualsRaw.add("1, 2, 3, 4, 5, 26");
    }

    @Test
    void of_createsTheSameNumberOfLottosAsManualLottosSizeAndRandomCount() {
        Lottos lottos = Lottos.of(manualsRaw, createCountsDto(MANUAL_LOTTOS_COUNT, AUTOS_COUNT));

        assertThat(lottos.getLottos().size()).isEqualTo(MANUAL_LOTTOS_COUNT + AUTOS_COUNT);
        assertThat(lottos.getManuals()).isEqualTo(MANUAL_LOTTOS_COUNT);
        assertThat(lottos.getAutos()).isEqualTo(AUTOS_COUNT);
    }

    @Test
    void of_allNewLottosAreDifferent() {
        Lottos lottos = Lottos.of(manualsRaw, createCountsDto(MANUAL_LOTTOS_COUNT, AUTOS_COUNT));

        Set<Lotto> noDuplicateLottoSet = new HashSet<>(lottos.getLottos());
        assertThat(noDuplicateLottoSet.size())
                .isEqualTo(lottos.getLottos().size());
    }

    @Test
    void of_passesOnRandomOnly() {
        Lottos lottos = Lottos.of(new ArrayList<>(), createCountsDto(0, AUTOS_COUNT));

        assertThat(lottos.getLottos().size()).isEqualTo(AUTOS_COUNT);
        assertThat(lottos.getManuals()).isEqualTo(0);
        assertThat(lottos.getAutos()).isEqualTo(AUTOS_COUNT);
    }

    @Test
    void of_passesOnManualOnly() {
        Lottos lottos = Lottos.of(manualsRaw, createCountsDto(MANUAL_LOTTOS_COUNT, 0));
        assertThat(lottos.getLottos().size()).isEqualTo(MANUAL_LOTTOS_COUNT);
        assertThat(lottos.getManuals()).isEqualTo(MANUAL_LOTTOS_COUNT);
        assertThat(lottos.getAutos()).isEqualTo(0);
    }

    @Test
    void getLottos_throwsExceptionOnDirectModification() {
        Lottos lottos = Lottos.of(manualsRaw, createCountsDto(MANUAL_LOTTOS_COUNT, AUTOS_COUNT));

        Lotto newLotto = createNewLotto(1, 2, 3, 4, 5, 6);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> lottos.getLottos().add(newLotto));
    }
}
