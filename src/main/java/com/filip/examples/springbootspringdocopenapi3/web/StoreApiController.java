package com.filip.examples.springbootspringdocopenapi3.web;

import com.filip.examples.springbootspringdocopenapi3.models.Order;
import com.filip.examples.springbootspringdocopenapi3.services.IStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Map;

import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.API_KEY_SECURITY_REQUIREMENT;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.STORE_TAG;
import static com.filip.examples.springbootspringdocopenapi3.config.OpenApiConstants.USER_TAG;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping(value = "/")
@Tag(name = STORE_TAG, description = "the store API")
public class StoreApiController {

    @Autowired
    private IStoreService storeService;

    @Operation(
            summary = "Get all orders",
            description = "",
            tags = {STORE_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping(value = "/store/order/all", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getAll(){
        return ResponseEntity.ok(storeService.getall());
    }

    @Operation(summary = "Delete purchase order by ID", tags = {STORE_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping(value = "/store/order/{orderId}")
    public void deleteOrder(@Parameter(description = "ID of the order that needs to be deleted", required = true) @PathVariable("orderId") String orderId) {
        storeService.deleteOrder(orderId);
    }

    @Operation(
            summary = "Returns pet inventories by status",
            description = "Returns a map of status codes to quantities",
            security = {
                    @SecurityRequirement(name = API_KEY_SECURITY_REQUIREMENT)
            },
            tags = {STORE_TAG}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Map.class)))
            )
    })
    @GetMapping(value = "/store/inventory", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity getInventory() {
        return ResponseEntity.ok(storeService.getInventory());
    }

    @Operation(summary = "Find purchase order by ID", tags = {STORE_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    @GetMapping(value = "/store/order/{orderId}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity getOrderById(@Parameter(description = "ID of pet that needs to be fetched", required = true) @Min(1L) @Max(5L) @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(storeService.getOrderById(orderId));
    }

    @Operation(summary = "Place an order for a pet", tags = {STORE_TAG})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Order")})
    @PostMapping(
            value = "/store/order",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
            consumes = {APPLICATION_JSON_VALUE}
    )
    public ResponseEntity placeOrder(@Parameter(description = "order placed for purchasing the pet", required = true) @Valid @RequestBody Order order) {
        return ResponseEntity.ok(storeService.placeOrder(order));
    }

}
