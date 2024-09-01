package com.cm.cm2.services.imp;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.cm.cm2.services.ImageService;

@Service
public class ImageServiceImp implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceImp(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage) {
        if (contactImage.isEmpty()) {
            return "https://res-console.cloudinary.com/dfikzvebd/media_explorer_thumbnails/9dcdb7c30711d66f219c1d8c14adf9cc/detailed";
        }
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            String fileName = UUID.randomUUID().toString();
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", fileName
            ));
            return this.getURLFromPublicId(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getURLFromPublicId(String publicID) {
        return cloudinary.url().transformation(
                new Transformation<>()
        ).generate(publicID);
    }

}
