<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APPPATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APPPATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APPPATH }/css/main.css">
	<link rel="stylesheet" href="${APPPATH }/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">许可维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.uname } <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${APPPATH }/loginout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@ include file="../main.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="from1">
				  <div class="form-group">
					<label for="exampleInputPassword1">许可名称</label>
					<input type="hidden" name="pid" id="pid" value="${permission.pid }"/>
					<input type="hidden" name="parentid" id="parentid" value="${permission.parentid }"/>
					<input type="text" class="form-control" id="name" name="name" value="${permission.name }" placeholder="请输入许可名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">链接位置</label>
					<input type="email" class="form-control" id="url" name="url"  value="${permission.url }" placeholder="请输入链接位置">
				  </div>
				  <button type="button" class="btn btn-success" id="saveBtn"><i class="glyphicon glyphicon-edit"></i> 修改</button>
				  <button type="button" class="btn btn-danger" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	
    <script src="${APPPATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APPPATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APPPATH }/script/docs.min.js"></script>
      	<script src="${APPPATH }/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    $("#resetBtn").click(function(){
			    	$("#from1")[0].reset();
			    	$("input").val("");
			    });
			    $("#saveBtn").click(function(){
			    	var nameVal = $("#name").val();
			    	var urlVal = $("#url").val();
			    	if (nameVal=="") {
			    		layer.msg("许可名称不能为空！", {time:1000, icon:5, shift:6}, function(){});
			    		return;
					}
			    	if (urlVal=="") {
			    		layer.msg("链接位置不能为空！", {time:1000, icon:5, shift:6}, function(){});
			    		return;
					}
			    	$.ajax({
			    		cache:false,
			    		url:"permissionUpdate",
			    		type:"post",
			    		data:$("#from1").serialize(),
			    		error:function(){
			    			alert("error");
			    		},
			    		success:function(result){
			    			if (result.flag) {
			    				layer.msg("修改许可成功！", {time:1000, icon:6, shift:6}, function(){});
								window.location.href="permission";
			    			}else{
								layer.msg("修改许可失败！", {time:1000, icon:2, shift:6}, function(){});
							}
			    		}
			    	});
			    });
            });
        </script>
  </body>
</html>
    