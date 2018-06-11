package org.avol.tinyservice.url.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.avol.tinyservice.common.error.ErrorCode;
import org.avol.tinyservice.common.exception.ServiceException;
import org.avol.tinyservice.url.api.model.ApiRequest;
import org.avol.tinyservice.url.repository.URLDao;
import org.avol.tinyservice.url.repository.entity.UrlModel;
import org.avol.tinyservice.url.service.URLService;
import org.avol.tinyservice.url.util.HashUtil;
import org.avol.tinyservice.url.util.UniqueKey;

import java.time.Instant;
import java.util.Optional;

/**
 *
 * Business layer class.
 *
 * @author Durga, Padala on 10/06/18.
 */
@Slf4j
public class URLServiceImpl implements URLService {

    private final URLDao urlDao;
    private final int defaultExpiry;

    public URLServiceImpl(URLDao urlDao, int defaultExpiry) {
        this.urlDao = urlDao;
        this.defaultExpiry = defaultExpiry;
    }

    @Override
    public UrlModel shorten(ApiRequest requestModel) throws ServiceException {
        validateRequest(requestModel);
        String id = StringUtils.isNotEmpty(requestModel.getCustomKey()) ? requestModel.getCustomKey()
                : getUniqueKey(requestModel.getOriginalUrl());
        UrlModel urlModel = buildPersistentModel(requestModel, defaultExpiry);
        try {
            urlDao.insert(id, urlModel);
        } catch (Exception e) {
            log.error("Error", e);
            throw new ServiceException(ErrorCode.SERVER_ERROR);
        }
        urlModel.setId(id);
        return urlModel;

    }

    @Override
    public String get(String uniqueKey) throws ServiceException {

        Optional<UrlModel> optionalUrlModel = urlDao.get(uniqueKey);
        if (optionalUrlModel.isPresent()) {
            return optionalUrlModel.get().getLongUrl();
        }

        throw new ServiceException(ErrorCode.KEY_NOT_FOUND);
    }


    /**
     * encode the url and verify whether it exists in datastore, if it exists append timestamp in ephoc millis to original url and retry the process.
     *
     * @param originalUrl
     *     url to be shortened.
     * @return
     *      id string.
     */
    private String getUniqueKey(String originalUrl) {
        StringBuilder url = new StringBuilder(originalUrl);
        int count = 0;
        String id;
        while (true) {
             id =UniqueKey.encode(HashUtil.hash(url.toString()));
            if (urlDao.isKeyExist(id)) {
                count++;
                url.append(String.valueOf(Instant.now().toEpochMilli()));
            } else {
               break;
            }
        }
        log.info("Number iterations happend to get uniquey key {}", count);
        return id;
    }
}
