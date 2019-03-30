var $winW = $(window).width();
var $winH = $(window).height();
var tp;
//
function GET_WINDOW_WIDTH_TYPE() {
	var winW = $(window).width();
	tp = "";
	if (winW < 769)
		tp = "MOBILE";
	else
		tp = "PC";
	return tp;
}
$(function() {
	$(window).on("load resize", function(e) {
		tp = GET_WINDOW_WIDTH_TYPE();
	});
});

(function(){ // 외부 라이브러리와 충돌을 막고자 모듈화.
	// 브라우저 및 버전을 구하기 위한 변수들.
	'use strict';
	var agent = navigator.userAgent.toLowerCase(),
		name = navigator.appName,
		browser;

	// MS 계열 브라우저를 구분하기 위함.
	if(name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edge/') > -1) {
		browser = 'ie';
		$("#nav").addClass("msie");//익스에서 nav css클래스 추가
		if(name === 'Microsoft Internet Explorer') { // IE old version (IE 10 or Lower)
			agent = /msie ([0-9]{1,}[\.0-9]{0,})/.exec(agent);
			browser += parseInt(agent[1]);
		} else { // IE 11+
			if(agent.indexOf('trident') > -1) { // IE 11
				browser += 11;
			} else if(agent.indexOf('edge/') > -1) { // Edge
				browser = 'edge';
			}
		}
	} else if(agent.indexOf('safari') > -1) { // Chrome or Safari
		if(agent.indexOf('opr') > -1) { // Opera
			browser = 'opera';
		} else if(agent.indexOf('chrome') > -1) { // Chrome
			browser = 'chrome';
		} else { // Safari
			browser = 'safari';
		}
	} else if(agent.indexOf('firefox') > -1) { // Firefox
		browser = 'firefox';
	}
	// IE: ie7~ie11, Edge: edge, Chrome: chrome, Firefox: firefox, Safari: safari, Opera: opera
	document.getElementsByTagName('html')[0].className = browser;
}());

/* default */
var scrollTop = $(window).scrollTop();

var filter = "win16|win32|win64|mac|macintel";
//OS사용에 따른 레이어 체크

function $treeview(){
	$('.treeview > a').on('click',function(){
		if($(this).parent('.treeview').hasClass('menu-open')!=true){
			if($(this).parent('li').parent('ul').hasClass('sidebar-menu')==true){
				$('.sidebar-menu > .treeview').stop().removeClass('menu-open');
			}
			$(this).parent('.treeview').stop().addClass('menu-open');
			if($('body').hasClass('sidebar-mini')!=true){
				$(this).next('.treeview-menu').stop().slideDown();
			}else{
				$('.treeview-menu').stop().hide();
				$(this).next('.treeview-menu').stop().show();
			}
		}else{
			$(this).parent('.treeview').stop().removeClass('menu-open');
			if($('body').hasClass('sidebar-mini')!=true){
				$(this).next('.treeview-menu').stop().slideUp();
			}else{
				$(this).next('.treeview-menu').stop().hide();
			}
		}
	});
}
$treeview();

function $sideBar_toggle(){
	$('.sidebar-toggle').on('click',function(){
		if(tp=="PC"){
			if($('body').hasClass('sidebar-mini')!=true){
				$('.treeview').stop().removeClass('menu-open');
				$('.treeview-menu').stop().hide();
			}
			$('body').stop().toggleClass('sidebar-mini');
		}else if(tp=="MOBILE"){
			if($('body').hasClass('sidebar-mini')==true){
				$('body').stop().removeClass('sidebar-mini');
			}
			$('body').stop().toggleClass('sidebar-open');
		}
	});
}
$sideBar_toggle();

function $Rsidebar_toggle(){
	$('.btn_Rsidebar').on('click',function(){
		$('.control-sidebar').stop().toggleClass('control-sidebar-open');
	});
}
$Rsidebar_toggle();

//모달레이어팝업
// touch
function lock_touch(e){
	e.preventDefault();
}
function layerPopup(target){
	$('.layer-content').css("marginTop", 0);
	var $layerContent = $(target).find($('.layer-content'));
	$(target).css({'overflow': 'auto'}).show().addClass('open');
	var $layerContentH = $(target).find($('.layer-content')).height() + 40;
	var $conPos = ($winH / 2) - ($layerContentH / 2);
	$('html').css({'overflow' : 'hidden','height' : $winH});
	if( $winH > $layerContentH ){
		$layerContent.css({marginTop: $conPos});
	} else {
		$layerContent.css({marginTop: 0});
	}
	$("<div class='overlay'></div>").appendTo('.wrapper');
	return false;
};

//모달레이어팝업닫기
function layerPopupClose(target){
	//document.removeEventListener('touchmove', lock_touch);
	if (document.removeEventListener) {
		document.removeEventListener('touchmove', lock_touch);
	}
	else {
		document.detachEvent('touchmove', lock_touch);
	}
	$(target).find($('.layer-content')).css('margin-top',0);
	$(target).hide().removeClass('open');
	$(".overlay").remove();
	$('html').css({'overflow' : 'scroll','height' : 'auto'});
};

//페이지 정보 출력
$(document).ready(function(){
	console.log($(location).attr('href'));
	console.log($(location).attr('protocol'));
	console.log($(location).attr('host'));
	console.log($(location).attr('pathname'));
	console.log($(location).attr('search'));
	
	var	sRetrunUrl		=	$(location).attr('href');
	var	sRetrunParams	=	$(location).attr('search');
	
	$('input[name=i_sGetRetrunUrl]').val(sRetrunUrl);
	$('input[name=i_sGetRetrunParams]').val(sRetrunParams);
});