import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTests {
    @Test
    void testArea() {
        Rectangle r = new Rectangle(2, 3, 2);
        assertEquals(3, r.getArea());
    }
}
