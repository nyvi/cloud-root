<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cloud统一认证</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container form-margin-top">
    <form class="form-login" action="/auth/authentication/login" method="post">
        <h2 class="form-login-heading" align="center">统一认证系统</h2>
        <div class="form-group">
            <input name="username" class="form-control form-margin-top" placeholder="账号" autocomplete="off" required
                   autofocus>
        </div>
        <div class="form-group">
            <input type="password" name="pwd" class="form-control" placeholder="密码" required>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
    </form>
</div>
</body>
</html>