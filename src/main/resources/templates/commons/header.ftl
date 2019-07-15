<head>
    <meta charset="utf-8">
    <title>卖家后端管理系统</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/supershop/css/style.css">
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        // 点击显示下拉列表框
        function show(obj){//
            var $object = $(obj);
            var Cts = $object.attr("class");
            // 是否含有 is-closed
            if(Cts.indexOf("is-closed") >= 0 ) {
                $object.removeClass('is-closed');
                $object.addClass('open');
            }
            // 是否含有 open
            if(Cts.indexOf("open") >= 0 ) {
                $object.removeClass('open');
                $object.addClass('is-closed');
            }
        };
    </script>
</head>