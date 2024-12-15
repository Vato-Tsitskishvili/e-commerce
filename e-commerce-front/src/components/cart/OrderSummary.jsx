import { Button, Divider } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getOrderById } from "../../state/order/Action";
import AddressCard from "../AddressCard";
import CartItem from "./CartItem";
import { createPayment } from "../../state/payment/Action";
import { useLocation } from "react-router-dom";

const OrderSummary = () => {
	const dispatch = useDispatch()
	const location = useLocation()
	const {order} = useSelector(store => store)
	
	const searchParams = new URLSearchParams(location.search)
	const orderId = searchParams.get("order_id")

	const handleCheckout = e => {
		e.preventDefault()
		dispatch(createPayment(orderId))
	}

	useEffect(() => {
		dispatch(getOrderById(orderId))
	}, [orderId])

	return (
		<>
			<div className="p-5 shadow-lg rounded-s-md border mb-10">
				<AddressCard address={order.order?.shippingAddress} />
			</div>
			
			<div className="lg:grid grid-cols-3 lg:px-16 relative">
				<div className="col-span-2">
					{order.order?.orderItems.map(cartItem => (
						<CartItem key={cartItem.id} cartItem={cartItem} />
					))}
				</div>
				<div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
					<div className="border">
						<div className="ml-5 mr-5">
							<div className="flex items-center h-12">
								<p className="uppercase font-bold opacity-60">
									Price Details
								</p>
							</div>
							<Divider />
							<div className="space-y-3 font-semibold">
								<div className="flex justify-between pt-3">
									<h3>Price</h3>
									<span>USD {order.order?.totalPrice}</span>
								</div>
								<div className="flex justify-between pt-3">
									<h3>Discount</h3>
									<span className="text-green-600">
										-USD {order.order?.discount}
									</span>
								</div>
								<div className="flex justify-between pt-3">
									<h3>Delivery Charge</h3>
									<span className="text-green-600">Free</span>
								</div>
								<div className="flex justify-between pt-3 font-bold">
									<h3>Total Amount</h3>
									<span className="text-green-600">
										USD {order.order?.totalDiscountedPrice}
									</span>
								</div>
							</div>
							<Button
								onClick={e => handleCheckout(e)}
								variant="contained"
								fullWidth
								sx={{
									px: "2.5rem",
									py: ".7rem",
									bgcolor: "#9155fd",
									mt: 3,
									mb: 3
								}}
							>
								Checkout
							</Button>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default OrderSummary;
