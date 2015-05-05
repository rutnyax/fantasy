<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title" /></title>

<link rel="icon" href='<spring:url value="/favicon.ico" />'>
<link rel="stylesheet" href='<spring:url value="/resources/landing/css/skel.css" />'>
<link rel="stylesheet" href='<spring:url value="/resources/landing/css/style.css" />'>

<script type="text/javascript">
	var override = {
		global : '<spring:url value="/resources/landing/css/style.css" />',
		xlarge : '<spring:url value="/resources/landing/css/style-xlarge.css" />',
		large : '<spring:url value="/resources/landing/css/style-large.css" />',
		medium : '<spring:url value="/resources/landing/css/style-medium.css" />',
		small : '<spring:url value="/resources/landing/css/style-small.css" />',
		xsmall : '<spring:url value="/resources/landing/css/style-xsmall.css" />'
	}
</script>

<!--[if lte IE 8]>
	<script src='<spring:url value="/resources/landing/css/ie/html5shiv.js" />'></script>
<![endif]-->
<script src='<spring:url value="/resources/landing/js/jquery.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/jquery.scrolly.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/jquery.dropotron.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/jquery.scrollex.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/skel.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/skel-layers.min.js" />'></script>
<script src='<spring:url value="/resources/landing/js/init.js" />'></script>
<!--[if lte IE 9]>
	<link rel="stylesheet" href='<spring:url value="/resources/landing/css/ie/v9.css" />' />
<![endif]-->
<!--[if lte IE 8]>
	<link rel="stylesheet" href='<spring:url value="/resources/landing/css/ie/v8.css" />' />
<![endif]-->
</head>
<body class="landing">

	<!-- Header -->
	<header id="header">
		<h1 id="logo">
			<a href='<spring:url value="/" />'>Fantasy</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href='<spring:url value="/signin.html" />'>Sign In</a></li>
				<li><a href='<spring:url value="/signup.html" />'
					class="button special">Sign Up</a></li>
			</ul>
		</nav>
	</header>

	<!-- Banner -->
	<section id="banner">
		<div class="content">
			<header>
				<h2>The future has landed</h2>
				<p>
					And there are no hoverboards or flying cars.<br /> Just apps. Lots
					of mother flipping apps.
				</p>
			</header>
			<span class="image"><img
				src='<spring:url value="/resources/landing/images/eden-hazard.png" />'
				alt="" /></span>
		</div>
		<a href="#one" class="goto-next scrolly">Next</a>
	</section>

	<!-- One -->
	<section id="one" class="spotlight style1 bottom">
		<span class="image fit main"><img
			src='<spring:url value="/resources/landing/images/gareth-bale.png" />'
			alt="" /></span>
		<div class="content">
			<div class="container">
				<div class="row">
					<div class="4u 12u$(medium)">
						<header>
							<h2>Odio faucibus ipsum integer consequat</h2>
							<p>Nascetur eu nibh vestibulum amet gravida nascetur praesent</p>
						</header>
					</div>
					<div class="4u 12u$(medium)">
						<p>Feugiat accumsan lorem eu ac lorem amet sed accumsan donec.
							Blandit orci porttitor semper. Arcu phasellus tortor enim mi nisi
							praesent dolor adipiscing. Integer mi sed nascetur cep aliquet
							augue varius tempus lobortis porttitor accumsan consequat
							adipiscing lorem dolor.</p>
					</div>
					<div class="4u$ 12u$(medium)">
						<p>Morbi enim nascetur et placerat lorem sed iaculis neque
							ante adipiscing adipiscing metus massa. Blandit orci porttitor
							semper. Arcu phasellus tortor enim mi mi nisi praesent
							adipiscing. Integer mi sed nascetur cep aliquet augue varius
							tempus. Feugiat lorem ipsum dolor nullam.</p>
					</div>
				</div>
			</div>
		</div>
		<a href="#two" class="goto-next scrolly">Next</a>
	</section>

	<!-- Two -->
	<section id="two" class="spotlight style2 right">
		<span class="image fit main"><img
			src='<spring:url value="/resources/landing/images/lionel-messi.png" />'
			alt="" /></span>
		<div class="content">
			<header>
				<h2>Interdum amet non magna accumsan</h2>
				<p>Nunc commodo accumsan eget id nisi eu col volutpat magna</p>
			</header>
			<p>Feugiat accumsan lorem eu ac lorem amet ac arcu phasellus
				tortor enim mi mi nisi praesent adipiscing. Integer mi sed nascetur
				cep aliquet augue varius tempus lobortis porttitor lorem et accumsan
				consequat adipiscing lorem.</p>
			<ul class="actions">
				<li><a href="#" class="button">Learn More</a></li>
			</ul>
		</div>
		<a href="#three" class="goto-next scrolly">Next</a>
	</section>

	<!-- Three -->
	<section id="three" class="spotlight style3 left">
		<span class="image fit main bottom"><img
			src='<spring:url value="/resources/landing/images/cristiano-ronaldo.png" />'
			alt="" /></span>
		<div class="content">
			<header>
				<h2>Interdum felis blandit praesent sed augue</h2>
				<p>Accumsan integer ultricies aliquam vel massa sapien phasellus</p>
			</header>
			<p>Feugiat accumsan lorem eu ac lorem amet ac arcu phasellus
				tortor enim mi mi nisi praesent adipiscing. Integer mi sed nascetur
				cep aliquet augue varius tempus lobortis porttitor lorem et accumsan
				consequat adipiscing lorem.</p>
			<ul class="actions">
				<li><a href="#" class="button">Learn More</a></li>
			</ul>
		</div>
		<a href="#four" class="goto-next scrolly">Next</a>
	</section>

	<!-- Four -->
	<section id="four" class="wrapper style1 special fade-up">
		<div class="container">
			<header class="major">
				<h2>Accumsan sed tempus adipiscing blandit</h2>
				<p>Iaculis ac volutpat vis non enim gravida nisi faucibus
					posuere arcu consequat</p>
			</header>
			<div class="box alt">
				<div class="row uniform">
					<section class="4u 6u(medium) 12u$(xsmall)">
						<span class="icon alt major fa-area-chart"></span>
						<h3>Ipsum sed commodo</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
					<section class="4u 6u$(medium) 12u$(xsmall)">
						<span class="icon alt major fa-comment"></span>
						<h3>Eleifend lorem ornare</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
					<section class="4u$ 6u(medium) 12u$(xsmall)">
						<span class="icon alt major fa-flask"></span>
						<h3>Cubilia cep lobortis</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
					<section class="4u 6u$(medium) 12u$(xsmall)">
						<span class="icon alt major fa-paper-plane"></span>
						<h3>Non semper interdum</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
					<section class="4u 6u(medium) 12u$(xsmall)">
						<span class="icon alt major fa-file"></span>
						<h3>Odio laoreet accumsan</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
					<section class="4u$ 6u$(medium) 12u$(xsmall)">
						<span class="icon alt major fa-lock"></span>
						<h3>Massa arcu accumsan</h3>
						<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec.
							Blandit orci porttitor.</p>
					</section>
				</div>
			</div>
			<footer class="major">
				<ul class="actions">
					<li><a href='<spring:url value="/signup.html" />' class="button">Sign up now</a></li>
				</ul>
			</footer>
		</div>
	</section>

	<!-- Five -->
	<section id="five" class="wrapper style2 special fade">
		<div class="container">
			<header>
				<h2>Magna faucibus lorem diam</h2>
				<p>Ante metus praesent faucibus ante integer id accumsan
					eleifend</p>
			</header>
			<form method="post" action="#" class="container 50%">
				<div class="row uniform 50%">
					<div class="8u 12u$(xsmall)">
						<input type="email" name="email" id="email"
							placeholder="Your Email Address" />
					</div>
					<div class="4u$ 12u$(xsmall)">
						<input type="submit" value="Get Started" class="fit special" />
					</div>
				</div>
			</form>
		</div>
	</section>

</body>