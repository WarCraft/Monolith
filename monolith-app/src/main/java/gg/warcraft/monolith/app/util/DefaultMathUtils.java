package gg.warcraft.monolith.app.util;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.util.MathUtils;

import java.util.Random;

public class DefaultMathUtils implements MathUtils {
    private static final float TWO_PI = 2 * (float) Math.PI;

    final Random random;

    @Inject
    public DefaultMathUtils() {
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public Vector3f randomVector() {
        float x = random.nextFloat() * 2f - 1f;
        float y = random.nextFloat() * 2f - 1f;
        float z = random.nextFloat() * 2f - 1f;
        Vector3f vector = new Vector3f(x, y, z);
        return vector.normalize();
    }

    @Override
    public Vector3f randomCircleVector() {
        float radian = randomAngle();
        float x = (float) Math.cos(radian);
        float z = (float) Math.sin(radian);
        return new Vector3f(x, 0f, z);
    }

    @Override
    public float randomAngle() {
        return random.nextFloat() * TWO_PI;
    }
}
