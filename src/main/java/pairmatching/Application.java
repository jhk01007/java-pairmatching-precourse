package pairmatching;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new PairMatchingRunner(
                new InputView(),
                new OutputView()
        ).run();
    }
}
