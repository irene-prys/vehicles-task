package demo.task.vehicles.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeoCalculatorTest {
    @Test
    public void publicVoidShouldCalculate() {
        final int rounded_value_in_redis = 648;
        assertEquals(rounded_value_in_redis, Math.round(GeoCalculator.calculateDistance(0, 0, 5, 3)));
    }
}
