import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import MainCarouselData from "../assets/data/MainCarouselData";

const MainCarousel = () => {
	const items = MainCarouselData.map(item => (
		<img
			className="cursor-pointer"
			role="presentation"
			src={item.src}
			alt="hello"
		/>
	));

	return (
		<AliceCarousel
			items={items}
			disableButtonsControls
			autoPlay
			autoPlayInterval={2000}
			infinite
		/>
	);
};

export default MainCarousel;
