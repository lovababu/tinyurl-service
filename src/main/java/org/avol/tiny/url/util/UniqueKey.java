package org.avol.tiny.url.util;

/**
 * UniqueKey class, which encode/decode the uniqueKey.
 *
 * Created by lovababu on 10/06/18.
 */
public class UniqueKey {

    private static final String ALPHA_NUMERIC_DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE_62 = ALPHA_NUMERIC_DATA.length();

    /**
     * takes the id, and Convert to a base 62 number.
     *
     * @param id
     *      id of integer
     * @return
     *      encoded string.
     */
    public static String encode(int id) {
        StringBuilder sb = new StringBuilder() ;

        while ( id > 0 ) {
            sb.append ( ALPHA_NUMERIC_DATA.charAt ( id % BASE_62 )) ;
            id /= BASE_62 ;
        }
        return sb.reverse().toString() ;
    }

    /**
     * Takes the string, and convert back to integer id.
     *
     * @param key
     *   unique key.
     * @return
     *   id integer.
     */
    public static int decode(String key) {
        int Num = 0 ;
        for ( int i = 0, len = key.length(); i < len; i++ ) {
            Num = Num * BASE_62 + ALPHA_NUMERIC_DATA.indexOf ( key.charAt(i) ) ;
        }
        return Num ;
    }
}
