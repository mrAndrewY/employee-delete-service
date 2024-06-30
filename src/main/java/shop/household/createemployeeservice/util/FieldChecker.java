package shop.household.createemployeeservice.util;

import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Objects;

public class FieldChecker {
    public static boolean hasNullsInFields(Object obj) {
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .map(field -> {
                    ReflectionUtils.makeAccessible(field);
                    try {
                        return field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .anyMatch(Objects::isNull);
    }
}
