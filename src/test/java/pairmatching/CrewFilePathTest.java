package pairmatching;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.impl.BackendCrewRepositoryImpl;
import pairmatching.repository.impl.FrontendCrewRepository;

class CrewFilePathTest {

    @Test
    @DisplayName("백엔드 크루들의 이름을 잘 읽어오는지 테스트한다.")
    void readBackendCrew_success() {
        CrewRepository backendCrewRepository = new BackendCrewRepositoryImpl();

        List<String> backendCrewNames = backendCrewRepository.findAllCrewNames();
        assertThat(backendCrewNames).isNotEmpty();
    }

    @Test
    @DisplayName("프론트엔드 크루들의 이름을 잘 읽어오는지 테스트한다.")
    void readFrontendCrew_success() {
        CrewRepository frontendCrewRepository = new FrontendCrewRepository();

        List<String> frontendCrewNames = frontendCrewRepository.findAllCrewNames();
        assertThat(frontendCrewNames).isNotEmpty();
    }

}
