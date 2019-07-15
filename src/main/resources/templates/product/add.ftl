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

                    <form role="form" method="post" enctype="multipart/form-data" action="/supershop/seller/product/add">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="name" type="text" class="form-control" value=""/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="price" type="text" class="form-control" value=""/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="stock" type="number" class="form-control" value=""/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="" alt="">
                            <input name="icon" type="file" class="form-control" value=""/>
                        </div>
                        <div class="form-group">
                            <label>状态</label>
                            <input name="status" type="text" class="form-control" value=""/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <input name="type" type="text" class="form-control" value=""/>
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