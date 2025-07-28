package org.soujava.demos.mongodb.document;

import jakarta.inject.Inject;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.core.Converters;
import org.eclipse.jnosql.mapping.document.DocumentTemplate;
import org.eclipse.jnosql.mapping.document.spi.DocumentExtension;
import org.eclipse.jnosql.mapping.reflection.Reflections;
import org.eclipse.jnosql.mapping.reflection.spi.ReflectionEntityMetadataExtension;
import org.eclipse.jnosql.mapping.semistructured.EntityConverter;
import org.jboss.weld.junit5.auto.AddExtensions;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@EnableAutoWeld
@AddPackages(value = {Database.class, EntityConverter.class, DocumentTemplate.class})
@AddPackages(Room.class)
@AddPackages(ManagerSupplier.class)
@AddPackages(Reflections.class)
@AddPackages(Converters.class)
@AddExtensions({ReflectionEntityMetadataExtension.class, DocumentExtension.class})
class RoomServiceTest {

    @Inject
    private RoomService service;


    @Test
    void shouldSaveRoom() {
        service.newRoom(room);
    }



    private static Room getRoom() {
        return Room.builder()
                .id(UUID.randomUUID().toString())
                .roomNumber(FAKER.number().numberBetween(100, 999))
                .type(randomEnum(RoomType.class))
                .status(randomEnum(RoomStatus.class))
                .cleanStatus(randomEnum(CleanStatus.class))
                .smokingAllowed(FAKER.bool().bool())
                .build();
    }
}
