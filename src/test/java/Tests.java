import be.seeseemelk.mockbukkit.MockBukkit;
import dev.s7a.base64.Base64ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Tests {
    @BeforeEach
    void setup() {
        MockBukkit.mock();
    }

    @AfterEach
    void teardown() {
        MockBukkit.unmock();
    }

    @Test
    void test_simple() {
        String base64 = Base64ItemStack.encode(new ItemStack(Material.STONE, 5));
        assertEquals("rO0ABXNyABpvcmcuYnVra2l0LnV0aWwuaW8uV3JhcHBlcvJQR+zxEm8FAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcgA1Y29tLmdvb2dsZS5jb21tb24uY29sbGVjdC5JbW11dGFibGVNYXAkU2VyaWFsaXplZEZvcm0AAAAAAAAAAAIAAkwABGtleXN0ABJMamF2YS9sYW5nL09iamVjdDtMAAZ2YWx1ZXNxAH4ABHhwdXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAABXQAAj09dAABdnQABHR5cGV0AAZhbW91bnR0AARtZXRhdXEAfgAGAAAABXQAHm9yZy5idWtraXQuaW52ZW50b3J5Lkl0ZW1TdGFja3NyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAABdAAFU1RPTkVzcQB+AA8AAAAFc3EAfgAAc3EAfgADdXEAfgAGAAAAB3EAfgAIdAAIZW5jaGFudHN0ABJQdWJsaWNCdWtraXRWYWx1ZXN0AAtVbmJyZWFrYWJsZXQACUl0ZW1GbGFnc3QABkRhbWFnZXQAC3JlcGFpci1jb3N0dXEAfgAGAAAAB3QANGJlLnNlZXNlZW1lbGsubW9ja2J1a2tpdC5pbnZlbnRvcnkubWV0YS5JdGVtTWV0YU1vY2tzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHhzcQB+AB8/QAAAAAAAAHcIAAAAEAAAAAB4c3IAEWphdmEubGFuZy5Cb29sZWFuzSBygNWc+u4CAAFaAAV2YWx1ZXhwAHNyACRqYXZhLnV0aWwuRW51bVNldCRTZXJpYWxpemF0aW9uUHJveHkFB9PbdlTK0QIAAkwAC2VsZW1lbnRUeXBldAARTGphdmEvbGFuZy9DbGFzcztbAAhlbGVtZW50c3QAEVtMamF2YS9sYW5nL0VudW07eHB2cgAdb3JnLmJ1a2tpdC5pbnZlbnRvcnkuSXRlbUZsYWcAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB1cgARW0xqYXZhLmxhbmcuRW51bTuojeotM9IvmAIAAHhwAAAAAHNxAH4ADwAAAABxAH4ALQ==", base64);
        assertEquals(new ItemStack(Material.STONE, 5), Base64ItemStack.decode(base64));
    }

    @Test
    void test_null() {
        String base64 = Base64ItemStack.encode(null);
        assertEquals("rO0ABXA=", base64);
        assertNull(Base64ItemStack.decode(base64));
    }
}
