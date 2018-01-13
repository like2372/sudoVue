import Sudo from '@/components/sudo/Sudo'
import Head from '@/components/sudo/child/Head'
import Main from '@/components/sudo/child/Main'
export default {
	path:'/',
	name:'sudo',
	component:Sudo,
	children:[{
		path:'/',
		components:{
			head:Head,
			main:Main,
		}
	}],	
}
