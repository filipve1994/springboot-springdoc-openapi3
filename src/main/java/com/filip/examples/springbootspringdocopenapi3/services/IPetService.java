package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.ModelApiResponse;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

public interface IPetService {

    public void addPet(Pet pet);

    public void deletePet(Long petId, String apiKey);

    public List<Pet> findPetsByStatus(List<String> status);

    public List<Pet> findPetsByTags(List<String> tags);

    public Pet getPetById(Long petId);

    public void updatePet(Pet pet);

    public void updatePetWithForm(Long petId, String name, String status);

    public ModelApiResponse uploadFile(Long petId, String additionalMetadata, @Valid MultipartFile file);


    public List<Pet> getall();
}
