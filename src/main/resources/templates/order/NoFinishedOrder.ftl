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
                            <th>收货信息</th>
                            <th>用户信息</th>
                            <th>订单总金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>订单创建时间</th>
                            <th>订单更新时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderMasterPage.content as orderMaster>
                        <tr>
                            <td>${orderMaster_index + 1}</td>
                            <td>
                                <a href="/supershop/seller/order/goToLocation?locationId=${orderMaster.locationId}">查看</a>
                            </td>
                            <td>
                                <a href="/supershop/seller/order/goToUser?userId=${orderMaster.userId}">查看</a>
                            </td>
                            <td>${orderMaster.orderAmount}</td>
                            <td>
                                <#if orderMaster.orderStatus == 0>
                                    新订单
                                </#if>
                                <#if orderMaster.orderStatus == 1>
                                    完结
                                </#if>
                                <#if orderMaster.orderStatus == 2>
                                    已取消
                                </#if>

                            </td>

                            <td>
                                <#if orderMaster.payStatus == 0>
                                    等待支付
                                </#if>
                                <#if orderMaster.payStatus == 1>
                                    支付成功
                                </#if>
                            </td>
                            <td>${orderMaster.createTime}</td>
                            <td>${orderMaster.updateTime}</td>
                            <td>
                                <a href="/supershop/seller/order/goToOrderDetail?orderId=${orderMaster.orderId}">订单详情</a>
                                <a href="/supershop/seller/order/finishOrder?orderId=${orderMaster.orderId}">完结订单</a>
                                <a href="/supershop/seller/order/cancelOrder?orderId=${orderMaster.orderId}">取消订单</a>

                            </td>
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
            <li><a href="/supershop/seller/order/noFinish?pageIndex=${currentPage - 1}&pageSize=${size}">上一页</a></li>
        </#if>

        <#list 1..orderMasterPage.getTotalPages() as index>
            <#if currentPage == index>
                <li class="disabled"><a href="#">${index}</a></li>
            <#else>
                <li><a href="/supershop/seller/order/noFinish?pageIndex=${index}&pageSize=${size}">${index}</a></li>
            </#if>
        </#list>

        <#if currentPage gte orderMasterPage.getTotalPages()>
            <li class="disabled"><a href="#">下一页</a></li>
        <#else>
            <li><a href="/supershop/seller/order/noFinish?pageIndex=${currentPage + 1}&pageSize=${size}">下一页</a></li>
        </#if>
        </ul>
    </div>
</body>
</html>