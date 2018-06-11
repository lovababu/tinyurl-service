package org.avol.tinyservice.url.repository;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.avol.tinyservice.common.error.ErrorCode;
import org.avol.tinyservice.common.exception.ServiceException;
import org.avol.tinyservice.url.repository.entity.UrlModel;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by lovababu on 10/06/18.
 */
public interface URLDao {

    ObjectMapper mapper = new ObjectMapper();

    boolean insert(String id, UrlModel urlModel);

    Optional<UrlModel> get(String id);

    boolean isKeyExist(String key);

    default JsonDocument toJsonDocument(String id, UrlModel document) {
        try {
            JsonTranscoder jsonTranscoder = new JsonTranscoder();
            JsonObject jsonObject = jsonTranscoder.stringToJsonObject(mapper.writeValueAsString(document));
            return JsonDocument.create(id, Math.toIntExact(TimeUnit.MILLISECONDS.toSeconds(document.getExpiryTime())),
                    jsonObject);
        } catch (Exception e) {
            throw ServiceException.wrap(e);
        }
    }


    default UrlModel fromJsonDocument(RawJsonDocument rawJsonDocument) {
        try {
            if (rawJsonDocument != null) {
                return mapper.readValue(rawJsonDocument.content(), UrlModel.class);
            } else {
                throw new ServiceException(ErrorCode.KEY_NOT_FOUND);
            }
        } catch (Exception e) {
            throw ServiceException.wrap(e);
        }
    }
}
