package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.PictureDTO;
import com.webshop.Webshop.jpa.Picture;
import com.webshop.Webshop.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/picture")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Picture> getAllPicture() {
        return pictureService.getAllPicture();
    }

    @GetMapping("/{id}")
    public Optional<Picture> getPictureById(@PathVariable("id") Long id) {
        return pictureService.getPictureById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewPicture(@RequestBody PictureDTO pictureDTO) {

        Picture pictureRequest = modelMapper.map(pictureDTO, Picture.class);

        Picture newPicture = pictureService.createNewPicture(pictureRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New picture added to the database! ID: " + newPicture.getId());

    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePicture(@RequestBody PictureDTO pictureDTO) {

        Picture pictureRequest = modelMapper.map(pictureDTO, Picture.class);

        Picture newPicture = pictureService.updateNewPicture(pictureRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("Picture updated: " + newPicture.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePictureById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Picture deleted!!");
    }


}
