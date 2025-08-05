package com.ortus.crud_productos.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductDTO {
    @PositiveOrZero(message = "El id no puede ser negativo")
    @NotNull(message = "El id es obligatorio")
    private Long id;

    private String name;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double price;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;
}
