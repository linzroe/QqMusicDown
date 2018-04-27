$(document).ready(function() {
    // 初始化表格
    initTable();
    console.log("版权所有：双木's Blog |  Https://Y.09L.Me")
    
    
    
});

 
 

function palys(titles,authors,urls,imgs,lyrs){
	
	var settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "qqmusic/lyric/"+lyrs+".lrc",
			  "method": "GET",
			  "headers": {
			    "cache-control": "no-cache",
			    "postman-token": "90212d5a-0ae8-bc07-15e9-374d075f6685"
			  }
			}

			$.ajax(settings).done(function (response) {
			  var ap1 = new APlayer({
			        element: document.getElementById('player1'),
			        narrow: false,
			        autoplay: true,
			        showlrc: true,
			        music: {
			            title: titles,
			            author: authors,
			            url: urls,
			            pic: imgs,
			            lrc: response
			        }
			    });
			  
			});
	
   
}


// 表格初始化
function initTable() {
    $('#eventTable').bootstrapTable({
        method: 'post', // 向服务器请求方式
        contentType: "application/x-www-form-urlencoded", // 如果是post必须定义
        url: 'qqmusic/soso', // 请求url
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        striped: true, // 隔行变色
        dataType: "json", // 数据类型
        pagination: true, // 是否启用分页
        classes: 'table table-bordered', // Class样式
        silent: true, // 必须设置刷新事件
        toolbar: '#toolbar', // 工具栏ID
        toolbarAlign: 'right', // 工具栏对齐方式
        queryParams: queryParams, // 请求参数，这个关系到后续用到的异步刷新
        columns: [
        {field: "check", title: "",align: "center", checkbox: true,formatter:stateFormatter},	
        {
            field: 'name',
            title: '歌名',
            align: 'center'
        }, {
            field: 'singer',
            title: '歌手',
            align: 'center'
        }, {
            field: 'album',
            title: '专辑',
            align: 'center'
        }, {
        	field: 'img',
        	title: 'img',
        	visible:false
        }, {
        	field: 'lyr',
        	title: 'lyr',
        	visible:false
        },{
            field: 'MP3',
            title: '试听',
            align: 'center',
            width: '80px',
            formatter: function(value, row, index) {
            	var mycars=new Array()
            	mycars[0]="\""+row["name"]+"\"";
            	mycars[1]="\""+row["singer"]+"\"";
            	mycars[2]="\""+value+"\"";
            	mycars[3]="\""+row["img"]+"\"";
            	mycars[4]="\""+row["lyr"]+"\"";
            	return "<button onclick='palys("+mycars+")' type='button' class='btn btn-success  btn-sm'>试听</button>";
            }
        }
        ,{
            field: 'HD',
            title: '高质',
            align: 'center',
            width: '80px',
        	visible:false,
            formatter: function(value, row, index) {
                return "<a href='"+value+"'  target='_blank'>直达</a>";
            }
        },{
            field: 'FALC',
            title: '无损',
            align: 'center',
            width: '80px',
        	visible:true,
            formatter: function(value, row, index) {
                return "<a href='"+value+"'  target='_blank'>直达</a>";
            }
        }
        ],
    });
}


function stateFormatter(value, row, index) {
    if (row.id == 561)
        return {
            disabled : true,//设置是否可用
            checked : true//设置选中
        };
    return value;
}

// 分页查询参数，是以键值对的形式设置的
function queryParams(params) {
    return {
        name: document.getElementById("name").value // 请求时向服务端传递的参数
    }
}




// 搜索按钮触发事件
$(function() {
    $("#eventquery").click(function() {
        $('#eventTable').bootstrapTable(('refresh')); // 很重要的一步，刷新url！
    });
   
});

$("#copyhd").click(function() {
		var row=$.map($("#eventTable").bootstrapTable('getSelections'),function(row){
			return row ;
		});
	 
 		for(var i=0;i<row.length;i++){
 			document.getElementById("urltext").value += row[i].HD+"\n";
 		}
		
});

$("#copyfalc").click(function() {
	var row=$.map($("#eventTable").bootstrapTable('getSelections'),function(row){
		return row ;
	});
 
	for(var i=0;i<row.length;i++){
 		document.getElementById("urltext").value += row[i].FALC+"\n";
		}
	
});


var clipboard = new ClipboardJS('.btn');

clipboard.on('success', function(e) {
    console.info('Action:', e.action);
    console.info('Text:', e.text);
    console.info('Trigger:', e.trigger);
    e.clearSelection();
    alert("复制成功");
});

clipboard.on('error', function(e) {
    console.error('Action:', e.action);
    console.error('Trigger:', e.trigger);
    alert("复制失败");
});

$('#name').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        $('#eventquery').click();
    }
});