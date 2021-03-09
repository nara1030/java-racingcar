package dev.djoon.racingcar;

import dev.djoon.racingcar.actor.Car;
import dev.djoon.racingcar.ui.ResultView;
import dev.djoon.racingcar.util.GameConstant;
import dev.djoon.racingcar.util.RandomNumbers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarGame {

  private final List<Car> carList;
  private final int loopTimes;

  public RacingCarGame(int carQuantity, int loopTimes) {
    this.carList = fillCarList(carQuantity);

    this.loopTimes = loopTimes;
  }

  public RacingCarGame(List<String> ownerNames, int loopTimes) {
    this.carList = fillCarList(ownerNames);

    this.loopTimes = loopTimes;
  }

  public void start(RandomNumbers random) {
    ResultView.printNewGame();
    for (int i = 0; i < loopTimes; i++) {
      carsMoveIfValid(random);

      ResultView.printCR();
    }
    ResultView.printWinner(findWinners());
  }

  public List<Car> getCarList() {
    return carList;
  }

  public List<Car> findWinners() {
    final int winnerScore = findWinnerScore();

    return carList.stream()
                  .filter(car -> car.getXPosition() == winnerScore)
                  .collect(Collectors.toList());
  }

  private int findWinnerScore() {
    return carList.stream()
                  .max(Comparator.comparingInt(Car::getXPosition))
                  .map(Car::getXPosition).orElse(0);
  }

  private void carsMoveIfValid(RandomNumbers random) {
    for (Car car : carList) {
      int randomValue = random.nextInt(GameConstant.RANDOM_BOUNDARY);
      car.moveIfValidCondition(randomValue);

      ResultView.printCarOwner(car);
      ResultView.printXPos(car.getXPosition());
    }
  }

  private List<Car> fillCarList(int carQuantity) {
    List<Car> filledCarList = new ArrayList<>();

    for (int i = 0; i < carQuantity; i++) {
      filledCarList.add(RacingCarFactory.create());
    }

    return filledCarList;
  }

  private List<Car> fillCarList(List<String> ownerNames) {
    List<Car> filledCarList = new ArrayList<>();

    for (String ownerName : ownerNames) {
      filledCarList.add(RacingCarFactory.createWithOwner(ownerName));
    }

    return filledCarList;
  }

}