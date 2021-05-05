package guru.springframework;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class InlineMockTest {

    @Test
    void testInlineMock() {
        Map map = Mockito.mock(Map.class);
        Assertions.assertEquals(0, map.size());
    }
}
