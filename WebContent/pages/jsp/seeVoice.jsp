<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<jsp:include page="../jsp/component/js_include.jsp"></jsp:include>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/main.css">	
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/seeVoice.css">	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/tooltipster-1.2/css/tooltipster.css" />
	<script type="text/javascript" src="<%=basePath %>/js/tooltipster-1.2/js/jquery.tooltipster.min.js"></script>
	<script charset="utf-8" src="<%=basePath %>/js/kindeditor/kindeditor-min.js"></script>
	
	<script type="text/javascript">
	 $(document).ready(function() {
         $('.tooltips').tooltipster({fixedWidth: 400});
         
     	var editor = KindEditor.create(
     			'#kind_editor',
     			{
     			   items:[ 'preview', 'cut', 'copy', 'paste', '|',
     				        'justifyleft', 'justifycenter', 'justifyright',
     				        'justifyfull', '|', 'undo', 'redo', 'selectall',  '/',
     				        'formatblock', 'fontname', 'fontsize',  'forecolor', 'hilitecolor', 'bold',
     				        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 
     				         'emoticons', 
     				        'link', 'unlink'
     				],
     				newlineTag:"br",
     				afterChange:function(){
     					this.sync();
     				},
     				langType:'en',
     				resizeType: 0
     			}
     		);
         
     });
	function voteVoice(){
		if(confirm("Are you sure to vote?")){
			$.ajax({
				type:"GET",
				url:"<%=basePath%>/voteForVoice?voiceId="+$("#voice_id").val(),
				dataType:"json",
				success:function(data){
					if(data.success){
						alert("Thanks for your feedback!");
					}else{
						alert("Please log in first.");
					}
				}
			});
		}
	}
	
	function voteForAgree(voiceId){
		if(confirm("Are you sure to vote?")){
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"<%=basePath%>/agree?voice=" + voiceId,
				success:function(data){
					$("#voteAgree").html("+ " + data.agree);
					$("#voteTotal").html(data.voteTotal);
				}
			});
		}
	}
	
	function voteForDisagree(voiceId){
		if(confirm("Are you sure to vote?")){
			$.ajax({
				type:"GET",
				dataType:"json",
				url:"<%=basePath%>/disagree?voice=" + voiceId,
				success:function(data){
					$("#voteDisagree").html("- " + data.disagree);
					$("#voteTotal").html(data.voteTotal);
				}
			});
		}
	}
	
	function checkEmpty(){
		
		var value =  $("#kind_editor").val();		
		var space = /&nbsp;|[<br/>]/g;
		
		value = value.replace(space,"");
		value = value.trim();
		
		if(value == ""){
			$("#comment_tip").html("Content can not be empty.");
			return false;
		}else{
			return true;
		}
	}
	
</script>

<style type="text/css">
	
	#warmTips{
		font-size:13px;
		color: #C5D811;
	}

</style>

</head>

<body style="margin: 0px;">

<jsp:include page="component/head.jsp"></jsp:include>


<div class="middleContent ">
	<div class="leftC">
		<div class="title toVoiceTitle"><b><s:property value="voice.voice.title"/></b></div>
		


<div class="data" style="border-bottom: 0px;">
	<table >
		<tr >
			<td valign="top">
				<div class="voteInfo">
					<b id="voteTotal"><s:property value="voice.voice.agreeCount + voice.voice.disagreeCount" /></b><br >
					<span >votes</span>
					<s:if test="voice.voice.status != 6">
						<div style="background-color: orange;">
							<b>OPEN</b>			
						</div>
					</s:if>
					<s:else>
						<div>
							<b>CLOSE</b>			
						</div>
					</s:else>					
				</div>
				<div class="viewNum"><s:property value="voice.voice.viewCount" /> views</div>
			</td>
			<td valign="top">
				<div class="voiceDetailTitle">
					<div class="v_content" ><s:property value="voice.voice.content" escape="false"/>
					</div>
				</div>		
				<br>
				<div>		
					<div class="authorInfo">			
						<table >
							<tr>
								<td rowspan="2"><img src="<%=basePath %>/images/ano.png" width="32" height="32"></td>
								<td style="font-size: 12px; color: #999999;">
									<s:if test="voice.voice.signature != null && !voice.voice.signature.trim().equals('')">
										<s:property value="voice.voice.signature" />
									</s:if>
									<s:else>
										anonymous									
									</s:else>								
								</td>
							</tr>
							<tr >
								<td style="font-size: 12px; color: #999999;">
									<b><s:date name="voice.voice.submitTime" format="  MMM dd h:mm a"/></b>
								</td>
							</tr>
						</table>	
					</div>		
					<div class="voiceTypeInfo">
						<a class="someType"><s:property value="voice.voice.type==null?'unclassfied':voice.voice.type.name" /></a>
					</div>			
				</div>
			</td>
		</tr>
	</table>
</div>

<div class="title subTitle"><b>Progress</b></div>

<div style="width: 745px;">
	<div class="workflow">        
		<s:iterator value="workflow" var="ah" status="st">
           <div class="w_record " >
            	<div class="w_status" >                        
                	<span><s:date name="updateTime" format="yyyy-MM-dd HH:mm"/></span>                	
            	</div>
            	<div >
            		<s:if test="#ah.keyPerson == null">
            			<b class="w_text tooltips" title="No Comments"  /><s:property value="#ah.getActionDesc()"/></b>
            		</s:if>
            		<s:else>
            			<b class="w_text tooltips" title="Comment from <s:property value='#ah.keyPerson.realname'/>:<br><br><s:property value='#ah.comments'/>"><s:property value="#ah.getActionDesc()"/></b>
            		</s:else>
            		 
            		<s:if test="!#st.last">
                        <span class="w_image"><img src="<%=basePath %>/images/arrow.png" /></span>
                    </s:if>
                    <s:else>
                    	<span class="w_hide_image"><img src="<%=basePath %>/images/arrow.png" height="21" width="1"/></span>
                    </s:else>
                    
            	</div>
           </div>
        </s:iterator>		    
    </div>
	<br><br>
		
	<div>
		<s:if test="voice.voice.isClosed()">
			<div id="vote_section">
				<b style="color: #E56C00;">Dissatisfied with the solution?</b>&nbsp;&nbsp;&nbsp;
				<span id="voteAgree" class="voteFor" onclick="voteForAgree(<s:property value="voice.voice.voiceId"/>);">+ <s:property value="voice.voice.agreeCount"/></span>&nbsp;&nbsp;&nbsp;
				<span id="voteDisagree" class="voteFor" onclick="voteForDisagree(<s:property value="voice.voice.voiceId"/>);">- <s:property value="voice.voice.disagreeCount"/></span>
				<input id="voice_id" type="hidden" value="<s:property value="voice.voice.voiceId"/>" />
			</div>
		</s:if>
	</div>
</div>

<br>
<div class="title subTitle"><b><s:property value="voice.voice.comments.size()" /> Discussions</b></div>
<div id="comments">
<!-- comments of voice --> 
<s:iterator value="comments">
<div class="data" style="margin-top: 10px;">
	<table >
		<tr >
			<td valign="top">				
				<div class="v_content" style="width: 740px;"><s:property value="content" escape="false"/></div>			
				<br >		
				<div style="position: relative; left: 600px;">					
					<table >
						<tr>
							<td rowspan="2"><img src="<%=basePath %>/images/ano.png" width="32" height="32"></td>
							<td style="font-size: 12px; color: #999999;">
								<s:if test="author != null && !author.trim().equals('')">
									<s:property value="author"/>
								</s:if>
								<s:else>
									anonymous									
								</s:else>
								
							</td>
						</tr>
						<tr >
							<td style="font-size: 12px; color: #999999;">
								<b><s:date format="yyyy-MM-dd hh:mm" name="commitDate"/></b>
							</td>
						</tr>
					</table>					
				</div>
			</td>
		</tr>
	</table>
</div>
</s:iterator>
</div>

<div class="pageBar">
	<jsp:include page="./component/pagination_voice_detail.jsp"></jsp:include>
</div>


<br><br><br>
<div class="title subTitle" style="font-size: 18px;">
	<b>Your Opinion</b>
	<span id="comment_tip">
		<b id="warmTips">All the Voices and Comments are Anonymous.</b>
	</span>
</div>


<!-- post comment form -->
<div >
	<form name="postVoiceComment" onsubmit="return checkEmpty();" class="postComment" method="post" action="<%=basePath%>/postVoiceComment" id="postCommentform" accept-charset="utf-8" >
			<table >		
				<tr >
					<td colspan="2" height="10"></td>
				</tr>
				<tr >
					<td colspan="2">
						<textarea id="kind_editor"  name="comment" style="width: 660px; height: 220px;"></textarea>
					</td>
				</tr>
				<tr ><td colspan="2" height="10"></td></tr>
				<tr >
					<td width="2">
						<b style="font-size: 12px; ">Signature</b>&nbsp;&nbsp;
						<input name="author" type="text" style="width: 140px; font-size: 14px;">&nbsp;&nbsp;
						<b style="font-size: 11px;">(Optional)</b>
						<input type="hidden" name="voiceId" value="<s:property value='voice.voice.voiceId'/>"/>
					</td>
				</tr>
			</table>
			
			<br />
			<input type="submit" class="submitButton" style="font-size: 90%;" value="Share Your opinion" />
	</form>
</div>  

  
  
  
	</div>
	<jsp:include page="component/right.jsp"></jsp:include>
</div>



<jsp:include page="component/foot.jsp"></jsp:include>

</body>
</html>