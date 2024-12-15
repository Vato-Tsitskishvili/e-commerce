import { Alert, AlertTitle, Grid2 } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { getOrderById } from "../../state/order/Action";
import { updatePayment } from "../../state/payment/Action";
import OrderTracker from "../order/OrderTracker";
import AddressCard from "../AddressCard";

const PaymentSuccess = () => {
	const [paymentId, setPaymentId] = useState();
	const [referenceId, setReferenceId] = useState();
	const [paymentStatus, setPaymentStatus] = useState();
	const { orderId } = useParams();
	const dispatch = useDispatch();
	const { order } = useSelector(store => store);

	useEffect(() => {
		const urlParam = new URLSearchParams(window.location.search);

		setPaymentId(urlParam.get("stripePaymentId"));
		setPaymentStatus(urlParam.get("stripePaymentLinkStatus"));
	}, []);

	useEffect(() => {
		const data = { orderId, paymentId };
		dispatch(getOrderById(orderId));
		dispatch(updatePayment(data));
	}, [orderId, paymentId, dispatch]);

	return (
		<div className="px-2 lg:px-36">
			<div className="flex flex-col justify-center items-center">
				<Alert
					variant="filled"
					severity="success"
					sx={{ mb: 6, width: "fit-content" }}
				>
					<AlertTitle>Payment Success</AlertTitle>
					Congratulations! Your Order Was Placed
				</Alert>
			</div>
			<OrderTracker activeStep={1} />
			<Grid2 container className="space-y-5 py-5 pt-20">
				{[1, 1, 1].map(item => (
					<Grid2
						key={item.id}
						container
						className="shadow-xl rounded-md p-5"
						alignItems="center"
						justifyContent="center"
					>
						<Grid2 size={{ xs: 6 }}>
							<div className="flex items-center">
								<img
									className="w-[10rem] h-[10rem] object-cover object-top"
									src="https://img-lcwaikiki.mncdn.com/mnresize/400/-/pim/productimages/20242/7161592/v1/l_20242-w42533z8-hcz-94-72-90-187_a.jpg"
									alt="a top"
								/>
								<div className="ml-5 space-y-2">
									<p>{item.product.title}</p>
									<div className="opacity-50 text-xs font-semibold space-x-5">
										<span>Color: {item.color}</span>
										<span>Size: {item.size}</span>
									</div>
									<p>Seller: {item.product.brand}</p>
									<p>USD {item.price}</p>
								</div>
							</div>
						</Grid2>
						<Grid2>
							<AddressCard address={""} />
						</Grid2>
					</Grid2>
				))}
			</Grid2>
		</div>
	);
};

export default PaymentSuccess;
