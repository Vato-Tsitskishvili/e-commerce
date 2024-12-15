const NavidationData = {
	categories: [
		{
			id: "women",
			name: "Women",
			featured: [
				{
					name: "New Arrivals",
					href: "/",
					imageSrc:
						"https://tailwindui.com/img/ecommerce-images/mega-menu-category-01.jpg",
					imageAlt:
						"Models sitting back to back, wearing Basic Tee in black and bone."
				},
				{
					name: "Basic Tees",
					href: "/",
					imageSrc:
						"https://tailwindui.com/img/ecommerce-images/mega-menu-category-02.jpg",
					imageAlt:
						"Close up of Basic Tee fall bundle with off-white, ochre, olive, and black tees."
				}
			],
			sections: [
				{
					id: "clothing",
					name: "Clothing",
					items: [
						{
							name: "Tops",
							id: "top",
							href: `{women/clothing/tops}`
						},
						{ name: "Dresses", id: "women_dress", href: "#" },
						{ name: "Women Jeans", id: "women_jeans" },
						{ name: "Lengha Choli", id: "lengha_choli" },
						{ name: "Sweaters", id: "sweater" },
						{ name: "T-Shirts", id: "t-shirt" },
						{ name: "Jackets", id: "jacket" },
						{ name: "Gouns", id: "gouns" },
						{ name: "Sarees", id: "saree" },
						{ name: "Kurtas", id: "kurtas" }
					]
				},
				{
					id: "accessories",
					name: "Accessories",
					items: [
						{ name: "Watches", id: "watch" },
						{ name: "Wallets", id: "wallet" },
						{ name: "Bags", id: "bag" },
						{ name: "Sunglasses", id: "sunglasse" },
						{ name: "Hats", id: "hat" },
						{ name: "Belts", id: "belt" }
					]
				},
				{
					id: "brands",
					name: "Brands",
					items: [
						{ name: "Full Nelson", id: "full-nelson" },
						{ name: "My Way", id: "my-way" },
						{ name: "Re-Arranged", id: "re-arranged" },
						{ name: "Counterfeit", id: "counterfeit" },
						{ name: "Significant Other", id: "significant-other" }
					]
				}
			]
		},
		{
			id: "men",
			name: "Men",
			featured: [
				{
					name: "New Arrivals",
					id: "#",
					imageSrc:
						"https://tailwindui.com/img/ecommerce-images/product-page-04-detail-product-shot-01.jpg",
					imageAlt:
						"Drawstring top with elastic loop closure and textured interior padding."
				},
				{
					name: "Artwork Tees",
					id: "#",
					imageSrc:
						"https://tailwindui.com/img/ecommerce-images/category-page-02-image-card-06.jpg",
					imageAlt:
						"Three shirts in gray, white, and blue arranged on table with same line drawing of hands and shapes overlapping on front of shirt."
				}
			],
			sections: [
				{
					id: "clothing",
					name: "Clothing",
					items: [
						{ name: "Mens Tops", id: "top" },
						{ name: "Shirt", id: "shirt" },
						{ name: "Men Jeans", id: "jeans" },
						{ name: "Sweaters", id: "sweaters" },
						{ name: "T-Shirts", id: "t-shirt" },
						{ name: "Jackets", id: "jackets" },
						{ name: "Activewear", id: "activewear" }
					]
				},
				{
					id: "accessories",
					name: "Accessories",
					items: [
						{ name: "Watches", id: "watches" },
						{ name: "Wallets", id: "wallets" },
						{ name: "Bags", id: "bags" },
						{ name: "Sunglasses", id: "sunglasses" },
						{ name: "Hats", id: "hats" },
						{ name: "Belts", id: "#belts" }
					]
				},
				{
					id: "brands",
					name: "Brands",
					items: [
						{ name: "Re-Arranged", id: "re-arranged" },
						{ name: "Counterfeit", id: "counterfeit" },
						{ name: "Full Nelson", id: "full-nelson" },
						{ name: "My Way", id: "my-way" }
					]
				}
			]
		}
	],
	pages: [
		{ name: "Company", id: "/" },
		{ name: "Stores", id: "/" }
	]
};

export default NavidationData;
