FormUtil = {
    getFormMap: function (id) {
        var map = {};

        //获取input
        var inputs = $(id).find('input');
        for (var i = 0; i < inputs.length; i++) {
            var elem = $(inputs[i]);
            if (!FormUtil.valid(elem)) {
                return;
            }
            var name = elem.attr('name');
            var val = elem.val();
            if (elem.attr('type') == 'radio') {
                val = $('input[name=' + name + ']:checked').val();
            }
            map[name] = val;
        }
        //获取select
        var selects = $(id).find('select');
        for (var i = 0; i < selects.length; i++) {
            var elem = $(selects[i]);
            if (!FormUtil.valid(elem)) {
                return;
            }
            var name = elem.attr('name');
            var val = elem.val();
            map[name] = val;
        }

        return map
    },
    loadForm: function (id, data) {
        //获取input
        var inputs = $(id).find('input');
        for (var i = 0; i < inputs.length; i++) {
            var elem = $(inputs[i]);
            var name = elem.attr('name');
            if (elem.attr('type') == 'radio' && data[name] != null) {
                if (elem.val() == data[name]) {
                    elem.attr('checked', 'checked');
                }
            }
            else if (data[name] != null) {
                elem.val(data[name]);
            }
        }
        //获取select
        var selects = $(id).find('select');
        for (var i = 0; i < selects.length; i++) {
            var elem = $(inputs[i]);
            var name = elem.attr('name');
            if (data[name] != null) {
                elem.val(data[name]);
            }
        }
    },
    valid: function (elem) {
        var val = elem.val();
        var validstr = elem.attr('data-valid');
        if (validstr != null && validstr != '') {
            var valid = eval('({' + validstr + '})');
            if (val.length < valid.length[0] || val.length > valid.length[1]) {
                layer.alert(valid.name == null ? '' : valid.name + '长度因在' + valid.length[0] + '到' + valid.length[1] + '之间', {
                    anim: 6,
                    icon: 5,
                    time: 2000
                });
                elem.focus();
                return false;
            }
        }
        return true;
    }
}

//编辑器加载器
var EditModel = {
    build: function (id, option) {
        var layedit = layui.layedit;
        layedit.set({
            uploadImage: {
                url: Config.UrlHead + '/uploader/uploadImg' //接口url
                , type: 'post' //默认post
            }
        });
        return layedit.build(id, option); //建立编辑器
    },
    setContent: function (tag, val) {   //设置值
        try {
            var layedit = layui.layedit;
            layedit.setContent(tag, val);
        } catch (err) {
            //不做处理
        }
    },
    getContent: function (tag) {   //获取值
        var layedit = layui.layedit;
        return layedit.getContent(tag);
    }
}
