<html>
<#include "../commons/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include  "../commons/nav.ftl">



<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>购买数量</th>
                            <th>商品图片</th>
                            <th>订单创建时间</th>
                            <th>订单更新时间</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderDeatilList as orderDeatil>
                        <tr>
                            <td>
                                ${orderDeatil.productName}
                            </td>
                            <td>
                                ${orderDeatil.productPrice}
                            </td>
                            <td>
                                ${orderDeatil.productQuantity}
                            </td>
                            <td>
                                <img style="width: 50px;height: 50px" src="${orderDeatil.productIcon}"/>
                            </td>
                            <td>${orderDeatil.createTime}</td>
                            <td>${orderDeatil.updateTime}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
</html>