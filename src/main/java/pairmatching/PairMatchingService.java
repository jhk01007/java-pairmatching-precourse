package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.dto.PairMatchingDto;
import pairmatching.repo.CrewRepository;
import pairmatching.repo.PairMatchingRepository;

import java.util.ArrayList;
import java.util.List;

import static pairmatching.util.ErrorMessage.NO_MATCHING_NUMBER_OF_CASE_ERROR;

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
     * 해당 조합 삭제
     */
    public void deleteByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        pairMatchingRepository.deleteByCourseAndLevelAndMission(course, level, mission);
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
        while (!crews.isEmpty()) {
            List<Crew> crewsInPair = pairCrew(level, crews); // 크루원 짝짓기
            crews.removeAll(crewsInPair); // 짝지어진 애들은 삭제
            newPairList.add(new Pair(crewsInPair)); // 페어 리스트에 추가
        }

        // 저장하기
        pairMatchingRepository.save(new PairMatching(newPairList, course, level, mission));

        // DTO 반환
        return new PairMatchingDto(createPairMatchingDto(newPairList));
    }

    private List<Crew> pairCrew(Level level, List<Crew> crews) {
        int tryCount = 0;
        while (tryCount < MAX_TRY) {
            List<Crew> crewsInPair = new ArrayList<>();
            tryCount++;

            List<Crew> shuffled = Randoms.shuffle(crews); // Crew 리스트 셔플하기

            // 짝짓기
            crewsInPair.add(shuffled.get(0));
            crewsInPair.add(shuffled.get(1));
            if(crews.size() == 3) { // 만약 3명이 남은 경우 3명이 짝
                crewsInPair.add(shuffled.get(2));
            }

            // 앞에서 해당 크루원들이 같은 레벨에서 이미 만난적이 있는지
            if(!pairMatchingRepository.existByCrewsAndLevel(crewsInPair, level)) { // 없다면 반환
                return crewsInPair;
            }
        }
        throw new IllegalArgumentException(NO_MATCHING_NUMBER_OF_CASE_ERROR.getMessage());
    }

    private static List<List<String>> createPairMatchingDto(List<Pair> newPairList) {
        List<List<String>> pairMatchingResults = new ArrayList<>(); // 페어 매칭 결과(목록)
        for (Pair newPair : newPairList) {
            List<String> crewNamesInPair = new ArrayList<>();
            for (Crew crew : newPair.getCrews()) {
                crewNamesInPair.add(crew.getName());
            }
            pairMatchingResults.add(crewNamesInPair);
        }
        return pairMatchingResults;
    }
}
