<%--
  Created by IntelliJ IDEA.
  User: Dobe
  Date: 2019/4/21
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="panel panel-default" style="position:relative;top: 80px;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            全部银行卡
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover" >
            <thead>
            <tr>
                <th>银行卡</th>
                <th>卡号</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cards}" var="card">
                <tr>
                    <td><c:out value="${card.bankName}"></c:out></td>
                    <td><c:out value="${card.cardNo}"></c:out></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
