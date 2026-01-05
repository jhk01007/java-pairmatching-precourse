package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readFunction() {
        return Console.readLine();
    }

    public String readMissionInfo() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
        return Console.readLine();
    }
}
