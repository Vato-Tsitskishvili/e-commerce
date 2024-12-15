import {
	Dialog,
	DialogPanel,
	Popover,
	PopoverButton,
	PopoverGroup,
	PopoverPanel,
	Tab,
	TabGroup,
	TabList,
	TabPanel,
	TabPanels,
	Transition,
	TransitionChild
} from "@headlessui/react";
import {
	Bars3Icon,
	MagnifyingGlassIcon,
	ShoppingBagIcon,
	XMarkIcon
} from "@heroicons/react/24/outline";
import { Fragment, useEffect, useState } from "react";

import { Avatar, Button, Menu, MenuItem } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import { useDispatch, useSelector } from "react-redux";
import { useLocation, useNavigate } from "react-router-dom";
import NavigationData from "../assets/data/NavigationData";
import AuthModal from "../auth/AuthModal";
import { getUser, logout } from "../state/auth/Action";
import logo from "../assets/images/logo.jpg"

const classNames = (...classes) => {
	return classes.filter(Boolean).join(" ");
};

const Navigation = () => {
	const [open, setOpen] = useState(false);
	const [openAuthModal, setOpenAuthModal] = useState(false);
	const [anchorEl, setAnchorEl] = useState(null);
	const navigate = useNavigate();
	const dispatch = useDispatch();
	const location = useLocation();
	const { auth } = useSelector(store => store);

	const openUserMenu = Boolean(anchorEl);
	const jwt = localStorage.getItem("jwt");

	const handleUserClick = e => {
		setAnchorEl(e.currentTarget);
	};

	const handleCloseUserMenu = () => {
		setAnchorEl(null);
	};

	const handleOpen = () => {
		setOpenAuthModal(true);
	};

	const handleClose = () => {
		setOpenAuthModal(false);
		navigate("/");
	};

	const handleCategoryClick = (category, section, item, close) => {
		navigate(`/${category.id}/${section.id}/${item.id}`);
		close();
	};

	const handleLogout = () => {
		dispatch(logout());
		handleCloseUserMenu();
	};

	useEffect(() => {
		if (jwt) dispatch(getUser(jwt));
	}, [jwt, auth.jwt]);

	useEffect(() => {
		if (auth.user) handleClose();

		if (location.pathname === "/login" || location.pathname === "/register")
			navigate(-1);
	}, [auth.user]);

	return (
		<div className="bg-white pb-10">
			{/* Mobile menu */}
			<Transition show={open} as={Fragment}>
				<Dialog
					as="div"
					className="relative z-40 lg:hidden"
					onClose={setOpen}
				>
					<TransitionChild
						as={Fragment}
						enter="transition-opacity ease-linear duration-300"
						enterFrom="opacity-0"
						enterTo="opacity-100"
						leave="transition-opacity ease-linear duration-300"
						leaveFrom="opacity-100"
						leaveTo="opacity-0"
					>
						<div className="fixed inset-0 bg-black bg-opacity-25" />
					</TransitionChild>

					<div className="fixed inset-0 z-40 flex">
						<TransitionChild
							as={Fragment}
							enter="transition ease-in-out duration-300 transform"
							enterFrom="-translate-x-full"
							enterTo="translate-x-0"
							leave="transition ease-in-out duration-300 transform"
							leaveFrom="translate-x-0"
							leaveTo="-translate-x-full"
						>
							<DialogPanel className="relative flex w-full max-w-xs flex-col overflow-y-auto bg-white pb-12 shadow-xl">
								<div className="flex px-4 pb-2 pt-5">
									<button
										type="button"
										className="-m-2 inline-flex items-center justify-center rounded-md p-2 text-gray-400"
										onClick={() => setOpen(false)}
									>
										<span className="sr-only">
											Close menu
										</span>
										<XMarkIcon
											className="h-6 w-6"
											aria-hidden="true"
										/>
									</button>
								</div>

								{/* Links */}
								<TabGroup as="div" className="mt-2">
									<div className="border-b border-gray-200">
										<TabList className="-mb-px flex space-x-8 px-4">
											{NavigationData.categories.map(
												category => (
													<Tab
														key={category.name}
														className={({
															selected
														}) =>
															classNames(
																selected
																	? "border-indigo-600 text-indigo-600"
																	: "border-transparent text-gray-900",
																"flex-1 whitespace-nowrap border-b-2 px-1 py-4 text-base font-medium border-none"
															)
														}
													>
														{category.name}
													</Tab>
												)
											)}
										</TabList>
									</div>
									<TabPanels as={Fragment}>
										{NavigationData.categories.map(
											category => (
												<TabPanel
													key={category.name}
													className="space-y-10 px-4 pb-8 pt-10"
												>
													<div className="grid grid-cols-2 gap-x-4">
														{category.featured.map(
															item => (
																<div
																	key={
																		item.name
																	}
																	className="group relative text-sm"
																>
																	<div className="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-100 group-hover:opacity-75">
																		<img
																			src={
																				item.imageSrc
																			}
																			alt={
																				item.imageAlt
																			}
																			className="object-cover object-center"
																		/>
																	</div>
																	<a
																		href={
																			item.href
																		}
																		className="mt-6 block font-medium text-gray-900"
																	>
																		<span
																			className="absolute inset-0 z-10"
																			aria-hidden="true"
																		/>
																		{
																			item.name
																		}
																	</a>
																	<p
																		aria-hidden="true"
																		className="mt-1"
																	>
																		Shop now
																	</p>
																</div>
															)
														)}
													</div>
													{category.sections.map(
														section => (
															<div
																key={
																	section.name
																}
															>
																<p
																	id={`${category.id}-${section.id}-heading-mobile`}
																	className="font-medium text-gray-900"
																>
																	{
																		section.name
																	}
																</p>
																{/* eslint-disable-next-line jsx-a11y/no-redundant-roles */}
																<ul
																	role="list"
																	aria-labelledby={`${category.id}-${section.id}-heading-mobile`}
																	className="mt-6 flex flex-col space-y-6"
																>
																	{section.items.map(
																		item => (
																			<li
																				key={
																					item.name
																				}
																				className="flow-root"
																			>
																				<p className="-m-2 block p-2 text-gray-500">
																					{
																						item.name
																					}
																				</p>
																			</li>
																		)
																	)}
																</ul>
															</div>
														)
													)}
												</TabPanel>
											)
										)}
									</TabPanels>
								</TabGroup>

								<div className="space-y-6 border-t border-gray-200 px-4 py-6">
									{NavigationData.pages.map(page => (
										<div
											key={page.name}
											className="flow-root"
										>
											<a
												href={page.href}
												className="-m-2 block p-2 font-medium text-gray-900"
											>
												{page.name}
											</a>
										</div>
									))}
								</div>

								<div className="space-y-6 border-t border-gray-200 px-4 py-6">
									<div className="flow-root">
										<a
											href="/"
											className="-m-2 block p-2 font-medium text-gray-900"
										>
											Log in
										</a>
									</div>
								</div>

								<div className="border-t border-gray-200 px-4 py-6">
									<a
										href="/"
										className="-m-2 flex items-center p-2"
									>
										<img
											src="https://tailwindui.com/img/flags/flag-canada.svg"
											alt=""
											className="block h-auto w-5 flex-shrink-0"
										/>
										<span className="ml-3 block text-base font-medium text-gray-900">
											CAD
										</span>
										<span className="sr-only">
											, change currency
										</span>
									</a>
								</div>
							</DialogPanel>
						</TransitionChild>
					</div>
				</Dialog>
			</Transition>

			<header className="relative bg-white">
				<p className="flex h-10 items-center justify-center bg-indigo-600 px-4 text-sm font-medium text-white sm:px-6 lg:px-8">
					Get free delivery on orders over $100
				</p>

				<nav aria-label="Top" className="mx-auto">
					<div className="border-b border-gray-200">
						<div className="flex h-16 items-center px-11">
							<button
								type="button"
								className="rounded-md bg-white p-2 text-gray-400 lg:hidden"
								onClick={() => setOpen(true)}
							>
								<span className="sr-only">Open menu</span>
								<Bars3Icon
									className="h-6 w-6"
									aria-hidden="true"
								/>
							</button>

							{/* Logo */}
							<div className="ml-4 flex lg:ml-0">
								<span className="sr-only">Your Company</span>
								<img
									src={logo}
									alt="Shopwithzosh"
									className="h-19 w-20 mr-2"
								/>
							</div>

							{/* Flyout menus */}
							<PopoverGroup className="hidden lg:ml-8 lg:block lg:self-stretch z-10">
								<div className="flex h-full space-x-8">
									{NavigationData.categories.map(category => (
										<Popover
											key={category.name}
											className="flex"
										>
											{({ open, close }) => (
												<>
													<div className="relative flex">
														<PopoverButton
															className={classNames(
																open
																	? "border-indigo-600 text-indigo-600"
																	: "border-transparent text-gray-700 hover:text-gray-800",
																"relative z-10 -mb-px flex items-center border-b-2 pt-px text-sm font-medium transition-colors duration-200 ease-out"
															)}
														>
															{category.name}
														</PopoverButton>
													</div>

													<Transition
														as={Fragment}
														enter="transition ease-out duration-200"
														enterFrom="opacity-0"
														enterTo="opacity-100"
														leave="transition ease-in duration-150"
														leaveFrom="opacity-100"
														leaveTo="opacity-0"
													>
														<PopoverPanel className="absolute inset-x-0 top-full text-sm text-gray-500">
															{/* Presentational element used to render the bottom shadow, if we put the shadow on the actual panel it pokes out the top, so we use this shorter element to hide the top of the shadow */}
															<div
																className="absolute inset-0 top-1/2 bg-white shadow"
																aria-hidden="true"
															/>

															<div className="relative bg-white">
																<div className="mx-auto max-w-7xl px-8">
																	<div className="grid grid-cols-2 gap-x-8 gap-y-10 py-16">
																		<div className="col-start-2 grid grid-cols-2 gap-x-8">
																			{category.featured.map(
																				item => (
																					<div
																						key={
																							item.name
																						}
																						className="group relative text-base sm:text-sm"
																					>
																						<div className="aspect-h-1 aspect-w-1 overflow-hidden rounded-lg bg-gray-100 group-hover:opacity-75">
																							<img
																								src={
																									item.imageSrc
																								}
																								alt={
																									item.imageAlt
																								}
																								className="object-cover object-center"
																							/>
																						</div>
																						<a
																							href={
																								item.href
																							}
																							className="mt-6 block font-medium text-gray-900"
																						>
																							<span
																								className="absolute inset-0 z-10"
																								aria-hidden="true"
																							/>
																							{
																								item.name
																							}
																						</a>
																						<p
																							aria-hidden="true"
																							className="mt-1"
																						>
																							Shop
																							now
																						</p>
																					</div>
																				)
																			)}
																		</div>
																		<div className="row-start-1 grid grid-cols-3 gap-x-8 gap-y-10 text-sm">
																			{category.sections.map(
																				section => (
																					<div
																						key={
																							section.name
																						}
																					>
																						<p
																							id={`${section.name}-heading`}
																							className="font-medium text-gray-900"
																						>
																							{
																								section.name
																							}
																						</p>
																						{/* eslint-disable-next-line jsx-a11y/no-redundant-roles */}
																						<ul
																							role="list"
																							aria-labelledby={`${section.name}-heading`}
																							className="mt-6 space-y-6 sm:mt-4 sm:space-y-4"
																						>
																							{section.items.map(
																								item => (
																									<li
																										key={
																											item.name
																										}
																										className="flex"
																									>
																										<p
																											onClick={() =>
																												handleCategoryClick(
																													category,
																													section,
																													item,
																													close
																												)
																											}
																											className="cursor-pointer hover:text-gray-800"
																										>
																											{
																												item.name
																											}
																										</p>
																									</li>
																								)
																							)}
																						</ul>
																					</div>
																				)
																			)}
																		</div>
																	</div>
																</div>
															</div>
														</PopoverPanel>
													</Transition>
												</>
											)}
										</Popover>
									))}

									{NavigationData.pages.map(page => (
										<a
											key={page.name}
											href={page.href}
											className="flex items-center text-sm font-medium text-gray-700 hover:text-gray-800"
										>
											{page.name}
										</a>
									))}
								</div>
							</PopoverGroup>

							<div className="ml-auto flex items-center">
								<div className="hidden lg:flex lg:flex-1 lg:items-center lg:justify-end lg:space-x-6">
									{auth.user?.firstName ? (
										<div>
											<Avatar
												className="text-white"
												onClick={handleUserClick}
												aria-controls={
													open
														? "basic-menu"
														: undefined
												}
												aria-haspopup="true"
												aria-expanded={
													open ? "true" : undefined
												}
												sx={{
													bgcolor: deepPurple[500],
													color: "white",
													cursor: "pointer"
												}}
											>
												{auth.user?.firstName[0].toUpperCase()}
											</Avatar>
											<Menu
												id="basic-menu"
												anchorEl={anchorEl}
												open={openUserMenu}
												onClose={handleCloseUserMenu}
												MenuListProps={{
													"aria-labelledby":
														"basic-button"
												}}
											>
												<MenuItem
													onClick={() =>
														navigate(
															"/account/order"
														)
													}
												>
													{
														//auth.user?.role === "ROLE_ADMIN"
														false
															? "Admin Dashboard"
															: "My Orders"
													}
												</MenuItem>
												<MenuItem
													onClick={handleLogout}
												>
													Logout
												</MenuItem>
											</Menu>
										</div>
									) : (
										<Button
											onClick={handleOpen}
											className="text-sm font-medium text-gray-700 hover:text-gray-800"
										>
											Log in
										</Button>
									)}
								</div>

								{/* Search */}
								<div className="flex items-center lg:ml-6">
									<p>
										<span className="sr-only">Search</span>

										<MagnifyingGlassIcon
											className="h-6 w-6"
											aria-hidden="true"
										/>
									</p>
								</div>

								{/* Cart */}
								<div className="ml-4 flow-root lg:ml-6">
									<Button className="group -m-2 flex items-center p-2">
										<ShoppingBagIcon
											className="h-6 w-6 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
											aria-hidden="true"
										/>
										<span className="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800"></span>
										<span className="sr-only">
											items in cart, view bag
										</span>
									</Button>
								</div>
							</div>
						</div>
					</div>
				</nav>
			</header>

			<AuthModal handleClose={handleClose} open={openAuthModal} />
		</div>
	);
};

export default Navigation;