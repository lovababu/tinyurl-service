package org.avol.tiny.url.repository.impl;

import org.avol.tiny.url.api.model.URLModel;
import org.avol.tiny.url.repository.URLDao;

/**
 * DAO class, which connects to Couchbase server.
 *
 * Created by lovababu on 10/06/18.
 */
public class URLDaoImpl implements URLDao {

    @Override
    public boolean insert() {
        return false;
    }

    @Override
    public URLModel get(String id) {
        return null;
    }
}
