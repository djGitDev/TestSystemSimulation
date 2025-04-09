package ca.uqam.mgl7230.tp3.part2;

import ca.uqam.mgl7230.tp3.part2.service.ExecuteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    private MockedStatic<ExecuteService> executeMock;

    @BeforeEach
    void setup() {
        executeMock = mockStatic(ExecuteService.class);
    }

    @AfterEach
    void tearDown() {
        executeMock.close();
    }

    @Test
    void instantiateApplicationClass() {
        // Given
//        executeMock.when(() -> ExecuteService.execute()).then((Answer<Void>) invocation -> null);

        // When
        Application application = new Application();

        // Then
        assertThat(application).isNotNull();
    }
}