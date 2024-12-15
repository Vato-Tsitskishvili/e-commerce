import { Grid2 } from "@mui/material";
import React from "react";
import OrderCard from "../order/OrderCard";

const orderStatus = [
	{ label: "On The Way", value: "onTheWay" },
	{ label: "Delivered", value: "delivered" },
	{ label: "Cancelled", value: "cancelled" },
	{ label: "Returned", value: "returned" }
];

const OrderPage = () => {
	return (
		<div className="lg:px-20 px-5">
			<Grid2 container justifyContent={"space-between"}>
				<Grid2 size={{ xs: 2.5 }}>
					<div className="h-auto shadow-lg bg-white p-5 sticky top-5">
						<h2 className="font-bold text-lg">Filter</h2>
						<div className="space-y-4 mt-10">
							<h2 className="uppercase font-semibold">
								Order Status
							</h2>
							{orderStatus.map(option => (
								<div className="flex items-center">
									<input
										type="checkbox"
										className="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-500"
										defaultValue={option.value}
									/>
									<label
										className="ml-3 text-sm text-gray-600"
										htmlFor={option.value}
									>
										{option.label}
									</label>
								</div>
							))}
						</div>
					</div>
				</Grid2>
				<Grid2 size={{ xs: 9 }}>
					<div className="space-y-5">
						{[1, 1, 1, 1, 1, 1, 1].map(() => (
							<OrderCard />
						))}
					</div>
				</Grid2>
			</Grid2>
		</div>
	);
};

export default OrderPage;
