package com.filip.examples.springbootspringdocopenapi3.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.filip.examples.springbootspringdocopenapi3.models.auditing.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "ORDERS")
public class Order extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "")
    @JsonProperty("id")
    private Long id = null;

    @Schema(description = "")
    @JsonProperty("petId")
    private Long petId;

    @Schema(description = "")
    @JsonProperty("quantity")
    private Integer quantity;

    @Schema(description = "")
    @JsonProperty("shipDate")
    private Date shipDate;

    public enum StatusEnum {
        PLACED("placed"),
        APPROVED("approved"),
        DELIVERED("delivered");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Schema(description = "Order Status")
    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Schema(description = "", defaultValue = "false")
    @JsonProperty("complete")
    private boolean complete;

}
