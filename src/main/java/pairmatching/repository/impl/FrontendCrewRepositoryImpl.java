package pairmatching.repository.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.repository.CrewFilePath;
import pairmatching.repository.CrewRepository;

public class FrontendCrewRepositoryImpl implements CrewRepository {

    @Override
    public List<String> findAllCrewNames() {
        try (
                FileInputStream fileInputStream = new FileInputStream(CrewFilePath.FRONTEND_CREW_FILE_PATH.getPath());
                BufferedReader crewNameReader = new BufferedReader(new InputStreamReader(fileInputStream))
        ) {
            return Arrays.stream(readAllLine(crewNameReader).split("\n"))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException("백엔드 크루 이름을 읽어오는 과정에서 오류가 발생하였습니다.");
        }
    }

}
