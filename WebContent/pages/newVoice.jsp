<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/all.css" />
	<style >
	
	body{
		font-family: 	Arial,​Liberation Sans,​DejaVu Sans,​sans-serif;
	}
	
	.logo{
		margin-right: 40px;
	}
	
	.toCenter{		
		width: 955px;
		margin: 0 auto;		
	}
	
	.title{
		margin-top: 40px;
		font-size: 24px;
		border-bottom: solid 1px #CCCCCC;
		width: 745px;
		float: none;
	}
	
	.data{
		margin-top: 20px;
		border-bottom: solid 1px #EAEAEA;
		width: 745px;
	}
	
	.navigator{		
		margin-top: 30px;
		float: none;
	}
	
	.navigator a{
		height: 18px;		
		background-color: #777777;
		color: white;
		padding: 5px 18px;
		cursor: pointer;
		font-weight: bold;
		font-size: 16px;
		text-align: center;
		margin-top: 23px;
		margin-right: 5px;
	}
	
	.navigator a:hover{
		background-color: #FF9900;
	}
	
	.navigator .giveVoice{
		
		margin-left: 185px;
	}
	
	.userInfo{
		height: 31px; 
		background-color: #EEEEEE;		
	}
	
	.userInfo tr td{			
		margin-right: 15px;
		padding-top: 5px;
		color: #0077CC;
		font-weight: bolder;		
		font-size: 12px;	
	}
	
	.userInfo table{
		margin-left: 666px;
		
	}
	
	.userInfo  .seperator{
		width: 14px;
		text-align: center;
		color: #CCCCCC;
	}
	
	.userInfo  .search{
		padding-left: 30px;
	}
	
	.voteInfo{
		text-align: center;
		width: 55px;
		height: 85px;
		background-color: #EEEEEE;
		padding: 4px 7px 6px 7px;
	}
	
	.voteInfo b{
		line-height: 28px;
		font-size: 28px;
		color: #808185;
	}
	
	.voteInfo span{
		font-size: 12px;	
		color: #555555;
	}
	
	.voteInfo div{		
		margin-top: 5px;
		background-color: #75845C;
		padding-top: 5px;
	}
	
	.voteInfo div b{
		line-height: 14px;
		font-size: 16px;	
		color: white;
		
	}
	
	.voteInfo div span{
		color: white;
	}
	
	.viewNum{
		text-align: center;
		color: #999999;
		font-size: 13px;
	}
	
	.voiceDetailTitle{
		margin-left: 30px;
	}
	
	.voiceDetailTitle b{
		color: #0077CC;
		font-size: 17px;
	}
	
	.middleContent{
		/*float: left; */
		width: 955px;
		height: auto;
		overflow: hidden;
		margin: 0 auto;	
	}
	
	.summarycount{
		width: 200px;
		color: #808185;
    	font-size: 44px;;
    	font-weight: bold;
    	margin-left: 10px;
	}
	
	.rsp{
		margin-left: 20px;
		font-size: 15px;
	}
	
	.rsp b{
		color: #ff9900;
		font-size: 15px;
	}
	
	.rightContent{
		margin-top: 25px;
		float: right;
		height: 900px;
	}
	
	.voiceTypeTitle{
		margin-left: 20px;
		font-size: 16px;
	}
	
	.someType{
		margin-left: 20px;
		background-color: #E0EAF1;
		padding: 3px 4px 3px 4px;
		border: solid 1px;
		border-top-color: #E0EAF1;
		border-left-color: #E0EAF1;
		border-right-color:	#8D9BA4;
		border-bottom-color:	#8D9BA4;		
		text-decoration: none;
		color: #3E6D8E;
		font-size: 11px;
	}
	
	.leftC{
		width: 700px;
		float: left;
	}
	
	.pageBar{
		
	}
	
	</style>
	
</head>

<body style="margin: 0px;">

<div	class="userInfo">
	<table >
		<tr >
			<td >Chen Kai</td>
			<td class="seperator">|</td>
			<td >FAQ</td>
			<td class="seperator">|</td>
			<td >About</td>
			<td class="seperator">|</td>
			<td >Contact</td>
			<td class="search"><input type="text" value="search.."></td>
		</tr>
	</table>
</div>

<div class="navigator toCenter">
		<img src="<%=basePath %>/images/logo1.png" width="219" height="52" class="logo">
		<a >All Voices</a>
		<a >Answered</a>
		<a >Unanswered</a>
		<a class="giveVoice">Give Voice</a>
</div>



<div class="middleContent ">

<div class="leftC">

<div class="title">
	<b>All Voices</b>
</div>

<div id="content">
            


<div id="mainbar" class="ask-mainbar">

    <form id="post-form" class="post-form" action="<%=basePath %>/saveVoice" method="post">
        <div class="form-item ask-title">
            <table class="ask-title-table">
                <tbody><tr>
                    <td class="ask-title-cell-key">
                        <label for="title">Title</label>
                    </td>
                    <td class="ask-title-cell-value">
                        <input type="text" maxlength="300" tabindex="100" class="actual-edit-overlay" autocomplete="off" style="opacity: 1; position: absolute; background-color: white; color: black; -webkit-text-fill-color: black; width: 608.8148145675659px; height: 16.814814567565918px; line-height: normal; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13.333333969116211px; text-align: start; border: 1.1111111640930176px solid rgb(153, 153, 153); " disabled="disabled">
                 <input id="title" name="voice.title" type="text" maxlength="300" tabindex="100" class="ask-title-field edit-field-overlayed" autocomplete="off" style="opacity: 0.4; z-index: 1; position: relative; ">                        
                        <span class="edit-field-overlay">what's your programming question? be specific.</span>
                    </td>
                </tr>
            </tbody></table>
            <div id="question-suggestions"></div>
        </div>



        



<script type="text/javascript">
    StackExchange.ready(function() {
        StackExchange.using("tagEditor", function () { StackExchange.tagEditor.ready.done(initFadingHelpText); });
        initTagRenderer("".split(" "), "".split(" "));
         
        prepareEditor({
            heartbeatType: 'ask',
            bindNavPrevention: true,
            postfix: "",
            onDemand: false,
            discardSelector: ".discard-question"
            ,immediatelyShowMarkdownHelp:true
        });
        

    });  
</script>


<div id="post-editor" class="post-editor">  

    
    <div class="wmd-container">
        <div id="wmd-button-bar" class="wmd-button-bar"><ul id="wmd-button-row" class="wmd-button-row"><li class="wmd-button" style="left: 0px; " id="wmd-bold-button" title="Strong &lt;strong&gt; Ctrl+B"><span style="background-position: 0px 0px; "></span></li><li class="wmd-button" style="left: 25px; " id="wmd-italic-button" title="Emphasis &lt;em&gt; Ctrl+I"><span style="background-position: -20px 0px; "></span></li><li class="wmd-spacer wmd-spacer1" id="wmd-spacer1"></li><li class="wmd-button" style="left: 75px; " id="wmd-link-button" title="Hyperlink &lt;a&gt; Ctrl+L"><span style="background-position: -40px 0px; "></span></li><li class="wmd-button" style="left: 100px; " id="wmd-quote-button" title="Blockquote &lt;blockquote&gt; Ctrl+Q"><span style="background-position: -60px 0px; "></span></li><li class="wmd-button" style="left: 125px; " id="wmd-code-button" title="Code Sample &lt;pre&gt;&lt;code&gt; Ctrl+K"><span style="background-position: -80px 0px; "></span></li><li class="wmd-button" style="left: 150px; " id="wmd-image-button" title="Image &lt;img&gt; Ctrl+G"><span style="background-position: -100px 0px; "></span></li><li class="wmd-spacer wmd-spacer2" id="wmd-spacer2"></li><li class="wmd-button" style="left: 200px; " id="wmd-olist-button" title="Numbered List &lt;ol&gt; Ctrl+O"><span style="background-position: -120px 0px; "></span></li><li class="wmd-button" style="left: 225px; " id="wmd-ulist-button" title="Bulleted List &lt;ul&gt; Ctrl+U"><span style="background-position: -140px 0px; "></span></li><li class="wmd-button" style="left: 250px; " id="wmd-heading-button" title="Heading &lt;h1&gt;/&lt;h2&gt; Ctrl+H"><span style="background-position: -160px 0px; "></span></li><li class="wmd-button" style="left: 275px; " id="wmd-hr-button" title="Horizontal Rule &lt;hr&gt; Ctrl+R"><span style="background-position: -180px 0px; "></span></li><li class="wmd-spacer wmd-spacer3" id="wmd-spacer3"></li><li class="wmd-button" style="left: 325px; " id="wmd-undo-button" title="Undo - Ctrl+Z"><span style="background-position: -200px -20px; "></span></li><li class="wmd-button" style="left: 350px; " id="wmd-redo-button" title="Redo - Ctrl+Y"><span style="background-position: -220px -20px; "></span></li><li class="wmd-button wmd-help-button active-help" id="wmd-help-button" style="right: 0px; " title="Markdown Editing Help"><span style="background-position: -240px 0px; "></span></li><div id="mdhelp" class="mdhelp" style="right: -7.9629364013671875px; width: 667px; top: 25px; ">
    <ul id="mdhelp-tabs" class="mdhelp-tabs">
        <li data-tab="mdhelp-links" data-buttons="link">Links</li>
        <li data-tab="mdhelp-images" data-buttons="image">Images</li>
        <li data-tab="mdhelp-styles" data-buttons="bold,italic,heading">Styling/Headers</li>
        <li data-tab="mdhelp-lists" data-buttons="olist,ulist">Lists</li>
        <li data-tab="mdhelp-blockquotes" data-buttons="quote">Blockquotes</li>
        <li data-tab="mdhelp-code" data-buttons="code">Code</li>
        <li data-tab="mdhelp-html">HTML</li>
        <li style="float:right"><a href="/editing-help" target="_blank">advanced help »</a></li>
    </ul>
    
    <div class="mdhelp-tab" id="mdhelp-links">
            <p>
                In most cases, a plain URL will be recognized as such and automatically linked:
            </p>
            <pre>Visit http://area51.stackexchange.com/ regularly!
Use angle brackets to force linking: Have you seen &lt;http://superuser.com&gt;?
</pre>
            <p>
                To create fancier links, use Markdown:
            </p>
                <pre>Here's [a link](http://www.example.com/)! And a reference-style link to [a panda][1].
References don't have to be [numbers][question].

 [1]: http://notfound.stackexchange.com/
 [question]: http://english.stackexchange.com/questions/11481
</pre>
                <p>You can add tooltips to links:</p>
<pre>Click [here](http://diy.stackexchange.com<span class="spaces">&nbsp;</span>"this text appears when you mouse over")!
This works with [reference links][blog] as well.

 [blog]: http://blog.stackoverflow.com/<span class="spaces">&nbsp;</span>"click here for updates"
</pre>
    </div>

    <div class="mdhelp-tab" id="mdhelp-images">
        <p>Images are exactly like links, but they have an exclamation point in front of them:</p>
        <pre>![a busy cat](http://sstatic.net/stackoverflow/img/error-lolcat-problemz.jpg)
![two muppets][1]

 [1]: http://i.imgur.com/I5DFV.jpg "tooltip"
</pre>
        <p>
            The word in square brackets is the alt text, which gets displayed if the browser
            can't show the image. Be sure to include meaningful alt text for screen-reading
            software.
        </p>
   
    </div>
    <div class="mdhelp-tab" id="mdhelp-styles">
        <div class="col1">
            <p>Be sure to use text styling sparingly; only where it helps readability.</p>
                <pre>*This is italicized*, and so
is _this_.

**This is bold**, just like __this__.

You can ***combine*** them
if you ___really have to___.
</pre>
        
        </div>
        <div class="col2">
            <p>
                To break your text into sections, you can use headers:
            </p>
                <pre>A Large Header
==============

Smaller Subheader
-----------------
</pre>
            <p>
                Use hash marks if you need several levels of headers:</p>
    <pre># Header 1 #
## Header 2 ##
### Header 3 ###
</pre>

            
        </div>

    </div>
    <div class="mdhelp-tab" id="mdhelp-lists">
        <p>Both bulleted and numbered lists are possible:</p>
        <div class="col1">
<pre>-<span class="spaces">&nbsp;</span>Use a minus sign for a bullet
+<span class="spaces">&nbsp;</span>Or plus sign
*<span class="spaces">&nbsp;</span>Or an asterisk

1.<span class="spaces">&nbsp;</span>Numbered lists are easy
2.<span class="spaces">&nbsp;</span>Markdown keeps track of
   the numbers for you
7.<span class="spaces">&nbsp;</span>So this will be item 3.
</pre>
        </div>
        <div class="col2">
<pre>1.<span class="spaces">&nbsp;</span>Lists in a list item:
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>-<span class="spaces">&nbsp;</span>Indented four spaces.
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>*<span class="spaces">&nbsp;</span>indented eight spaces.
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>-<span class="spaces">&nbsp;</span>Four spaces again.
2.<span class="spaces">&nbsp;&nbsp;</span>You can have multiple
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>paragraphs in a list items.
<span class="spaces">&nbsp;</span>
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>Just be sure to indent.
</pre>        
        </div>
    </div>

    <div class="mdhelp-tab" id="mdhelp-blockquotes">
        <div class="col1">
            <pre>&gt; Create a blockquote by
&gt; prepending “&gt;” to each line.
&gt;
&gt; Other formatting also works here, e.g.
&gt;
&gt; 1. Lists or
&gt; 2. Headings:
&gt;
&gt; ## Quoted Heading ##
            </pre>
        </div>
        <div class="col2">
                <p>
                    You can even put blockquotes in blockquotes:
                </p>
                <pre>&gt; A standard blockquote is indented
&gt; &gt; A nested blockquote is indented more
&gt; &gt; &gt; &gt; You can nest to any depth.
</pre>
        </div>
    </div>

    <div class="mdhelp-tab" id="mdhelp-code">
        <p>
            To create code blocks or other preformatted text, indent by four spaces:
        </p>
        <pre><span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>This will be displayed in a monospaced font. The first four spaces
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>will be stripped off, but all other whitespace will be preserved.
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>Markdown and HTML are turned off in code blocks:
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;</span>&lt;i&gt;This is not italic&lt;/i&gt;, and [this is not a link](http://example.com)
</pre>
        <p>
            To create not a block, but an inline code span, use backticks:
        </p>
        <pre>Press the `&lt;Tab&gt;` key, then type a `$`.
</pre>
        <p>
            If you want to have a preformatted block within a list, indent by eight spaces:
        </p>
        <pre>1. This is normal text.
2. So is this, but now follows a code block:
<span class="spaces">&nbsp;</span>
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>Skip a line and indent eight spaces.
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>That's four spaces for the list
<span class="spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>and four to trigger the code block.
</pre>
    </div>

    <div class="mdhelp-tab" id="mdhelp-html">
        <p>
            If you need to do something that Markdown can't handle, use HTML. Note that
            <a href="http://meta.stackoverflow.com/questions/1777/what-html-tags-are-allowed" target="_blank">we only support a very strict subset of HTML!</a>
        </p>
        <pre>Strikethrough humor is &lt;strike&gt;funny&lt;/strike&gt;.
</pre>
        <p>
            Markdown is smart enough not to mangle your span-level HTML:
        </p>
        <pre>&lt;b&gt;Markdown works *fine* in here.&lt;/b&gt;
</pre>
        <p>
            Block-level HTML elements have a few restrictions:
        </p>
        <ol>
            <li>They must be separated from surrounding text by blank lines.</li>
            <li>The begin and end tags of the outermost block element must not be indented.</li>
            <li>Markdown can't be used within HTML blocks.</li>
        </ol>
        <br>
        <pre>&lt;pre&gt;
    You can &lt;em&gt;not&lt;/em&gt; use Markdown in here.
&lt;/pre&gt;
</pre>
    </div>

    <div class="cbt"></div>
</div></ul></div>
        <div style="clear: both; height: 23.425918579101563px; "></div><textarea id="wmd-input" class="wmd-input processed" name="voice.Content" cols="92" rows="15" tabindex="101"></textarea>
    <div class="grippie" style="margin-right: 0px; "></div></div>

    <div class="fl" style="margin-top: 8px; height:24px;">&nbsp;</div>
    <div id="draft-saved" class="draft-saved community-option fl" style="margin-top: 8px; height:24px; display:none;">draft saved</div>

    <div id="draft-discarded" class="draft-discarded community-option fl" style="margin-top: 8px; height:24px; display:none;">draft discarded</div>



    <div id="wmd-preview" class="wmd-preview"></div>
    <div></div>
    <div class="edit-block">
        <input id="fkey" name="fkey" type="hidden" value="b7766dc2edb3e0b2d3d146df629e3575">
        <input id="author" name="author" type="text">
    <input type="hidden" name="i1l" value="32MhamccuJLbTdhk5H0BiAQ4q3zV2bTJ3HXATVmUWZg="></div>
</div>

        <script type="text/javascript">
    StackExchange.using("tagEditor", function () {
        initTagRenderer("".split(" "), "".split(" "));
        var tagInput = $("#tagnames");
        StackExchange.tagEditor(tagInput);
    });
</script>	
<div class="form-item">
    <label>Tags</label>
    <input id="tagnames" name="tagnames" type="text" size="60" value="" tabindex="103" style="display: none; "><div class="actual-edit-overlay" style="width: 663.777777671814px; height: 24.777777671813965px; opacity: 1; position: absolute; background-color: white; color: black; -webkit-text-fill-color: black; line-height: 24.777777671813965px; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 13.333333969116211px; text-align: start; border: 1.1111111640930176px solid rgb(153, 153, 153); margin-top: 0px; " disabled="disabled">&nbsp;at least one tag such as (.net c database), max 5 tags</div><div class="tag-editor edit-field-overlayed" style="width: 664.777777671814px; height: 25.777777671813965px; opacity: 0.4; z-index: 1; position: relative; "><span style=""></span><input type="text" tabindex="103" style="width: 656.777777671814px; "><span></span></div>

<span class="edit-field-overlay">at least one tag such as (.net c database), max 5 tags</span>
</div>
        
        
        
        <div id="question-only-section">
<div>
    <h2 class="bottom-notice">
        Would you like to have responses to your questions <a id="inbox-notify-1" href="#">sent to you via email</a>?
    </h2>
</div>
<script type="text/javascript">
    StackExchange.ready(function () {
        $("#inbox-notify-1").click(function () {
            $('html, body').animate({ scrollTop: 0 }, 200);
            StackExchange.ready(function () {
                genuwine.click();
                $("#seTabEmail").click();
            });
            return false;
        });
    });
</script>

            <div class="form-submit cbt">
                <input id="submit-button" type="submit" value="Post Your Question" tabindex="120">
                <a href="#" class="discard-question dno">discard</a>
            </div>
        </div>

    </form>
</div>
<div id="sidebar" class="ask-sidebar">
    <div id="scroller-anchor"></div>
    <div id="scroller" style="position: relative; width: 270px; ">
        <div class="module newuser sidebar-help" id="how-to-title" style="display: block; ">
            <h4>How to Ask</h4>
            <p><b>Is your question about programming?</b></p><p>We prefer questions that can be <i>answered</i>, not just discussed.</p><p>Provide details. Share your research.</p><p>If your question is about this website, <b><a href="http://meta.stackoverflow.com">ask it on meta</a></b> instead.</p><p class="ar"><a href="/faq">read the faq »</a><br><a href="/questions/how-to-ask">asking help »</a></p>
        </div>
        <div class="module newuser sidebar-help" id="how-to-format" style="display: none; ">
    <h4>How to Format</h4>    
    <p><span class="dingus">►</span> put returns between paragraphs</p>
    <p><span class="dingus">►</span> for linebreak add 2 spaces at end</p>
    <p><span class="dingus">►</span> <i>_italic_</i> or <b>**bold**</b></p>
    <p><span class="dingus">►</span> indent code by 4 spaces</p>
    <p><span class="dingus">►</span> backtick escapes <code>`like _so_`</code></p>
    <p><span class="dingus">►</span> quote by placing &gt; at start of line</p>
    <p><span class="dingus">►</span> to make links</p>
    <p>&lt;http://foo.com&gt;<br>[foo](http://foo.com)<br>&lt;a href="http://foo.com"&gt;foo&lt;/a&gt;</p>
    <p><span class="dingus">►</span> <a href="http://meta.stackoverflow.com/questions/1777/what-html-tags-are-allowed" target="_blank">basic HTML</a> also allowed</p>
    <p class="ar">
    <a href="/editing-help" target="_edithelp">formatting help »</a><br>
    <a href="/questions/how-to-ask">asking help »</a>
    </p>
</div>
        <div class="module newuser sidebar-help" id="how-to-tag" style="display: none; ">
    <h4>How to Tag</h4>
    <p>A tag is a keyword or label that categorizes your question with other, similar questions.</p>
    <p><span class="dingus">►</span> favor existing popular tags; avoid creating new tags</p>
    <p><span class="dingus">►</span> use common abbreviations</p>
    <p><span class="dingus">►</span> don't include synonyms</p>
    <p><span class="dingus">►</span> combine multiple words into single-words with dashes</p>
    <p><span class="dingus">►</span> maximum of 5 tags, 25 chars per tag</p>
    <p><span class="dingus">►</span> tag characters: [a-z 0-9 + # - .]</p>
    <p><span class="dingus">►</span> delimit tags by space, semicolon, or comma</p>
    <p class="ar"><a href="/tags">popular tags »</a></p>
</div>    
    </div>
    <script type="text/javascript">
        StackExchange.ready(function () { moveScroller(); });
        StackExchange.using("editor", function () {
                        
            var lengthCheckInterval = setInterval(function () {
                if ($("#wmd-input").val().length >= 220) {
                    clearInterval(lengthCheckInterval);
                    StackExchange.cardiologist.beatASAP();
                }
            }, 3000);
        });
        

    </script>
        
</div>

<br class="cbt">
<div style="display:none" id="prettify-lang"></div>

        </div>





</div>


<div class="rightContent">
	<table >
		<tr >
			<td align="center"><b class="summarycount">123</b></td>
		</tr>
		<tr >
			<td align="center"><span class="rsp">voices with <b>no answered</b></span></td>
		</tr>
		<tr ><td height="30"></td></tr>
		<tr >
			<td >
				<b class="voiceTypeTitle">Voice type</b><br>
				<br><a href="#" class="someType">type1</a> x 200<br>
				<br><a href="#" class="someType">type2</a> x 200<br>
				<br><a href="#" class="someType">type3</a> x 200<br>
				<br><a href="#" class="someType">type4</a> x 200<br>
				<br><a href="#" class="someType">type5</a> x 200<br>
				<br><a href="#" class="someType">type6</a> x 200<br>
				<br><a href="#" class="someType">type7</a> x 200<br>
				<br><a href="#" class="someType">type8</a> x 200<br>
			</td>
		</tr>
	</table>	
</div>

</div>

<div style="height: 100px; background-color: #777777;"></div>

</body>
</html>