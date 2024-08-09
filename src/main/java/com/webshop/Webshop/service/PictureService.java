package com.webshop.Webshop.service;

import com.webshop.Webshop.jpa.Picture;
import com.webshop.Webshop.jpa.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService implements PictureServiceInterface{

    private final PictureRepository pictureRepository;

    @Override
    public Optional<Picture> getPictureById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void deletePictureById(Long id) {
        Optional<Picture> foundPicture = pictureRepository.findById(id);

        if(foundPicture.isPresent()){
            pictureRepository.deleteById(id);
        }
    }

    @Override
    public Picture createNewPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture updateNewPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAllPicture() {
        return pictureRepository.findAll();
    }
}
