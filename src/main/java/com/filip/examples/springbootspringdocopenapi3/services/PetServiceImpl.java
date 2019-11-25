package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.ModelApiResponse;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import com.filip.examples.springbootspringdocopenapi3.models.Tag;
import com.filip.examples.springbootspringdocopenapi3.repositories.PetRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public void addPet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void deletePet(Long petId, String apiKey) {
        petRepository.deleteById(petId);
    }

    @Override
    public List<Pet> findPetsByStatus(List<String> statusList) {

        List<Pet.StatusEnum> statusEnums = new ArrayList<>();
        for (String s : statusList) {
            Pet.StatusEnum statusEnum = Optional.ofNullable(Pet.StatusEnum.fromValue(s))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + s));
            statusEnums.add(statusEnum);
        }

        List<Pet> entities = petRepository.findAll();

        List<Pet> collect = entities.stream()
                .filter(entity -> entity.getStatus() != null)
                .filter(entity -> statusList.contains(entity.getStatus()))
                .collect(Collectors.toList());

        return collect;
        //return petRepository.findPetsByStatusIn(statusEnums);
    }

    @Override
    public List<Pet> findPetsByTags(List<String> tags) {

        List<Pet> entities = petRepository.findAll();

        List<Pet> collect = entities.stream()
                .filter(entity -> entity.getTags() != null)
                .filter(entity -> entity.getTags().stream()
                        .map(Tag::getName)
                        .anyMatch(tags::contains)
                )
                .collect(Collectors.toList());

        return collect;
        //return petRepository.findPetsByTagsIn(tags);
    }

    @Override
    public Pet getPetById(Long petId) {
        //ApiUtil.checkApiKey(request);
        return petRepository.findById(petId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updatePet(Pet pet) {

    }

    @Override
    public void updatePetWithForm(Long petId, String name, String status) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!StringUtils.isEmpty(name)) {
            pet.setName(name);
        }
        if (!StringUtils.isEmpty(name)) {
            pet.setStatus(Pet.StatusEnum.fromValue(status));
        }
    }

    @Override
    public ModelApiResponse uploadFile(Long petId, String additionalMetadata, @Valid MultipartFile file) {
        try {
            String uploadedFileLocation = "./" + file.getName();
            System.out.println("uploading to " + uploadedFileLocation);
            IOUtils.copy(file.getInputStream(), new FileOutputStream(uploadedFileLocation));
            String msg = String.format("additionalMetadata: %s\nFile uploaded to %s, %d bytes", additionalMetadata,
                    uploadedFileLocation, (new File(uploadedFileLocation)).length());
            ModelApiResponse output = new ModelApiResponse();
            output.setCode(200);
            output.setMessage(msg);
            return output;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't upload file", e);
        }
    }

    @Override
    public List<Pet> getall() {
        return petRepository.findAll();
    }
}
