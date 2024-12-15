import React from "react";
import { Route, Routes } from "react-router-dom";
import Footer from "../components/Footer";
import Navigation from "../components/Navigation";
import CartPage from "../components/pages/CartPage";
import HomePage from "../components/pages/HomePage";
import ProductDetailsPage from "../components/pages/ProductDetailsPage";
import ProductPage from "../components/pages/ProductPage";
import CheckoutPage from "../components/pages/CheckoutPage";
import OrderPage from "../components/pages/OrderPage";
import OrderDetailsPage from "../components/pages/OrderDetailsPage";
import PaymentSuccess from "../components/payment/PaymentSuccess";

const CustomerRoutes = () => {
	return (
		<>
			<Navigation />
			<Routes>
				<Route path="/login" element={<HomePage />} />
				<Route path="/register" element={<HomePage />} />
				<Route path="/" element={<HomePage />} />
				<Route path="/cart" element={<CartPage />} />
				<Route
					path="/:levelOne/:levelTwo/:levelThree"
					element={<ProductPage />}
				/>
				<Route
					path="/product/:productId"
					element={<ProductDetailsPage />}
				/>
				<Route path="/checkout" element={<CheckoutPage />} />
				<Route path="/account/order" element={<OrderPage />} />
				<Route
					path="/account/order/:orderId"
					element={<OrderDetailsPage />}
				/>
				<Route path="/payments/:orderId" element={<PaymentSuccess />} />
			</Routes>
			<Footer />
		</>
	);
};

export default CustomerRoutes;
