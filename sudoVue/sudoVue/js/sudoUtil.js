	
	var sudoUtil=new Object({
		
		//检查行
		const checkedRow=(arr,n)=>checkArray(arr[n]);
		
		//检查数组
		const checkArray=arr=>arr.filter(val=>val!=0).filter(i=>arr.indexOf(i)===arr.lastIndexOf(i)).toString()==arr.filter(val=>val!=0).toString();	
				
		//检查列		
		const checkedCol=(arr,n)=>checkArray(Array.from(arr,row=>row[n]));
				
		//检查数组是否含有0
		const checkArrayHasZero=arr=>{
			
			let sign=false;
			
			arr.map(row=>row.map(cell=>{
				if(!cell)sign=true;
			}))
			
			return sign;
			
		}
		
		//检查宫
		const checkedGong=(arr,i,j)=>{
			
			let num=gong(i,j);
			
			let y=((num-1)%3)*3+1;
			
			let x=Math.floor((num-1)/3)*3+1;

			let narr=[arr[x-1][y-1],arr[x-1][y],arr[x-1][y+1],arr[x][y-1],arr[x][y],arr[x][y+1],arr[x+1][y-1],arr[x+1][y],arr[x+1][y+1]];
			
			return checkArray(narr);
			
		}
		
		//根据坐标检查九宫格
		const checkArrayByIndex=(arr,x,y)=>{
			
			let signRow=false;
							
			let signCol=false;
							
			let signGong=false;
			
			//检查行								
			signRow=checkedRow(arr,x);
			
			//检查列
			signCol=checkedCol(arr,y);
			
			//检查宫
			signGong=checkedGong(arr,x,y);
									
			if(signRow&&signCol&&signGong){
				return true;
			}else{
				return false;
			}			
				
		}
		
		//生成数独
		const makeArray=(arr,n)=>{
					
			if(n==81){
				return arr;
			}
			
			let x=Math.floor(n/9);
			let y=n%9;
			
			if(arr[x][y]==0){
								
				let index=0;
				
				let arrn=makeRandomArray(9);
				for(let i=0;i<arrn.length;i++){
					
					arr[x][y]=arrn[i];				
					if(checkArrayByIndex(arr,x,y)){
						let endArr=makeArray(arr,n+1);
						if(!checkArrayHasZero(arr))return endArr;
					}
					
				}
						
				arr[x][y]=0;//回溯
				
			}else{
				makeArray(arr,n+1);
			}
			
			return arr;
		}
		
		//根据坐标判断第几个宫
		const gong=(i,j)=>{
			
			if(i<3&&j<3)return 1;
			if(i<3&&j<6)return 2;
			if(i<3&&j<9)return 3;
			if(i<6&&j<3)return 4;
			if(i<6&&j<6)return 5;
			if(i<6&&j<9)return 6;
			if(i<9&&j<3)return 7;
			if(i<9&&j<6)return 8;
			if(i<9&&j<9)return 9;
			
		}
		
		//生成1到n的不重复的数组
		const makeRandomArray=n=>{
			
			let arr=new Array(n);
			
			for(let i=0;i<n;i++){
				
				let index=0;
				
				let val="";
				
				do{
					
					val=randomNumber(n);
					
					index=arr.indexOf(val);
					
				}while(index!=-1)
				
				arr[i]=val;
				
			}
			
			return arr;
			
		}
		
		//随机生成1到n的数字	
		const randomNumber=n=>Math.floor(Math.random()*n+1);
				
		//初始化一维数组		
		const initArrayRow=(n,value)=>new Array(n).fill(value);
		
		//初始化二维数组
		const initArray=(n,value)=>Array.from(initArrayRow(n,value),()=>initArrayRow(n,value));
	
	});
	
	export default sudoUtil ;