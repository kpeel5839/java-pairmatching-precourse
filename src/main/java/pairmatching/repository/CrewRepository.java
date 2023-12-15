package pairmatching.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface CrewRepository {

    List<String> findAllCrewNames();

    default String readAllLine(BufferedReader bufferedReader) throws IOException {
        StringBuilder allLine = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            allLine.append(line)
                    .append("\n");
        }

        return allLine.toString();
    }

}
