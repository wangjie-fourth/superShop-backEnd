<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li class="dropdown is-closed" onclick="show(this)">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <i class="fa fa-fw fa-plus"></i> 推荐 <span class="caret"></span>
            </a>

            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/supershop/recommend/update">更新推荐</a></li>
            </ul>
        </li>

        <li class="dropdown is-closed" onclick="show(this)">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <i class="fa fa-fw fa-plus"></i> 订单 <span class="caret"></span>
            </a>

            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/supershop/seller/order/list?pageIndex=1">所有订单</a></li>
                <li><a href="/supershop/seller/order/noPayList?pageIndex=1">未支付订单</a></li>
                <li><a href="/supershop/seller/order/noFinish?pageIndex=1">未完结订单</a></li>
            </ul>
        </li>

        <li class="dropdown is-closed" onclick="show(this)">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span>
            </a>

            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/supershop/seller/product/list?index=1">列表</a></li>
                <li><a href="/supershop/seller/product/index">新增</a></li>
            </ul>
        </li>

        <li class="dropdown is-closed" onclick="show(this)">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/supershop/seller/category/list">列表</a></li>
                <li><a href="/supershop/seller/category/goToAdd">新增</a></li>
            </ul>
        </li>

        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>