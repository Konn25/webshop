package com.webshop.Webshop.service;


import com.webshop.Webshop.jpa.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureServiceInterface {

    Optional<Picture> getPictureById(Long id);

    void deletePictureById(Long id);

    Picture createNewPicture(Picture picture);

    Picture updateNewPicture(Picture picture);

    List<Picture> getAllPicture();

}
