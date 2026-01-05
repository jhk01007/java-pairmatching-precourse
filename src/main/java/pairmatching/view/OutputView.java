package pairmatching.view;

import pairmatching.dto.PairMatchingDto;

import java.util.List;

public class OutputView {

    public void printFunctionSelectGuide() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public void printMatchingResult(PairMatchingDto pairMatchingDto) {
        System.out.println("\n페어 매칭 결과입니다.");
        for (List<String> pairMatchingResult : pairMatchingDto.getPairMatchingResults()) {
            StringBuilder sb = new StringBuilder();

            for (String crewName : pairMatchingResult) {
                sb.append(crewName);
                sb.append(" : ");
            }
            sb.delete(sb.length() - 3, sb.length());
            System.out.println(sb);
        }
    }

    public void printInitDone() {
        System.out.println("\n초기화 되었습니다.");
    }

    public void printNewLine() {
        System.out.println();
    }
}
