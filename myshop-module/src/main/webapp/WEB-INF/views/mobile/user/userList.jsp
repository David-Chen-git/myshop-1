<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>商城管理 | 用户列表</title>
    <jsp:include page="/WEB-INF/views/includes/header.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <sys:nav />
    <sys:menu parentTitle="用户管理" subTitle="用户列表" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <sys:message message="${message}"/>

            <div class="row">
                <div class="col-md-12">
                    <div class="box box-info">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <div class="box-tools">
                                <div class="input-group input-group-sm" style="width: 150px;">
                                    <input type="text" name="table_search" class="form-control pull-right" placeholder="搜索...">

                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Check all button -->
                        <div class="mailbox-controls">
                            <!-- Check all button -->
                            <button type="button" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i>
                            </button>
                            <div class="btn-group">
                                <button  id="deleteTbUser"  class="btn btn-default btn-sm" onclick="batchDeletes()"  ><i class="fa fa-trash-o"></i></button>
                            </div>
                            <!-- /.btn-group -->
                            <a href="/user/list" type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></a>
                            <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover mailbox-messages">
                                <tr>
                                    <th>选择</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>

                                    <c:forEach items="${list}" var="tbUser">
                                        <tr>
                                            <td ><input class="subcheck" type="checkbox" name="subcheck" value="${tbUser.id}"></td>
                                            <td>${tbUser.username}</td>
                                            <td>${tbUser.phone}</td>
                                            <td>${tbUser.email}</td>
                                            <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                            <td>
                                                <a href="/user/form?id=${tbUser.id}" type="button" class="btn btn-xs btn-primary"><i class="fa fa-edit"></i> 编辑</a>
                                                <a id="deleteUser" href="/user/delete?id=${tbUser.id}" onclick= "if(confirm( '是否确定！ ')==false)return   false; " type="button" data-toggle="modal" class="btn btn-xs btn-danger ${tbUser.id eq user.id?"hidden":""} "><i class="fa fa-remove"></i> 删除</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="/WEB-INF/views/includes/copyright.jsp" />
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
<script>
    $(function () {
        //Enable iCheck plugin for checkboxes
        $('.mailbox-messages input[type="checkbox"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
        });

        //Enable check and uncheck all functionality
        $(".checkbox-toggle").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[type='checkbox']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

    });
    function batchDeletes(){
        //判断至少写了一项
        var checkedNum = $("input[name='subcheck']:checked").length;
        if(checkedNum==0){
            alert("请至少选择一项!");
            return false;
        }
        if(confirm("确定删除所选项目?")){
            var checkedList = new Array();
            $("input[name='subcheck']:checked").each(function(){
                checkedList.push($(this).val());
            });
            $.ajax({
                type:"POST",
                url:"deletes",
                data:{"delitems":checkedList.toString()},
                datatype:"html",
                success:function(data){
                    $("[name='checkbox2']:checkbox").attr("checked",false);
                    location.reload();//页面刷新
                },
                error:function(data){
                    art.dialog.tips('删除失败!');
                }
            });
        }
    };



</script>
</body>
</html>
