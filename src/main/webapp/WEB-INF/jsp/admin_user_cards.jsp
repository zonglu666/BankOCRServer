<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部银行卡</title>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
        .table{
            margin-bottom: 0px;
        }
        .td-text {
            font-size: larger;
            color: #5e5e5e;
        }
        .img-box{
            width: 280px;
            height: 150px;
            overflow: hidden;
        }
        .img{
            display: block;
            max-width: 280px;
            max-height: 150px;
            transition: all 1s;
        }
        .img:hover{
            transform: scale(1.4); //放大 倍数随意
        }
    </style>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
</head>
<body>
    <nav  style="position:fixed;z-index: 999;width: 100%;background-color: #fff" class="navbar navbar-default" role="navigation" >
        <div class="container-fluid">
            <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
                <a class="navbar-brand" href="admin_main.html">卡包管理系统</a>
            </div>
            <div class="collapse navbar-collapse" >
                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            银行管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="allbanks.html">全部银行</a></li>
                            <li class="divider"></li>
                            <li><a href="bank_add.html">增加银行</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            用户管理
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="allusers.html">全部用户</a></li>
                        </ul>
                    </li>
                    <li >
                        <a href="admin_repasswd.html" >
                            密码修改
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
                    <li><a href="logout.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
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
                <th>开户行</th>
                <th>卡号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cards}" var="card">
                <tr>
                    <td>
                        <div class="img-box">
                            <img class="img card-image" src="<c:out value="${card.cardImg}"></c:out>">
                        </div>
                    </td>
                    <td class="td-text" style="vertical-align: middle;"><c:out value="${card.bankName}"></c:out></td>
                    <td class="td-text" style="vertical-align: middle;"><c:out value="${card.cardNo}"></c:out></td>
                    <td style="vertical-align: middle;"><a href="deletecard.html?cardId=<c:out value="${card.cardId}"></c:out>&userId=<c:out value="${card.userId}"></c:out>"><button type="button" class="btn btn-danger btn-xs">删除银行卡</button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
