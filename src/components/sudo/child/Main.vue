<template>
  <div class="main">
  		<div class="sudoMain" >
  			<Row class="numRow" type="flex" v-for="(row,i) in arr" justify="center">    		
    			<i-col class="numCol" v-for="(cell,j) in row" :data-x='i'  :data-y='j'><div class="spanDiv" @click="cellClick($event,i,j)">{{arr[i][j]==0?"":arr[i][j]}}</div></i-col>   		
    		</Row>	
  		</div>   	
    	<div class="footer">
	    	<div class="buttons">
	    		<i-button type="success" @click="checkedSudo">检查</i-button>
	    		<i-button type="success" @click="resetSudo">重玩</i-button>
	    		<i-button type="error" @click="resetModal=true">换题</i-button>
	    	</div>
 		</div>
 		<Modal v-model="resetModal" title="该局还没结束是否换题？" @on-ok="replaceSudo(this)">
											        <p>该局还没结束是否换题？</p>
		</Modal>
    	<div class="selectPop" v-show="showSelectPop">
    		<Row class="selectRow" type="flex" v-for="i in 3">
    			<i-col class="selectCell"  v-for="j in 3"><div @click="selectClick($event,i,j)">{{(i-1)*3+j}}</div></i-col>
    		</Row>
    	</div>
  </div>
</template>

<script>
	
import sudoUtils from '@/utils/sudoUtil'	
	
export default {
	name: 'main',
	props:{
		arr:{
			type:Array,
			require:false,
		}
	},
	data() {
		return {
			selectX:0,
			selectY:0,
			showSelectPop:false,
			resetModal:false,
		}
	},
	created: function() {
		console.log(this.arr);
	},mounted:function(){
		 
		 
		 
	},
	methods: {
		cellClick(e,i,j) {
			let ele=e.target;
			if(this.showSelectPop==true){
				let updateEle=document.getElementsByClassName("update");
				let goalEle="";
				for(let k=0;k<updateEle.length;k++){
					let nowEle=updateEle[k];
					let x=nowEle.parentNode.getAttribute("data-x");
					let y=nowEle.parentNode.getAttribute("data-y");
					if(x==this.selectX&&y==this.selectY)goalEle=nowEle;
				}		
				goalEle.setAttribute("class","spanDiv")
			}

			if(this.arr[i][j]==0||ele.getAttribute("class")=="spanDiv update"){
				this.selectX=i;
				this.selectY=j;
				ele.setAttribute("class","spanDiv update");
				this.showSelectPop=true;
				let selectPopEle=document.getElementsByClassName("selectPop");
				let gong=sudoUtils.getSiteFromIndex(i,j);
				let topNum=Math.floor((gong-1)/3)*90;
				let leftNum=15+((gong-1)%3)*90;
				selectPopEle[0].setAttribute("style","top:"+topNum+"px;left:"+leftNum+"px");			
			}else{
				this.showSelectPop=false;
			}
		},
		selectClick(e,i,j){
			this.showSelectPop=false;
			this.arr[this.selectX][this.selectY]=(i-1)*3+j;					
		},
		checkedSudo(){			
			let sign=sudoUtils.checkSudoArray(this.arr);
			if(sign){
				this.$Message.success("恭喜你成功");
			}else{
				this.$Message.error("请再检查");
			}
		},
		resetSudo(){
			let updateEle=document.getElementsByClassName("update");
			let updateNum=new Number(updateEle.length);
			for(let i=0;i<updateNum;i++){	
				let nowEle=updateEle[0];
				let x=nowEle.parentNode.getAttribute("data-x");
				let y=nowEle.parentNode.getAttribute("data-y");
				this.arr[x][y]=0;
				this.showSelectPop=true;
				nowEle.setAttribute("class","spanDiv");
				this.showSelectPop=false;
			}
			
		},
		replaceSudo(){
			
			this.resetSudo();			
			this.$emit('replaceSudo',{})
			this.showSelectPop=true;
			this.showSelectPop=false;
		}
		
		
	}
}</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.sudoMain{
	height:300px;
}
	
.selectPop{
	width:90px;
	height:90px;
	position: absolute;
	top:15px;
	left:15px;
	z-index: 1;
}

.selectCell{
	width:30px;
	height:30px;
	line-height:30px;
	font-size: 20px;
	border-bottom: 1px solid black;
	border-right: 1px solid black;
	color:yellow;
	background-color:#00BFFF;
}
	
.numRow {
	width: 100%;
	height: 30px;
}

.update{
	color:#00f;
	background-color:#FEAE1B ;
}

.numCol {
	width: 30px;
	height: 30px;
	font-size: 20px;
	line-height: 30px;
	color: #000000;
	/*background-color: antiquewhite;*/
	border-bottom: 1px solid black;
	border-right: 1px solid black;
	/*cursor: pointer;*/
}

.numCol[data-x='0'] {
	border-top: 2px solid black;
}

.numCol[data-y='0'] {
	border-left: 2px solid black;
}

.numCol[data-x='3'],
.numCol[data-x='6'] {
	border-top: 1px solid black;
}

.numCol[data-x='8'] {
	border-bottom: 2px solid black;
}

.numCol[data-y='3'],
.numCol[data-y='6'] {
	border-left: 1px solid black;
}

.spanDiv{
	height:100%;
	width:100%;
}

.numCol[data-y='8'] {
	border-right: 2px solid black;
}

.buttons{
	height:100%;
	width:100%;
}

</style>
