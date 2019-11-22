package com.filip.examples.springbootspringdocopenapi3.repositories;

import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Override
    Optional<Pet> findById(Long aLong);

//    List<Pet> findAllByStatusIn(Pet.StatusEnum statusEnum);
//
//    //yes
//    List<Pet> findPetsByStatusIn(List<Pet.StatusEnum> statusEnums);
//
//    //yes
//    List<Pet> findPetsByTagsIn(List<String> tags);
//
//    List<Pet> findAllByTagsIn(String tags);
}
