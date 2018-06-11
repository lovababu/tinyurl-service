package org.avol.tinyservice.url.repository.impl;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.RawJsonDocument;
import org.avol.tinyservice.url.repository.URLDao;
import org.avol.tinyservice.url.repository.entity.UrlModel;

import java.util.Optional;

/**
 * DAO class, which connects to Couchbase server.
 *
 * Created by lovababu on 10/06/18.
 */
public class URLDaoImpl implements URLDao {

    private final Bucket bucket;

    public URLDaoImpl(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public boolean insert(String id, UrlModel urlModel) {
        bucket.insert(toJsonDocument(id, urlModel));
        return true;
    }

    @Override
    public Optional<UrlModel> get(String id) {
        return Optional.of(fromJsonDocument(bucket.get(id, RawJsonDocument.class)));
    }

    @Override
    public boolean isKeyExist(String key) {
        return bucket.exists(key);
    }
}
