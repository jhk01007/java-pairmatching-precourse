package pairmatching.dto;

import java.util.Collections;
import java.util.List;

public class PairMatchingDto {
    private List<List<String>> pairMatchingResults; // 페어 매칭 결과(목록)

    public PairMatchingDto(List<List<String>> pairMatchingResults) {
        this.pairMatchingResults = pairMatchingResults;
    }

    public List<List<String>> getPairMatchingResults() {
        return Collections.unmodifiableList(pairMatchingResults);
    }
}
