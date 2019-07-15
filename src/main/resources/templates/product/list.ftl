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
                                <th>序号</th>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>商品库存</th>
                                <th>商品图标</th>
                                <th>商品状态</th>
                                <th>所属分类</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list productInfoPage.content as product>
                            <tr>
                                <td>${product_index + 1}</td>
                                <td>${product.productName}</td>
                                <td>${product.productPrice}</td>
                                <td>${product.productStock}</td>
                                <td>
                                    <img src="${product.productIcon}" height="100" width="100">
                                </td>

                                <td>
                                    <#if product.productStatus == 0>
                                        在架
                                    </#if>
                                    <#if product.productStatus == 1>
                                        下架
                                    </#if>
                                </td>
                                <td>${product.categoryType}</td>
                                <td>${product.createTime}</td>
                                <td>${product.updateTime}</td>
                                <td><a href="/supershop/seller/product/goToEditProduct?productId=${product.productId}">修改</a></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    <#--分页-->
        <div class="col-md-12 column">
            <ul class="pagination pull-right">

            <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
            <#else>
                <li><a href="/supershop/seller/product/list?index=${currentPage - 1}&size=${size}">上一页</a></li>
            </#if>

            <#list 1..productInfoPage.getTotalPages() as index>
                <#if currentPage == index>
                    <li class="disabled"><a href="#">${index}</a></li>
                <#else>
                    <li><a href="/supershop/seller/product/list?index=${index}&size=${size}">${index}</a></li>
                </#if>
            </#list>

            <#if currentPage gte productInfoPage.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
            <#else>
                <li><a href="/supershop/seller/product/list?index=${currentPage + 1}&size=${size}">下一页</a></li>
            </#if>
            </ul>
        </div>
</body>
</html>