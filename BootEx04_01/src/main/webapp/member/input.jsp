<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>input</h2>
	<hr/>
	<form action="do_selectOne.do" method="post">
		이름:<input type="text"  name="name" size="15"><br/>
		성별:<input type="text"  name="sex" size="15"><br/>
		전화:<input type="text"  name="tel" size="15"><br/>
		나이:<input type="text"  name="age" size="15"><br/>
		<input type="submit" value="전송" />
	</form>
	<br/>
	<hr/>
	${name }
</body>
</html>