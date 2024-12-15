export const color = [
	"White",
	"Black",
	"Red",
	"Marun",
	"Beige",
	"Pink",
	"Green",
	"Yellow"
];

export const filters = [
	{
		id: "color",
		name: "Color",
		options: [
			{ value: "white", label: "White" },
			{ value: "beige", label: "Beige" },
			{ value: "blue", label: "Blue" },
			{ value: "brown", label: "Brown" },
			{ value: "green", label: "Green" },
			{ value: "purple", label: "Purple" },
			{ value: "yellow", label: "Yellow" }
		]
	},
	{
		id: "size",
		name: "Size",
		options: [
			{ value: "S", label: "S" },
			{ value: "M", label: "M" },
			{ value: "L", label: "L" }
		]
	}
];

export const singleFilter = [
	{
		id: "price",
		name: "Price",
		options: [
			{ value: "25-49", label: "25$ To 50$" },
			{ value: "50-99", label: "50$ To 99$" },
			{ value: "100-149", label: "100$ To 149$" },
			{ value: "150-199", label: "150$ To 199$" },
			{ value: "200-249", label: "200$ To 249$" },
			{ value: "250-300", label: "250$ To 300$" }
		]
	},
	{
		id: "discount",
		name: "Discount Range",
		options: [
			{ value: "10", label: "10% and Above" },
			{ value: "20", label: "20% and Above" },
			{ value: "30", label: "30% and Above" },
			{ value: "40", label: "40% and Above" },
			{ value: "50", label: "50% and Above" },
			{ value: "60", label: "60% and Above" },
			{ value: "70", label: "70% and Above" },
			{ value: "80", label: "80% and Above" }
		]
	},
	{
		id: "stock",
		name: "Avalibility",
		options: [
			{ value: "in_stock", label: "In Stock" },
			{ value: "out_of_stock", label: "Out of Stock" }
		]
	}
];

export const sortOptions = [
	{ name: "Price: Low to High", query: "price_low", current: false },
	{ name: "Price: High to Low", query: "price_high", current: false }
];
