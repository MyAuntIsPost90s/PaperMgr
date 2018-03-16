var AjaxHelper={
	formLoad:function(id,url,loadsuccesscall){
		var $elem=$('#'+id);
		$elem.form({
        	onLoadSuccess:function(data){
        		if(data.code==0){
        			$.messager.alert("错误",data.msg);
        			return;
        		}
        		if(loadsuccesscall!=null)
        			loadsuccesscall();
        		elem.form('load',data.data)
        	}
        });
		elem.form('load',Config.UrlHead+"/Users/NowUser?appKey="+Config.AppKey);
	}
}