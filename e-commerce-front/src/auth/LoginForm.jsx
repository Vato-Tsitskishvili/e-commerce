import { Button, TextField } from "@mui/material";
import Grid2 from "@mui/material/Grid2";
import React from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { login } from "../state/auth/Action";

const LoginForm = () => {
	const dispatch = useDispatch();

	const handleSubmit = e => {
		e.preventDefault();

		const data = new FormData(e.currentTarget);
		const userData = {
			email: data.get("email"),
			password: data.get("password")
		};

		dispatch(login(userData));
		console.log(userData);
	};

	return (
		<div>
			<form onSubmit={handleSubmit}>
				<Grid2 container spacing={3}>
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
							Log In
						</Button>
					</Grid2>
				</Grid2>
			</form>
			<div className="flex justify-center flex-col items-center">
				<div className="py-3 flex items-center">
					<p>If you don't have an account yet </p>
					<Link
						to="/register"
						className="ml-2 text-[#9551FD]"
						size="small"
					>
						register
					</Link>
				</div>
			</div>
		</div>
	);
};

export default LoginForm;
