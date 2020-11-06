<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>crud-damo</title>
    <style>
        table, tr, td {
            border: 1px;
        }
    </style>
</head>
<form action="/crud" method="post">
    用户名：<input type="text" name="username" placeholder="用户名"/>
    <br/>
    密 码： <input type="password" name="password" placeholder="密 码"/>
    <br/>
    <input type="submit" value="提交"/>
    <input type="reset" value="重置"/>
</form>
<br/>
<input type="text" placeholder="搜索框" id="search"/>
<br/>
<input type="button" onclick="search()" value="查询"/>
<input type="button" onclick="mdelete()" value="根据查询删除"/>
<span>${msg!}</span>
<hr/>
<table id="show">
    <tr>
        <td>
            用户名
        <td>
        <td>
            昵称
        <td>
        <td>
            性别
        <td>
        <td>
            创建账号日期
        <td>
    </tr>
    <#list users! as user>
        <tr>
            <td>
                ${user.username!}
            <td>
            <td>
                ${user.nickname!}
            <td>
            <td>
                ${user.sex!}
            <td>
            <td>
                ${user.createDate!}
            <td>
        </tr>
    </#list>
</table>

</body>
<script src="jQuery-v2.0.2.js"></script>
<script src="user.js"></script>
</html>

