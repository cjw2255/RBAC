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
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">角色维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>${loginUser.uname } <span class="caret"></span>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" name="queryName" id="queryName" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning" id="btnQuery"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="delRoles()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='roleSave'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          <form id="delForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="checkAll"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="tableContent">
                
                
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination" id="byPage">
								
							 </ul>
					 </td>
				 </tr>

			  </tfoot>
            </table>
            </form>
          </div>
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
        	var queryFlag=false;
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
			    
			    queryByPage(1);
			    
            });
            
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });
            
            $("#checkAll").click(function(){
            	var flag = $(this).prop("checked");
            	$("#tableContent :checkbox").prop("checked",flag);
            });
            $("#btnQuery").click(function(){
		    	var queryVal = $("#queryName").val();
		    	if (queryVal!="") {
		    		queryFlag = true;
				}
		    	queryByPage(1);
		    });
            function delRoles(){
            	layer.confirm("是否删除选中的用户？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
                		cache:false,
                		url:"delRoles",
                		type:"post",
                		data:$("#delForm").serialize(),
                		error:function(){
			    			alert("error");
			    		},
			    		success:function(result){
			    			if (result.flag) {
			    				layer.msg("删除成功！", {time:5000, icon:6, shift:6}, function(){});
								window.location.href="show";
			    			}else{
								layer.msg("删除失败！", {time:1000, icon:2, shift:6}, function(){});
							}
			    		}
                	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            function update(rid){
            	window.location.href="update?rid="+rid;
            }
            function assignPermission(rid){
            	window.location.href="assignPermission?rid="+rid;
            }
            function deleteRole(rid){
            	layer.confirm("是否删除该用户？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
                		cache:false,
                		url:"deleteRole",
                		type:"post",
                		data:{"rid":rid},
                		error:function(){
			    			alert("error");
			    		},
			    		success:function(result){
			    			if (result.flag) {
			    				layer.msg("删除成功！", {time:5000, icon:6, shift:6}, function(){});
								window.location.href="show";
			    			}else{
								layer.msg("删除失败！", {time:1000, icon:2, shift:6}, function(){});
							}
			    		}
                	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
			function queryByPage(currentPage){
				var jsonData = {"currentPage":currentPage};
            	if (queryFlag) {
					jsonData.queryVal= $("#queryName").val();
				}
				$.ajax({
			    	cache:false,
			    	url:"roleshow",
			    	type:"post",
			    	data:jsonData,
			    	error:function(){
			    		alert("error");
			    	},
			    	success:function(result){
			    		if (result.flag) {
			    			var roleStr = "";
			    			$.each(result.obj.list,function(index,role){
			    				roleStr+="<tr>";
				                roleStr+="<td>"+(index+1)+"</td>";
								roleStr+="<td><input type='checkbox' name='rids' value='"+role.rid+"'></td>";
				                roleStr+="<td>"+role.rname+"</td>";
				                roleStr+="<td>";
								roleStr+="<button type='button' class='btn btn-success btn-xs' onclick='assignPermission("+role.rid+")'><i class=' glyphicon glyphicon-check'></i></button>";
								roleStr+="<button type='button' class='btn btn-primary btn-xs' onclick='update("+role.rid+")'><i class=' glyphicon glyphicon-pencil'></i></button>";
								roleStr+="<button type='button' class='btn btn-danger btn-xs' onclick='deleteRole("+role.rid+")'><i class=' glyphicon glyphicon-remove'></i></button>";
								roleStr+="</td>";
				                roleStr+="</tr>";
			    			});
			    			$("#tableContent").html(roleStr);
			    			var byPageStr="";
		    				byPageStr+="<li class='"+(result.obj.currentPage==1?'disabled':'')+"'><a href=''#' onclick='queryByPage("+(result.obj.currentPage-1)+")'>上一页</a></li>";
							for (var i = 1; i <= result.obj.pages; i++) {
								if (result.obj.currentPage==i) {
									byPageStr+="<li class='active'><a href='#' onclick='queryByPage("+(i)+")'>"+i+"<span class='sr-only'>(current)</span></a></li>";
								}else{
									byPageStr+="<li><a href='#' onclick='queryByPage("+(i)+")'>"+i+"</a></li>";
								}
							}
							byPageStr+="<li class='"+(result.obj.currentPage==result.obj.pages?'disabled':'')+"'><a href='#' onclick='queryByPage("+(result.obj.currentPage+1)+")'>下一页</a></li>";
							byPageStr+="<li class='disabled'><span>共"+result.obj.pages+"页</span></li>";
							byPageStr+="<li class='disabled'><span>共"+result.obj.totalCount+"条</span></li>";
							$("#byPage").html(byPageStr);
						}else{
							layer.msg("查询失败！", {time:1000, icon:2, shift:6}, function(){});
						}
			    	}
			    });
            }
        </script>
  </body>
</html>
    