//根据序号检查行
const checkedRow = (arr, n) => checkArray(arr[n]);

//检查一维数组
const checkArray = arr => arr.filter(val => val != 0).filter(i => arr.indexOf(i) === arr.lastIndexOf(i)).toString() == arr.filter(val => val != 0).toString();

//根据序号检查列		
const checkedCol = (arr, n) => checkArray(Array.from(arr, row => row[n]));

//根据序号检查宫
const checkedGongByNum=(arr,num)=>{
	
	let y = ((num - 1) % 3) * 3 + 1;

	let x = Math.floor((num - 1) / 3) * 3 + 1;

	let narr = [arr[x - 1][y - 1], arr[x - 1][y], arr[x - 1][y + 1], arr[x][y - 1], arr[x][y], arr[x][y + 1], arr[x + 1][y - 1], arr[x + 1][y], arr[x + 1][y + 1]];

	return checkArray(narr);
	
}

//根据坐标检查宫
const checkedGongByIndex = (arr, i, j) => {

	let num = gong(i, j);

	return checkedGongByNum(arr,num);

}


//根据坐标检查九宫格
const checkArrayByIndex = (arr, x, y) => {

	let signRow = false;

	let signCol = false;

	let signGong = false;

	//检查行								
	signRow = checkedRow(arr, x);

	//检查列
	signCol = checkedCol(arr, y);

	//检查宫
	signGong = checkedGongByIndex(arr, x, y);

	if(signRow && signCol && signGong) {
		return true;
	} else {
		return false;
	}

}

//检查二维数组是否含有0
const checkArrayHasZero = arr => {

	let sign = false;

	arr.map(row => row.map(cell => {
		if(!cell) sign = true;
	}))

	return sign;

}

export default {
	
	//直接生成二维数组数独
	getSudoArray(){
		
		let arr=initArray(9,0);
		
		return makeArray(arr,0);
		
	},
	getHiddenSudoArray(arr,n){
		
		return resetZeroByArray(arr,n);
		
	},
	
	//判断二维数组是否是完整数独
	checkSudoArray(arr){				
		
		let sign=true;
		
		//检查数组中是否含有0，如果有0立刻返回false
		if(checkArrayHasZero(arr))return false;
		
		//对每一行每一列每一宫进行检查
		for(let i=0;i<9;i++){
			
			let rowSign=checkedRow(arr,i);
			let colSign=checkedCol(arr,i);
			let gongSign=checkedGongByNum(arr,i+1);
			if(!(rowSign&&colSign&&gongSign)){
				sign=false;
			};
			
		}
		
		return sign;
		
	}

	
}

//生成数独
const makeArray=(arr, n)=>{

		if(n == 81) {
			return arr;
		}

		let x = Math.floor(n / 9);
		let y = n % 9;

		if(arr[x][y] == 0) {

			let index = 0;

			let arrn = makeRandomArray(9);
			for(let i = 0; i < arrn.length; i++) {

				arr[x][y] = arrn[i];
				if(checkArrayByIndex(arr, x, y)) {
					let endArr = makeArray(arr, n + 1);
					if(!checkArrayHasZero(arr)) return endArr;
				}

			}

			arr[x][y] = 0; //回溯

		} else {
			makeArray(arr, n + 1);
		}

		return arr;
	}


//对二维数组进行随机n位置0
const resetZeroByArray=(arr,n)=>{
	
	let k=0;
	
	while(k<n){
		
		let ranNum=randomNumber(81);
		
		let x = Math.floor(ranNum / 9);
		
		let y = ranNum % 9;
		
		if(arr[x][y]!=0){
			arr[x][y]=0;
			k++;
		}	
	}
	
	return arr;
	
}

//根据坐标判断第几个宫
const gong = (i, j) => {

	if(i < 3 && j < 3) return 1;
	if(i < 3 && j < 6) return 2;
	if(i < 3 && j < 9) return 3;
	if(i < 6 && j < 3) return 4;
	if(i < 6 && j < 6) return 5;
	if(i < 6 && j < 9) return 6;
	if(i < 9 && j < 3) return 7;
	if(i < 9 && j < 6) return 8;
	if(i < 9 && j < 9) return 9;

}



//生成1到n的不重复的数组
const makeRandomArray = n => {

	let arr = new Array(n);

	for(let i = 0; i < n; i++) {

		let index = 0;

		let val = "";

		do {

			val = randomNumber(n);

			index = arr.indexOf(val);

		} while (index != -1)

		arr[i] = val;

	}

	return arr;

}



//随机生成1到n的数字	
const randomNumber = n => Math.floor(Math.random() * n + 1);

//初始化一维数组		
const initArrayRow = (n, value) => new Array(n).fill(value);

//初始化二维数组
const initArray = (n, value) => Array.from(initArrayRow(n, value), () => initArrayRow(n, value));
