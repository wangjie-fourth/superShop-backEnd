<html>
<#include "../commons/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include  "../commons/nav.ftl">


    <#--主要内容content-->
    <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>类目名称</th>
                                <th>类目编号</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list categoryList as category>
                            <tr>
                                <td>${category_index + 1}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>
                                    <#if category.categoryExist == 0>
                                        在架
                                    </#if>
                                    <#if category.categoryExist == 1>
                                        下架
                                    </#if>
                                </td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td>
                                    <a href="/supershop/seller/category/goToUpdate?categoryId=${category.categoryId}&categoryName=${category.categoryName}">修改</a>
                                    <a href="/supershop/seller/category/delete?categoryId=${category.categoryId}">删除</a>
                                </td>
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