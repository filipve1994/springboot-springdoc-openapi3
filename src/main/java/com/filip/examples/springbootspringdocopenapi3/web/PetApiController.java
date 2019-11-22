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

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/")
@SecurityScheme(
        name = "petstore_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                implicit = @OAuthFlow(
                        authorizationUrl = "http://petstore.swagger.io/oauth/dialog",
                        scopes = {
                                @OAuthScope(name = "write:pets", description = "modify pets in your account"),
                                @OAuthScope(name = "read:pets", description = "read your pets")
                        }
                )
        )
)
@Tag(name = "pet", description = "the pet API")
public class PetApiController {

    @Autowired
    private IPetService petService;

    @Operation(
            summary = "Add a new pet to the store",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            parameters = {
                    @Parameter(name = "pet", description = "Pet object that needs to be added to the store", required = true)
            },
            tags = {"pet"}
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "405", description = "Invalid input")})
    @PostMapping(value = "/pet", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity addPet(@Valid @RequestBody Pet pet) {

        return ResponseEntity.ok("");

    }

    @Operation(
            summary = "Deletes a pet",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "petId", description = "Pet id to delete", required = true),
                    @Parameter(name = "apiKey", description = "")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    })
    @DeleteMapping(value = "/pet/{petId}")
    public void deletePet(@PathVariable("petId") Long petId, @RequestHeader(value = "api_key", required = false) String apiKey) {
        //return getDelegate().deletePet(petId, apiKey);
        petService.deletePet(petId, apiKey);
    }

    @Operation(
            summary = "Finds Pets by status",
            description = "Multiple status values can be provided with comma separated strings",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "status", description = "Status values that need to be considered for filter", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(value = "/pet/findByStatus", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity findPetsByStatus(@NotNull @Valid @RequestParam(value = "status", required = true) List<String> status) {
        // ResponseEntity<List<Pet>>
        //return getDelegate().findPetsByStatus(status);

        return ResponseEntity.ok(petService.findPetsByStatus(status));
    }

    @Operation(
            summary = "Finds Pets by tags",
            description = "Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "tags", description = "Tags to filter by", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid tag value")
    })
    @GetMapping(value = "/pet/findByTags", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity findPetsByTags(@NotNull @Valid @RequestParam(value = "tags", required = true) List<String> tags) {
        // ResponseEntity<List<Pet>>
        //return getDelegate().findPetsByTags(tags);

        return ResponseEntity.ok(petService.findPetsByTags(tags));
    }

    @Operation(
            summary = "Find pet by ID",
            description = "Returns a single pet",
            security = {
                    @SecurityRequirement(name = "api_key")
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "petId", description = "ID of pet to return", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Pet.class)))
            ,
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")})
    @GetMapping(value = "/pet/{petId}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getPetById(@PathVariable("petId") Long petId) {
        // ResponseEntity<Pet>
        //return getDelegate().getPetById(petId);

        return ResponseEntity.ok(petService.getPetById(petId));
    }

    @Operation(
            summary = "Update an existing pet",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "pet", description = "Pet object that needs to be added to the store", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")
    })
    @PutMapping(value = "/pet", consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public void updatePet(@Valid @RequestBody Pet pet) {
        //return getDelegate().updatePet(pet);

        //return ResponseEntity.ok("");

        petService.updatePet(pet);
    }

    @Operation(
            summary = "Updates a pet in the store with form data",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "petId", description = "ID of pet that needs to be updated", required = true),
                    @Parameter(name = "name", description = "Updated name of the pet"),
                    @Parameter(name = "status", description = "Updated status of the pet")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PostMapping(value = "/pet/{petId}", consumes = {APPLICATION_FORM_URLENCODED_VALUE})
    public void updatePetWithForm(@PathVariable("petId") Long petId,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "status", required = false) String status) {
        //return getDelegate().updatePetWithForm(petId, name, status);

        //return ResponseEntity.ok("");

        petService.updatePetWithForm(petId, name, status);
    }

    @Operation(
            summary = "uploads an image",
            description = "",
            security = {
                    @SecurityRequirement(
                            name = "petstore_auth",
                            scopes = {"write:pets", "read:pets"}
                    )
            },
            tags = {"pet"},
            parameters = {
                    @Parameter(name = "petId", description = "ID of pet to update", required = true),
                    @Parameter(name = "additionalMetadata", description = "Additional data to pass to server"),
                    @Parameter(name = "file", description = "file detail")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = ModelApiResponse.class))
            )}
    )
    @PostMapping(
            value = "/pet/{petId}/uploadImage",
            produces = {APPLICATION_JSON_VALUE},
            consumes = {MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity uploadFile(@PathVariable("petId") Long petId,
                                     @RequestParam(value = "additionalMetadata", required = false) String additionalMetadata,
                                     @Valid @RequestPart("file") MultipartFile file) {

        // ResponseEntity<ModelApiResponse>
        //return getDelegate().uploadFile(petId, additionalMetadata, file);

        return ResponseEntity.ok(petService.uploadFile(petId, additionalMetadata, file));
    }


}
