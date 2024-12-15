import StarBorderIcon from "@mui/icons-material/StarBorder";
import { Box, Grid2 } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import React from "react";
import AddressCard from "../AddressCard";
import OrderTracker from "../order/OrderTracker";

const OrderDetailsPage = () => {
	return (
		<div className="px-5 lg:px-20">
			<div>
				<h2 className="font-bold text-lg py-7">Delivery Address</h2>
				<AddressCard />
			</div>
			<div className="py-20">
				<OrderTracker activeStep={3} />
			</div>
			<Grid2 containe className="space-y-5">
				{[1, 1, 1, 1, 1, 1].map(() => (
					<Grid2
						container
						className="shadow-xl rounded-md p-5 border"
						alignItems={"center"}
						justifyContent={"space-between"}
					>
						<Grid2 size={{ xs: 6 }}>
							<div className="flex items-center space-x-4">
								<img
									className="w-[11rem] h-[14rem] object-cover object-top"
									src="https://img-lcwaikiki.mncdn.com/mnresize/400/-/pim/productimages/20242/7161592/v1/l_20242-w42533z8-hcz-94-72-90-187_a.jpg"
									alt="addf"
								/>
								<div className="space-y-2 ml-5">
									<p className="font-semibold">
										Standart Fit Hooded Men's Coat
									</p>
									<p className="space-x-5 opacity-50 text-xs font-semibold">
										<span className="mr-2">
											Color: Green
										</span>
										Size: M
									</p>
									<p>Seller: Someone</p>
									<p>USD 123234</p>
								</div>
							</div>
						</Grid2>
						<Grid2>
							<Box color={deepPurple[500]}>
								<StarBorderIcon
									sx={{ fontSize: "2.3rem" }}
									className="px-2"
								/>
								<span>Rate & Review Product</span>
							</Box>
						</Grid2>
					</Grid2>
				))}
			</Grid2>
		</div>
	);
};

export default OrderDetailsPage;
