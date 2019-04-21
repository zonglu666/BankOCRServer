<%--
  Created by IntelliJ IDEA.
  User: Dobe
  Date: 2019/4/21
  Time: 下午3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑 ${detail.name}</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
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
<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
    <form action="bank_add_do.html" method="post" id="addbank" >
        <div class="form-group">
            <label for="name">银行名称</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入银行名称">
        </div>


        <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
        <script>
            function mySubmit(flag){
                return flag;
            }
            $("#addbank").submit(function () {
                if($("#name").val()==''){
                    alert("请填入完整银行信息！");
                    return mySubmit(false);
                }
            })
        </script>
    </form>

</div>
</body>
</html>
