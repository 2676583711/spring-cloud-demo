<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./css/user.css"/>
</head>
<body>

<div class="show">
    <h2>Register</h2>
    <br/>
    <form method="post" action="/register">
        <input name="username" type="text" placeholder="账号"/><br/>
        <input name="password" type="password" placeholder="密码"/><br/>
        <input type="submit" value="提交"/>
        <input type="reset" value="重置"/>
        <input type="button" value="去登陆" onclick="toLogin()"/>
    </form>
    <span>${msg!}</span>
</div>

</body>
<script>
    function toLogin() {
        window.location.href = '/';
    }
</script>
</html>
