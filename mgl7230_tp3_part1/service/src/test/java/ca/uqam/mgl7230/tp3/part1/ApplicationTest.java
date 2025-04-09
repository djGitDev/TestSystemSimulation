package ca.uqam.mgl7230.tp3.part1;

import ca.uqam.mgl7230.tp3.part1.config.ApplicationInitializer;
import ca.uqam.mgl7230.tp3.part1.service.ExecuteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    private MockedStatic<ApplicationInitializer> initializeMock;
    private MockedStatic<ExecuteService> executeMock;

    @BeforeEach
    void setup() {
        initializeMock = mockStatic(ApplicationInitializer.class);
        executeMock = mockStatic(ExecuteService.class);
    }

    @AfterEach
    void tearDown() {
        initializeMock.close();
        executeMock.close();
    }

    @Test
    void instantiateApplicationClass() {
        // Given
        initializeMock.when(ApplicationInitializer::init).then((Answer<Void>) invocation -> null);
        executeMock.when(ExecuteService::execute).then((Answer<Void>) invocation -> null);

        // When
        Application application = new Application();

        // Then
        assertThat(application).isNotNull();
    }
}