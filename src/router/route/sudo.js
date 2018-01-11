import Sudo from '@/components/sudo/Sudo'
import Head from '@/components/sudo/child/Head'
import Main from '@/components/sudo/child/Main'
import Footer from '@/components/sudo/child/Footer'

export default {
	path:'/',
	name:'sudo',
	component:Sudo,
	children:[{
		path:'/',
		components:{
			head:Head,
			main:Main,
			footer:Footer,
		}
	}],	
}
