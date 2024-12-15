import { Avatar, Box, Grid2, Rating } from "@mui/material";
import React from "react";

const ProductReviewCard = () => {
	return (
		<Grid2 container spacing={2} gap={3}>
			<Grid2 xs={1}>
				<Box>
					<Avatar
						className="text-white"
						sx={{ width: 56, height: 56, bgcolor: "#9155fd" }}
					>
						V
					</Avatar>
				</Box>
			</Grid2>
			<Grid2 xs={9}>
				<div className="space-y-2">
					<p className="font-semibold text-lg">Vato</p>
					<p className="opacity-70">November 22, 2024</p>
				</div>
				<Rating
					value={4.5}
					name="half-rating"
					readOnly
					precision={0.5}
				/>
				<p>Nice product</p>
			</Grid2>
		</Grid2>
	);
};

export default ProductReviewCard;
