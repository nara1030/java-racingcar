package com.nextstep.racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Race {
    private List<Car> carList = new ArrayList<>();
    private static final int THRESHOLD = 3;

    public Race(int carCount, int moveLimit) {
        for ( int ix = 0 ; ix < carCount ; ix ++ ) {
            carList.add(new Car(moveLimit));
        }
    }

    public List<Car> moveAndGet(Supplier<Integer> numberGenerator) {
        for (Car car : carList) {
            int number = numberGenerator.get();
            tryMove(car, number);
        }
        return carList;
    }

    private void tryMove(Car car, int number) {
        if (!isMove(number)) {
            return;
        }
        car.move();
    }

    private boolean isMove(int number) {
        return THRESHOLD < number;
    }

    public boolean isFinished() {
        boolean isFinished = false;
        for ( Car car : carList ) {
            isFinished = isFinished | car.isFinished();
        }
        return isFinished;
    }
}