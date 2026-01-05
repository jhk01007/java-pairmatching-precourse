package pairmatching;

import pairmatching.repo.CrewRepository;
import pairmatching.repo.PairMatchingRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new PairMatchingRunner(
                new InputView(),
                new OutputView(),
                new PairMatchingService(new PairMatchingRepository(), new CrewRepository())
        ).run();
    }
}
