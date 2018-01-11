<template>
  <div class="main">
    	<Row class="numRow" type="flex" v-for="i in 9" justify="space-around">    		
    		<i-col class="numCol" span="2" v-for="j in 9" :data-x='i-1' :data-y='j-1'><span>0</span></i-col>   		
    	</Row>
  </div>
</template>

<script>

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.numRow{
	width:100%;
	height:50px;
}

.numCol{
	width:50px;
	height:50px;
	font-size:0.1rem;
	line-height:50px;
}


</style>
