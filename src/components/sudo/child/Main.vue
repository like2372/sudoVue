<template>
  <div class="main">
    	<Row class="numRow" type="flex" v-for="(row,i) in arr" justify="center">    		
    		<i-col class="numCol"  v-for="(cell,j) in row" :data-x='i'  :data-y='j'><div class="spanDiv" @click="cellClick($event,i,j)">{{arr[i][j]==0?"":arr[i][j]}}</div></i-col>   		
    	</Row>
    	<div class="selectPop" v-show="showSelectPop">
    		<Row class="selectRow" type="flex" v-for="i in 3">
    			<i-col class="selectCell"  v-for="j in 3"><div @click="selectClick($event,i,j)">{{(i-1)*3+j}}</div></i-col>
    		</Row>
    	</div>
  </div>
</template>

<script>
	
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
		}
	},
	created: function() {
		
	},mounted:function(){
		 
	},
	methods: {
		cellClick(e,i,j) {
			if(this.arr[i][j]==0){
				this.selectX=i;
				this.selectY=j;
				this.showSelectPop=true;
			}else{
				this.showSelectPop=false;
			}
		},
		selectClick(e,i,j){
			this.showSelectPop=false;
			this.arr[this.selectX][this.selectY]=(i-1)*3+j;
			//this.$emit('arrayChange',{})
		}
		
	}
}</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	
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

.numCol {
	width: 30px;
	height: 30px;
	font-size: 20px;
	line-height: 30px;
	color: deepskyblue;
	background-color: antiquewhite;
	border-bottom: 1px solid black;
	border-right: 1px solid black;
	cursor: pointer;
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
}</style>
