var _auth = false;

function changeStatus(status) {
    switch (status) {
        case 0:
            $('#tips').text('手机 QQ 扫描二维码');
            break;
        case 1:
            $('#tips').text('二维码认证中...');
            break;
        case 2:
            $('#login_success').css('display', 'inline-block');
            $('#tips').text('登录成功，点击可刷新二维码');
            _auth = true;
            break;
        case 3:
            $('#qr_invalid').css('display', 'inline-block');
            $('#tips').text('二维码失效，请点击刷新');
            _auth = false;
            break;
        default:
            console.log(status);
    }
}

function qrLoginQuery() {
    function trigger() {
        var url = 'qqmusic/login?t=' + (new Date().getTime());
        $.ajax({
        url : url,
        cache : false,
        dataType : "json",
        success : function(obj) {
            var status = obj;
            changeStatus(status);
            if ([ 2, 3 ].includes(status)) {
                clearInterval(window.queryTimmer);
            }
        }
    });
}
    window.queryTimmer = setInterval(trigger, 2000);
}

function qrRefresh() {
    _auth = false;
    clearInterval(window.queryTimmer);
    $('#qrcode').attr('src', '');
    var src = '';
    $.ajax({
        type : "GET",
        url : "qqmusic/qrcode",
        contentType : "application/json",
        success : function(r) {
            var reg = new RegExp('"', "g");
            r = r.replace(reg, "");
            $('#qrcode').attr('src', r);
        }
    });
    $('#login_success').css('display', 'none');
    $('#qr_invalid').css('display', 'none');
    $('#alacarte').css('display', 'none');
    $('#tips').text('手机 QQ 扫描二维码');
    qrLoginQuery();
}

(function() {
    qrRefresh();
    $("#the_form").submit(
        function(e) {
            e.preventDefault();
            if (!_auth) {
                alert('请先扫码授权登录');
            } else {
                $(".kwbox p").css("background","url('https://i.loli.net/2018/04/11/5accf42b73396.gif') center center no-repeat");
                $("input[type=submit]").prop("disabled",	true);
                $('#alacarte').css('display', '');
                document.getElementById("alacarte").style.display="";//显
                var id=$(" #id ").val()
                $.ajax({
                    type : "POST",
                    url : "qqmusic/getmusic/"+id,
                    success : function(r) {
                        var obj = JSON.parse(r);
                        $('#mp3').attr('href', obj.mp3);
                        $('#hd').attr('href', obj.mp3pro);
                        $('#ape').attr('href', obj.ape);
                        $('#flac').attr('href', obj.flac);
                    },
                    error: function() {
                        $(".kwbox p").css("background", "");
                        $("input[type=submit]").prop("disabled", false);
                    }
                });
            }
            return false;
        });
})();