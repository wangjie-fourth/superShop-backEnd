<html>
<#include "../commons/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../commons/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <form role="form" method="post" action="/supershop/seller/category/add">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="name" type="text" class="form-control" value=""/>
                        </div>

                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>