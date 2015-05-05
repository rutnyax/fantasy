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
				<h2>The future is here</h2>
				<p>
					With a better way to play fantasy football.
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
							<h2>Control</h2>
							<p>Complete control</p>
						</header>
					</div>
					<div class="4u 12u$(medium)">
						<p>
							You decide... <br>how your league will be structured.<br>what
							your league will be called.<br>how many teams will join your
							league.<br>who joins your league.
						</p>
					</div>
					<div class="4u$ 12u$(medium)">
						<p><br>The power is yours. <br>Wield it responsibly.</p>
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
				<h2>No player duplication</h2>
				<p>There is only one <em>Leo</em></p>
			</header>
			<p>Players are not duplicated in real life. So why should you and
				your friend both have Messi on your respective teams in the same
				league?</p>
			<p>By incorporating a player draft system with the traditional
				transfer market we all love, you get the realistic experience of
				hunting for players to sign up to your team and putting your
				inconsistent players on the market.</p>
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
				<h2>New ways to score</h2>
				<p>Every fantasy fixture is almost real</p>
			</header>
			<p>We give you a completely new experience in scoring points.
				Each fantasy fixture is modeled after a real world fixture where
				each player's efforts in creating or preventing goals drives the
				team towards victory.</p>
			<p>Each player's score contributes to your team's final tally
				which is a round score just like each real life goal scored. The
				team that outscores its opponent wins. Sound familiar?</p>
		</div>
		<a href="#four" class="goto-next scrolly">Next</a>
	</section>

	<!-- Four -->
	<section id="four" class="wrapper style1 special fade-up">
		<div class="container">
			<header class="major">
				<h2>Experience the revolution in fantasy football</h2>
				<p>Once you start you'll wonder how you ever played any other way</p>
			</header>
			<div class="box alt">
				<div class="row uniform">
					<section class="4u 6u(medium) 12u$(xsmall)">
						<span class="icon alt major fa-area-chart"></span>
						<h3>Live trading</h3>
						<p>Bid on available players and beat out all contenders for
							that player you believe holds the key to the season's success.</p>
					</section>
					<section class="4u 6u$(medium) 12u$(xsmall)">
						<span class="icon alt major fa-bullhorn"></span>
						<h3>Bragging rights</h3>
						<p>Battle it out with your friends and show them that you have
							the best managerial talent in the group.</p>
					</section>
					<section class="4u$ 6u$(medium) 12u$(xsmall)">
						<span class="icon alt major fa-lock"></span>
						<h3>Private leagues</h3>
						<p>Leave behind the days of being just another username on
							global leaderbaords. We let you create private leagues for you
							and your friends.</p>
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

</body>