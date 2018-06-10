package org.avol.tiny.url.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lovababu on 10/06/18.
 */
@RunWith(JUnit4.class)
public class UniqueKeyTest {


    @Test
    public void testEncode() {
        assertNotNull(UniqueKey.encode(123456789));
    }

    @Test
    public void testDecode() {
        assertNotNull(UniqueKey.decode("aby0ef"));
    }

    @Test
    public void testIsDecodeReturnsOriginalId() {
        int input = 5657878;
        String encoded = UniqueKey.encode(input);
        int decoded = UniqueKey.decode(encoded);

        assertEquals(decoded, input);
    }

    public void testIsEncodeReturnsExpectedString() {
        String input = "abcdef";
        int decode = UniqueKey.decode(input);
        String encoded = UniqueKey.encode(decode);

        assertEquals(encoded, input);
    }
}
