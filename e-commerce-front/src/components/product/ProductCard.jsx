import React from "react";
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";

const ProductCard = ({ product }) => {
	const navigate = useNavigate();

	return (
		<div
			key={product.id}
			onClick={() => navigate(`/product/${product.id}`)}
			className="product-card w-[15rem] m-3 transition-all cursor-pointer"
		>
			<div className="h-[20rem]">
				<img
					className="h-full w-full object-cover object-left-top"
					src={product.imageUrl}
					alt=""
				/>
			</div>

			<div className="main-text bg-white p-3">
				<h3 className="font-bold opacity-60">{product.brand}</h3>
				<p>{product.title}</p>

				<div className="flex items-center space-x-2">
					<p className="line-through opacity-50">
						USD {product.price}
					</p>
					<p className="font-semibold">USD {product.discountedPrice}</p>
					<p className="text-green-600 font-semibold">
						{product.discountPercent}% Off
					</p>
				</div>
			</div>
		</div>
	);
};

export default ProductCard;
