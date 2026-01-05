package pairmatching;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.dto.MissionInfoDto;
import pairmatching.dto.PairMatchingDto;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;


public class PairMatchingRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairMatchingRunner(InputView inputView, OutputView outputView, PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        // 기능 입력받기
        String function = "";
        while (!function.equals("Q")) {
            outputView.printFunctionSelectGuide();
            function = inputView.readFunction();
            System.out.println();
            if (function.equals("1")) { // 1번 페어 매칭을 선택한 경우
                String missionInfo = inputView.readMissionInfo(); // 과정, 레벨, 미션 입력
                MissionInfoDto missionInfoDto = InputParser.parseMissionInfo(missionInfo);

                // 기존에 해당조합으로 맺어진 페어가 있는지 확인
                boolean isExist = pairMatchingService.isExistByCourseAndLevelAndMission(
                        Course.of(missionInfoDto.getCourse()),
                        Level.of(missionInfoDto.getLevel()),
                        Mission.of(missionInfoDto.getMission(), Level.of(missionInfoDto.getLevel())));

                boolean isRematch = false;
                if (isExist) { // 있다면, 다시 매칭할거냐고 물어보기
                    isRematch = InputParser.parseRematchFlag(inputView.readRematching());
                }

                if (isRematch || !isExist) { // 다시 매칭하거나 아예 새로 매칭하는 경우
                    PairMatchingDto pairMatchingDto = pairMatchingService.createPairMatching(
                            Course.of(missionInfoDto.getCourse()),
                            Level.of(missionInfoDto.getLevel()),
                            Mission.of(missionInfoDto.getMission(), Level.of(missionInfoDto.getLevel())),
                            isRematch
                    ); // 매치 생성
                    // 생성된 매치 출력
                    outputView.printMatchingResult(pairMatchingDto);
                }
            } else if (function.equals("2")) {
                String missionInfo = inputView.readMissionInfo(); // 과정, 레벨, 미션 입력
                MissionInfoDto missionInfoDto = InputParser.parseMissionInfo(missionInfo);

                PairMatchingDto pairMatchingDto = pairMatchingService.findByCourseAndLevelAndMission(
                        Course.of(missionInfoDto.getCourse()),
                        Level.of(missionInfoDto.getLevel()),
                        Mission.of(missionInfoDto.getMission(), Level.of(missionInfoDto.getLevel()))
                ); // 해당 과정, 레벨, 미션 조합의 페어매칭 조회
                outputView.printMatchingResult(pairMatchingDto); // 출력

            } else if (function.equals("3")) {
                pairMatchingService.initializePairMatching();
                outputView.printInitDone();
            }
            outputView.printNewLine();
        }
    }
}
