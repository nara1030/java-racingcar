package racing.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CarTest {
    private Car car;

    @BeforeEach
    void 차를_생성() {
        car = new Car("test");
    }

    @Test
    void 차가_전진() {
        car.move(() -> true);
        assertThat(car.isLocatedAt(1)).isTrue();
    }

    @Test
    void 차가_정지() {
        car.move(() -> false);
        assertThat(car.isLocatedAt(0)).isTrue();
    }

    @Test
    void 차의_누적거리() {
        assertAll(
                () -> car.move(() -> true),
                () -> assertThat(car.isLocatedAt(1)).isTrue(),
                () -> car.move(() -> false),
                () -> assertThat(car.isLocatedAt(1)).isTrue(),
                () -> car.move(() -> true),
                () -> assertThat(car.isLocatedAt(2)).isTrue(),
                () -> car.move(() -> true),
                () -> assertThat(car.isLocatedAt(3)).isTrue(),
                () -> car.move(() -> false),
                () -> assertThat(car.isLocatedAt(3)).isTrue()
        );
    }
}
