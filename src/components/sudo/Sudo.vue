<template>
  <div class="sudo">
  	 <router-view name='head'/>
  	 <router-view name='main' :arr="arr" @arrayChange="arrayChange"/>
     <router-view name='footer' :arr="arr"/>
  </div>
</template>

<script>
	
import sudoUtils from '@/utils/sudoUtil'
	
export default {
  name: 'Sudo',
  data () {
    return {
      arr:[],
    }
  },created:function(){
  		this.arr = sudoUtils.getSudoArray();
			sudoUtils.getHiddenSudoArray(this.arr, 46);  		
  },methods:{	
  		arrayChange(){
  			let sign=sudoUtils.checkSudoArray(this.arr);
  			alert(sign);
  		}, 		
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.sudo{
	max-width:300px;
	max-height:700px;
	margin:0 auto;
	
}

.head{
	width:100%;
	height:10%;
}

.main{
	width:100%;
	height:300px;
	position:relative;
}

.footer{
	width:100%;
	height:10%;
}
</style>
