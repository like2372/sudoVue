<template>
  <div class="sudo">
  	 <router-view name='head'/>
  	 <router-view name='main' :arr="arr" @replaceSudo="replaceSudo"/>
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
  		  		this.initSudo();
  },methods:{	
  		replaceSudo(){
  			this.initSudo();
  		},
  		initSudo(){
  			this.arr = sudoUtils.getSudoArray();
				sudoUtils.getHiddenSudoArray(this.arr, 55);
  		}
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
	position:relative;
	margin-top:20px;
}

.footer{
	width:100%;
	height:10%;
}
</style>
