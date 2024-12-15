// import React from 'react'
// import CartItem from '../../cart/CartItem'
// import { Button, Divider } from '@mui/material'

// const CartPageTemplate = ({items, handleClick, btnTitle}) => {
//   return (
//     <>
// 			<div className="lg:grid grid-cols-3 lg:px-16 relative">
// 				<div className="col-span-2">
// 					{items.map(cartItem => (
// 						<CartItem key={cartItem.id} cartItem={cartItem} />
// 					))}
// 				</div>
// 				<div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
// 					<div className="border">
// 						<div className="ml-5 mr-5">
// 							<div className="flex items-center h-12">
// 								<p className="uppercase font-bold opacity-60">
// 									Price Details
// 								</p>
// 							</div>
// 							<Divider />
// 							<div className="space-y-3 font-semibold">
// 								<div className="flex justify-between pt-3">
// 									<h3>Price</h3>
// 									<span>USD {cart.cart?.totalPrice}</span>
// 								</div>
// 								<div className="flex justify-between pt-3">
// 									<h3>Discount</h3>
// 									<span className="text-green-600">
// 										-USD {cart.cart?.discount}
// 									</span>
// 								</div>
// 								<div className="flex justify-between pt-3">
// 									<h3>Delivery Charge</h3>
// 									<span className="text-green-600">Free</span>
// 								</div>
// 								<div className="flex justify-between pt-3 font-bold">
// 									<h3>Total Amount</h3>
// 									<span className="text-green-600">
// 										USD {cart.cart?.totalDiscountedPrice}
// 									</span>
// 								</div>
// 							</div>
// 							<Button
// 								onClick={handleClick}
// 								variant="contained"
// 								fullWidth
// 								sx={{
// 									px: "2.5rem",
// 									py: ".7rem",
// 									bgcolor: "#9155fd",
// 									mt: 3,
// 									mb: 3
// 								}}
// 							>
// 								{btnTitle}
// 							</Button>
// 						</div>
// 					</div>
// 				</div>
// 			</div>
// 		</>
//   )
// }

// export default CartPageTemplate
