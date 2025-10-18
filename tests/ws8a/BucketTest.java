package ws8a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BucketTest {

    @Test
    public void testCreationZero() {
        Bucket b = new Bucket(0.0);
        assertEquals(0.0, b.getCapacity(), "After creating Bucket of capacity zero, b.getCapacity() returned incorrect value");
        assertEquals(0.0, b.getContents(), "After creating Bucket of capacity zero, b.getContents() returned incorrect value");
    }

    @Test
    public void testCreationNonZero() {
        Bucket b1 = new Bucket(10);
        Bucket b2 = new Bucket(1);

        assertEquals(10.0, b1.getCapacity(), "After creating Bucket b1 of capacity 10, b1.getCapacity() returned incorrect value");
        assertEquals(0.0, b1.getContents(), "After creating Bucket b1 of capacity 10, b1.getContents() returned incorrect value");
        assertEquals(1.0, b2.getCapacity(), "After creating Bucket b2 of capacity 1, b2.getCapacity() returned incorrect value");
        assertEquals(0.0, b2.getContents(), "After creating Bucket b2 of capacity 1, b2.getContents() returned incorrect value");
    }

    @Test
    public void testContentsOverflow() {
        Bucket b1 = new Bucket(10.0);
        Bucket b2 = new Bucket(1.0);

        b1.add(20.0);
        b2.add(20.0);
        double contents = b1.getContents();
        assertEquals(10.0, contents, "Bucket contents after overflow should be equal to capacity");
        contents = b2.getContents();
        assertEquals(1.0, contents, "Bucket contents after overflow should be equal to capacity");
    }

    @Test
    public void testContentsCumulative() {
        Bucket b1 = new Bucket(10.0);
        Bucket b2 = new Bucket(1.0);

        double c1 = 0.0;
        double c2 = 0.0;
        for (int i = 0; i < 30; i++) {
            b1.add(0.5);
            c1 += 0.5;
            if (c1 > 10) c1 = 10;
            b2.add(0.5);
            c2 += 0.5;
            if (c2 > 1.0) c2 = 1;
            assertEquals(c1, b1.getContents(), "Bucket b1.getContents()");
            assertEquals(c2, b2.getContents(), "Bucket b2.getContents()");
        }
    }

    @Test
    public void testEmpty() {
        Bucket b1 = new Bucket(10);
        Bucket b2 = new Bucket(1);

        b1.add(4.0);
        b2.add(2.0);

        assertEquals(4.0, b1.empty(), "Given Bucket b of capacity 10, after calling b.add(4), b.empty() returned incorrect value");
        assertEquals(0.0, b1.getContents(), "Given Bucket b of capacity 10, after calling b.empty(), b.getContents() returned incorrect value");
        assertEquals(1.0, b2.empty(), "Given Bucket b2 of capacity 1, after calling b2.add(2), b2.empty() returned incorrect value");
        assertEquals(0.0, b2.getContents(), "Given Bucket b2 of capacity 1, after calling b2.empty(), b2.getContents() returned incorrect value");
    }
}
