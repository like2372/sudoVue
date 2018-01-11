

	import sudoUtil from './sudoUtil'
	
	//grid类
	//根据二维数组动态生成grid
	class Grid{
		
		constructor(array){
			this.array=array;
		}
		
		build(){
			this.array.map(rowArray=>rowArray.map(cellValue=>$("#main").append($("<div class='ndiv'>").append($("<span>").text(cellValue)))));
		}
	
	}
	
	//主方法
	$(()=>{
		
	var g=new Grid(sudoUtil.makeArray(sudoUtil.initArray(9,0),0));
	
	g.build();	
	
	})
	