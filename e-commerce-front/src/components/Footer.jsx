import { Button } from "@headlessui/react";
import { Grid2, Link, Typography } from "@mui/material";
import React from "react";

const Footer = () => {
	return (
		<footer className="bg-black text-white text-center mt-10">
			<Grid2
				className="mr-48 ml-48 uppercase flex justify-between"
				container
				sx={{
					bgcolor: "black",
					color: "white",
					py: 3
				}}
			>
				<Grid2 xs={12} sm={6} md={3} className="flex flex-col">
					<Typography className="pb-5" variant="h6">
						Company
					</Typography>
					<Button className="pb-5" variant="h6">
						About
					</Button>
					<Button className="pb-5" variant="h6">
						Blog
					</Button>
					<Button className="pb-5" variant="h6">
						Press
					</Button>
					<Button className="pb-5" variant="h6">
						Jobs
					</Button>
					<Button className="pb-5" variant="h6">
						Partners
					</Button>
				</Grid2>
				<Grid2 xs={12} sm={6} md={3} className="flex flex-col">
					<Typography className="pb-5" variant="h6">
						Solutions
					</Typography>
					<Button className="pb-5" variant="h6">
						Marketing
					</Button>
					<Button className="pb-5" variant="h6">
						Analitics
					</Button>
					<Button className="pb-5" variant="h6">
						Commerce
					</Button>
					<Button className="pb-5" variant="h6">
						Insights
					</Button>
					<Button className="pb-5" variant="h6">
						Support
					</Button>
				</Grid2>
				<Grid2 xs={12} sm={6} md={3} className="flex flex-col">
					<Typography className="pb-5" variant="h6">
						Documentation
					</Typography>
					<Button className="pb-5" variant="h6">
						Guides
					</Button>
					<Button className="pb-5" variant="h6">
						API Status
					</Button>
				</Grid2>
				<Grid2 xs={12} sm={6} md={3} className="flex flex-col">
					<Typography className="pb-5" variant="h6">
						Legal
					</Typography>
					<Button className="pb-5" variant="h6">
						Claim
					</Button>
					<Button className="pb-5" variant="h6">
						Privacy
					</Button>
					<Button className="pb-5" variant="h6">
						Terms
					</Button>
				</Grid2>
			</Grid2>

			<div>
				<Typography variant="body2" component="p" align="center">
					&copy; 2024 My Company. All Right Reserved
				</Typography>
				<Typography variant="body2" component="p" align="center">
					Made by Vakhtang Tsitskishvili.
				</Typography>
				<Typography variant="body2" component="p" align="center">
					Icons Made by{" "}
					<Link
						href="https://freepik.com"
						color="inherit"
						underline="always"
					>
						Freepik
					</Link>{" "}
					from{" "}
					<Link
						href="https://flaticon.com"
						color="inherit"
						underline="always"
					>
						flaticon.com
					</Link>
				</Typography>
				<br />
			</div>
		</footer>
	);
};

export default Footer;
