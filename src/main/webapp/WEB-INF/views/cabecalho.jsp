
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<header id="layout-header">
	<div class="clearfix container">
		<a href="${s:mvcUrl('HC#index').build() }" id="logo"> </a>
		<div id="header-content">
			<nav id="main-nav">
				<ul class="clearfix">
					<security:authorize access="isAuthenticated()">
						<li><a href="${s:mvcUrl('PC#listar').build() }"
							rel="nofollow"><fmt:message key="menu.produto" /></a></li>
						<li><a href="${s:mvcUrl('PC#form').build() }" rel="nofollow"><fmt:message
									key="menu.cadastrar" /></a></li>
					</security:authorize>
					<li><a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow">
							<%-- 							<fmt:message key="menu.carrinho"> --%> <%-- 								<fmt:param value="${carrinhoCompras.quantidade}" /> --%>
							<%-- 							</fmt:message> --%> <s:message code="menu.carrinho"
								arguments="${carrinhoCompras.quantidade}" />

					</a></li>
					<li><a href="?locale=pt" rel="nofollow">
							<fmt:message key="menu.pt"   />
					   </a>
					<li><a href="?locale=en" rel="nofollow">
							<fmt:message key="menu.en"   />
					   </a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>

<nav class="categories-nav">
	<ul class="container">
		<li class="category"><a href="${s:mvcUrl('HC#index').build() }">
				<fmt:message key="navegacao.categoria.home" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.agile" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.front_end" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.games" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.java" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.mobile" />
		</a></li>
		<li class="category"><a href="#"> <fmt:message
					key="navegacao.categoria.outros" />
		</a></li>
	</ul>
</nav>
