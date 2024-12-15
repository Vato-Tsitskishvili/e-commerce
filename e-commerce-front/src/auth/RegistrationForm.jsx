import { Button, Grid2, TextField } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { getUser, register } from "../state/auth/Action";

const RegistrationForm = () => {
	const dispatch = useDispatch();
	const jwt = localStorage.getItem("jwt");
	const { auth } = useSelector(store => store);

	useEffect(() => {
		if (jwt) 
			dispatch(getUser(jwt));
	}, [jwt, dispatch, auth.jwt]);

	const handleSubmit = e => {
		e.preventDefault();

		const data = new FormData(e.currentTarget);
		const userData = {
			firstName: data.get("firstName"),
			lastName: data.get("lastName"),
			email: data.get("email"),
			password: data.get("password")
		};

		dispatch(register(userData));

		console.log(userData);
	};

	return (
		<div>
			<form onSubmit={handleSubmit}>
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
					<Grid2 size={{ xs: 12 }}>
						<TextField
							required
							id="email"
							name="email"
							label="Email"
							fullWidth
							autoComplete="email"
							type="email"
						/>
					</Grid2>
					<Grid2 size={{ xs: 12 }}>
						<TextField
							required
							id="password"
							name="password"
							label="Password"
							fullWidth
							autoComplete="password"
							type="password"
						/>
					</Grid2>
					<Grid2 size={{ xs: 12 }}>
						<Button
							className="hover:bg-blue-500"
							fullWidth
							type="submit"
							variant="contained"
							size="large"
							sx={{ padding: ".8rem 0", bgcolor: "#9155FD" }}
						>
							Register
						</Button>
					</Grid2>
				</Grid2>
			</form>
			<div className="flex justify-center flex-col items-center">
				<div className="py-3 flex items-center">
					<p>If you already have an account </p>
					<Link
						to="/login"
						className="ml-2 text-[#9551FD]"
						size="small"
					>
						log in
					</Link>
				</div>
			</div>
		</div>
	);
};

export default RegistrationForm;
