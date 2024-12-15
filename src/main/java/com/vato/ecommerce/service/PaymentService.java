package com.vato.ecommerce.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.vato.ecommerce.model.dto.PaymentLinkResponse;
import com.vato.ecommerce.model.entity.Order;
import com.vato.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("/stripe.properties")
public class PaymentService {

    private final OrderRepository orderRepository;

    @Value("${stripe.secret-key}")
    private String secretKey;

    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public PaymentLinkResponse createPaymentLink(Order order) {
        Stripe.apiKey = secretKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Top")
                        .build();

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setProductData(productData
                )
                .setCurrency("USD")
                .setUnitAmount((long) order.getTotalPrice() * 100)
                .build();

        SessionCreateParams.LineItem lineitem = SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(priceData)
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setCustomerEmail(order.getUser().getEmail())
                .addPaymentMethodType(
                        SessionCreateParams.PaymentMethodType.CARD
                )
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payments/" + order.getOrderId())
                .addLineItem(lineitem)
                .build();

        try {
            Session session = Session.create(params);

            return new PaymentLinkResponse(session.getUrl(), session.getId());
        } catch (StripeException e) {
            System.err.println(e.getMessage());
            return new PaymentLinkResponse("", "");
        }
    }

    public String redirect(Order order, String paymentId) {
        Stripe.apiKey = secretKey;

        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);

            if (paymentIntent.getStatus().equalsIgnoreCase("succeeded")) {
                order.getPaymentDetails().setPaymentId(paymentId);
                order.getPaymentDetails().setStatus("COMPLETED");
                order.setOrderStatus("PLACED");
                orderRepository.save(order);

                return "Your order got placed";
            } else {
                return "Somethings wrong";
            }

        } catch (StripeException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
