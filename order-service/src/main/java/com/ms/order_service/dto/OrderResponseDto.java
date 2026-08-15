package com.ms.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
	
	private Long orderId;
	private Long productId;
	private Integer quantity;
	private Double totalPrice;
	private String productName;
	private Double productPrice;
	

}
