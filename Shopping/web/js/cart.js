window.onchange= function(evt) {//当输入时
    var txt=evt.target,//放回目标节点
        wid=txt.parentElement.parentElement.cells[0].dataset.code,
        ajax=new XMLHttpRequest();
    ajax.open("get", "modifyAmount?w="+wid+"&n="+txt.value);
    ajax.onload=function() {
        location.reload(true);
    };
    ajax.send();
};
