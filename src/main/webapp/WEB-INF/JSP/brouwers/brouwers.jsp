<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head><v:head title='Brouwers' /></head>
<body>
	<v:menu />
	<h1>Brouwers</h1>
	<c:forEach items='${brouwers}' var='brouwer'>
		<h2>${brouwer.naam}</h2>
		<p>${brouwer.adres.straat}
			${brouwer.adres.huisNr}<br> ${brouwer.adres.postcode}
			${brouwer.adres.gemeente}
		</p>
	</c:forEach>
</body>
</html>