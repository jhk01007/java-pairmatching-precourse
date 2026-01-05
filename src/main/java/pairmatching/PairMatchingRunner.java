package pairmatching;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.dto.MissionInfoDto;
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
        outputView.printFunctionSelectGuide();
        String function = inputView.readFunction();

        
        if(function.equals("1")) { // 1번 페어 매칭을 선택한 경우
            outputView.printMissionInfo(); // 과정, 미션 출력
            String missionInfo  = inputView.readMissionInfo(); // 과정, 레벨, 미션 입력

            MissionInfoDto missionInfoDto = InputParser.parseMissionInfo(missionInfo);
            // TODO: 실제 존재하는 과정, 레벨, 미션인지 검증

            // 기존에 해당조합으로 맺어진 페어가 있는지 확인
            boolean isExist = pairMatchingService.isExistByCourseAndLevelAndMission(
                    Course.of(missionInfoDto.getCourse()),
                    Level.of(missionInfoDto.getLevel()),
                    Mission.of(missionInfoDto.getMission(), Level.of(missionInfoDto.getLevel())));

            boolean isRematch = false;
            if(isExist) { // 있다면, 다시 매칭할거냐고 물어보기
                isRematch = InputParser.parseRematchFlag(inputView.readRematching());
            }
            if(isRematch) {
               pairMatchingService.deleteByCourseAndLevelAndMission(
                       Course.of(missionInfoDto.getCourse()),
                       Level.of(missionInfoDto.getLevel()),
                       Mission.of(missionInfoDto.getMission(), Level.of(missionInfoDto.getLevel())));// 기존꺼 삭제
            }

            // 매치 생성




        } else if(function.equals("2")) {
            
        } else if(function.equals("3")) {
            
        } else if (function.equals("Q")) {
            
        }
    }
}
