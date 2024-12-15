import React from "react";
import MainCarousel from "../MainCarousel";
import HomeSectionCarousel from "../HomeSectionCarousel";
import MansShortCoat from "../../assets/data/MansShortCoat";

const HomePage = () => {
	return (
		<>
			<MainCarousel />
			<div className="py-20 space-y-10 flex flex-col justify-center px-5 lg:px-10">
				<HomeSectionCarousel
					data={MansShortCoat}
					sectionName="Men's Coats"
				/>
				<HomeSectionCarousel
					data={MansShortCoat}
					sectionName="Men's Shoes"
				/>
				<HomeSectionCarousel
					data={MansShortCoat}
					sectionName="Women's Shoes"
				/>
				<HomeSectionCarousel
					data={MansShortCoat}
					sectionName="Women's Dress"
				/>
			</div>
		</>
	);
};

export default HomePage;
