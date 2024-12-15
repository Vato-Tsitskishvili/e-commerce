import { Grid2 } from "@mui/material";
import React from "react";
import AdjustIcon from "@mui/icons-material/Adjust";
import { useNavigate } from "react-router-dom";

const OrderCard = () => {
	const navigate = useNavigate();

	return (
		<div
			onClick={() => navigate(`/account/order/${5}`)}
			className="p-5 shadow-lg hover:shadow-2xl border"
		>
			<Grid2 container spacing={2} justifyContent={"space-between"}>
				<Grid2 size={{ xs: 6 }}>
					<div className="flex cursor-pointer">
						<img
							className="w-[8rem] h-[8rem] object-cover object-top"
							src="https://img-lcwaikiki.mncdn.com/mnresize/400/-/pim/productimages/20242/7161592/v1/l_20242-w42533z8-hcz-94-72-90-187_a.jpg"
							alt="asda"
						/>
						<div className="ml-5 space-y-5">
							<p>Standart Fit Hooded Men's Coat</p>
							<p className="opacity-50 text-xs font-semibold">
								Size: M
							</p>
							<p className="opacity-50 text-xs font-semibold">
								Color: Black
							</p>
						</div>
					</div>
				</Grid2>
				<Grid2 size={{ xs: 2 }}>
					<p>USD 1092</p>
				</Grid2>
				<Grid2 size={{ xs: 4 }}>
					{true ? (
						<>
							<p>
								<AdjustIcon
									color="success"
									sx={{
										width: "20px",
										height: "20px",
										marginRight: 1
									}}
								/>
								<span>Delivered On March 3</span>
							</p>
							<p className="text-xs capitalize">
								Your item has been delivered
							</p>
						</>
					) : (
						<p>
							<span>Expected Delivery on Match 3</span>
						</p>
					)}
				</Grid2>
			</Grid2>
		</div>
	);
};

export default OrderCard;
