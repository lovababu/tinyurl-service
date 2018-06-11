package org.avol.tinyservice.url.service;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.avol.tinyservice.common.error.ErrorCode;
import org.avol.tinyservice.common.exception.ServiceException;
import org.avol.tinyservice.url.api.model.ApiRequest;
import org.avol.tinyservice.url.repository.entity.UrlModel;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * @author Durga, Padala on 10/06/18.
 */
public interface URLService {

    int MAX_URL_LENGTH = 2048;

    UrlModel shorten(ApiRequest requestModel) throws SerializationException;

    String get(String uniqueKey) throws ServiceException;

    /**
     * verify whether supplied url is valid or not.
     *
     * @param uri
     *  uri to be shortened.
     * @return
     *   boolean
     */
    default boolean isValidURI(String uri) {
        if (StringUtils.isNotEmpty(uri) && StringUtils.length(uri) <= MAX_URL_LENGTH) {
            try {
                new URL(uri).toURI();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * if expiry time passed and the value is greater than zero '0', date time represented by this value should be after today.
     *
     * @param time
     * @return
     */
    default boolean isValidExpiryDateTime(long time) {
        if (time == 0) {
            return true;
        }

        if (time > 0) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
            return localDateTime.isAfter(LocalDateTime.now());
        } else {
            return false;
        }
    }

    /**
     * if custom key passed, length should be more than 6 characters.
     *
     * @param customKey
     * @return
     */
    default boolean isCustomKeyValid(String customKey) {
        return !StringUtils.isNotEmpty(customKey) || StringUtils.length(customKey) >= 6;
    }

    /**
     * validate request params.
     *
     * @param requestModel
     *      request model
     * @throws ServiceException
     *      if any validation fails throw exception.
     */
    default void validateRequest(ApiRequest requestModel) throws ServiceException {
        if (!isValidURI(requestModel.getOriginalUrl())) {
            throw new ServiceException(ErrorCode.INVALID_URL);
        }

        if (!isValidExpiryDateTime(requestModel.getExpiryTime())) {
            throw new ServiceException(ErrorCode.INVALID_EXPIRY);
        }

        if (!isCustomKeyValid(requestModel.getCustomKey())) {
            throw new ServiceException(ErrorCode.INVALID_CUSTOM_KEY);
        }
    }

    /**
     * construct persistent model object.
     *
     * @param requestModel
     *      request model
     * @return
     *      UrlModel
     */
    default UrlModel buildPersistentModel(ApiRequest requestModel, int defaultExpiry) {
        UrlModel urlModel = new UrlModel();
        urlModel.setLongUrl(requestModel.getOriginalUrl());
        urlModel.setCreatedTime(Instant.now().toEpochMilli());
        if (requestModel.getExpiryTime() == 0) {
            urlModel.setExpiryTime(Instant.now().toEpochMilli() + TimeUnit.DAYS.toMillis(defaultExpiry));
        }
        return urlModel;
    }
}
