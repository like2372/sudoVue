<template>
  <div class="sudo">
  	 <router-view name='head'/>
  	 <router-view name='main'/>
     <router-view name='footer'/>
  </div>
</template>

<script>
	
	import sudoUtils from '@/utils/sudoUtil'
	
export default {
  name: 'Sudo',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },created:function(){
  		let arr =sudoUtils.getSudoArray();
  		sudoUtils.getHiddenSudoArray(arr,1);
  		console.log(arr);
  		console.log(sudoUtils.checkSudoArray(arr));
  		
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.sudo{
	width:500px;
	height:800px;
	margin:0 auto;
}

.head{
	width:100%;
	height:10%;
}

.main{
	width:100%;
	height:500px;
	
}

.footer{
	width:100%;
	height:10%;
}
</style>
