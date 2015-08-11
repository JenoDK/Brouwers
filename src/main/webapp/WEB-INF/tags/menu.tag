<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		<li><a href="#">brouwers</a>
			<ul>
				<li><a href="<c:url value='/brouwers'/>">Lijst</a></li>
				<li><a href="<c:url value='/brouwers/beginnaam'/>">Beginnaam</a></li>
				<security:authorize url='/brouwers/toevoegen'>
					<li><a href="<c:url value='/brouwers/toevoegen'/>">Toevoegen</a></li>
				</security:authorize>
				<li><a href="<c:url value='/brouwers/opalfabet'/>">Op
						alfabet</a></li>
				<%-- 				<li><a href="<c:url value='/brouwers/perid'/>">Per id</a></li> --%>
				<%-- 				<li><a href="<c:url value='/brouwers/afschrijven'/>">Afschrijven</a></li> --%>
			</ul></li>
		<c:url value='' var='nederlandsURL'>
			<c:param name='locale' value='nl_be' />
		</c:url>
		<li><a href='${nederlandsURL}'>Nederlands</a></li>
		<c:url value='' var='engelsURL'>
			<c:param name='locale' value='en_us' />
		</c:url>
		<li><a href='${engelsURL}'>Engels</a></li>
		<!-- 		<li><a href="#">Werknemers</a> -->
		<!-- 			<ul> -->
		<%-- 				<li><a href="<c:url value='/werknemers'/>">Lijst</a></li> --%>
		<!-- 			</ul></li> -->
		<!-- 		<li><a href="#">Offertes</a> -->
		<!-- 			<ul> -->
		<%-- 				<li><a href="<c:url value='/offertes/aanvraag'/>">Aanvraag</a></li> --%>
		<!-- 			</ul></li> -->
		<%-- 		<c:url value='' var='nederlandsURL'> --%>
		<%-- 			<c:param name='locale' value='nl_be' /> --%>
		<%-- 		</c:url> --%>
		<%-- 		<li><a href='${nederlandsURL}'>Nederlands</a></li> --%>
		<%-- 		<c:url value='' var='engelsURL'> --%>
		<%-- 			<c:param name='locale' value='en_us' /> --%>
		<%-- 		</c:url> --%>
		<%-- 		<li><a href='${engelsURL}'>Engels</a></li> --%>
		<security:authorize access='isAnonymous()'>
			<li><a href="<c:url value='/login'/>">Aanmelden</a></li>
		</security:authorize>
		<security:authorize access='isAuthenticated ()'>
			<li>
				<form method='post' action='<c:url value="/logout"/>'
					id='logoutform'>
					<input type='submit'
						value='<security:authentication property="name"/> afmelden'
						id='logoutbutton'>
					<security:csrfInput />
				</form>
			</li>
		</security:authorize>
	</ul>
</nav>
