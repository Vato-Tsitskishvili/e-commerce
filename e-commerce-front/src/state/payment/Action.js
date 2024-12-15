import { api } from "../../config/ApiConfig";
import {
	CREATE_PAYMENT_FAILURE,
	CREATE_PAYMENT_REQUEST,
	UPDATE_PAYMENT_REQUEST
} from "./ActionType";

export const createPayment = orderId => async dispatch => {
	dispatch({ type: CREATE_PAYMENT_REQUEST });

	try {
		const { data } = await api.post(
			`/api/payments/${orderId}`,
			{}
		);

		if (data.paymentLinkUrl) window.location.href = data.paymentLinkUrl;

		// dispatch({type: CREATE_PAYMENT_SUCCESS, payload: data})
	} catch (error) {
		dispatch({ type: CREATE_PAYMENT_FAILURE, payload: error.message });
	}
};

export const updatePayment = requestData => async dispatch => {
	dispatch({ type: UPDATE_PAYMENT_REQUEST });

	try {
		const { data } = await api.get(
			`/api/payments?paymentId=${requestData.orderId}&orderId=${requestData.orderId}`
		);
	} catch (error) {}
};
