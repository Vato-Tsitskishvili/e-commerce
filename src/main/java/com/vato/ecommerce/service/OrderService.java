package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.CartNotFoundException;
import com.vato.ecommerce.exceptions.OrderNotFoundException;
import com.vato.ecommerce.model.entity.*;
import com.vato.ecommerce.repository.AddressRepository;
import com.vato.ecommerce.repository.OrderItemRepository;
import com.vato.ecommerce.repository.OrderRepository;
import com.vato.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            AddressRepository addressRepository,
            UserRepository userRepository,
            CartService cartService,
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository
    ) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public Order findOrderById(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
    }

    @Transactional
    public Order createOrder(User user, Address shippingAddress) throws CartNotFoundException {
        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddresses().add(address);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .price(cartItem.getPrice())
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .size(cartItem.getSize())
                    .userId(cartItem.getUserId())
                    .discountedPrice(cartItem.getDiscountedPrice())
                    .build();

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }
        Order createdOrder = Order.builder()
                .user(user)
                .orderItems(orderItems)
                .totalPrice(cart.getTotalPrice())
                .totalDiscountedPrice(cart.getTotalDiscountedPrice())
                .discount(cart.getDiscount())
                .totalItem(cart.getTotalItem())
                .paymentDetails(new PaymentDetails())
                .shippingAddress(address)
                .orderDate(LocalDateTime.now())
                .orderStatus("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        createdOrder.getPaymentDetails().setStatus("PENDING");

        Order savedOrder = orderRepository.save(createdOrder);

        for (OrderItem item : orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    public Order placedOrder(Long orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");

        return order;
    }

    public Order confirmedOrder(Long orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");

        return orderRepository.save(order);
    }

    public Order shippedOrder(Long orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");

        return orderRepository.save(order);
    }

    public Order deliveredOrder(Long orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");

        return orderRepository.save(order);
    }

    public Order canceledOrder(Long orderId) throws OrderNotFoundException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");

        return orderRepository.save(order);
    }

    public List<Order> usersOrderHistory(Long userId) {
        return orderRepository.getUsersOrders(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String deleteOrder(Long orderId) throws OrderNotFoundException {
        findOrderById(orderId);
        orderRepository.deleteById(orderId);
        return "Order with id " + orderId + " deleted successfully";
    }
}
