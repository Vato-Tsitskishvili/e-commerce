import { api } from "../../config/ApiConfig";
import {
	FIND_PRODUCT_BY_ID_FAILURE,
	FIND_PRODUCT_BY_ID_REQUEST,
	FIND_PRODUCT_BY_ID_SUCCESS,
	FIND_PRODUCTS_FAILURE,
	FIND_PRODUCTS_REQUEST,
	FIND_PRODUCTS_SUCCESS
} from "./ActionType";

export const findProducts = requestData => async dispatch => {
	dispatch({ type: FIND_PRODUCTS_REQUEST });

	const {
		color,
		sizes,
		minPrice,
		maxPrice,
		minDiscount,
		category,
		stock,
		sort,
		pageNumber,
		pageSize
	} = requestData;

	try {
		const { data } = await api.get(
			`/api/products?color=${color}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
		);
		console.log("data ", data)
		dispatch({ type: FIND_PRODUCTS_SUCCESS, payload: data });
	} catch (error) {
		dispatch({ type: FIND_PRODUCTS_FAILURE, payload: error.message });
	}
};

export const findProductById = requestData => async dispatch => {
	dispatch({ type: FIND_PRODUCT_BY_ID_REQUEST });

	const { productId } = requestData;

	try {
		const { data } = await api.get(`/api/products/id/${productId}`);

		dispatch({ type: FIND_PRODUCT_BY_ID_SUCCESS, payload: data });
	} catch (error) {
		dispatch({ type: FIND_PRODUCT_BY_ID_FAILURE, payload: error.message });
	}
};
