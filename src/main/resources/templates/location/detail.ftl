<html>
<#include "../commons/header.ftl">
<body>

<div id="wrapper" class="toggled">

    <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>收货人</th>
                                <th>联系电话</th>
                                <th>收货地址</th>
                                <th>收货地址状态</th>
                            </tr>
                            </thead>
                            <tbody>


                            <tr>
                                <td>${location.locationName}</td>
                                <td>${location.locationPhone}</td>
                                <td>${location.locationAddress}</td>
                                <td>
                                    <#if location.locationExist == 0>
                                        已删除
                                    </#if>
                                    <#if location.locationExist == 1>
                                        未删除
                                    </#if>
                                </td>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

</body>
</html>