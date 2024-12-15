import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import { Button, IconButton } from "@mui/material";
import React from "react";
import { useDispatch } from "react-redux";
import { removeCartItem, updateCartItem } from "../../state/cart/Action";

const CartItem = ({ cartItem }) => {
	const dispatch = useDispatch();

	const handleUpdateCartItem = num => {
		const data = {
			data: { quantity: cartItem.quantity + num },
			cartItemId: cartItem?.id
		};
		dispatch(updateCartItem(data));
	};

	const handleRemoveCartItem = () => {
		dispatch(removeCartItem(cartItem.id));
	};

	return (
		<div className="p-5 shadow-lg border rounded-md">
			<div className="flex items-center">
				<div className="w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem]">
					<img
						className="w-full h-full object-cover object-top"
						src={cartItem.product?.imageUrl}
						alt="hello"
					/>
				</div>
				<div className="ml-5 space-y-1">
					<p className="font-semibold">{cartItem.product?.title}</p>
					<p className="opacity-70">Size: L, White</p>
					<p className="opacity-70 mt-2">Seller: H&M</p>

					<div className="flex space-x-5 items-center text-gray-900 pt-4">
						<p className="opacity-50 line-through">
							USD {cartItem.product?.price}
						</p>
						<p className="font-semibold">
							USD {cartItem.product?.discountedPrice}
						</p>
						<p className="text-green-600 font-semibold">
							{cartItem.product?.discountPercent}% Off
						</p>
					</div>
				</div>
			</div>
			<div className="lg:flex items-center lg:space-x-10 pt-4">
				<div className="flex items-center space-x-2 gap-3">
					<IconButton
						onClick={() => handleUpdateCartItem(-1)}
						disabled={cartItem.quantity <= 1}
					>
						<RemoveCircleOutlineIcon />
					</IconButton>
					<span className="py-1 px-7 border rounded-sm">
						{cartItem.quantity}
					</span>
					<IconButton
						onClick={() => handleUpdateCartItem(1)}
						sx={{ color: "#9055fd" }}
					>
						<AddCircleOutlineIcon />
					</IconButton>
					<Button onClick={handleRemoveCartItem}>Remove</Button>
				</div>
			</div>
		</div>
	);
};

export default CartItem;
