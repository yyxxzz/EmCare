<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
<style type="text/css">
   .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
</style>
</head>
<link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	    <script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
	      <script type="text/javascript" src="/EmCare/js/bootstrap/js/bootstrap.js"></script>
	      <script type="text/javascript">
<!--
	$(function(){
		$("#a_change_passs").click(function(){
			var oldpass = $("input[name='oldpassword']").val();
			var newpass = $("input[name='newpassword']").val();
			var newpass_confirm = $("input[name='newpassword_confirm']").val();
			if(oldpass==""){
				alert("Please input the old password!");
				$("input[name='oldpassword']").focus();
				return;
			}
			if(newpass!=newpass_confirm){
				$("input[name='newpassword']").focus();
				alert("The two new passwords mismatch");
				return;
			}
			if(newpass==""||newpass_confirm==""){
				$("input[name='newpassword']").focus();
				alert("Please input new password");
				return;
			}
			$.get("<%=basePath%>/admin/changePassword!executeChange?newPassword="+newpass+"&oldPassword="+oldpass,function(json){
				//alert(json.msg);
				if(json.success){ 
				$(".alert").show();
				}
			});
		});
	});
//-->
</script>

<body>
<div style="display:none;" class="alert alert-success">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Congratulations!</strong> You successfully change your password.
</div>
	<div id="password_change" class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">Change Password</h2>
        <div class="modal-body">
       <span class="label label-success">old password</span> <input name="oldpassword" type="password" class="input-block-level" placeholder="Old Password" />
      <span class="label label-success">new password</span>  <input name="newpassword" type="password" class="input-block-level" placeholder="New Password" />
      <span class="label label-success">confirm</span>  <input name="newpassword_confirm" type="password" class="input-block-level" placeholder="Confirm Password" />
        </div>
        <div class="modal-footer">
        <a id="a_change_passs" class="btn btn-primary" type="submit">Save Changes</a>
        </div>
      </form>

 </div>
</body>
</html>