import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import React from "react";
import { useLocation } from "react-router-dom";
import LoginForm from "./LoginForm";
import RegistrationForm from "./RegistrationForm";

const style = {
	position: "absolute",
	top: "50%",
	left: "50%",
	transform: "translate(-50%, -50%)",
	width: 500,
	bgcolor: "background.paper",
	outline: "none",
	boxShadow: 24,
	p: 4
};

const AuthModal = ({ handleClose, open }) => {
	const location = useLocation();

	return (
		<>
			<Modal
				open={open}
				onClose={handleClose}
				aria-labelledby="modal-modal-title"
				aria-describedby="modal-modal-description"
			>
				<Box sx={style}>
					{location.pathname === "/login" ? (
						<LoginForm />
					) : (
						<RegistrationForm />
					)}
				</Box>
			</Modal>
		</>
	);
};

export default AuthModal;
