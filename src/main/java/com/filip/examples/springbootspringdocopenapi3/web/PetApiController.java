package com.filip.examples.springbootspringdocopenapi3.web;

import com.filip.examples.springbootspringdocopenapi3.models.ModelApiResponse;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import com.filip.examples.springbootspringdocopenapi3.services.IPetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.API_KEY_SECURITY_REQUIREMENT;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.PETSTORE_AUTH_SECURITY_REQUIREMENT;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.PET_TAG;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.READ_PETS_SECURITY_REQUIREMENT_SCOPE;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.WRITE_PETS_SECURITY_REQUIREMENT_SCOPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/")
@SecurityScheme(
        name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                implicit = @OAuthFlow(
                        authorizationUrl = "http://petstore.swagger.io/oauth/dialog",
                        scopes = {
                                @OAuthScope(name = WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, description = "modify pets in your account"),
                                @OAuthScope(name = READ_PETS_SECURITY_REQUIREMENT_SCOPE, description = "read your pets")
                        }
                )
        )
)
@Tag(name = PET_TAG, description = "the pet API")
public class PetApiController {

    @Autowired
    private IPetService petService;

    @Operation(
            summary = "Get all pets from the store",
            description = "",
//            security = {
//                    @SecurityRequirement(
//                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
//                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
//                    )
//            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping(value = "/pet/all", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getAll(){
        return ResponseEntity.ok(petService.getall());
    }

    @Operation(
            summary = "Add a new pet to the store",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PostMapping(value = "/pet", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public void addPet(@Parameter(description = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody Pet pet) {
        petService.addPet(pet);
    }

    @Operation(
            summary = "Deletes a pet",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    })
    @DeleteMapping(value = "/pet/{petId}")
    public void deletePet(@Parameter(description = "Pet id to delete", required = true) @PathVariable("petId") Long petId,
                          @Parameter(description = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
        //return getDelegate().deletePet(petId, apiKey);
        petService.deletePet(petId, apiKey);
    }

    @Operation(
            summary = "Finds Pets by status",
            description = "Multiple status values can be provided with comma separated strings",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(value = "/pet/findByStatus", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity findPetsByStatus(@Parameter(description = "Status values that need to be considered for filter", required = true)
                                           @NotNull @Valid @RequestParam(value = "status", required = true) List<String> status) {
        // ResponseEntity<List<Pet>>
        //return getDelegate().findPetsByStatus(status);

        return ResponseEntity.ok(petService.findPetsByStatus(status));
    }

    @Operation(
            summary = "Finds Pets by tags",
            description = "Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG},
            deprecated = true
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid tag value")
    })
    @GetMapping(value = "/pet/findByTags", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity findPetsByTags(@Parameter(description = "Tags to filter by", required = true) @NotNull @Valid @RequestParam(value = "tags", required = true) List<String> tags) {
        // ResponseEntity<List<Pet>>
        //return getDelegate().findPetsByTags(tags);

        return ResponseEntity.ok(petService.findPetsByTags(tags));
    }

    @Operation(
            summary = "Find pet by ID",
            description = "Returns a single pet",
            security = {
                    @SecurityRequirement(name = API_KEY_SECURITY_REQUIREMENT)
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Pet.class)))
            ,
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")})
    @GetMapping(value = "/pet/{petId}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getPetById(@Parameter(description = "ID of pet to return", required = true) @PathVariable("petId") Long petId) {
        // ResponseEntity<Pet>
        //return getDelegate().getPetById(petId);

        return ResponseEntity.ok(petService.getPetById(petId));
    }

    @Operation(
            summary = "Update an existing pet",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")
    })
    @PutMapping(value = "/pet", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public void updatePet(@Parameter(description = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody Pet pet) {
        //return getDelegate().updatePet(pet);

        //return ResponseEntity.ok("");

        petService.updatePet(pet);
    }

    @Operation(
            summary = "Updates a pet in the store with form data",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PostMapping(value = "/pet/{petId}", consumes = {APPLICATION_FORM_URLENCODED_VALUE})
    public void updatePetWithForm(@Parameter(description = "ID of pet that needs to be updated", required = true) @PathVariable("petId") Long petId,
                                  @Parameter(description = "Updated name of the pet") @RequestParam(value = "name", required = false) String name,
                                  @Parameter(description = "Updated status of the pet") @RequestParam(value = "status", required = false) String status) {
        //return getDelegate().updatePetWithForm(petId, name, status);

        //return ResponseEntity.ok("");

        petService.updatePetWithForm(petId, name, status);
    }

    @Operation(
            summary = "uploads an image",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = PETSTORE_AUTH_SECURITY_REQUIREMENT,
                            scopes = {WRITE_PETS_SECURITY_REQUIREMENT_SCOPE, READ_PETS_SECURITY_REQUIREMENT_SCOPE}
                    )
            },
            tags = {PET_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ModelApiResponse.class))
            )}
    )
    @PostMapping(value = "/pet/{petId}/uploadImage", produces = {APPLICATION_JSON_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadFile(@Parameter(description = "ID of pet to update", required = true) @PathVariable("petId") Long petId,
                                     @Parameter(description = "Additional data to pass to server") @RequestParam(value = "additionalMetadata", required = false) String additionalMetadata,
                                     @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file) {

        // ResponseEntity<ModelApiResponse>
        //return getDelegate().uploadFile(petId, additionalMetadata, file);

        return ResponseEntity.ok(petService.uploadFile(petId, additionalMetadata, file));
    }


}
