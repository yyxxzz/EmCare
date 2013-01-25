$(function() {
	var editor = KindEditor.create(
		'#kind_editor',
		{
		   items:[ 'preview', 'cut', 'copy', 'paste', '|',
			        'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', '|', 'undo', 'redo', 'selectall',  '/',
			        'formatblock', 'fontname', 'fontsize',  'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
			         'emoticons', 
			        'link', 'unlink'
			],
			newlineTag:"br",
			uploadJson:'uploadImg',
			afterChange:function(){
				this.sync();
			},
			langType:'en'
		}
	);
	
});