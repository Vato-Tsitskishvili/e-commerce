import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import CustomerRoutes from "./routes/CustomerRoutes";

function App() {
	return (
		<Router>
			<Routes>
				<Route path="/*" element={<CustomerRoutes />} />
			</Routes>
		</Router>
	);
}

export default App;

