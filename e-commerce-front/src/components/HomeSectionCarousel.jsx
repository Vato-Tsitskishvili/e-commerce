import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { Button } from "@mui/material";
import React, { useRef, useState } from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "./HomeSectionCard";

const HomeSectionCarousel = ({ data, sectionName }) => {
	const carouselRef = useRef(null);
	const [activeIndex, setActiveIndex] = useState(0);
	const responsive = {
		0: { items: 1 },
		720: { items: 3 },
		1024: { items: 5.5 }
	};
	const items = data
		.slice(0, 10)
		.map(item => <HomeSectionCard product={item} />);

	const slidePrev = () => {
		const newIndex = activeIndex - 1;
		setActiveIndex(newIndex);
		carouselRef.current.slideTo(newIndex);
	};

	const slideNext = () => {
		const newIndex = activeIndex + 1;
		setActiveIndex(newIndex);
		carouselRef.current.slideTo(newIndex);
	};

	return (
		<div className="px-4 lg:px-8">
			<h2 className="text-2xl font-extrabold text-grey-800 py-5">
				{sectionName}
			</h2>
			<div className="relative p-5">
				<AliceCarousel
					items={items}
					responsive={responsive}
					disableButtonsControls
					disableDotsControls
					ref={carouselRef}
					activeIndex={activeIndex}
				/>

				{activeIndex !== 0 && (
					<Button
						className="z-50"
						variant="contained"
						sx={{
							position: "absolute",
							top: "8rem",
							left: "0rem",
							transform: "translateX(-50%) rotate(-90deg)"
						}}
						color="white"
						aria-label="next"
						onClick={slidePrev}
					>
						<KeyboardArrowLeftIcon
							sx={{ transform: "rotate(90deg)" }}
						/>
					</Button>
				)}

				{activeIndex !== items.length - 5 && (
					<Button
						className="z-50"
						variant="contained"
						sx={{
							position: "absolute",
							top: "8rem",
							right: "0rem",
							transform: "translateX(50%) rotate(90deg)"
						}}
						color="white"
						aria-label="next"
						onClick={slideNext}
					>
						<KeyboardArrowLeftIcon
							sx={{ transform: "rotate(90deg)" }}
						/>
					</Button>
				)}
			</div>
		</div>
	);
};

export default HomeSectionCarousel;
