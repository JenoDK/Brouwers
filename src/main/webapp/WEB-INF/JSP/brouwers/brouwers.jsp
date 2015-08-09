<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head><v:head title='Brouwers' /></head>
<body>
	<v:menu />
	<h1>Brouwers</h1>
	<c:forEach items='${page.content}' var='brouwer'>
		<h2>${brouwer.naam}</h2>
		<p>${brouwer.adres.straat}
			${brouwer.adres.huisNr}<br> ${brouwer.adres.postcode}
			${brouwer.adres.gemeente}
		</p>
	</c:forEach>
	<p class='pagineren'><c:forEach var="pageNr" begin="1"
			end="${page.totalPages}">
			<c:choose>
				<c:when test="${pageNr-1 == page.number}">
					${pageNr}
				</c:when>
				<c:otherwise>
					<c:url value="" var="url">
						<c:param name="page" value="${pageNr-1}" />
						<c:param name="sort" value="${param.sort}" />
					</c:url>
					<a href="${url}">${pageNr}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach></p>
</body>
</html>