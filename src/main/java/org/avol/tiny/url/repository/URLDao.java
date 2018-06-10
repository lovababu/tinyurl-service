package org.avol.tiny.url.repository;

import org.avol.tiny.url.api.model.URLModel;

/**
 * Created by lovababu on 10/06/18.
 */
public interface URLDao {

    boolean insert();

    URLModel get(String id);
}
