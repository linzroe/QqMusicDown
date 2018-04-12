<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="QQMusicDown, Music, QQMusic, QQ音乐, QQ音乐解析, 音乐解析, 收费音乐,无损音乐,ape,flac, 免费音乐, 免费下载, 双木" />
    <meta name="description" content="QQ音乐解析(QQMusicDonw),可解析收费音乐/无损音乐,直接下载APE/Flac无损音质音乐|双木's Blog" />
    <title>QQ音乐解析|免费解析收费音乐|双木's Blog</title>
    <link rel="icon" type="image/ico" href="https://09l.me/favicon.ico">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/qqmusic.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.flat.css" rel="stylesheet">
</head>
<body>
<header class="am-topbar am-topbar-inverse">
    <div class="am-container">
        <p class="am-topbar-brand"><a href="https://09l.me" class="am-topbar-logo">双木</a></p>
        <a class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></a>
        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="/music">QQMusic解析</a></li>
            </ul>
        </div>
    </div>
</header>


<div class="wrapper">
    <div class="blank_header"></div>
    <div class="qr_login">
        <div id="qr_area" class="qr_area">
            <img id="qrcode" class="qrcode" title="点击可刷新" onclick="qrRefresh()" />
            <span id="login_success" class="login_success" onclick="qrRefresh()">
					<i class="fa fa-check" aria-hidden="true" title="登录成功，点击可刷新"></i>
				</span> <span id="qr_invalid" class="qr_invalid" onclick="qrRefresh()">
					<i class="fa fa-refresh" aria-hidden="true" title="二维码失效，请点击刷新"></i>
				</span>
        </div>
        <p id="tips" class="tips">手机 QQ 扫描二维码</p>
    </div>
    <form id="the_form" action="" method="">
        <div class="kwbox">
            <div class="input-group">
                <span class="input-group-addon"> ID：</span> <input id="id"
                                                                   type="text" class="form-control" placeholder="0005jrRs05ELQv">
                <span class="input-group-addon">
						<button value="Submit" type="submit" class="btn btn-primary">解析</button>
					</span>
            </div>
            <h4>
                https://y.qq.com/n/yqq/song/<span class="label label-default">0005jrRs05ELQv</span>.html
            </h4>
            <h4>
                音乐搜索：<a
                    href="https://y.qq.com/portal/search.html#page=1&searchid=1&remoteplace=txt.yqq.top&t=song&w=%E6%9E%97%E4%BF%8A%E6%9D%B0%E6%88%91%E7%BB%A7%E7%BB%AD"
                    target="_blank">QQ音乐</a>
            </h4>
            <h4>
                <span class="label label-default">Beta v0.0.1</span>
            </h4>
        </div>
    </form>

    <div class="alacarte" id="alacarte">
        <a id="mp3" target="_blank">
            <button type="button" class="btn btn-primary">普通品质</button>
        </a> <a id="hd" target="_blank">
        <button type="button" class="btn btn-primary">HD高品质</button>
    </a> <a id="ape" target="_blank">
        <button type="button" class="btn btn-primary">Ape无损</button>
    </a> <a id="flac" target="_blank">
        <button type="button" class="btn btn-primary">Flac无损</button>
    </a>
    </div>
    <div class="alacarte">
        <span class="label label-default"> 手机上使用方法</span>
        <p>因为要扫描登录，所以在浏览器里面打开我们的网站</p>
        <p>然后长按二维码保存或者直接截图，然后打开手机QQ</p>
        <p>发送刚刚这张图，然后长按扫描登录即可</p>
        <p>这个时候再返回浏览器，就已经登录成功了。</p>
    </div>
</div>
<div class="footer">
    <i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;
    本站不会存储任何关于信息,可放心使用. &nbsp; &copy; 2018 <a href="https://09l.me/"
                                              target="_blank">双木's Blog</a>
</div>
<script src="js/qqmusic.js"></script>


</body>
</html>