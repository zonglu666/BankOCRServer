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
    <title>Title</title>
</head>
<body>
<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
    <form action="bank_add_do.html" method="post" id="addbank" >
        <div class="form-group">
            <label for="name">银行</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入银行">
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
