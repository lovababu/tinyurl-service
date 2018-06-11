package org.avol.tinyservice.url.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * @author Durga, Padala on 11/06/18.
 */
@RunWith(JUnit4.class)
public class HashUtilTest {

    @Test
    public void test() {
        int hash = HashUtil.hash("https://www.zycus.com/solution/strategic-sourcing-suite.html");
        assertTrue(hash > 0);
    }
}
