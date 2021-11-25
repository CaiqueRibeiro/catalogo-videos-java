package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CastMemberTests {

  @Test
  public void throwIllegalArgumentExceptionWhenNameIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new CastMember((String) null));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenNameIsBlank() {
    assertThrows(IllegalArgumentException.class, () -> new CastMember(""));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenNameIsNullAndTypeIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new CastMember(null, null));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenNameIsBlankAndTypeIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new CastMember("", null));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenTypeIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new CastMember("Cast Member 1", null));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenTypeIsNotValid() throws IllegalArgumentException {
    assertThrows(IllegalArgumentException.class, () -> {
      CastMember entity = mock(CastMember.class);

      entity.setName("Caique");
      doThrow(IllegalArgumentException.class).when(entity).setType(CastMemberType.TYPE2);
      entity.setType(CastMemberType.TYPE2);
    });
  }

  @Test
  public void createCastMemberWithNameTest() {
    final CastMember entity = new CastMember("Caique");

    assertNotNull(entity);
    assertEquals(entity.getName(), "Caique");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertNull(entity.getType());
  }

  @Test
  @EnumSource(value = CastMemberType.class)
  public void createCastMemberWithNameAndTypeTest() {
    final CastMember entity = new CastMember("Caique", CastMemberType.TYPE1);

    assertNotNull(entity);
    assertEquals(entity.getName(), "Caique");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertTrue(CastMemberType.valueOf(entity.getType()));
    assertEquals(entity.getType(), CastMemberType.TYPE1);
  }
}
