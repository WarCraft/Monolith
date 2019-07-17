package gg.warcraft.monolith.app.util;

import gg.warcraft.monolith.api.math.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultMathUtilsTest {
    private static final float FLOAT_ERROR_MARGIN = 0.0000001f;
    private static final float TWO_PI = 2 * (float) Math.PI;

    private DefaultMathUtils defaultMathUtils;

    @Before
    public void beforeEach() {
        defaultMathUtils = new DefaultMathUtils();
    }

    @Test
    public void shouldCreateRandomVector() {
        for (int i = 0; i < 10; i += 1) {
            Vector3f vector = defaultMathUtils.randomVector();

            Assert.assertTrue(vector.getX() >= -1);
            Assert.assertTrue(vector.getX() <= 1);
            Assert.assertTrue(vector.getY() >= -1);
            Assert.assertTrue(vector.getY() <= 1);
            Assert.assertTrue(vector.getZ() >= -1);
            Assert.assertTrue(vector.getZ() <= 1);
        }
    }

    @Test
    public void shouldCreateRandomCircleVector() {
        for (int i = 0; i < 10; i += 1) {
            Vector3f vector = defaultMathUtils.randomCircleVector();

            Assert.assertEquals(0, vector.getY(), FLOAT_ERROR_MARGIN);
            Assert.assertEquals(1, vector.length(), FLOAT_ERROR_MARGIN);
        }
    }

    @Test
    public void shouldCreateRandomAngle() {
        for (int i = 0; i < 10; i += 1) {
            float angle = defaultMathUtils.randomAngle();

            Assert.assertTrue(angle >= 0);
            Assert.assertTrue(angle <= TWO_PI);
        }
    }
}
