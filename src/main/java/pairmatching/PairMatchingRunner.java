package pairmatching;

import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public PairMatchingRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 기능 입력받기
        outputView.printFunctionSelectGuide();
        String function = inputView.readFunction();

        System.out.println(function);
    }
}
