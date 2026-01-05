package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.dto.PairMatchingDto;
import pairmatching.repo.CrewRepository;
import pairmatching.repo.PairMatchingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pairmatching.util.ErrorMessage.*;

public class PairMatchingService {

    private final PairMatchingRepository pairMatchingRepository;
    private final CrewRepository crewRepository;

    private static final int MAX_TRY = 3;

    public PairMatchingService(PairMatchingRepository pairMatchingRepository, CrewRepository crewRepository) {
        this.pairMatchingRepository = pairMatchingRepository;
        this.crewRepository = crewRepository;
    }

    /**
     * 기존에 해당조합으로 맺어진 페어가 있는지
     */
    public boolean isExistByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        return pairMatchingRepository.existByCourseAndLevelAndMission(
                course,
                level,
                mission
        );
    }

    /**
     * 새로운 페어 매칭 생성
     */
    public PairMatchingDto createPairMatching(Course course, Level level, Mission mission, boolean isRematch) {
        if (isRematch) { // 만약 재생성이라면 먼저 삭제
            pairMatchingRepository.deleteByCourseAndLevelAndMission(course, level, mission);
        }

        // 크루원 리스트 읽어오기
        List<Crew> crews = crewRepository.findByCourse(course);

        List<Pair> newPairList = new ArrayList<>();
        List<String> crewNameList = crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());

        // 크루원 짝짓기
        List<String> shuffled = Randoms.shuffle(crewNameList); // Crew 리스트 셔플하기

        for (int i = 0; i < shuffled.size(); i += 2) {
            int tryCount = 0;
            while (tryCount < MAX_TRY) {
                List<Crew> crewsInPair = new ArrayList<>();
                tryCount++;

                // 짝짓기
                crewsInPair.add(findCrewByName(shuffled.get(i)));
                crewsInPair.add(findCrewByName(shuffled.get(i + 1)));
                if (crews.size() % 2 == 1 && i == shuffled.size() - 3) { // 만약 3명이 남은 경우 3명이 짝
                    crewsInPair.add(findCrewByName(shuffled.get(i + 2)));
                }

                // 앞에서 해당 크루원들이 같은 레벨에서 이미 만난적이 있는지
                if (!pairMatchingRepository.existByCrewsAndLevel(crewsInPair, level)) { // 없다면
                    newPairList.add(new Pair(crewsInPair));
                    break;
                }
            }

            if(tryCount == MAX_TRY) { // 최대 횟수 넘으면
                throw new IllegalArgumentException(NO_MATCHING_NUMBER_OF_CASE_ERROR.getMessage());
            }
        }

        // 저장하기
        pairMatchingRepository.save(new PairMatching(newPairList, course, level, mission));

        // DTO 반환
        return createPairMatchingDto(newPairList);
    }

    private Crew findCrewByName(String name) {
        return crewRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(CREW_NOT_FOUND_ERROR.getMessage()));
    }

    private static PairMatchingDto createPairMatchingDto(List<Pair> newPairList) {
        List<List<String>> pairMatchingResults = new ArrayList<>(); // 페어 매칭 결과(목록)
        for (Pair newPair : newPairList) {
            List<String> crewNamesInPair = new ArrayList<>();
            for (Crew crew : newPair.getCrews()) {
                crewNamesInPair.add(crew.getName());
            }
            pairMatchingResults.add(crewNamesInPair);
        }
        return new PairMatchingDto(pairMatchingResults);
    }

    /**
     * 특정 과정, 레벨, 미션 조합의 페어 매칭 찾기
     */
    public PairMatchingDto findByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        PairMatching pairMatching = pairMatchingRepository.findByCourseAndLevelAndMission(course, level, mission)
                .orElseThrow(() -> new IllegalArgumentException(PAIR_MATCHING_NOT_FOUND_ERROR.getMessage()));

        return createPairMatchingDto(pairMatching.getPairs());
    }

    /**
     * 모든 페어 매칭 초기화
     */
    public void initializePairMatching() {
        pairMatchingRepository.deleteAll();
    }
}
