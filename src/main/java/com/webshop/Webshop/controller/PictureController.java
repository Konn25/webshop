package com.webshop.Webshop.controller;

import com.webshop.Webshop.dto.PictureDTO;
import com.webshop.Webshop.jpa.Picture;
import com.webshop.Webshop.service.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearerToken")
@Tag(name = "Order Status API", description = "The Order Status API ")
public class PictureController {

    private final PictureService pictureService;

    private final ModelMapper modelMapper;

    @GetMapping("/all")
    @Operation(summary = "Get all picture", description = "Return all picture url from the database")
    @ApiResponse(responseCode = "200", description = "Get all picture")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public List<Picture> getAllPicture() {
        return pictureService.getAllPicture();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get picture by id", description = "Return picture url by id from the database")
    @ApiResponse(responseCode = "200", description = "Get all picture")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public Optional<Picture> getPictureById(@PathVariable("id") Long id) {
        return pictureService.getPictureById(id);
    }

    @PostMapping("/new")
    @Operation(summary = "Create picture", description = "Create picture")
    @ApiResponse(responseCode = "201", description = "Create picture")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> createNewPicture(@RequestBody PictureDTO pictureDTO) {

        Picture pictureRequest = modelMapper.map(pictureDTO, Picture.class);

        Picture newPicture = pictureService.createNewPicture(pictureRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("New picture added to the database! ID: " + newPicture.getId());

    }

    @PostMapping("/update")
    @Operation(summary = "Update picture", description = "Update picture")
    @ApiResponse(responseCode = "200", description = "Update picture")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> updatePicture(@RequestBody PictureDTO pictureDTO) {

        Picture pictureRequest = modelMapper.map(pictureDTO, Picture.class);

        Picture newPicture = pictureService.updateNewPicture(pictureRequest);

        return ResponseEntity.status(HttpStatus.OK)
                             .body("Picture updated: " + newPicture.getId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete picture", description = "Delete picture by id")
    @ApiResponse(responseCode = "200", description = "Picture deleted")
    @ApiResponse(responseCode = "403", description = "Authentication needed")
    public ResponseEntity<String> deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePictureById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Picture deleted!!");
    }


}
