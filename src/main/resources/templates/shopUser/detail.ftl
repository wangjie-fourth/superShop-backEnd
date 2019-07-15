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
                                <th>用户名</th>
                                <th>用户openId</th>
                                <th>头像信息</th>
                            </tr>
                            </thead>
                            <tbody>


                            <tr>
                                <td>${shopUser.username}</td>
                                <td>${shopUser.userOpenid}</td>
                                <td>
                                    <img src="${shopUser.userIcon}">
                                </td>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

</body>
</html>