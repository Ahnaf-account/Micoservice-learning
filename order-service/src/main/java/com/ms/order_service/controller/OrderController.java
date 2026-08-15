package com.ms.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.ms.order_service.dto.OrderResponseDto;
import com.ms.order_service.dto.ProductDto;
import com.ms.order_service.entity.Order;
import com.ms.order_service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	//create method to place order
	@PostMapping("/placeOrder")
	public Mono<ResponseEntity<OrderResponseDto>> placeOrder(@RequestBody Order order){

		//fetch product details from product service
		 return webClientBuilder.build().get().uri("http://localhost:8081/product/" + order.getProductId())
				 .retrieve()
				 .bodyToMono(ProductDto.class).map(ProductDto-> {
					 OrderResponseDto orderResponseDto = new OrderResponseDto();
					 orderResponseDto.setProductId(order.getProductId());
					 orderResponseDto.setQuantity(order.getQuantity());
					 //product details
					 orderResponseDto.setProductName(ProductDto.getName());
					 orderResponseDto.setProductPrice(ProductDto.getPrice());
					 orderResponseDto.setTotalPrice(order.getQuantity() *ProductDto.getPrice());
					 orderRepository.save(order);
					 orderResponseDto.setOrderId(order.getId());
					 return ResponseEntity.ok(orderResponseDto);
					 
				 });
		
	}
	
	@GetMapping
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
		
	}
}
