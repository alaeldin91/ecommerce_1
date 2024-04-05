package com.alaeldin.order_service.service.serviceImpl;

import com.alaeldin.order_service.dto.InventoryResponse;
import com.alaeldin.order_service.dto.OrderRequest;
import com.alaeldin.order_service.dto.OrderResponse;
import com.alaeldin.order_service.mapper.MapperOrderLineItemDto;
import com.alaeldin.order_service.mapper.MapperResponse;
import com.alaeldin.order_service.model.Order;
import com.alaeldin.order_service.model.OrderLineItem;
import com.alaeldin.order_service.repository.OrderRepository;
import com.alaeldin.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
   private final OrderRepository orderRepository;
   private  WebClient webClient;
   @Override
    public void placeHolder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDto()
                .stream().map(MapperOrderLineItemDto::mapToOrderLineItem).toList();
        order.setOrderLineItems(orderLineItems);
        order.setOrderLineItems(orderLineItems);
        List<String>skuCode = orderLineItems.stream()
                .map(OrderLineItem::getSkuCode).toList();
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8085/api/v1/inventory"
                ,uriBuilder -> uriBuilder
                                .queryParam("skuCode",skuCode)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponseArray != null;
        boolean allProductInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
        if (allProductInStock){

            Order SaveOrder =  orderRepository.save(order);
        }

        else {
            throw new IllegalArgumentException("product is not In Stock" +
                    "please Try Again");
        }
   }


    @Override
    public Page<OrderResponse> getAllOrders(int numberPage, int pageSize) {
        Pageable pageable = PageRequest.of(numberPage,pageSize);
        Page<Order> order = orderRepository.findAll(pageable);
        return order.map(MapperResponse::mapToOrderDto);
    }
}
