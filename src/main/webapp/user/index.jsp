<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
	<link rel="stylesheet" href="${APPPATH }/ztree/zTreeStyle.css">
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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>${loginUser.uname }<span class="caret"></span>
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
				<%@ include file="/main.jsp" %>
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
      <input class="form-control has-success" id="queryName" name="queryName" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning" id="btnQuery" name="btnQuery"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="delUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='save'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          <form id="delForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="checkAll"></th>
                  <th>账号</th>
                  <th>名称</th>
                  <th>邮箱地址</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="tableContent">
              <%-- <c:forEach items="${pageBean.list }" var="user" varStatus="i">
              	 <tr>
                  <td>${i.count }</td>
				  <td><input type="checkbox"></td>
                  <td>${user.uname }</td>
                  <td>${user.uaccount }</td>
                  <td>${user.uemail }</td>
                  <td>
				      <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
				      <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
					  <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
				  </td>
                </tr>
              </c:forEach> --%>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination" id="byPage">
								<%-- <li class="${pageBean.currentPage==1?'disabled':''}"><a href="index?currentPage=${pageBean.currentPage-1 }">上一页</a></li>
								<c:forEach begin="1" end="${ pageBean.pages}" var="i">
									<c:if test="${pageBean.currentPage==i }">
										<li class="active"><a href="index?currentPage=${i }">${i } <span class="sr-only">(current)</span></a></li>
									</c:if>
									<c:if test="${pageBean.currentPage!=i }">
										<li><a href="index?currentPage=${i }">${i }</a></li>
									</c:if>
								</c:forEach>
								<li class="${pageBean.currentPage==pageBean.pages?'disabled':''}"><a href="index?currentPage=${pageBean.currentPage+1 }">下一页</a></li> --%>
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
        	var queryFlag = false;
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
			    $("#checkAll").click(function(){
			    	var flag = $(this).prop("checked");
			    	$.each($("#tableContent  :checkbox"),function(index,obj){
			    		obj.checked=flag;
			    	});
			    });
			    $("#btnQuery").click(function(){
			    	var queryVal = $("#queryName").val();
			    	if (queryVal!="") {
			    		queryFlag = true;
					}
			    	queryPage(1);
			    });
			    queryPage(1);
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            function delUsers(){
            	layer.confirm("是否删除选中的用户？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
                		cache:false,
                		url:"delUsers",
                		type:"post",
                		data:$("#delForm").serialize(),
                		error:function(){
			    			alert("error");
			    		},
			    		success:function(result){
			    			if (result.flag) {
			    				layer.msg("删除成功！", {time:5000, icon:6, shift:6}, function(){});
								window.location.href="index";
			    			}else{
								layer.msg("删除失败！", {time:1000, icon:2, shift:6}, function(){});
							}
			    		}
                	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
			}
            function update(uid){
            	window.location.href="update?uid="+uid;
            }
            function assignRole(uid){
            	window.location.href="assignRole?uid="+uid;
            }
            function deleteUser(uid){
    			layer.confirm("是否删除该用户？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
                		cache:false,
                		url:"deleteUser",
                		type:"post",
                		data:{"uid":uid},
                		error:function(){
			    			alert("error");
			    		},
			    		success:function(result){
			    			if (result.flag) {
			    				layer.msg("删除成功！", {time:5000, icon:6, shift:6}, function(){});
								window.location.href="index";
			    			}else{
								layer.msg("删除失败！", {time:1000, icon:2, shift:6}, function(){});
							}
			    		}
                	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            	
            }
            function queryPage(currentPage){
            	var jsonData = {"currentPage":currentPage};
            	if (queryFlag) {
					jsonData.queryVal= $("#queryName").val();
				}
            	$.ajax({
            		cache:false,
            		url:"indexajax",
            		type:"post",
            		data:jsonData,
            		error:function(){
            			alert("error");
            		},
            		success:function(result){
            			if (result.flag) {
            				var tableStr = ""
							//result.obj.list
							$.each(result.obj.list,function(index,user){
								//用html方法拼字符串
								tableStr+="<tr>";
				                tableStr+="<td>"+(index+1)+"</td>";
								tableStr+="<td><input type='checkbox' class='chbox' name='uids' value='"+user.uid+"'></td>";
				                tableStr+="<td>"+user.uname+"</td>";
				                tableStr+="<td>"+user.uaccount+"</td>";
				                tableStr+="<td>"+user.uemail+"</td>";
				                tableStr+="<td>";
								tableStr+="<button type='button' class='btn btn-success btn-xs' onclick='assignRole("+user.uid+")'><i class='glyphicon glyphicon-check'></i></button>";
								tableStr+="<button type='button' class='btn btn-primary btn-xs' id='updateBtn' onclick='update("+user.uid+")'><i class='glyphicon glyphicon-pencil'></i></button>";
								tableStr+="<button type='button' class='btn btn-danger btn-xs' onclick='deleteUser("+user.uid+")'><i class='glyphicon glyphicon-remove'></i></button>";
								tableStr+="</td>";
				                tableStr+="</tr>";
							});
							$("#tableContent").html(tableStr);
							//result.obj
							var pageStr = "";
							pageStr+="<li class='"+(result.obj.currentPage==1?'disabled':'')+"'><a href='#' onclick='queryPage("+(result.obj.currentPage-1)+")'>上一页</a></li>";
							for (var i = 1; i <= result.obj.pages; i++) {
								if (result.obj.currentPage == i) {
									pageStr+="<li class='active'><a href='#' onclick='queryPage("+(i)+")'>"+i+" <span class='sr-only'>(current)</span></a></li>";
								}else{
									pageStr+="<li><a href='#' onclick='queryPage("+(i)+")'>"+i+"</a></li>";
									
								}
							}
							pageStr+="<li class='"+(result.obj.currentPage==result.obj.pages?'disabled':'')+"'><a href='#' onclick='queryPage("+(result.obj.currentPage+1)+")'>下一页</a></li>";
							pageStr+="<li class='disabled'><span>共"+result.obj.pages+"页</span></li>";
							pageStr+="<li class='disabled'><span>共"+result.obj.totalCount+"条</span></li>";
							$("#byPage").html(pageStr);
            			}else{
							layer.msg("查询失败！", {time:1000, icon:2, shift:6}, function(){});
						}
            		}
            	});
            }
        </script>
  </body>
</html>
    