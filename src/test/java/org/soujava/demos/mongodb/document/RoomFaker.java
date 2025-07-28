package org.soujava.demos.mongodb.document;

import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

final class RoomFaker {

    private static final Faker FAKER = new Faker();

    static Room getRoom() {
        return Room.builder()
                .id(UUID.randomUUID().toString())
                .roomNumber(FAKER.number().numberBetween(100, 999))
                .type(randomEnum(RoomType.class))
                .status(randomEnum(RoomStatus.class))
                .cleanStatus(randomEnum(CleanStatus.class))
                .smokingAllowed(FAKER.bool().bool())
                .build();
    }

    static <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        int index = ThreadLocalRandom.current().nextInt(constants.length);
        return constants[index];
    }

    static Stream<Arguments> room() {
        return Stream.of(Arguments.of(RoomFaker.getRoom(), Arguments.of(RoomFaker.getRoom(),
                Arguments.of(RoomFaker.getRoom()))));
    }
}
