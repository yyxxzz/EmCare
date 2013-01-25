<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath();%>
<div class="pager fl">
<s:if test="page.totalPage>5&&page.currentPage<=page.totalPage-5&&page.currentPage>=5">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage-1)<=0?1:page.currentPage-1"/>.htm"><span class="page-numbers prev">prev </span></a>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/1.htm"><span class="page-numbers">1</span></a>
<span class="page-numbers dots">…</span>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.currentPage-2"/>.htm"><span class="page-numbers"><s:property value="page.currentPage-2"/></span></a>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.currentPage-1"/>.htm"><span class="page-numbers"><s:property value="page.currentPage-1"/></span></a>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.currentPage+1"/>.htm"><span class="page-numbers"><s:property value="page.currentPage+1"/></span></a>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.currentPage+2"/>.htm"><span class="page-numbers"><s:property value="page.currentPage+2"/></span></a>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.currentPage+3"/>.htm"><span class="page-numbers"><s:property value="page.currentPage+3"/></span></a>
<span class="page-numbers dots">…</span>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage" />.htm"><span class="page-numbers"><s:property value="page.totalPage" /></span></a>
<a href='<%=basePath%>/<s:property value="userAction"/>?category=<s:property value="category"/>&${page.currentPage+1>=page.totalPage?page.totalPage:page.currentPage+1 }'>
<span class="page-numbers next"> next</span></a>
</s:if>
<s:elseif test="page.currentPage<5&&page.totalPage>5">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage-1)<=0?1:page.currentPage-1"/>.htm"><span class="page-numbers prev">prev</span></a>
<s:iterator var="counter" status="stat" value="page.totalPage<5?(page.totalPage).{ #this }:(5).{#this}">
	<s:if test="#stat.count==page.currentPage">
		<span class="page-numbers current"><s:property value="page.currentPage"/></span>
	</s:if>
	<s:else>
		<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="#stat.count" />.htm"><span class="page-numbers"><s:property value="#stat.count"/></span></a>
	</s:else>
</s:iterator>
<span class="page-numbers dots">…</span>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage" />.htm"><span class="page-numbers"><s:property value="page.totalPage"/></span></a>
<a href='<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage+1)<page.totalPage?(page.currentPage+1):page.totalPage"/>.htm'><span class="page-numbers next"> next</span></a>
</s:elseif>
<s:elseif test="page.currentPage<5&&page.totalPage<=5">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage-1)<=0?1:page.currentPage-1"/>.htm"><span class="page-numbers prev">prev</span></a>
<s:iterator var="counter" status="stat" value="page.totalPage<5?(page.totalPage).{ #this }:(5).{#this}">
	<s:if test="#stat.count==page.currentPage">
		<span class="page-numbers current"><s:property value="page.currentPage"/></span>
	</s:if>
	<s:else>
		<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="#stat.count" />.htm"><span class="page-numbers"><s:property value="#stat.count"/></span></a>
	</s:else>
</s:iterator>
<a href='<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage+1)<page.totalPage?(page.currentPage+1):page.totalPage"/>.htm'><span class="page-numbers next"> next</span></a>
</s:elseif>
<s:else>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage-1)<=0?1:page.currentPage-1"/>.htm"><span class="page-numbers prev">prev</span></a>
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/1.htm"><span class="page-numbers">1</span></a>
<span class="page-numbers dots">…</span>
<s:if test="(page.totalPage-4)!=page.currentPage">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage-4"/>.htm"><span class="page-numbers"><s:property value="page.totalPage-4"/></span></a>
</s:if>
<s:else>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
</s:else>
<s:if test="(page.totalPage-3)!=page.currentPage">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage-3"/>.htm"><span class="page-numbers"><s:property value="page.totalPage-3"/></span></a>
</s:if>
<s:else>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
</s:else>
<s:if test="(page.totalPage-2)!=page.currentPage">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage-2"/>.htm"><span class="page-numbers"><s:property value="page.totalPage-2"/></span></a>
</s:if>
<s:else>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
</s:else>
<s:if test="(page.totalPage-1)!=page.currentPage">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage-1"/>.htm"><span class="page-numbers"><s:property value="page.totalPage-1"/></span></a>
</s:if>
<s:else>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
</s:else>
<s:if test="(page.totalPage)!=page.currentPage">
<a href="<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="page.totalPage"/>.htm"><span class="page-numbers"><s:property value="page.totalPage"/></span></a>
</s:if>
<s:else>
<span class="page-numbers current"><s:property value="page.currentPage"/></span>
</s:else>
<a href='<%=basePath %>/<s:property value="userAction"/>/<s:property value="category"/>/<s:property value="(page.currentPage+1)<page.totalPage?(page.currentPage+1):page.totalPage"/>.htm'><span class="page-numbers next"> next</span></a>
</s:else>
</div>