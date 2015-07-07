<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head><v:head title='Brouwers op naam' /></head>
<body>
	<v:menu />
	<h1>Brouwers op naam</h1>
	<c:url value='/brouwers' var='url' />
	<form:form action="${url}" commandName='beginnaam' method='get'>
		<form:label path='beginnaam'>Begin v/d naam:<form:errors
				path='beginnaam' />
		</form:label>
		<form:input path='beginnaam' autofocus='autofocus' />

		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout' />
	</form:form>
	<c:forEach items='${brouwers}' var='brouwer'>
		<h2>${brouwer.naam}</h2>
		<p>${brouwer.adres.straat}${brouwer.adres.huisNr}<br>
			${brouwer.adres.postcode} ${brouwer.adres.gemeente}
		</p>
	</c:forEach>
</body>
</html>