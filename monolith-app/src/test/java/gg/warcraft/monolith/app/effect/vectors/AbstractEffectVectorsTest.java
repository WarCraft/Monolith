package gg.warcraft.monolith.app.effect.vectors;

import gg.warcraft.monolith.api.effect.EffectVectors;
import gg.warcraft.monolith.api.math.Vector3f;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AbstractEffectVectorsTest {

    @Test
    public void addOffset_shouldReturnOffsetVectors() {
        // Given
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f target = new Vector3f(10, 0, 0);
        int count = 10;
        Vector3f offset = new Vector3f(2, 2, 2);

        EffectVectors vectors = new LineVectors(origin, target, count);
        Vector3f[] expectedVectors = new Vector3f[]{
                new Vector3f(2, 2, 2),
                new Vector3f(3, 2, 2),
                new Vector3f(4, 2, 2),
                new Vector3f(5, 2, 2),
                new Vector3f(6, 2, 2),
                new Vector3f(7, 2, 2),
                new Vector3f(8, 2, 2),
                new Vector3f(9, 2, 2),
                new Vector3f(10, 2, 2),
                new Vector3f(11, 2, 2),
                new Vector3f(12, 2, 2),
        };

        // When
        EffectVectors result = vectors.addOffset(offset);
        List<Vector3f> resultVectors = new ArrayList<>(result.getVectors());

        // Then
        for (int i = 0; i < expectedVectors.length; i += 1) {
            assertEquals(expectedVectors[i], resultVectors.get(i));
        }
    }

    @Test
    public void rotateAroundAxisX_shouldReturnRotatedVectors() {
        // Given
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f target = new Vector3f(0, 10, 0);
        int count = 10;
        float rotation = -0.5f * (float) Math.PI;

        EffectVectors vectors = new LineVectors(origin, target, count);
        Vector3f[] expectedVectors = new Vector3f[]{
                new Vector3f(0, 0, -0f),
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -2),
                new Vector3f(0, 0, -3),
                new Vector3f(0, 0, -4),
                new Vector3f(0, 0, -5),
                new Vector3f(0, 0, -6),
                new Vector3f(0, 0, -7),
                new Vector3f(0, 0, -8),
                new Vector3f(0, 0, -9),
                new Vector3f(0, 0, -10),
        };

        // When
        EffectVectors result = vectors.rotateAroundAxisX(rotation);
        List<Vector3f> resultVectors = new ArrayList<>(result.getVectors());

        // Then
        for (int i = 0; i < expectedVectors.length; i += 1) {
            assertEquals(expectedVectors[i], resultVectors.get(i));
        }
    }

    @Test
    public void rotateAroundAxisY_shouldReturnRotatedVectors() {
        // Given
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f target = new Vector3f(0, 0, 10);
        int count = 10;
        float rotation = -0.5f * (float) Math.PI;

        EffectVectors vectors = new LineVectors(origin, target, count);
        Vector3f[] expectedVectors = new Vector3f[]{
                new Vector3f(-0f, 0, 0),
                new Vector3f(-1, 0, 0),
                new Vector3f(-2, 0, 0),
                new Vector3f(-3, 0, 0),
                new Vector3f(-4, 0, 0),
                new Vector3f(-5, 0, 0),
                new Vector3f(-6, 0, 0),
                new Vector3f(-7, 0, 0),
                new Vector3f(-8, 0, 0),
                new Vector3f(-9, 0, 0),
                new Vector3f(-10, 0, 0),
        };

        // When
        EffectVectors result = vectors.rotateAroundAxisY(rotation);
        List<Vector3f> resultVectors = new ArrayList<>(result.getVectors());

        // Then
        for (int i = 0; i < expectedVectors.length; i += 1) {
            assertEquals(expectedVectors[i], resultVectors.get(i));
        }
    }

    @Test
    public void rotateAroundAxisZ_shouldReturnRotatedVectors() {
        // Given
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f target = new Vector3f(-10, 0, 0);
        int count = 10;
        float rotation = -0.5f * (float) Math.PI;

        EffectVectors vectors = new LineVectors(origin, target, count);
        Vector3f[] expectedVectors = new Vector3f[]{
                new Vector3f(0, -0f, 0),
                new Vector3f(0, 1, 0),
                new Vector3f(0, 2, 0),
                new Vector3f(0, 3, 0),
                new Vector3f(0, 4, 0),
                new Vector3f(0, 5, 0),
                new Vector3f(0, 6, 0),
                new Vector3f(0, 7, 0),
                new Vector3f(0, 8, 0),
                new Vector3f(0, 9, 0),
                new Vector3f(0, 10, 0),
        };

        // When
        EffectVectors result = vectors.rotateAroundAxisZ(rotation);
        List<Vector3f> resultVectors = new ArrayList<>(result.getVectors());

        // Then
        for (int i = 0; i < expectedVectors.length; i += 1) {
            assertEquals(expectedVectors[i], resultVectors.get(i));
        }
    }
}
