import { Box, Button, Grid2, TextField } from "@mui/material";
import React from "react";
import AddressCard from "../AddressCard";
import { useDispatch } from "react-redux";
import { createOrder } from "../../state/order/Action";
import { useNavigate } from "react-router-dom";

const DeliveryAddressForm = () => {
	const dispatch = useDispatch()
	const navigate = useNavigate()
	
	const handleSubmit = e => {
		e.preventDefault();

		const data = new FormData(e.currentTarget);
		const address = {
			firstName: data.get("firstName"),
			lastName: data.get("lastName"),
			streetAddress: data.get("address"),
			city: data.get("city"),
			state: data.get("state"),
			zipCode: data.get("zip"),
			mobile: data.get("phoneNum")
		};

		const orderData = {address, navigate}
		dispatch(createOrder(orderData))
		console.log(address);
	};

	return (
		<div>
			<Grid2 container spacing={4}>
				<Grid2
					size={{ xs: 12, lg: 5 }}
					className="border rounded-e-md shadow-md h-[30.5rem] overflow-y-scroll"
				>
					<div className="px-5 py-7 border-b cursor-pointer">
						<AddressCard />
						<Button
							sx={{ mt: 2, bgcolor: "#9155fd" }}
							size="large"
							variant="contained"
						>
							Deliver Here
						</Button>
					</div>
				</Grid2>

				<Grid2 size={{ xs: 12, lg: 7 }}>
					<Box className="border rounded-s-md shadow-md p-5">
						<form onSubmit={e => handleSubmit(e)}>
							<Grid2 container spacing={3}>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="firstName"
										name="firstName"
										label="First Name"
										fullWidth
										autoComplete="given-name"
									/>
								</Grid2>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="lastName"
										name="lastName"
										label="Last Name"
										fullWidth
										autoComplete="given-name"
									/>
								</Grid2>
								<Grid2 size={12}>
									<TextField
										required
										id="address"
										name="address"
										label="Address"
										fullWidth
										autoComplete="given-name"
										multiline
										rows={4}
									/>
								</Grid2>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="city"
										name="city"
										label="City"
										fullWidth
										autoComplete="given-name"
									/>
								</Grid2>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="state"
										name="state"
										label="State/Province/Region"
										fullWidth
										autoComplete="given-name"
									/>
								</Grid2>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="zip"
										name="zip"
										label="Zip Code"
										fullWidth
									/>
								</Grid2>
								<Grid2 size={{ xs: 12, sm: 6 }}>
									<TextField
										required
										id="phoneNum"
										name="phoneNum"
										label="Phone Number"
										fullWidth
										autoComplete="given-name"
									/>
								</Grid2>
								<Grid2 size={12}>
									<Button
										sx={{ mt: 2, bgcolor: "#9155fd" }}
										size="large"
										variant="contained"
										type="submit"
										fullWidth
									>
										Deliver Here
									</Button>
								</Grid2>
							</Grid2>
						</form>
					</Box>
				</Grid2>
			</Grid2>
		</div>
	);
};

export default DeliveryAddressForm;
