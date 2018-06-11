package org.avol.tinyservice.url.util;

import java.util.zip.CRC32;

/**
 * @author Durga, Padala on 10/06/18.
 */
public final class HashUtil {

    /**
     * returns the CRC32 hash value of supplied URL, max length of url will be 2048 hence the hash value generated within integer boundary.
     *
     * @param url
     * @return
     */
    public static int hash(String url) {
        CRC32 crc32 = new CRC32();
        crc32.update(url.getBytes());
        return Math.abs(Long.valueOf(crc32.getValue()).intValue());
    }
}
