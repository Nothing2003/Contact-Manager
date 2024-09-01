package com.cm.cm2.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile contactImage);

    String getURLFromPublicId(String publicID);

}
