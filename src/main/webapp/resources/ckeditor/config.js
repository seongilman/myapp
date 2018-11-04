/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';

	/** Enter <p></p>를 사용하지 않고 <br>을 사용함 */
	config.enterMode = CKEDITOR.ENTER_BR;
	/** 에디터 리사이징 */
//	config.resize_enabled = false;
	/** 에디터 오토 사이징 (Plug-in) /resources/ckeditor/plugins/autogrow */
	config.extraPlugins = 'autogrow';		// autogrow 플러그인 허용
//	config.autoGrow_minHeight = 250;	// 최소 사이즈
	config.autoGrow_maxHeight = 480;	// 최대 사이즈
	/** 이미지 업로드 (설정하면 이미지 업로드에 업로드 탭이생김), 현재는 스프링 시큐리티 CSRF설정으로 인해 페이지에서 설정함 */
//	config.filebrowserImageUploadUrl = '/myapp/file/upload/ckeditor';	//이미지 업로드 경로
//	config.filebrowserUploadUrl = '/myapp/file/upload/aaa';
//	config.filebrowserBrowseUrl = '/myapp/file/upload/bbb';	// 링크 탭 등 다른 정보 탭의 서버보기 URL
//	config.filebrowserImageBrowseUrl = '/myapp/file/upload/bbb';	// 이미지 탭의 서버보기 URL
	/** 이미지 업로드 미리보기 창에 설명글 표시 */
	config.image_previewText = ' ';
	/** 툴바를 접는 설정 */
	config.toolbarCanCollapse = true;
	/** video , embed 등 막힌 태그를 허용하게 하는 설정 */
	config.extraAllowedContent = 'video[*]{*};source[*]{*}';
	/** 시작시 포커스 설정 */
	config.startupFocus = false; 
	/** 하단 Bar HTML Tag표시 */
	config.removePlugins = 'elementspath';
	
//	config.removeContents('advanced'); // 자세히탭 제거
//	config.removeContents('Link'); // 링크탭 제거
//	config.removeDialogTabs = 'image:Link';
//	config.removeDialogTabs = 'image:advanced';
	config.removeDialogTabs = 'link:target;link:advanced;image:Link;image:advanced';

	//	 config.linkShowAdvancedTab = false;
//	  config.linkShowTargetTab = false;
	
	/** 사용자 툴바 (기본 사용 시 모두 주석 처리) */
	config.toolbar = [
	    {name: 'clipboard', items: ['Source', '-', 'NewPage', 'Preview', '-', 'Templates', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo', 'Find', 'Replace']},
	    {name: 'basicstyles', items: ['Bold' ,'Italic' ,'Underline' ,'Strike' , 'Subscript' ,'Superscript' , '-']},
	    {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote']},
	    '/',
	    {name: 'aaaaa', items: ['AlignLeft', 'AlignCenter', 'AlignRight', 'Justify', 'Link', 'Unlink']},
	    {name: 'insert', items: ['Image', 'Table', 'HorizontalRule', 'SpecialChar']},
	    {name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize', 'TextColor', 'BGColor', 'Maximize', 'ShowBlocks']}
	];
};
