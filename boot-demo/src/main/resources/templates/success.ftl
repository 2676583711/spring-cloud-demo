<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./css/user.css"/>
</head>
<body>

<div class="show">
    <h2>登录成功，此处是主页面</h2>
    <br/>
    <form method="post" action="/login">
        <input name="username" type="text" placeholder="账号" value="${username!}"/><br/>
        <input name="password" type="password" placeholder="密码" value="${password!}"/><br/>
        <input type="submit" value="登录"/>
        <input type="button" value="注册"/>
        <input type="button" value="退出登录" onclick="window.location.href='/'"/>
    </form>
</div>

</body>
</html>
