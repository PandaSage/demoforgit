<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>template</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/resources/css/common.css">
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="/resources/css/jquery/jquery-ui.css">
	<link rel="stylesheet" href="/resources/css/jquery/ui.jqgrid.css">
	<link rel="stylesheet" href="/resources/css/content.css">
	<!--[if lt IE 9]>
	<script src="/resources/js/html5shiv.min.js"></script>
	<script src="/resources/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="wrapper">
		<header class="header">
			<!-- Logo -->
			<a href="#" class="logo">
				<span class="logo-mini"><b>A</b></span>
				<span class="logo-lg"><b>Admin</b></span>
			</a>
			<!-- Header topNav -->
			<nav class="topNav clearfix">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle">
					<span class="sr-only">Toggle navigation</span>
				</a>
				<!-- topNav nav_menu -->
				<div class="nav_menu">
					<ul class="nav clearfix">
						<li class="dropdown msg_menu">
							<a href="#" class="dropdown-toggle">
								<i class="fa fa-envelope-o"></i>
								<span class="label label-success">4</span>
							</a>
						</li>
						<!-- Notifications -->
						<li class="dropdown noti_menu">
							<a href="#" class="dropdown-toggle">
								<i class="fa fa-bell-o"></i>
								<span class="label label-warning">10</span>
							</a>
						</li>
						<!-- Tasks -->
						<li class="dropdown tasks-menu">
							<a href="#" class="dropdown-toggle">
								<i class="fa fa-flag-o"></i>
								<span class="label label-danger">9</span>
							</a>
						</li>
						<!-- User Account -->
						<li class="dropdown user-menu">
							<a href="#" class="dropdown-toggle">
								<img src="/resources/images/temp/temp_user_img.jpg" class="user-image" alt="User Image">
								<span class="user_name">Alexander Pierce</span>
							</a>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<li>
							<a href="#" class="btn_Rsidebar"><i class="fa fa-gears"></i></a>
						</li>
					</ul>
				</div>
			</nav>
		</header>

		<aside class="main-sidebar">
			<section class="sidebar">
				<!-- user-panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="/resources/images/temp/temp_user_img.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>Alexander Pierce</p>
						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
				<!-- //.user-panel -->

				<!-- search form -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button type="submit" name="search" id="search-btn" class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- //.search form -->

				<!-- sidebar menu -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header">Templates</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-table"></i>
							<span>Tables</span>
							<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li><a href="#"><i class="fa fa-circle-o"></i> Simple tables</a></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> Data tables</a></li>
						</ul>
					</li>
					<li class="treeview active menu-open">
						<a href="#">
							<i class="fa fa-pie-chart"></i>
							<span>Charts</span>
							<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li class="active"><a href="#"><i class="fa fa-circle-o"></i> Charts</a></li>
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-laptop"></i>
							<span>UI Elements</span>
							<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li><a href="#"><i class="fa fa-circle-o"></i> Buttons</a></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> Paging</a></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> Sliders</a></li>
						</ul>
					</li>
					<li class="treeview">
						<a href="#">
							<i class="fa fa-share"></i>
							<span>Popup</span>
							<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<ul class="treeview-menu">
							<li><a href="#"><i class="fa fa-circle-o"></i> layerPopup</a></li>
							<li><a href="#"><i class="fa fa-circle-o"></i> modalPopup</a></li>
						</ul>
					</li>
				</ul>
			</section>
			<!-- //.sidebar -->
		</aside>