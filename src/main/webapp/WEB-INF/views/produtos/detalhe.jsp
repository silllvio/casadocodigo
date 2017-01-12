<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<c:url value="/" var="contextPath" />
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="icon"
	href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979"
	type="image/ico" />
<link href="https://plus.googlecom/108540024862647200608"
	rel="publisher" />
<title>Livros de Java, SOA, Android, iPhone, Ruby on Rails e
	muito mais - Casa do Código</title>
<link href="${contextPath}resources/css/cssbase-min.css"
	rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' />
<link href="${contextPath}resources/css/fonts.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="${contextPath}resources/css/fontello-ie7.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${contextPath}resources/css/fontello-embedded.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${contextPath}resources/css/fontello.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="${contextPath}resources/css/style.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="${contextPath}resources/css/layout-colors.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${contextPath}resources/css/responsive-style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${contextPath}resources/css/guia-do-programador-style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${contextPath}resources/css/produtos.css" rel="stylesheet"
	type="text/css" media="all" />
<link rel="canonical" href="http://www.casadocodigo.com.br/" />
</head>
<body class="produto">

<%@include file="/WEB-INF/views/cabecalho.jsp" %>

	<h1 class="product-title" itemprop="name">${produto.titulo}</h1>
	<p class="product-author">
		<span class="product-author-link"> </span>
	</p>

	<p itemprop="description" class="book-description">${produto.descricao}</p>

	<article id="${produto.id}">
		<header id="product-highlight" class="clearfix">
			<div id="product-overview" class="container">
				<img width="280px" height="395px"
					src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145"
					class="product-featured-image" />
				<title>${produto.titulo}-CasadoCódigo</title>
				<p class="product-author">
					<span class="product-author-link"> </span>
				</p>

				<p class="book-description">${produto.descricao}</p>
			</div>
		</header>

		<section class="buy-options clearfix">
			<form:form servletRelativeAction="/carrinho/add"  method="post"
				cssClass="container">
				<ul id="variants" class="clearfix">
					<input type="hidden" name="produtoId" value="${produto.id}" />
					<c:forEach items="${produto.precos}" var="preco">
						<li class="buy-option"><input type="radio" name="tipo"
							class="variant-radio" id="tipo" value="${preco.tipo}"
							checked="checked" /> <label class="variant-label">
								${preco.tipo} </label> <small class="compare-at-price">R$ 39,90</small>
							<p class="variant-price">${preco.valor}</p></li>
					</c:forEach>
				</ul>
				<button type="submit" class="submit-image icon-basket-alt"
					alt="Compre Agora" title="Compre Agora${produto.titulo}"></button>
			</form:form>

		</section>

		<div class="container">
			<section class="summary">
				<ul>
					<li><h3>
							E muito mais... <a href='/pages/sumario-java8'>veja o sumário</a>.
						</h3></li>
				</ul>
			</section>

			<section class="data product-detail">
				<h2 class="section-title">Dados do livro:</h2>
				<p>
					Número de páginas: <span>${produto.paginas}</span>
				</p>
				<p></p>
				<p>Data de publicação: ${produto.dataLancamento}</p>
				<p>
					Encontrou um erro? <a href='/submissao-errata' target='_blank'>Submeta
						uma errata</a>
				</p>
			</section>
		</div>

	</article>

	
<%@include file="/WEB-INF/views/rodaPe.jsp" %>

</body>
</html>